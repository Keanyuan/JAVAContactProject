<template>
	<view class="bottom-cart">
		<view class="bottom-cart-content">
			<view class="bottom-cart-content__left">
				<view class="cart-icon-content" @click="cartClick">
					<image class="cart-icon-content__img" src="../../static/icons/shopping_bag.png" mode="aspectFit" v-if="goodsNum>0"></image>
					<image class="cart-icon-content__img" src="../../static/icons/shopping_bag_disable.png" mode="aspectFit" v-if="goodsNum==0"></image>
					<view class="food-select-item__goodnum" v-if="goodsNum>0"><text>{{goodsNum}}</text></view>
				</view>
				<view class="price-content">
					<text class="price" :class="{'price-none': goodsNum<=0}">￥{{goodsNum<=0 ? 0 : price}}</text>
					<text class="shopping-fee">另送配送费￥{{goodsNum<=0 ? 0 : fee}}元</text>
				</view>
			</view>
			<view class="bottom-cart-content__right" :class="{'bg-none': goodsNum<=0}" @click="detailCick">
				<text>去结算</text>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			price: {
				type: Number,
				default: 0
			},
			// 是否已选择
			goodsNum: {
				type: Number,
				default: 0
			}, 
			fee: {
				type: Number,
				default: 0
			}
		},
		methods: {
			// 详情
			detailCick() {
				if(this.goodsNum <= 0)return;
				console.log("detailCick 选择");
				this.$emit('detailCick');
			},
			// 购物车
			cartClick() {
				if(this.goodsNum <= 0)return;
				console.log("cartClick 取消");
				this.$emit('cartClick');
			},
		}
	}
</script>

<style lang="scss" scoped>
	
	.bottom-cart{
		position: fixed;
		bottom: 40rpx;
		left: 0;
		right: 0;
		display: flex;
		justify-content: center;
		align-items: center;
		// background-color: #007AFF;
	}
	.bottom-cart-content{
		width: 90%;
		height: 100rpx;
		// overflow: hidden;
		display: flex;
		flex-direction: row;
		border-radius: 120rpx;
	}
	.bottom-cart-content__left{
		width: 70%;
		height: 100%;
		background-color: #000000;
		position: relative;
		border-radius: 120rpx 0rpx 0rpx 120rpx;
	}
	.bottom-cart-content__right{
		width: 30%;
		height: 100%;
		// background-color: $mainColor;
		background: linear-gradient(to left, $mainColor, $mainColorTransport);
		
		display: flex;
		justify-content: center;
		align-items: center;
		color: #FFFFFF;
		font-size: 30rpx;
		border-radius: 0rpx 120rpx 120rpx 0rpx;
		
	}
	
	.cart-icon-content{
		position: absolute;
		width: 120rpx;
		height: 120rpx;
		border-radius: 120rpx;
		left: 50rpx;
		bottom: 20rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		// background: linear-gradient(to left, $mainColor, $mainColorTransport);
	}
	
	.cart-icon-content__img{
		width: 120rpx;
		height: 120rpx;
	}
	
	.food-select-item__goodnum {
		position: absolute;
		right: -10rpx;
		top: 0rpx;
		border-radius: 1000rpx;
		background-color: $mainColorTransport;
		color: #FFFFFF;
		padding: 5rpx;
		min-width: 40rpx;
		display: flex;
		justify-content: center;
		align-items: center;
		box-shadow: 0px 0px 0px 4rpx #FFFFFF;
	}
	
	.price-content{
		margin-left: 180rpx;
		height: 100%;
		flex: 1;
		display: flex;
		flex-direction: column;
		justify-content: center;
		align-items: flex-start;
		color: #FFFFFF;
	}
	.price{
		font-size: 40rpx;
	}
	.price-none{
		color: $levelColor2;
	}
	.bg-none{
		background: linear-gradient(to left, $mainBlack2, $mainBlack2);
		
		color: $levelColor2;
	}
	.shopping-fee{
		font-size: 24rpx;
		color: $levelColor2;
	}
</style>
