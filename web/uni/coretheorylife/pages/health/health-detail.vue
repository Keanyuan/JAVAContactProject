<template>
	<view style="height: 100vh; position: relative; display: flex; flex-direction: column;">
		<!-- 头部轮播 -->
		<view class="carousel-section">
			<!-- 标题栏和状态栏占位符 -->
			<!-- <view class="titleNview-placing"></view> -->
			<!-- 背景色区域 -->
			<swiper class="carousel" autoplay circular @change="swiperChange">
				<swiper-item v-for="(item, index) in carouselList" :key="index" class="carousel-item" @click="navToDetailPage({title: '轮播广告'})">
					<!-- <image :src="item.src" /> -->
					<view class="banner-item " :style="{backgroundImage: carouselList.length > 0 ? 'url('+host + item+')' : ''}">
						<view class="bottom-content">
							<view class="v-flex"></view>
							<!-- <text>{{index}}</text> -->
						</view>
					</view>
				</swiper-item>
			</swiper>
			<!-- 自定义swiper指示器 -->
			<view class="swiper-dots">
				<text class="num">{{swiperLength > 0 ? swiperCurrent+1 : 0}}</text>
				<text class="sign">/</text>
				<text class="num">{{swiperLength}}</text>
			</view>
		</view>
		<text class="margin-left-20 text-font-size-18 margin-top-20">{{shopDetail.shopName}}</text>
		<text class="margin-left-20 margin-top-10">地址：{{shopDetail.address}}</text>
		<text class="margin-left-20  margin-top-10 text-font-size-12">{{shopDetail.shopDescribe}}</text>

		<view class="bottom">
			<my-button class="bottom-button" title="点餐" @click="diancan"></my-button>
			<my-button class="bottom-button" title="健身信息"></my-button>
		</view>
		<view class="backIcon" :style="{top: navTop + 'rpx'}" @click="back"></view>
	</view>
</template>

<script>
	import myButton from '@/components/my-button.vue'

	export default {
		components: {
			myButton,
		},
		data() {
			return {
				host: this.$config.host,
				swiperCurrent: 0,
				swiperLength: 0,
				carouselList: [],
				shopList: [],
				navTop: this.StatusBar,
				storeInfoModel: '',
				shopDetail: {},
				storeInfoModelStr: ''

			}
		},
		onLoad(options) {
			// #ifdef H5
			this.navTop = 40;
			// #endif
			if (options && options.item) {
				let item = this.$util.aesDecrypt(options.item);
				console.log(item);
				this.storeInfoModelStr = options.item;
				// this.storeInfoModel = JSON.parse(decodeURIComponent(item));
				this.storeInfoModel = JSON.parse(item);
				this.loadBanner();
			}
		},
		onShow() {},
		methods: {
			back() {
				uni.navigateBack()
			},
			loadBanner() {

				this.$api.queryById({
					id: this.storeInfoModel.id,
					success: (res) => {
						this.shopDetail = res.data;
						this.carouselList = this.shopDetail.picUrlList;
						this.swiperLength = this.carouselList.length;
					},
					fail: (res) => {

					}
				});
			},
			//轮播图切换修改背景色
			swiperChange(e) {
				const index = e.detail.current;
				this.swiperCurrent = index;
			},
			diancan() {
				uni.navigateTo({
					url: './date_select_page?item=' + this.storeInfoModelStr
				})
			}
		}
	}
</script>

<style lang="scss">
	.carousel-section {
		position: relative;
		background-color: $lienColor;
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

	.backIcon {
		position: fixed;
		left: 20rpx;
		top: 10rpx;
		width: 60rpx;
		height: 60rpx;
		background: url(../../static/icons/back_svg.svg);
		background-size: cover;
	}

	.bottom {
		position: fixed;
		left: 20rpx;
		right: 20rpx;
		bottom: 40rpx;
		display: flex;
		justify-content: space-around;
		flex-direction: row;
	}

	.bottom-button {
		width: 280rpx;
	}
</style>
