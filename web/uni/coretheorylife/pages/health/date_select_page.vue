<template>
	<view class="main">
		<view v-if="showCalendar">
			<!-- 插入模式 -->
			<uni-calendar :selected="dateInfo.selected" @change="calenderChange" :startDate="dateInfo.startDate" :endDate="dateInfo.endDate"
			 :lunar="dateInfo.lunar" />
		</view>
		<view class="check-food">
			<data-select-item :selected="selectedCan1.selected" :title="selectedCan1.cal" @normalClick="normalClick(1)"
			 @selectClick="selectClick(1)" @cancleClick="cancleClick(1)"></data-select-item>
			<data-select-item :selected="selectedCan2.selected" :title="selectedCan2.cal" @normalClick="normalClick(2)"
			 @selectClick="selectClick(2)" @cancleClick="cancleClick(2)"></data-select-item>
			<data-select-item :selected="selectedCan3.selected" :title="selectedCan3.cal" @normalClick="normalClick(3)"
			 @selectClick="selectClick(3)" @cancleClick="cancleClick(3)"></data-select-item>
		</view>
		<cart-item :goodsNum="goodsNums" :fee="fee" :price="prices" @cartClick="cartClick" @detailCick="detailCick"></cart-item>
	</view>
</template>

<script>
	import uniCalendar from '@/components/uni-calendar/uni-calendar.vue'

	import dataSelectItem from '@/pages/health/items/data-select-item.vue'

	import cartItem from '@/components/cart-item.vue'

	export default {
		components: {
			uniCalendar,
			dataSelectItem,
			cartItem
		},
		data() {
			return {
				showCalendar: false,
				selectDate: '',
				storeInfoModelStr: '',
				storeInfoModel: '',
				healthDateList: [],
				goodsNums: 0,
				fee: 0,
				prices: 0,
				cals: 0,
				selectedCan1: {
					selected: false,
					cal: "0"
				},
				selectedCan2: {
					selected: false,
					cal: "0"
				},
				selectedCan3: {
					selected: false,
					cal: "0"
				},
				dateInfo: {
					date: '',
					startDate: '',
					endDate: '',
					lunar: true,
					range: true,
					insert: false,
					selected: [],
				}
			}
		},
		onLoad(options) {
			if (options && options.item) {
				let item = this.$util.aesDecrypt(options.item);
				console.log(item);
				this.storeInfoModelStr = options.item;
				// this.storeInfoModel = JSON.parse(decodeURIComponent(item));
				this.storeInfoModel = JSON.parse(item);
				console.log(this.storeInfoModel);
				this.initDateTime()
			}
		},
		onShow() {
			if (this.storeInfoModelStr.length > 0) {
				this.checkLocalInfo()
			}

		},
		methods: {
			// 校验本地数据并保存
			checkLocalInfo() {
				var dateList = [];
				let health_locals = this.$util.getStorageSync(this.$code.health_locals);
				let nowDate = this.$util.getCurrentDate()
				var startTimeStamp = new Date(nowDate).getTime();
				for (let i in health_locals) {
					var currentTimeStamp = new Date(health_locals[i].date).getTime();
					if (currentTimeStamp < startTimeStamp) {
						health_locals.splice(i, 1);
					}
				}
				uni.setStorageSync(this.$code.health_locals, health_locals);
				health_locals = this.$util.getStorageSync(this.$code.health_locals);
				console.log(health_locals);
				var prices = 0;
				var cals = 0;
				var goodsNum = 0;

				this.dateInfo.selected.splice(0, this.dateInfo.selected.length);
				console.log(this.dateInfo.selected);

				if (health_locals && health_locals.length > 0) {
					for (let var1 in health_locals) {
						var localDateItem = health_locals[var1];
						this.dateInfo.selected.push({
							date: localDateItem.date,
						})
						if (localDateItem.canItems.length > 0) {
							var dayItem = {};
							dayItem.date = localDateItem.date;
							dayItem.calTotal = localDateItem.calTotal;
							dayItem.goodsNum = localDateItem.goodsNum;
							dayItem.priceTotal = localDateItem.priceTotal;
							prices += localDateItem.priceTotal;
							cals += localDateItem.calTotal;
							goodsNum += localDateItem.goodsNum;
							let canItems = localDateItem.canItems;
							for (let var2 in canItems) {
								if (canItems[var2].canId == 1) {
									dayItem.canId1 = {};
									dayItem.canId1.calTotal = canItems[var2].calTotal ? canItems[var2].calTotal : 0;
									dayItem.canId1.canId = canItems[var2].canId;
									dayItem.canId1.price = canItems[var2].price;
									dayItem.canId1.goodsNum = canItems[var2].goodsNum ? canItems[var2].goodsNum : 0;
								} else if (canItems[var2].canId == 2) {
									dayItem.canId2 = {};
									dayItem.canId2.calTotal = canItems[var2].calTotal ? canItems[var2].calTotal : 0;
									dayItem.canId2.canId = canItems[var2].canId;
									dayItem.canId2.price = canItems[var2].price;
									dayItem.canId2.goodsNum = canItems[var2].goodsNum ? canItems[var2].goodsNum : 0;
								} else if (canItems[var2].canId == 3) {
									dayItem.canId3 = {};
									dayItem.canId3.calTotal = canItems[var2].calTotal
									dayItem.canId3.canId = canItems[var2].canId;
									dayItem.canId3.price = canItems[var2].price;
									dayItem.canId3.goodsNum = canItems[var2].goodsNum ? canItems[var2].goodsNum : 0;
								}
							}
							dateList.push(dayItem);
						}
					}
				}
				this.cals = this.$util.numToFixed(cals);
				this.goodsNums = goodsNum;
				this.prices = this.$util.numToFixed(prices);
				this.healthDateList = dateList;
				this.dealCalInfo()
			},
			// 处理日期及显示
			dealCalInfo() {
				var checked = false;
				if (this.healthDateList.length > 0) {
					for (let var1 in this.healthDateList) {
						if (this.healthDateList[var1].date == this.selectDate) {
							let dataItem = this.healthDateList[var1];
							if (dataItem.canId1) {
								this.selectedCan1.selected = true;
								this.selectedCan1.cal = dataItem.calTotal + '';

							} else {
								this.selectedCan1.selected = false;
								this.selectedCan1.cal = "0";
							}

							console.log(this.selectedCan1);
							if (dataItem.canId2) {
								this.selectedCan2.selected = true;
								this.selectedCan2.cal = dataItem.calTotal + '';

							} else {
								this.selectedCan2.selected = false;
								this.selectedCan2.cal = "0";
							}

							if (dataItem.canId3) {
								this.selectedCan3.selected = true;
								this.selectedCan3.cal = dataItem.calTotal + '';

							} else {
								this.selectedCan3.selected = false;
								this.selectedCan3.cal = "0";
							}
							checked = true;
							break;
						}
					}
				}
				if (!checked) {
					this.selectedCan1.selected = false;
					this.selectedCan1.cal = "0";
					this.selectedCan2.selected = false;
					this.selectedCan2.cal = "0";
					this.selectedCan3.selected = false;
					this.selectedCan3.cal = "0";
				}


			},
			calenderChange(e) {
				this.selectDate = e.fulldate;

				this.dealCalInfo();
				console.log(this.dateInfo);
			},
			initDateTime() {
				let nowDate = this.$util.getCurrentDate()
				let endDate = this.$util.getAfterWeekDate()
				this.selectDate = nowDate;

				this.$nextTick(() => {
					this.dateInfo.date = nowDate;
					this.dateInfo.startDate = nowDate;
					this.dateInfo.endDate = endDate;
					this.showCalendar = true
				})
			},
			// 默认点击
			normalClick(canId) {
				console.log("未选择" + canId + ' ' + this.selectDate);
				this.checkCanInfo(canId)
			},
			// 已选择点击
			selectClick(canId) {
				console.log("已选择" + canId + ' ' + this.selectDate);
				this.checkCanInfo(canId)
			},
			// 取消点击
			cancleClick(canId) {
				console.log("取消" + canId + ' ' + this.selectDate);
				var health_locals = this.$util.getStorageSync(this.$code.health_locals);
				if (health_locals && health_locals.length > 0) {
					for (let var1 in health_locals) {
						if (health_locals[var1].date == this.selectDate) {
							for (let var2 in health_locals[var1].canItems) {
								if (health_locals[var1].canItems[var2].canId == canId) {
									health_locals[var1].calTotal -= health_locals[var1].canItems[var2].calTotal;
									health_locals[var1].priceTotal -= health_locals[var1].canItems[var2].price;
									health_locals[var1].goodsNum -= health_locals[var1].canItems[var2].goodsNum;
									health_locals[var1].canItems.splice(var2, 1);
									if (health_locals[var1].canItems.length == 0) {
										health_locals.splice(var1, 1);
									}
									break;
								}
							}
						}
					}
				}
				uni.setStorageSync(this.$code.health_locals, health_locals);
				this.checkLocalInfo()
			},
			setAllSelect(isSelect) {
				// var allSelectCount = 0;
				let health_locals = this.$util.getStorageSync(this.$code.health_locals);
				
				// // 遍历所有数据
				for (let var1 in health_locals) {
					// 日期Item
					let dateItem = health_locals[var1];
					// 修改日期选择 dateItem.isSelect
					var dateSelectCount = 0;
					for (let var2 in dateItem.canItems) {
						// 餐Item 每一餐
						let canItem = dateItem.canItems[var2];
						// 修改餐选择 canItem.isSelect
						var canItemCount = 0;
						for (let var3 in canItem.foodItem) {
							// 修改食物选择 canItem.foodItem[var3].isSelect
							canItem.foodItem[var3].isSelect = isSelect;
							if (canItem.foodItem[var3].isSelect) {
								canItemCount++;
							}
						}
						if (canItemCount == canItem.foodItem.length && canItemCount != 0) {
							canItem.isSelect = true;
						} else {
							canItem.isSelect = false;
						}
						if (canItem.isSelect) {
							dateSelectCount++;
						}
					}
					if (dateSelectCount == dateItem.canItems.length && dateSelectCount != 0) {
						dateItem.isSelect = true;
					} else {
						dateItem.isSelect = false;
					}
				}
				uni.setStorageSync(this.$code.health_locals, health_locals);
			},
			//购物车跳转
			cartClick() {
				this.setAllSelect(false);
				uni.navigateTo({
					url: './health_cart?item=' + this.storeInfoModelStr
				})
			},
			// 下单跳转
			detailCick() {
				this.setAllSelect(true);
				uni.navigateTo({
					url: './health_makesure?item=' + this.storeInfoModelStr
				})
			},
			//选择canId处理
			checkCanInfo(canId) {
				console.log(this.selectDate);

				var item = {}
				switch (canId) {
					case 1:
						item.canName = "早餐";
						break;
					case 2:
						item.canName = "午餐";
						break;
					case 3:
						item.canName = "晚餐";
						break;
					default:
						break;
				}
				item.canId = canId;
				item.date = this.selectDate;
				item.storeInfoModel = this.storeInfoModel;

				var item = JSON.stringify(item);
				item = this.$util.aesEncrypt(item);
				uni.navigateTo({
					url: './health_shop?item=' + item
				})
			}
		}
	}
</script>

<style lang="scss" scoped>
	.check-food {
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.main {
		height: 100vh;
		width: 100%;


	}
</style>
