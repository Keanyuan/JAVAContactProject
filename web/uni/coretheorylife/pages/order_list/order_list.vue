<template>
	<view class="main-view">
		<view class="top-wrap">
			<cus-tab id="category" :tab-data="tabBars" :tab-cur="categoryCur" :tabHeight="90" :beyondScroll="true" @change="toggleCategory"></cus-tab>
		</view>
		<swiper :current="categoryCur" :duration="duration" @animationfinish="swipeChange">
			<swiper-item v-for="(item,index) in categoryData" :key="index">
				<cus-scroll :requesting="item.requesting" :end="item.end" :empty-show="item.emptyShow" :list-count="item.listCount"
				 :has-top="true" :refresh-size="90" @refresh="refresh" @more="more">
					<view class="cells">
						<order-list-item v-for="(newItem, index2) in item.listData" :options="newItem" :key="index2" @click="clickDetail(newItem)"
						 @add="add" @subtract="subtract"></order-list-item>
					</view>
				</cus-scroll>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	import cusTab from "@/components/cus-tab/cus-tab.vue";
	import cusScroll from "@/components/cus-tab/cus-scroll.vue";

	import orderListItem from './order-list-item.vue';

	let pageStart = 0;
	let pageSize = 15;
	let testData = [{
			id: 0,
			foodName: "三明治",
			goodsNum: 100,
			price: 180.0,
			cal: 18.0,
			picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
		},
		{
			id: 2,
			foodName: "三明治2",
			goodsNum: 100,
			price: 180.0,
			cal: 18.0,
			picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
		},
		{
			id: 3,
			foodName: "三明治3",
			goodsNum: 100,
			price: 180.0,
			cal: 18.0,
			picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
		},
		{
			id: 4,
			foodName: "三明治4",
			goodsNum: 100,
			price: 180.0,
			cal: 18.0,
			picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
		},
	];



	export default {
		components: {
			orderListItem,
			cusTab,
			cusScroll
		},
		data() {
			return {
				categoryCur: 0,
				duration: 300, // swiper-item 切换过渡时间
				tabbarId: "",
				ScreenHeight: this.ScreenHeight,
				contentHeight: 0,
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar,
				tabBars: [{
					id: "tab01",
					name: '推荐',
					newsid: 0
				}, {
					id: "tab01",
					name: '最新体验',
					newsid: 1
				},  ],
				categoryData: []
			}

		},
		onLoad() {
			this.contentHeight = this.ScreenHeight - this.StatusBar - this.CustomBar - 40;
			
			for(var i = 0; i <  this.tabBars.length; i++){
				this.categoryData.push({
						name: this.tabBars[i].name,
						requesting: false,
						end: false,
						emptyShow: false,
						page: pageStart,
						listCount: 0,
						listData: []
					})
			}

			this.getList('refresh', pageStart);
		},

		methods: {
			clickDetail(item) {
				console.log(item);
			},
			add() {
				console.log("++++++");
			},
			subtract() {
				console.log("-------");
			},
			getList(type, currentPage) {
				// 获取当前列表数据
				let pageData = this.getCurrentData();
				pageData.requesting = true;
				// 先保存数据 
				this.setCurrentData(pageData);
				uni.showNavigationBarLoading();
				setTimeout(() => {
					pageData.requesting = false;
					pageData.end = false;
					uni.hideNavigationBarLoading();

					// 如果是下拉刷新
					if (type === 'refresh') {
						// 将清除数据 把mork数据赋给列表
						//当前页码+1
						pageData.listData = testData;
						pageData.page = currentPage + 1;
						pageData.emptyShow = pageData.listData.length == 0;
						
					} else {
						//在当前数据基础上追加数据 页码加1
						pageData.listData = pageData.listData.concat(testData);
						pageData.page = currentPage + 1;
						pageData.emptyShow = pageData.listData.length == 0;
						
					}
					// 如果当前页码大于3 没有更多数据
					if (pageData.page > 3) {
						pageData.end = true; //没有更多数据
					}
					// 保存当前列表条数 
					pageData.listCount = pageData.listData.length;
					// 将数据保存
					this.setCurrentData(pageData)
				}, 1000)
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
			}

		}
	}
</script>

<style scoped>
	page {
		width: 100%;
		height: 100%;
	}
	.main-view{
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
		margin-bottom: 30rpx;
	
	}
</style>
