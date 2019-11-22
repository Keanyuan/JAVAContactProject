<template>
	<view class="main-content">
		<view class="header">
			<image src="../../static/icons/my_bg.png" class="header-img" mode="aspectFill" v-if="show"></image>
			<image src="../../static/icons/my_bg_gray.png" class="header-img" mode="aspectFill" v-if="!show"></image>

			<view class="item-body" :style="[{top:(StatusBar) + 'px'}]">
				<view class="item-content">
					<view class="nav-content" :style="[{height:(NavBarHeight) + 'px'}]">
						<text>我的</text>
					</view>
					<view class="item-info">
						<image src="../../static/icons/img_placehoder.png" class="item-portrait" mode="aspectFill"></image>
						<text class="my-name" v-if="show">{{user.userName}}</text>
						<image src="../../static/icons/icon_mobile.png" class="mobile-icon" mode="aspectFill" v-if="show"></image>
						<view class="phone-contnet" v-if="show">
							<view class="margin-tb-sm">
							</view>
							<text class="phone-num">{{user.mobile}}</text>
							<view class="integral-content">
								<text class="phone-num">{{user.point}}分</text>
								<text class="pro-little">积</text>
							</view>
						</view>
					</view>
				</view>
			</view>
		</view>
		<view class="list-view">
			<my-cell v-for="(item,index) in itemsInfo" :imageName="item.image" :title="item.title" @click="listClick(item)" :key="index" v-if="item.show"></my-cell>
		</view>
		<view class="padding flex flex-direction" v-if="!show">
			<button class="cu-btn bg-theme margin-tb-sm lg round" @click="login">登录</button>
		</view>
	</view>
</template>

<script>
	import myCell from "@/components/my-cell.vue";

	export default {
		components: {
			myCell,
		},
		data() {
			return {
				itemsInfo: [{
						"title": "我的订单",
						"address": '上海市杨浦区江浦路1000号',
						"distance": "距离您34.99km",
						// #ifdef MP-WEIXIN
						"image": "../../../static/icons/icon_my_food.png",
						// #endif
						// #ifndef MP-WEIXIN
						"image": "../../static/icons/icon_my_food.png",
						// #endif
						"show": true
					},
					{
						"title": "个人信息",
						"address": '上海市杨浦区江浦路1000号',
						"distance": "距离您34.99km",
						// #ifdef MP-WEIXIN
						"image": "../../../static/icons/icon_my_profile.png",
						// #endif
						// #ifndef MP-WEIXIN
						"image": "../../static/icons/icon_my_profile.png",
						// #endif
						"show": true
					},
					{
						"title": "收货地址",
						"address": '上海市杨浦区江浦路1000号',
						"distance": "距离您34.99km",
						// #ifdef MP-WEIXIN
						"image": "../../../static/icons/icon_my_addr.png",
						// #endif
						// #ifndef MP-WEIXIN
						"image": "../../static/icons/icon_my_addr.png",
						// #endif
						"show": true
					},
					{
						"title": "设置",
						"address": '上海市杨浦区江浦路1000号',
						"distance": "距离您34.99km",
						// #ifdef MP-WEIXIN
						"image": "../../../static/icons/mine_icon_setup.png",
						// #endif
						// #ifndef MP-WEIXIN
						"image": "../../static/icons/mine_icon_setup.png",
						// #endif
						"show": true
					}
				],
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar,
				NavBarHeight: 44,
				show: false,
				user: ''
			}
		},
		onLoad() {
			this.NavBarHeight = this.CustomBar - this.StatusBar;
		},
		onShow() {
			this.getUserInfo();
		},
		methods: {
			listClick(item) {
				console.log(item);
				if (item.title == "设置") {
					uni.navigateTo({
						url: "./setting"
					})
				}
			},
			login() {
				uni.navigateTo({
					url: "../login/login"
				})
			},
			getUserInfo(){
				let user = this.$util.getStorageSync(this.$code.user);
				if (user) {
					this.show = true;
					this.user = user;
					this.itemsInfo[3].show = true;
				} else {
					this.itemsInfo[3].show = false;
					this.show = false;
				}
			}
		}
	}
</script>

<style lang="scss">
	.main-content {
		background-color: #ffffff;

	}

	.header {
		overflow: hidden;
		background-color: #ffffff;

		.header-img {
			width: 100%;
			height: 360rpx;
		}

		.item-body {
			position: absolute;
			left: 0rpx;
			right: 0rpx;

			.item-content {
				width: 100%;
				height: 360rpx;
				position: relative;
				display: flex;
				// padding: 10rpx;
				// background-color: rgba($color: #000000, $alpha: 0.1);
				// background-color: #007AFF;
				// border-bottom: 1upx solid #ddd;
				// justify-content: flex-start;
				flex-direction: column;

				.nav-content {
					display: flex;
					flex-direction: row;
					align-items: center;
					justify-content: center;

					text {
						font-size: 36rpx;
					}
				}

				.item-info {
					display: flex;
					flex-direction: row;
					align-items: center;

					.item-portrait {
						margin: 10rpx;
						margin-left: 40rpx;
						// border-radius: 10rpx;
						width: 100rpx;
						height: 100rpx;
					}

					.my-name {
						font-size: 30rpx;
						flex: 1;
						margin: 20rpx;
						color: #FFFFFF;

					}

					.mobile-icon {
						margin: 10rpx;
						margin-left: 40rpx;
						// border-radius: 10rpx;
						width: 40rpx;
						height: 40rpx;
					}

					.phone-contnet {
						display: flex;
						flex-direction: column;
						flex: 1;

						.integral-content {
							margin-top: 10rpx;
							margin-left: 10rpx;
							display: flex;
							flex-direction: row;
							width: 150rpx;
							height: 40rpx;
							justify-content: space-between;
							align-items: center;
							background-color: #D23038;
							border-radius: 40rpx;

							.pro-little {
								background-color: #FFFFFF;
								color: #D23038;
								width: 35rpx;
								height: 35rpx;
								text-align: center;
								border-radius: 35rpx;
								// margin: 10rpx;
								margin-right: 10rpx;

							}

						}
					}


				}

			}

		}
	}

	.phone-num {
		color: #FFFFFF;
		margin-left: 10rpx;
	}

	.list-view {
		margin-top: 30upx;
		// 溢出隐藏
		overflow: hidden;

		.list-item {
			position: relative;
			display: flex;
			padding: 10rpx;
			background-color: #ffffff;
			border-bottom: 1upx solid #ddd;
			align-items: center;
			justify-content: flex-start;
			flex-direction: row;
		}

		.item-img {
			margin: 10rpx;
			margin-left: 40rpx;
			// border-radius: 10rpx;
			width: 64upx;
			height: 64upx;
		}

		.item-content {
			position: relative;
			display: flex;
			flex: 1;
			flex-direction: column;

			margin: 10rpx;

			.l-title {
				color: #2C272D;
				font-size: 28rpx;
			}

			.sub-address {
				color: #999999;
			}
		}

		.sub-distance {
			color: #808080;
			padding: 10rpx;
		}

		.arrow {
			padding-right: 40rpx;
		}
	}
</style>
