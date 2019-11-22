<template>
	<view class="content" @click="clickDetail">
		<image :src="host + options.picUrl" class="item-img comm-gradient" mode="widthFix"></image>
		<view class="item-content">
			<view class="item-title">
				<view class="l-title">{{options.shopName}}</view>
				<text class="sub-address">{{options.address}}</text>
			</view>
			<text class="sub-distance">{{options.distance/1000}}km</text>
			<view class="arrow">
				<view class="cuIcon-right"></view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			options: {
				type: Object,
				default: function(e) {
					return {}
				},
			},
		},
		data() {
			return {
				host: this.$config.host
			}
		},
		methods: {
			// 详情
			clickDetail() {
				this.$emit('clickDetail');
			},
		}
	}
</script>

<style>
	.content {
		position: relative;
		margin: 40rpx;
	}

	.item-img {
		border-radius: 20rpx;
		height: 360rpx;
		width: 100%;
	}
	.item-content{
		position: absolute;
		left: 0rpx;
		right: 0rpx;
		bottom: 0rpx;
		display: flex;
		border-radius: 20rpx;
		align-items: center;
		justify-content: flex-start;
		flex-direction: row;
	}
	.item-title {
		position: relative;
		display: flex;
		flex: 1;
		flex-direction: column;
		margin: 10rpx;
	}

	.l-title {
		color: #FFFFFF;
		font-size: 48rpx;
	}
				
	.sub-address {
		font-size: 36rpx;
		color: #FFFFFF;
	}
	
	.sub-distance {
		font-size: 24rpx;
		color: #FFFFFF;
		padding: 10rpx;
	}
					
	.arrow {
		color: #FFFFFF;
		padding-right: 40rpx;
	}
</style>
