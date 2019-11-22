<template>
	<view class="cell-content"  @click="click()">
		<image  v-if='imageName !== ""' :src="imageName" :class='imageName !== "" ? "item-img" : "margin-left-20"' mode="aspectFit"></image>
		<view class="item-content" :class='imageName !== "" ? "" : "margin-left-20"'>
			<view class="l-title">{{title}}</view>
			<text class="sub-address" v-if='subtitle !== ""'>{{subtitle}}</text>
		</view>
		<view class="arrow">
			<view class="cuIcon-right" v-if="showArrow"></view>
			<slot></slot>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			imageName: {
				type: String,
				value: ''
			},
			title: {
				type: String,
				value: ''
			},
			subtitle: {
				type: String,
				value: ''
			},
			showArrow: {
				type: Boolean,
				default () {
					return true;
				}
			}
		},
		onLoad() {
		},
		methods: {
			// 详情
			click() {
				this.$emit('click');
			},
		}
	}
</script>

<style>
	.cell-content {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: flex-start;
		background-color: #ffffff;
		padding: 10rpx;
		border-bottom: 1upx solid #ddd;
	}

	.item-img {
		margin: 10rpx;
		margin-left: 40rpx;
		width: 64upx;
		height: 64upx;
	}


	.item-content {
		width: 100%;
		position: relative;
		display: flex;
		flex-direction: column;
	}
	.nav-content {
		display: flex;
		flex-direction: row;
		align-items: center;
		justify-content: center;
	}
	.nav-content text{
		font-size: 36rpx;
	}
	.l-title {
		color: #2C272D;
		font-size: 28rpx;
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
