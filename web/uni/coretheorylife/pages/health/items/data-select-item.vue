<template>
	<view class="food-item">
		<view v-if="selected" @click="selectClick">
			<view class="food-select-item__main">
				<text class="food-select-item__title">{{title}}</text>
				<text class="food-select-item__des">Calories</text>
			</view>
			<view class="food-select-item__cancle cuIcon-close" @click.stop="cancleClick"></view>
		</view>
		<view class="food-normal-item__main" v-if="!selected" @click="normalClick">
			<image src="../../../../../static/icons/icon_addfood.png" mode="aspectFit" class="food-normal-item__img"></image>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			title: {
				type: String,
				default: ""
			},
			// 是否已选择
			selected: {
				type: Boolean,
				default () {
					return false
				}
			}, 
		},
		methods: {
			// 默认点击
			normalClick() {
				console.log("normalClick 未选择");
				this.$emit('normalClick');
			},
			// 选择点击
			selectClick() {
				console.log("selectClick 选择");
				this.$emit('selectClick');
			},
			// 取消
			cancleClick() {
				console.log("cancleClick 取消");
				this.$emit('cancleClick');
			},
		}
	}
</script>

<style lang="scss" scoped>
	.food-item {
		width: 100px;
		height: 200rpx;
		text-align: center;
		display: flex;
		justify-content: center;
		align-items: center;
		position: relative;
	}
	
	
	.food-select-item__cancle {
		position: absolute;
		right: 10rpx;
		top: 10rpx;
		width: 40rpx;
		height: 40rpx;
		border-radius: 40rpx;
		background-color: $mainColorTransport;
		color: #FFFFFF;
		display: flex;
		justify-content: center;
		align-items: center;
		box-shadow: 0px 0px 2px 2rpx #FFFFFF;
	}
	
	.food-select-item__main {
		border: 8rpx solid $mainColorTransport;
		border-radius: 100rpx;
		background-color: $whiteColor;
		width: 100px;
		height: 100px;
		display: flex;
		position: relative;
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}
	
	.food-select-item__title {
		font-weight: 400;
		font-size: 48rpx;
		word-break: break-all;
	}
	
	.food-select-item__des {
		font-size: 28rpx;
		color: $levelColor2;
	}
	
	.food-normal-item__main {
		background-color: #FFFFFF;
		border-radius: 100rpx;
		width: 180rpx;
		height: 180rpx;
		display: flex;
		justify-content: center;
		align-items: center;
	}
	
	.food-normal-item__img {
		width: 150rpx;
		height: 150rpx;
		border-radius: 150rpx;
	}
</style>
