<template>
	<!-- <view class="main-view">
		<view class="top-wrap" >
			<cus-tab id="category" 
			:tab-data="tabBars" 
			:tab-cur="categoryCur"
			:tabHeight="80"
			:beyondScroll="true"
			@change="toggleCategory"
			></cus-tab>
		</view>
		<view class="normal-color body-content">
			
		</view>
	</view>
 -->
	<view class="tabs">
		<scroll-view class="scroll-h" :scroll-x="true" :show-scrollbar="false" :scroll-into-view="tabbarId">
			<view class="uni-tab-item" v-for="(tab,index) in tabBars" :key="tab.id" :id="tab.id" :data-current="index" @click="ontabtap">
				<text class="uni-tab-item-title" :class="tabIndex==index ? 'uni-tab-item-title-active' : ''">{{tab.name}}</text>
			</view>
		</scroll-view>
		
		
		
		
		<view class="line-h"></view>
		<swiper :current="tabIndex" class="swiper-box" :style="[{height:(contentHeight) + 'px'}]" :duration="300" @change="ontabchange">
			<swiper-item class="swiper-item" v-for="(tab, index1) in orderList" :key="index1">
				<scroll-view class="scroll-v" :scroll-y="true" enableBackToTop="true" @scrolltolower="loadMore(index1)"
				 @scrolltoupper="refreshTo(index1)">
					<order-list-item v-for="(newItem, index2) in tab.data" :options="newItem" :key="newItem.id" @click="clickDetail(newItem)" @add="add" @subtract="subtract"></order-list-item>
				</scroll-view>
			</swiper-item>
		</swiper>
	</view>
</template>

<script>
	import cusTab from "@/components/cus-tab/cus-tab.vue";

	import orderListItem from './order-list-item.vue';

	export default {
		components: {
			orderListItem,
			cusTab
		},
		data() {
			return {
				categoryCur: 0,
				tapTop: 100,
				tabbarId: "",
				ScreenHeight: this.ScreenHeight,
				contentHeight: 1000,
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar,
				tabBars: [{
					id: "tab01",
					name: '最新',
					newsid: 0
				}, {
					id: "tab02",
					name: '大公司',
					newsid: 23
				},{
					id: "tab02",
					name: '大公司',
					newsid: 23
				},{
					id: "tab02",
					name: '大公司123123213',
					newsid: 23
				},{
					id: "tab02",
					name: '大公司',
					newsid: 23
				},{
					id: "tab02",
					name: '大公司',
					newsid: 23
				}, ],
				tabIndex: 0,
				orderList: [{
						data: [{
								id: 0,
								foodName: "三明治",
								goodsNum: 100,
								price: 180.0,
								cal: 18.0,
								picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
							},
							{
								id: 2,
								foodName: "三明治",
								goodsNum: 100,
								price: 180.0,
								cal: 18.0,
								picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
							},
							{
								id: 3,
								foodName: "三明治",
								goodsNum: 100,
								price: 180.0,
								cal: 18.0,
								picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
							},
							{
								id: 4,
								foodName: "三明治",
								goodsNum: 100,
								price: 180.0,
								cal: 18.0,
								picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
							},


						]
					},
					{
						data: [{
								id: 10,
								foodName: "三明治",
								goodsNum: 100,
								price: 180.0,
								cal: 18.0,
								picUrl: "http://www.coretheorylife.com/ctfbe/pic/getPic/1",
							},

						]
					},
				]
			}

		},
		onLoad() {
			this.tapTop = this.StatusBar + this.CustomBar + 50;
			uni.getSystemInfo({
				success: (e) => {
					console.log(e);
				}
			})
			console.log(this.tapTop);
			this.contentHeight = this.ScreenHeight - this.StatusBar - this.CustomBar - 40;

		},
		methods: {
			ontabtap(e) {
				// 点击切换
				let index = e.target.dataset.current || e.currentTarget.dataset.current;
				this.switchTab(index);
			},
			ontabchange(e) {
				// 手动切换
				let index = e.target.current || e.detail.current;
				this.switchTab(index);
			},
			switchTab(index) { //切换页面
				if (this.tabIndex === index) {
					return;
				}

				this.tabIndex = index;
				this.tabbarId = this.tabBars[index].id;
			},
			loadMore(index) {
				console.log("加载更多");
			},
			refreshTo(index1) {
				console.log("下拉刷新");
			},
			clickDetail(item) {
				console.log(item);
			},
			add() {
				console.log("++++++");
			},
			subtract() {
				console.log("-------");
			},
			toggleCategory(e){
				console.log(e.index);
			}

		}
	}
</script>

<style lang="scss">
	page {
		width: 100%;
		height: 100%;
		display: flex;
	}
	
	.main-view{
		flex: 1;
		.top-wrap {
			position: position;
			left: 0;
			top: 0;
			right: 0;
			width: 100%;
			background-color: #FFFFFF;
			// z-index: 99;
		}
		.body-content{
			width: 100%;
			height: 100%;
		}
	}
	




	.tabs {
		margin-top: 2rpx;
		flex: 1;
		background-color: #FFFFFF;
		flex-direction: column;
		overflow: hidden;
		background-color: #ffffff;
		/* #ifdef MP-ALIPAY || MP-BAIDU */
		height: 100vh;

		/* #endif */
		.scroll-h {
			width: 750upx;
			height: 80upx;
			flex-direction: row;
			/* #ifndef APP-PLUS */
			// 规定段落中的文本不进行换行：
			white-space: nowrap;

			/* #endif */
			.uni-tab-item {
				/* #ifndef APP-PLUS */
				display: inline-block;
				/* #endif */
				flex-wrap: nowrap;
				padding-left: 34upx;
				padding-right: 34upx;


				.uni-tab-item-title {
					color: #555;
					font-size: 30upx;
					height: 80upx;
					line-height: 80upx;
					flex-wrap: nowrap;
					/* #ifndef APP-PLUS */
					white-space: nowrap;
					/* #endif */
				}

				.uni-tab-item-title-active {
					color: #FD300D;
					position: relative;

				}

				.uni-tab-item-title-active::before {
					content: "";
					display: block;
					position: absolute;
					bottom: -10rpx;
					text-align: center;
					width: 100%;
					height: 2rpx;
					background-color: #FD300D;
				}

			}
		}

		.line-h {
			height: 1upx;
			background-color: #cccccc;
		}

		.swiper-box {
			height: 100%;

			.swiper-item {
				width: 750rpx;

				.scroll-v {
					height: 100%;
					/* #ifndef MP-ALIPAY */
					flex-direction: column;
					/* #endif */
				}
			}
		}
	}
</style>
