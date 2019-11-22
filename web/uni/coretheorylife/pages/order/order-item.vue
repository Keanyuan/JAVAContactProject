<template>
	<view class="content" @click="clickDetail">
		<image :src="host + options.picUrl" class="item-img" mode="aspectFill"></image>
		<view class="item-content">
			<view class="l-title">{{options.shopName}}</view>
			<text class="sub-address">{{options.address}}</text>
		</view>
		<text class="sub-distance">{{options.distance/1000}}km</text>
		<view class="arrow">
			<view class="cuIcon-right"></view>
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
		border-radius: 10rpx;
		width: 240rpx;
		height: 160rpx;
	}
	.item-content {
		position: relative;
		display: flex;
		flex: 1;
		flex-direction: column;
		margin: 10rpx;
	}
	.l-title {
		color: #333333;
		font-size: 36rpx;
	}
		
	.sub-address {
		color: #999999;
	}
	
	.sub-distance {
		color: #808080;
		padding: 10rpx;
	}
	
	.arrow {
		padding-right: 40rpx;
	}
</style>
