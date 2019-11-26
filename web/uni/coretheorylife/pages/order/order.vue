<template>
	<cus-scroll id='scroll' :requesting="requesting" :end="end" :empty-show="emptyShow" :listCount="listCount" @refresh="refresh"
	 @more="more">
		<view>
			<order-item v-for="(item,index) in itemsInfo" :key="index" @clickDetail="listClick(index)" :options="item"></order-item>
		</view>
	</cus-scroll>
</template>

<script>
	import orderItem from './order-item.vue';
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
				locaSuccess: false
			}
		},
		onLoad() {
		// 	this.getLocation();
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
				var item = JSON.stringify(this.itemsInfo[index]);
				item = this.$util.aesEncrypt(item);
				uni.navigateTo({
					// url: "./date_select_page"
					url: './order_list/order_list?item=' + item
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


<!-- { -->
<!-- 
				this.requesting = true;

				uni.showNavigationBarLoading();

				setTimeout(() => {
					this.requesting = false;
					this.end = false;

					uni.hideNavigationBarLoading();
					if (type === 'refresh') {
						this.itemsInfo = dataItem;
						this.page = currentPage + 1;
					} else {
						this.itemsInfo = [...this.itemsInfo, ...dataItem];
						this.page = currentPage + 1;
					}					

					// 没有数据的时候这样设置
					if(this.itemsInfo.length == 0){
						this.emptyShow = true;
					}

					if (this.page > 6) {
						this.end = true; //没有更多数据
					} 

					this.listCount = this.itemsInfo.length; //列表条数
				}, 1000);

			} -->
