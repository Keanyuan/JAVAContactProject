<template>
	<view>
		<view class="main-view" v-if="tabBars.length > 0">
			<view class="top-wrap">
				<cus-tab id="category" :tab-data="tabBars" :tab-cur="categoryCur" :tabHeight="90" :beyondScroll="true" @change="toggleCategory"></cus-tab>
			</view>
			<swiper :current="categoryCur" :duration="duration" @animationfinish="swipeChange">
				<swiper-item v-for="(item,index) in categoryData" :key="index">
					<cus-scroll :requesting="item.requesting" :end="item.end" :empty-show="item.emptyShow" :list-count="item.listCount"
					 :has-top="true" :refresh-size="90" @refresh="refresh" @more="more">
						<view class="cells">
							<order-list-item v-for="(newItem, index2) in item.listData" :options="newItem" :key="index2" @click="clickDetail(newItem)"
							 @add="add(index2)" @subtract="subtract(index2)"></order-list-item>
						</view>
					</cus-scroll>
				</swiper-item>
			</swiper>
		</view>
		<view class="bottom-button">
			<my-button title="完成" @click="wancheng"></my-button>
		</view>
	</view>
</template>

<script>
	import cusTab from "@/components/cus-tab/cus-tab.vue";
	import cusScroll from "@/components/cus-tab/cus-scroll.vue";
	import myButton from '@/components/my-button.vue'

	import orderListItem from '@/pages/order_list/order-list-item.vue';

	let pageStart = 0;
	let pageSize = 15;

	export default {
		components: {
			orderListItem,
			cusTab,
			cusScroll,
			myButton
		},
		data() {
			return {
				categoryCur: 0,
				duration: 300, // swiper-item 切换过渡时间
				ScreenHeight: this.ScreenHeight,
				ScreenWidth: this.ScreenWidth,
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar,
				requestList: [],
				tabBars: [],
				categoryData: [],
				storeModel: '',
			}

		},
		onLoad(options) {

			if (options && options.item) {
				let item = this.$util.aesDecrypt(options.item);
				// this.storeModelStr = options.item;
				// this.storeModel = JSON.parse(decodeURIComponent(item));
				// this.storeModel = JSON.parse(item);
				this.storeModel = JSON.parse(item);
				console.log(this.storeModel);
				this.getData(options.shopId);
			}
		},

		methods: {
			clickDetail(item) {
				console.log(item);
			},
			add(index) {
				console.log(this.categoryCur);
				// if(!this.categoryData[this.categoryCur].listData[index].goodsNum){
				// 	this.categoryData[this.categoryCur].listData[index].goodsNum = 0
				// }
				// this.categoryData[this.categoryCur].listData[index].goodsNum += 1;
				// console.log(this.categoryData[this.categoryCur].listData[index]);
				// console.log(this.categoryData[this.categoryCur].listData[index].goodsNum);				

				let pageData = this.getCurrentData();
				// let list = JSON.parse(JSON.stringify(pageData));
				pageData.listData[index].goodsNum += 1;
				this.setCurrentData(pageData);

				console.log(this.categoryData);
				console.log("++++++");
			},
			subtract(index) {
				let pageData = this.getCurrentData();
				if (pageData.listData[index].goodsNum > 0) {
					pageData.listData[index].goodsNum -= 1;
				}
				this.setCurrentData(pageData);
				console.log("-------");
			},
			getData(shopId) {
				this.$api.queryFoodTree({
					shopId: shopId,
					success: (res) => {
						console.log(res.data);
						this.requestList = res.data;
						this.tabBars = [];
						this.categoryData = [];
						let health_locals = this.$util.getStorageSync(this.$code.health_locals);
						var localCanItem;
						if (health_locals && health_locals.length > 0) {
							for (let ind in health_locals) {
								if (health_locals[ind].date == this.storeModel.date) {
									var canItems = health_locals[ind].canItems;
									for (let i in canItems) {
										if (canItems[i].canId == this.storeModel.canId) {
											//  如果日期存在且餐存在 保存数据
											localCanItem = health_locals[ind].canItems[i];
											break;
										}
									}
									break;
								}
							}
						}


						if (this.requestList.length > 0) {
							for (let index in this.requestList) {

								for (let i in this.requestList[index].foodList) {

									var isLocalTrue = false;
									var foodItem;
									//对本地列表遍历 与每个item匹配
									if (localCanItem && localCanItem.foodItem.length > 0) {
										for (let var1 in localCanItem.foodItem) {
											if (localCanItem.foodItem[var1].id == this.requestList[index].foodList[i].id) {
												foodItem = localCanItem.foodItem[var1];
												isLocalTrue = true;
												break;
											}
										}
									}

									//数据处理
									this.requestList[index].foodList[i].goodsNum = isLocalTrue ? foodItem.goodsNum : 0;
									this.requestList[index].foodList[i].date = this.storeModel.date;
									this.requestList[index].foodList[i].canId = this.storeModel.canId;
									this.requestList[index].foodList[i].isSelect = false;
									this.requestList[index].foodList[i].calTotal = 0;
								}


								this.requestList[index].foodList = JSON.parse(JSON.stringify(this.requestList[index].foodList));

								this.tabBars.push({
									id: index,
									name: this.requestList[index].foodClassName
								});
								this.categoryData.push({
									name: this.requestList[index].foodClassName,
									requesting: false,
									end: false,
									emptyShow: false,
									page: pageStart,
									listCount: 0,
									listData: [],
								})
							}
							this.getList('refresh', pageStart);
						} else {

						}
					},
					fail: (res) => {

					}
				});
			},
			getList(type, currentPage) {
				// 获取当前列表数据
				let pageData = this.getCurrentData();
				pageData.requesting = true;
				// 先保存数据 
				this.setCurrentData(pageData);
				// uni.showNavigationBarLoading();
				setTimeout(() => {
					pageData.requesting = false;
					pageData.end = false;
					// uni.hideNavigationBarLoading();

					// 如果是下拉刷新
					if (type === 'refresh') {
						// 将清除数据 把mork数据赋给列表
						//当前页码+1
						pageData.listData = this.requestList[this.categoryCur].foodList;
						pageData.page = currentPage + 1;
						pageData.emptyShow = pageData.listData.length == 0;

					} else {
						//在当前数据基础上追加数据 页码加1
						// pageData.listData = pageData.listData.concat(testData);
						// pageData.page = currentPage + 1;
						// pageData.emptyShow = pageData.listData.length == 0;

					}
					// 如果当前页码大于3 没有更多数据
					// if (pageData.page > 3) {
					// 	pageData.end = true; //没有更多数据
					// }

					pageData.end = true;
					// 保存当前列表条数 
					pageData.listCount = pageData.listData.length;
					// 将数据保存
					this.setCurrentData(pageData)
				}, 800)
			},
			// 获取当前激活页面的数据
			getCurrentData() {
				// 当前id数据列表数据
				return this.categoryData[this.categoryCur];
			},
			// 更新页面数据
			setCurrentData(pageData) {
				// 将获取到的数据塞进去
				let categoryData = this.categoryData;
				categoryData[this.categoryCur] = pageData;
				this.categoryData = categoryData;
			},

			// 顶部tab切换事件
			toggleCategory(e) {
				this.duration = 0;
				setTimeout(() => {
					this.categoryCur = e.index;
				}, 0);
			},
			// 页面滑动切换事件
			swipeChange(e) {
				this.duration = 300;
				setTimeout(() => {
					this.categoryCur = e.detail.current;
					this.$forceUpdate();
					this.loadData();
				}, 0);
			},
			// 判断是否为加载新的页面,如果是去加载数据
			loadData() {
				let pageData = this.getCurrentData();
				if (pageData.listData.length == 0) {
					this.getList('refresh', pageStart);
				}
			},
			// 刷新数据
			refresh() {
				this.getList('refresh', pageStart);
			},
			// 加载更多
			more() {
				this.getList('more', this.getCurrentData().page);
			},
			wancheng() {

				var foodItem = [];
				var calTotals = 0.0;
				var prices = 0.0;
				var goodsNum = 0;
				if (this.categoryData.length > 0) {
					for (let index in this.categoryData) {
						var tabItem = this.categoryData[index].listData;
						if (tabItem.length > 0) {
							for (let i in tabItem) {
								if (tabItem[i].goodsNum > 0) {
									goodsNum += tabItem[i].goodsNum;
									if (tabItem[i].cal > 0) {
										tabItem[i].calTotal = tabItem[i].goodsNum * tabItem[i].cal;
										calTotals += tabItem[i].calTotal;
									}

									if (tabItem[i].price > 0) {
										prices += tabItem[i].price * tabItem[i].goodsNum;
									}

									//保存每一个商品至foodItem
									foodItem.push(tabItem[i]);
								}
							}
						}
					}
				}
				let health_locals = this.$util.getStorageSync(this.$code.health_locals);
				console.log(health_locals);
				if (foodItem.length > 0) {
					var canItem = {
						canName: this.storeModel.canName,
						canId: this.storeModel.canId,
						date: this.storeModel.date,
						shopName: this.storeModel.storeInfoModel.shopName,
						subOrderSeq: '',
						goodsNum: goodsNum,
						isSelect: false,
						foodItem: foodItem,
						calTotal: calTotals,
						price: prices,
					}

					console.log(canItem);

					if (health_locals && health_locals.length > 0) {
						//日期是否存在
						var isDateTrue = false;
						// 餐是否存在
						var isCanTrue = false;
						for (let ind in health_locals) {
							if (health_locals[ind].date == canItem.date) {
								isDateTrue = true;
								//判断 数据中是否存在当前日期的数据
								var canItems = health_locals[ind].canItems;
								var calTotal = 0;
								var goodsNum = 0;
								var prices = 0;

								for (let i in canItems) {
									console.log(canItems[i].canId);
									console.log(canItem.canId);
									console.log(canItems[i].canId == canItem.canId);
									if (canItems[i].canId == canItem.canId) {
										isCanTrue = true;
										// ① todo 如果日期存在且餐存在 更新数据
										console.log(1);
										if (isDateTrue && isCanTrue) {
											health_locals[ind].canItems[i] = canItem;
										}
									}
									calTotal += health_locals[ind].canItems[i].calTotal;
									goodsNum += health_locals[ind].canItems[i].goodsNum;
									prices += health_locals[ind].canItems[i].price;
								}
								// ② todo如果日期存在 餐不存在 添加到日期
								if (isDateTrue && !isCanTrue) {
									console.log(2);

									health_locals[ind].canItems.push(canItem);

									calTotal += canItem.calTotal;
									goodsNum += canItem.goodsNum;
									prices += canItem.price;
								}
								health_locals[ind].calTotal = calTotal;
								health_locals[ind].goodsNum = goodsNum;
								health_locals[ind].priceTotal = prices;
								break;
							}
						}
						// ③ 如果日期不存在且餐存在 添加到日期
						if (!isDateTrue && !isCanTrue) {
							console.log(3);

							var dateItem = {
								date: this.storeModel.date,
								calTotal: 0,
								priceTotal: 0,
								goodsNum: 0,
								isSelect: false,
								canItems: []
							}
							dateItem.calTotal = canItem.calTotal;
							dateItem.priceTotal = canItem.price;
							dateItem.goodsNum = canItem.goodsNum;
							dateItem.canItems.push(canItem);
							health_locals.push(dateItem);
						}

					} else {
						//④ 如果日期不存在且整个数据不存在
						console.log(4);

						health_locals = [];
						var dateItem = {
							date: this.storeModel.date,
							calTotal: 0,
							priceTotal: 0,
							goodsNum: 0,
							isSelect: false,
							canItems: []
						}
						dateItem.calTotal = canItem.calTotal;
						dateItem.priceTotal = canItem.price;
						dateItem.goodsNum = canItem.goodsNum;
						dateItem.canItems.push(canItem);
						health_locals.push(dateItem);

					}

					console.log(health_locals);

					// this.$util.getStorageSync(this.$code.health_locals);

					this.$util.setStorage(this.$code.health_locals, health_locals, () => {
						uni.navigateBack({
							delta: 2
						})
					})

				} else {
					this.$util.showToast("请点餐");
				}

			}
		}
	}
</script>

<style scoped>
	page {
		width: 100%;
		height: 100%;
		position: relative;
	}

	.main-view {
		width: 100%;
		height: 100%;
	}

	.top-wrap {
		position: fixed;
		left: 0;
		/* #ifdef H5 */
		top: 89;
		/* #endif */
		/* #ifndef H5 */
		top: 0;
		/* #endif */
		right: 0;
		width: 100%;
		background-color: #FFFFFF;
		z-index: 99;
	}

	swiper {
		width: 100%;
		height: 100vh;

		/* background-color: #007AFF; */

	}

	.cells {
		background: #ffffff;
		width: 100%;
		height: 100%;
		margin-bottom: 180rpx;

	}

	.bottom-button {
		position: fixed;
		left: 20rpx;
		right: 20rpx;
		bottom: 40rpx;
		display: flex;
		justify-content: space-around;
		flex-direction: row;
	}
</style>
