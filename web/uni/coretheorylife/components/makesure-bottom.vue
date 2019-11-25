<template>
	<view class="bottom-cart">
		<view class="bottom-cart__left">
			<view class="bottom-cart__left__cal">
				<image src="../../static/icons/icon_calories.png" mode="aspectFit"></image>
				<text class="bottom-cal__text">{{calTotal}}cal</text>
			</view>
			<view style="flex: 1;"></view>
			<view class="bottom-cart__left__price">
				<text class="food-cart_combined">合计</text>
				<text class="food-cart_price">￥{{prices}}</text>
			</view>
		</view>
		<view class="bottom-cart__right" :class="prices > 0 ? 'bottom-cart__right__select' : 'bottom-cart__right__normal'" @click="makeSureClick">
			<text>{{btitle}}</text>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			// 卡路里
			calTotal: {
				type: Number,
				default: 0.0
			},
			// 价格
			prices: {
				type: Number,
				default: 0.0
			}, 
			btitle: String
		},
		methods: {
			// 购物车
			makeSureClick() {
				if(this.prices <= 0)return;
				console.log("makeSureClick 取消");
				this.$emit('makeSureClick');
			},
		}
	}
</script>

<style lang="scss" scoped>
	
	image {
		margin-right: 10rpx;
		width: 30rpx;
		height: 30rpx;
	}
	
	.bottom-cart {
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		height: 120rpx;
		background-color: $levelColor1;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	
	.bottom-cart__left {
		background-color: $levelColor1;
		display: flex;
		flex-direction: row;
		align-items: center;
		color: $whiteColor;
		height: 100%;
		flex: 7;
	}
	
	.bottom-cart__right {
		flex: 3;
		height: 100%;
		display: flex;
		flex-direction: row;
		align-items: center;
		color: $whiteColor;
		justify-content: center;
	}
	.bottom-cart__right__normal{
		background-color: $levelColor1;
	}
	.bottom-cart__right__select{
		background-color: $mainColor;
	}
	.bottom-cart__left__cal{
		margin-left: 20rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
	}
	.bottom-cal__text{
		font-size: 28rpx;
		color: $whiteColor;
	}
	.bottom-cart__left__price{
		margin-left: 20rpx;
		margin-right: 20rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		
	}
	.food-cart_combined{
		color: $levelColor2;
		font-size: 20rpx;
	}
	.food-cart_price{
		margin-left: 10rpx;
		color: $whiteColor;
		font-size: 28rpx;
	}
</style>
