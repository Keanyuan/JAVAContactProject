<template>
	<cus-scroll id='scroll' :requesting="requesting" :end="end" :empty-show="emptyShow" :listCount="listCount" @refresh="refresh"
	 @more="more">
		<view>
			<health-item v-for="(item,index) in itemsInfo" :key="index" @clickDetail="listClick(index)" :options="item"></health-item>
		</view>
	</cus-scroll>
</template>

<script>
	import healthItem from './health-item.vue';
	import cusScroll from "@/components/cus-tab/cus-scroll.vue";
	import locationUtil from "@/tools/localutil.js";
	

	let pageStart = 0;
	
	export default {
		components: {
			healthItem,
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
		onLoad() {
			// this.getLocation();
		},
		methods: {
			getList(type, currentPage) {
				this.requesting = true;
				// uni.showNavigationBarLoading();
				if (type === 'refresh'){
					this.$api.queryHealthShopListByName({
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
				// var item = encodeURIComponent(JSON.stringify(this.itemsInfo[index]));
				var item = JSON.stringify(this.itemsInfo[index]);
				item = this.$util.aesEncrypt(item);
				uni.navigateTo({
					// url: "./date_select_page"
					url: './health-detail?item=' + item
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
