<template>
	<view class="main">
		<view class="top-content">

			<view class="c-flex padding-left-right-10 padding-top-10">
				<text style="font-weight: 500;font-size: 24rpx;">自提店面：</text>
				<text style="font-size: 20rpx;">(定制套餐仅限自提)</text>
				<view style="flex: 1;"></view>
				<image style="width: 240rpx; height: 80rpx;" src="../../static/icons/Switch_disabled.png" mode="aspectFit"></image>
			</view>

			<view class="c-flex padding-10 ">
				<image class="address-img" src="../../static/icons/makesure_store.png" mode="aspectFit"></image>

				<view class="padding-left-right-10">
					<view class="c-flex">
						<text class="address-shopName">{{storeInfoModel.shopName}}</text>
						<text class="address-distance">距离您{{storeInfoModel.distance}}km</text>
					</view>
					<text style="font-size: 20rpx;">{{storeInfoModel.address}}</text>
				</view>
			</view>

			<image class="makesure_line" src="../../static/icons/makesure_line.png" mode="aspectFit"></image>

			<view class="c-flex zhufu padding-left-right-10">
				<text class="zhufu__title">支付方式</text>
				<view style="flex: 1;">
				</view>
				<text class="zhufu__select">请选择</text>
				<view class="cuIcon-right"></view>
			</view>
			<!-- 日期列表 -->
			<view class="padding-left-right-10" v-for="(dateItem, dateIndex) in health_select_locals" :key="dateItem.date">
				<view class="c-flex date-select">
					<text>{{dateItem.date}}</text>
				</view>
				<!-- 餐列表 -->
				<view class="can-main" v-for="(canItem, canIndex) in dateItem.canItems" :key="canItem.date + canIndex">
					<view class="c-flex can-title">
						<view class="c-flex">
							<text>{{canItem.canName}}</text>
						</view>
						<text>{{canItem.calTotal}}cal</text>
					</view>
					<!-- 食物列表 -->
					<view class="c-flex food-main" v-for="(foodItem, foodIndex) in canItem.foodItem" :key="foodItem.date + foodItem.canId + foodIndex">

						<image class="food-image" :src="host + foodItem.picUrl" mode="aspectFill"></image>
						<view class="food-text">
							<!-- <view class="food-name">
								<image :src="host + foodItem.picUrl" mode="aspectFit"></image>

							</view> -->
							<text class="food-name">{{foodItem.foodName}}</text>

							<view class="c-flex food-desc">
								<image src="../../static/icons/icon_calories.png" mode="aspectFit"></image>
								<text class="food-desc__text">{{foodItem.cal}}cal</text>
							</view>
						</view>
						<view style="flex: 1;"></view>
						<view class="c-flex food-goodsNum">
							<text style="font-size: 16rpx;">x</text>
							<text style="font-size: 20rpx;">{{foodItem.goodsNum}}</text>
						</view>
						<view class="c-flex food-price">
							<text style="font-size: 18rpx;">￥</text>
							<text style="font-size: 28rpx;">{{foodItem.price}}</text>
						</view>
					</view>
				</view>
			</view>
		</view>
		<makesure-bottom :calTotal="calTotal" :prices="prices" btitle="去结算" @makeSureClick="makeSureClick"></makesure-bottom>
	</view>
</template>

<script>
	import makesureBottom from '@/components/makesure-bottom.vue'

	export default {
		components: {
			makesureBottom
		},
		onLoad(options) {
			if (options && options.item) {
				let item = this.$util.aesDecrypt(options.item);
				// console.log(item);
				this.storeInfoModelStr = options.item;
				this.storeInfoModel = JSON.parse(item);
				this.storeInfoModel.distance = this.$util.format45(this.storeInfoModel.distance / 1000, 100);
				console.log(this.storeInfoModel);

				this.initData()
			}
		},
		onShow() {

		},
		onBackPress() {
			this.setAllSelect(false)
			this.checkAllInfo(true, this.health_locals)
			uni.setStorageSync(this.$code.health_locals, this.health_locals);

			console.log("点击了返回  true 进行拦截");
			return false;
		},
		data() {
			return {
				storeInfoModelStr: '',
				storeInfoModel: '',
				health_locals: '',
				health_select_locals: '',
				allSelect: false,
				calTotal: 0.0,
				prices: 0.0,
				host: this.$config.host,
			}
		},
		methods: {
			initData() {
				let health_locals = this.$util.getStorageSync(this.$code.health_locals);
				let health_locals1 = this.$util.getStorageSync(this.$code.health_locals);
				console.log(health_locals);
				this.health_locals = health_locals;
				this.health_select_locals = health_locals1;
				this.checkAllSelectInfo()
				// this.checkAllSelect()
			},
			checkAllSelectInfo() {
				// 遍历所有数据
				for (let var1 in this.health_select_locals) {
					// 日期Item
					let dateItem = this.health_select_locals[var1];
					// 修改日期选择 dateItem.isSelect
					var dateSelectCount = 0;
					for (let var2 in dateItem.canItems) {
						// 餐Item 每一餐
						let canItem = dateItem.canItems[var2];
						// 修改餐选择 canItem.isSelect
						var canItemCount = 0;
						canItem.foodItem = canItem.foodItem.filter(function(foodItem) {
							if (foodItem.isSelect) {
								canItemCount++;
							}
							return foodItem.isSelect;
						});

						// 如果（早、中、晚）餐 选中的食物列表不为0  canItem.isSelect为true
						if (canItemCount != 0) {
							canItem.isSelect = true;
						} else {
							canItem.isSelect = false;
						}
					}
					dateItem.canItems = dateItem.canItems.filter(function(canItem) {
						if (canItem.isSelect) {
							dateSelectCount++;
						}
						return canItem.isSelect;
					});
					// 如果 对应日期数据不为0  dateItem.isSelect为true
					if (dateSelectCount != 0) {
						dateItem.isSelect = true;
					} else {
						// 删除 （日期 对应列表
						dateItem.isSelect = false;
					}
				}
				this.health_select_locals = this.health_select_locals.filter(function(dateItem) {
					return dateItem.isSelect;
				});
				console.log("-----------");
				console.log(this.health_select_locals);
				this.checkAllInfo(false, this.health_select_locals)
			},
			checkAllInfo(isCheck = false, health_locals) {
				this.prices = 0.0;
				this.calTotal = 0.0;
				// 遍历所有数据
				for (let var1 in health_locals) {
					// 日期Item
					let dateItem = health_locals[var1];
					dateItem.calTotal = 0.0;
					dateItem.goodsNum = 0;
					dateItem.priceTotal = 0.0;
					for (let var2 in dateItem.canItems) {
						// 餐Item 每一餐
						let canItem = dateItem.canItems[var2];
						canItem.calTotal = 0.0;
						canItem.goodsNum = 0;
						canItem.price = 0.0;
						for (let var3 in canItem.foodItem) {
							// 食物item
							let foodItem = canItem.foodItem[var3];
							if (isCheck) {
								canItem.calTotal += (foodItem.goodsNum * foodItem.cal);
								canItem.goodsNum += (foodItem.goodsNum);
								canItem.price += (foodItem.goodsNum * foodItem.price);
							} else {
								if (foodItem.isSelect) {
									canItem.calTotal += (foodItem.goodsNum * foodItem.cal);
									canItem.goodsNum += (foodItem.goodsNum);
									canItem.price += (foodItem.goodsNum * foodItem.price);
								}
							}
						}
						dateItem.calTotal += canItem.calTotal;
						dateItem.goodsNum += canItem.goodsNum;
						dateItem.priceTotal += canItem.price;
					}
					this.prices += dateItem.priceTotal;
					this.calTotal += dateItem.calTotal;
				}

				this.prices = this.$util.format45(this.prices, 100);
				this.calTotal = this.$util.format45(this.calTotal, 100);
			},
			setAllSelect(isSelect) {
				// // 遍历所有数据
				for (let var1 in this.health_locals) {
					// 日期Item
					let dateItem = this.health_locals[var1];
					// 修改日期选择 dateItem.isSelect
					var dateSelectCount = 0;
					for (let var2 in dateItem.canItems) {
						// 餐Item 每一餐
						let canItem = dateItem.canItems[var2];
						// 修改餐选择 canItem.isSelect
						var canItemCount = 0;
						for (let var3 in canItem.foodItem) {
							// 修改食物选择 canItem.foodItem[var3].isSelect
							canItem.foodItem[var3].isSelect = isSelect;
							if (canItem.foodItem[var3].isSelect) {
								canItemCount++;
							}
						}
						if (canItemCount == canItem.foodItem.length && canItemCount != 0) {
							canItem.isSelect = true;
						} else {
							canItem.isSelect = false;
						}
						if (canItem.isSelect) {
							dateSelectCount++;
						}
					}
					if (dateSelectCount == dateItem.canItems.length && dateSelectCount != 0) {
						dateItem.isSelect = true;
					} else {
						dateItem.isSelect = false;
					}
				}
			},
			makeSureClick() {

			}
		}

	}
</script>

<style lang="scss" scoped>
	.main {
		height: 100vh;
		width: 100%;
		display: flex;
		flex-direction: column;
		background-color: #FFFFFF;
		position: relative;

	}

	.c-flex {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.top-content {
		padding-bottom: 120rpx;
	}


	.address-img {
		width: 50rpx;
		height: 50rpx;
	}

	.address-shopName {
		font-weight: 500;
		font-size: 30rpx;
		margin-right: 20rpx;
	}

	.address-distance {
		color: $levelColor2;
		font-size: 20rpx;
	}

	.makesure_line {
		height: 9rpx;
		width: 100%;
	}

	.zhufu {
		height: 100rpx;
		border-bottom: 2rpx solid $lienColor;
	}

	.zhufu__title {
		font-size: 24rpx;
		color: $levelColor2;
	}

	.zhufu__select {
		font-size: 28rpx;
		color: $levelColor1;
		margin-right: 20rpx;
	}


	image {
		margin-right: 10rpx;
		width: 30rpx;
		height: 30rpx;
	}

	.date-select {
		height: 60rpx;
		background-color: $whiteColor;
		justify-content: flex-start;
		position: relative;
	}

	.date-select__img {
		position: absolute;
		left: 20rpx;
		top: 10rpx;
	}

	.can-main {
		background-color: $whiteColor;
		padding-left: 40rpx;
		padding-right: 40rpx;
	}

	.can-title {
		height: 60rpx;
		border-top: 2rpx solid $lienColor;
		border-bottom: 2rpx solid $lienColor;
		justify-content: space-between;
	}

	.food-main {
		margin-top: 10rpx;
		// justify-content: space-between;
		padding-left: 20rpx;
		padding-right: 20rpx;
		padding-bottom: 10rpx;
		border-bottom: 2rpx solid $lienColor;

	}

	.food-image {
		width: 80rpx;
		height: 80rpx;
		border-radius: 50%;
	}

	.food-text {
		display: flex;
		flex-direction: column;
	}

	.food-name {
		color: $levelColor1;
		font-size: 26rpx;
	}

	.food-desc {
		image {
			width: 20px;
			height: 20rpx;
		}
	}

	.food-desc__text {
		color: $levelColor2;
		font-size: 20rpx;
	}

	.food-goodsNum {
		color: $levelColor2;
		margin-left: 40rpx;
	}

	.food-price {
		color: $levelColor1;
		margin-left: 40rpx;
	}
</style>
