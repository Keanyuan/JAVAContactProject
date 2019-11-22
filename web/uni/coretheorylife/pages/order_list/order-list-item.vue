<template>
	<view class="content" @click="clickDetail">
		<text class="margin-left-10 padding-bottm-10  text-font-size-12 text-level-two-color">{{options.foodName}}</text>
		<view class="bottom-content" :style="{height: bottomHeight + 'rpx'}">
			<image :src="host + options.picUrl" class="bottom-image comm-gradient" mode="widthFix"></image>
			<view class="content-pannel">
				<text class="content-title"></text>
				<view class="content-bottom-pannel">
					<view class="content-cal-pannel">
						<image src="../../static/icons/icon_calories.png" class="cal-image" mode="aspectFill"></image>
						<text class="cal-text">{{options.cal}}cal</text>
					</view>
					<view class="contet-edit-pannel">
						<text class="edit-price-text">￥{{options.price}}</text>
						<view style="flex: 1;"></view>
						<text class="subtract-text edit-text" @click.stop="subtract" v-if="options.goodsNum > 0">-</text>
						<text class="num-text edit-text" v-if="options.goodsNum > 0">{{options.goodsNum}}</text>
						<text class="add-text edit-text" @click.stop="add">+</text>
					</view>
				</view>
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
				}
			},
		},
		data() {
			let height = (this.ScreenWidth - 40) * 1 / 1.5 * 2;
			return {
				host: this.$config.host,
				bottomHeight: height,
			}
		},
		methods: {
			// 详情
			clickDetail() {
				console.log("clickDetail");
				this.$emit('clickDetail');
			},
			// 加
			add() {
				this.$emit('add');
			},
			// 减
			subtract() {
				this.$emit('subtract');
			},
		}
	}
</script>

<style>
	.content {
		position: relative;
		display: flex;
		width: 100%;
		flex-direction: column;
		/* background-color: #4CD964; */
		justify-content: space-between;
		padding: 20rpx;
		margin-bottom: 10rpx;
	}

	.bottom-content {
		height: 480rpx;
	}

	.bottom-image {
		border-radius: 20rpx;
		width: 100%;
		height: 100%;
	}

	.content-pannel {
		position: absolute;
		left: 0rpx;
		right: 0rpx;
		bottom: 0rpx;
		height: 40%;
		display: flex;
		flex-direction: column;
		justify-content: flex-end;
		padding-left: 20rpx;
		padding-right: 20rpx;
		padding-bottom: 20rpx;

	}

	.content-title {
		color: #FFFFFF;
		font-size: 36rpx;
		line-height: 36rpx;
		margin: 20rpx;
	}

	.content-bottom-pannel {
		border-radius: 0rpx 0rpx 20rpx 20rpx;
		background-color: #FFFFFF;
		border-right: 1rpx solid #CCCCCC;
		border-left: 1rpx solid #CCCCCC;
		border-bottom: 1rpx solid #CCCCCC;
		display: flex;
		flex-direction: column;
	}

	.content-cal-pannel {
		display: flex;
		flex-direction: row;
		margin-left: 40rpx;
		margin-top: 20rpx;
	}

	.cal-image {
		width: 20rpx;
		height: 20rpx;
	}

	.cal-text {
		font-size: 24rpx;
		line-height: 24rpx;
		margin-left: 10rpx;
		color: #999999;
	}

	.contet-edit-pannel {
		display: flex;
		flex-direction: row;
		min-height: 45rpx;
		margin: 15rpx 20px 10px 20px;
	}

	.edit-price-text {
		font-size: 36rpx;
		line-height: 36rpx;
		color: #FD300D;
	}

	.edit-text {
		text-align: center;
		height: 35rpx;
		color: 999999;
		border-radius: 35rpx;
	}

	.subtract-text {
		width: 35rpx;
		border: 1rpx solid #CCCCCC;
	}

	.num-text {
		margin-left: 10rpx;
		margin-right: 10rpx;
	}

	.add-text {
		width: 35rpx;
		background: linear-gradient(to top, #FF3660, #FD300D);
		color: #FFFFFF;
	}
</style>
