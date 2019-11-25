<template>
	<view class="main">
		
		<makesure-bottom :calTotal="calTotal" :prices="prices"  btitle="去结算" @makeSureClick="makeSureClick"></makesure-bottom>
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
				// console.log(this.storeInfoModel);
				this.initData()
			}
		},
		onShow() {

		},
		onBackPress() {
			this.setAllSelect(false)
			this.checkAllInfo(true)
			uni.setStorageSync(this.$code.health_locals, this.health_locals);
			
			console.log("点击了返回  true 进行拦截");
			return false;
		},
		data() {
			return {
				storeInfoModelStr: '',
				storeInfoModel: '',
				health_locals: '',
				allSelect: false,
				calTotal: 0.0,
				prices: 0.0
			}
		},
		methods: {
			initData() {
				let health_locals = this.$util.getStorageSync(this.$code.health_locals);
				console.log(health_locals);
				this.health_locals = health_locals;
				this.checkAllInfo()
			},
			checkAllInfo(isCheck = false) {
				this.prices = 0.0;
				this.calTotal = 0.0;
				// 遍历所有数据
				for (let var1 in this.health_locals) {
					// 日期Item
					let dateItem = this.health_locals[var1];
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
							if(isCheck){
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
			checkAllSelect() {
				var allSelectCount = 0;

				// 遍历所有数据
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

					if (dateItem.isSelect) {
						allSelectCount++;
					}
				}

				if (allSelectCount == this.health_locals.length && allSelectCount != 0) {
					this.allSelect = true;
				} else {
					this.allSelect = false;
				}

				this.checkAllInfo();
			},
			allSelectClick() {
				this.allSelect = !this.allSelect;
				// 遍历所有数据
				for (let var1 in this.health_locals) {
					// 日期Item
					let dateItem = this.health_locals[var1];
					// 修改日期选择
					dateItem.isSelect = this.allSelect;
					for (let var2 in dateItem.canItems) {
						// 餐Item 每一餐
						let canItem = dateItem.canItems[var2];
						// 修改餐选择
						canItem.isSelect = this.allSelect;
						for (let var3 in canItem.foodItem) {
							// 修改食物选择
							canItem.foodItem[var3].isSelect = this.allSelect;
						}
					}
				}
				this.checkAllSelect();
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
			makeSureClick(){
				
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

	.main-select {
		height: 80rpx;
		border-top: 2rpx solid $lienColor;
		border-bottom: 2rpx solid $lienColor;
		display: flex;
		flex-direction: row;
		// background-color: #007AFF;
		align-items: center;
		justify-content: space-between;
		padding-left: 20rpx;
		padding-right: 20rpx;
	}

	.top-content {
		padding-bottom: 120rpx;
	}

	.all-select {
		// background-color: #007AFF;
		width: 120rpx;
		height: 60rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		color: $levelColor2;
		font-size: 28rpx;
	}

	image {
		margin-right: 10rpx;
		width: 30rpx;
		height: 30rpx;
	}


	.all-clear {
		background-color: #007AFF;
		width: 120rpx;
		height: 60rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.date-select {
		height: 60rpx;
		display: flex;
		flex-direction: row;
		background-color: $whiteColor;
		align-items: center;
		justify-content: center;
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

	.can-select {
		display: flex;
		flex-direction: row;
		align-items: center;
	}

	.can-title {
		height: 60rpx;
		border-top: 2rpx solid $lienColor;
		border-bottom: 2rpx solid $lienColor;
		display: flex;
		flex-direction: row;
		// background-color: #007AFF;
		align-items: center;
		justify-content: space-between;
	}

	.food-main {
		margin-top: 10rpx;
		display: flex;
		flex-direction: row;
		align-items: center;
		// justify-content: space-between;
		padding-left: 20rpx;
		padding-right: 20rpx;
		padding-bottom: 10rpx;
		border-bottom: 2rpx solid $lienColor;

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
		display: flex;
		flex-direction: row;
		align-items: center;

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
		display: flex;
		flex-direction: row;
		align-items: center;
		color: $levelColor2;
		margin-left: 40rpx;
	}

	.food-price {
		display: flex;
		flex-direction: row;
		align-items: center;
		color: $levelColor1;
		margin-left: 40rpx;
	}
</style>
