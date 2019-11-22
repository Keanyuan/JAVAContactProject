<template>
	<cus-scroll id='scroll' :requesting="requesting" :end="end" :empty-show="emptyShow" :listCount="listCount" @refresh="refresh"
	 @more="more">
		<view>
			<order-item v-for="(item,index) in itemsInfo" :key="index" @clickDetail="listClick(index)" :options="item"></order-item>
		</view>
	</cus-scroll>
</template>

<script>
	import orderItem from '@/pages/order/order-item.vue';
	import cusScroll from "@/components/cus-tab/cus-scroll.vue";
	import locationUtil from "@/tools/localutil.js";

	let pageStart = 0;

	export default {
		components: {
			orderItem,
			cusScroll
		},
		data() {
			return {
				requesting: false,
				end: true,
				emptyShow: false,
				page: pageStart,
				itemsInfo: [],
				listCount: 0,
				location: {
					longitude: 0.0,
					latitude: 0.0
				},
				locaSuccess: false,
				orderItem: ''
				
			}
		},
		onLoad(options) {
			if (options && options.item) {
				this.orderItem = options.item;				
			}
		},
		onShow() {			
			if(this.listCount == 0 && !this.locaSuccess){
				// #ifdef H5
				this.locaSuccess = true;
				this.getList('refresh', pageStart);
				// #endif
				// #ifndef H5
				this.getLocation();
				// #endif
			}
		},
		methods: {
			getList(type, currentPage) {
				this.requesting = true;
				// uni.showNavigationBarLoading();
				if (type === 'refresh') {
					this.$api.queryShopListByName({
						shopName: "",
						latitude: this.location.latitude,
						longitude: this.location.longitude,
						success: (res) => {
							this.requesting = false;
							this.itemsInfo = res.data;
							this.listCount = this.itemsInfo.length;
						},
						fail: (res) => {
							this.requesting = false;
							this.listCount = this.itemsInfo.length;
							// 没有数据的时候这样设置
							if (this.itemsInfo.length == 0) {
								this.emptyShow = true;
							}
						}
					});
				} else { // loadmore

				}
			},
			refresh() {
				this.empty = false;
				this.getList('refresh', pageStart);
			},
			more() {
				this.getList('more', this.page);
			},
			listClick(index) {
				console.log(index);
				uni.navigateTo({
					url: "./health_order_list?shopId="+this.itemsInfo[index].id +"&&item="+this.orderItem
				})
			},
			async getLocation() {
				let res = await locationUtil.getLocation()
				if(res.status === 1){
					this.locaSuccess = true;
					this.location = res.location;
					this.getList('refresh', pageStart);
				} else {					
					this.getList('refresh', pageStart);
				}
				
			},
		}
	}
</script>

<style>
	.list-view {
		margin-top: 30upx;
		overflow: hidden;
	}
</style>

