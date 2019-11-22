<template>
	<view style="height: 100vh;">
		<!-- 头部轮播 -->
		<view class="carousel-section">
			<!-- 标题栏和状态栏占位符 -->
			<!-- <view class="titleNview-placing"></view> -->
			<!-- 背景色区域 -->
			<swiper class="carousel" autoplay circular @change="swiperChange">
				<swiper-item v-for="(item, index) in carouselList" :key="index" class="carousel-item" @click="navToDetailPage({title: '轮播广告'})">
					<!-- <image :src="item.src" /> -->
					<view class="banner-item " :style="{backgroundImage: 'url('+host + item.picUrl+')'}">
						<view class="bottom-content">
							<view class="v-flex"></view>
							<text>{{item.bannerName}}</text>
						</view>
					</view>
				</swiper-item>
			</swiper>
			<!-- 自定义swiper指示器 -->
			<view class="swiper-dots">
				<text class="num">{{swiperCurrent+1}}</text>
				<text class="sign">/</text>
				<text class="num">{{swiperLength}}</text>
			</view>
		</view>

		<view class="cu-list menu-avatar">
			<view class="cu-item" v-for="(item,index) in itemsInfo" :key="index" @click="listClick(index)">
				<view class="cu-avatar bg-white sm" :style="[{backgroundImage:'url('+item.image+')'}]"></view>
				<view class="content-view">
					<view class="text-grey">{{item.title}}</view>
					<view class="text-gray text-sm">
						<text class=" margin-right-xs"></text> {{item.desc}}</view>
				</view>
				<view class="action" v-if="index != 0">
					<view class="cuIcon-right"></view>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import locationUtil from "@/tools/localutil.js";

	export default {
		components: {},
		data() {
			return {
				host: this.$config.host,
				swiperCurrent: 0,
				swiperLength: 0,
				location: {
					longitude: 0.0,
					latitude: 0.0
				},
				carouselList: [{
					"picUrl": "/pic/getPic/74",
					"bannerName": "首页",
				}, ],
				itemsInfo: [{
						"title": "",
						"desc": "距离您0km",
						"image": "../../static/icons/home_place.png"
					},
					{
						"title": "现在下单",
						"desc": "ORDER NOW",
						"image": "../../static/tabbar/tabbar_health_normal.png"

					},
					{
						"title": "企业账户",
						"desc": "ENTERPRIST ACCOUNT",
						"image": "../../static/tabbar/tabbar_health_normal.png"
					},
				],
				shopList: [],
				locaSuccess: false,
			}
		},
		onLoad() {
			this.loadBanner();
		},
		onShow() {
			if (this.shopList.length == 0 && !this.locaSuccess) {
				// #ifdef H5
				this.locaSuccess = true;
				this.loadData();
				// #endif
				// #ifndef H5
				this.getLocation();
				// #endif
			}
		},
		methods: {
			loadBanner() {
				this.$api.queryBannerList({
					success: (res) => {
						this.carouselList = res.data;
						this.swiperLength = this.carouselList.length;
					},
					fail: (res) => {

					}
				});
			},
			loadData() {
				this.$api.queryShopListByName({
					shopName: "",
					latitude: this.location.latitude,
					longitude: this.location.longitude,
					success: (res) => {
						this.requesting = false;
						this.shopList = res.data;
						if (this.shopList.length > 0) {
							this.itemsInfo[0].title = this.shopList[0].shopName;
							this.itemsInfo[0].desc = "距离您" + this.shopList[0].distance / 1000 + "km";
							this.itemsInfo[0].image = this.$config.host + this.shopList[0].picUrl;
						}
					},
				});

			},
			//轮播图切换修改背景色
			swiperChange(e) {
				const index = e.detail.current;
				this.swiperCurrent = index;
			},
			listClick(index) {
				console.log(index);
			},
			navToDetailPage(e) {
				console.log(e);
			},
			async getLocation() {
				let res = await locationUtil.getLocation()
				if (res.status == 1) {
					this.locaSuccess = true;
					this.location = res.location;
					this.loadData();
				} else {
					this.loadData();
				}
			},
		}
	}
</script>

<style lang="scss">
	.carousel-section {
		position: relative;
	}

	.carousel {
		width: 100%;
		height: 400upx;

		.carousel-item {
			width: 100%;
			height: 100%;
			// padding: 0 28upx;
			overflow: hidden;

			.banner-item {
				width: 100%;
				height: 100%;
				border-radius: 10upx;
				background-size: cover;
				display: flex;

				.bottom-content {
					flex: 1;
					text-align: bottom;
					display: flex;
					flex-direction: column;
					background: linear-gradient(to top, rgba(0, 0, 0, 0.8), rgba(0, 0, 0, 0.1));

					text {
						color: #FFFFFF;
						font-size: 30rpx;
						margin-left: 20rpx;
						margin-bottom: 20rpx;
					}
				}
			}
		}

		image {
			width: 100%;
			height: 100%;
			border-radius: 10upx;
		}
	}

	.swiper-dots {
		display: flex;
		position: absolute;
		// background-color: #007AFF;
		right: 60upx;
		bottom: 15upx;
		width: 72upx;
		height: 36upx;
		background-image: url(data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAMgAAABkCAYAAADDhn8LAAAAGXRFWHRTb2Z0d2FyZQBBZG9iZSBJbWFnZVJlYWR5ccllPAAAAyZpVFh0WE1MOmNvbS5hZG9iZS54bXAAAAAAADw/eHBhY2tldCBiZWdpbj0i77u/IiBpZD0iVzVNME1wQ2VoaUh6cmVTek5UY3prYzlkIj8+IDx4OnhtcG1ldGEgeG1sbnM6eD0iYWRvYmU6bnM6bWV0YS8iIHg6eG1wdGs9IkFkb2JlIFhNUCBDb3JlIDUuNi1jMTMyIDc5LjE1OTI4NCwgMjAxNi8wNC8xOS0xMzoxMzo0MCAgICAgICAgIj4gPHJkZjpSREYgeG1sbnM6cmRmPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5LzAyLzIyLXJkZi1zeW50YXgtbnMjIj4gPHJkZjpEZXNjcmlwdGlvbiByZGY6YWJvdXQ9IiIgeG1sbnM6eG1wTU09Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC9tbS8iIHhtbG5zOnN0UmVmPSJodHRwOi8vbnMuYWRvYmUuY29tL3hhcC8xLjAvc1R5cGUvUmVzb3VyY2VSZWYjIiB4bWxuczp4bXA9Imh0dHA6Ly9ucy5hZG9iZS5jb20veGFwLzEuMC8iIHhtcE1NOkRvY3VtZW50SUQ9InhtcC5kaWQ6OTk4MzlBNjE0NjU1MTFFOUExNjRFQ0I3RTQ0NEExQjMiIHhtcE1NOkluc3RhbmNlSUQ9InhtcC5paWQ6OTk4MzlBNjA0NjU1MTFFOUExNjRFQ0I3RTQ0NEExQjMiIHhtcDpDcmVhdG9yVG9vbD0iQWRvYmUgUGhvdG9zaG9wIENDIDIwMTcgKFdpbmRvd3MpIj4gPHhtcE1NOkRlcml2ZWRGcm9tIHN0UmVmOmluc3RhbmNlSUQ9InhtcC5paWQ6Q0E3RUNERkE0NjExMTFFOTg5NzI4MTM2Rjg0OUQwOEUiIHN0UmVmOmRvY3VtZW50SUQ9InhtcC5kaWQ6Q0E3RUNERkI0NjExMTFFOTg5NzI4MTM2Rjg0OUQwOEUiLz4gPC9yZGY6RGVzY3JpcHRpb24+IDwvcmRmOlJERj4gPC94OnhtcG1ldGE+IDw/eHBhY2tldCBlbmQ9InIiPz4Gh5BPAAACTUlEQVR42uzcQW7jQAwFUdN306l1uWwNww5kqdsmm6/2MwtVCp8CosQtP9vg/2+/gY+DRAMBgqnjIp2PaCxCLLldpPARRIiFj1yBbMV+cHZh9PURRLQNhY8kgWyL/WDtwujjI8hoE8rKLqb5CDJaRMJHokC6yKgSCR9JAukmokIknCQJpLOIrJFwMsBJELFcKHwM9BFkLBMKFxNcBCHlQ+FhoocgpVwwnv0Xn30QBJGMC0QcaBVJiAMiec/dcwKuL4j1QMsVCXFAJE4s4NQA3K/8Y6DzO4g40P7UcmIBJxbEesCKWBDg8wWxHrAiFgT4fEGsB/CwIhYE+AeBAAdPLOcV8HRmWRDAiQVcO7GcV8CLM8uCAE4sQCDAlHcQ7x+ABQEEAggEEAggEEAggEAAgQACASAQQCCAQACBAAIBBAIIBBAIIBBAIABe4e9iAe/xd7EAJxYgEGDeO4j3EODp/cOCAE4sYMyJ5cwCHs4rCwI4sYBxJ5YzC84rCwKcXxArAuthQYDzC2JF0H49LAhwYUGsCFqvx5EF2T07dMaJBetx4cRyaqFtHJ8EIhK0i8OJBQxcECuCVutxJhCRoE0cZwMRyRcFefa/ffZBVPogePihhyCnbBhcfMFFEFM+DD4m+ghSlgmDkwlOgpAl4+BkkJMgZdk4+EgaSCcpVX7bmY9kgXQQU+1TgE0c+QJZUUz1b2T4SBbIKmJW+3iMj2SBVBWz+leVfCQLpIqYbp8b85EskIxyfIOfK5Sf+wiCRJEsllQ+oqEkQfBxmD8BBgA5hVjXyrBNUQAAAABJRU5ErkJggg==);
		background-size: 100% 100%;

		.num {
			width: 36upx;
			height: 36upx;
			border-radius: 50px;
			font-size: 24upx;
			color: #fff;
			text-align: center;
			line-height: 36upx;
		}

		.sign {
			position: absolute;
			top: 0;
			left: 50%;
			line-height: 36upx;
			font-size: 12upx;
			color: #fff;
			transform: translateX(-50%);
		}
	}
</style>
