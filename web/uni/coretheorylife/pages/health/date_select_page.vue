<template>
	<view class="main">
		<view v-if="showCalendar">
			<!-- 插入模式 -->
			<uni-calendar :selected="dateInfo.selected" @change="calenderChange" :startDate="dateInfo.startDate" :endDate="dateInfo.endDate"
			 :lunar="dateInfo.lunar" />
		</view>
		<view class="check-food">
			<data-select-item :selected="false" @normalClick="normalClick(1)" @selectClick="selectClick(1)" @cancleClick="cancleClick(1)"></data-select-item>
			<data-select-item :selected="false" @normalClick="normalClick(2)" @selectClick="selectClick(2)" @cancleClick="cancleClick(2)"></data-select-item>
			<data-select-item :selected="true" @normalClick="normalClick(3)" @selectClick="selectClick(3)" @cancleClick="cancleClick(3)"></data-select-item>
		</view>
		<cart-item :goodsNum="0" :fee="10.0" :price="193.09"></cart-item>
	</view>
</template>

<script>
	import uniCalendar from '@/components/uni-calendar/uni-calendar.vue'

	import dataSelectItem from '@/pages/health/items/data-select-item.vue'

	import cartItem from '@/components/cart-item.vue'

	export default {
		components: {
			uniCalendar,
			dataSelectItem,
			cartItem
		},
		data() {
			return {
				showCalendar: false,
				selectDate: '',
				storeInfoModelStr: '',
				storeInfoModel: '',
				healthDateList: [],
				dateInfo: {
					date: '',
					startDate: '',
					endDate: '',
					lunar: true,
					range: true,
					insert: false,
					selected: [],
				}
			}
		},
		onLoad(options) {
			if (options && options.item) {
				let item = this.$util.aesDecrypt(options.item);
				console.log(item);
				this.storeInfoModelStr = options.item;
				// this.storeInfoModel = JSON.parse(decodeURIComponent(item));
				this.storeInfoModel = JSON.parse(item);
				console.log(this.storeInfoModel);
				this.initDateTime()
			}
		},
		onShow() {

		},
		methods: {
			calenderChange(e) {
				this.selectDate = e.fulldate;
				if (e.fulldate == '2019-11-27') {
					let isTrue = false;
					for (var index in this.dateInfo.selected) {
						if (this.dateInfo.selected[index].date == e.fulldate) {
							isTrue = true;
						}
					}
					if (!isTrue) {
						this.dateInfo.selected.push({
							date: e.fulldate,
						})
					}
				}
				console.log(this.dateInfo);
			},
			initDateTime() {
				let nowDate = this.$util.getCurrentDate()
				let endDate = this.$util.getAfterWeekDate()
				this.$nextTick(() => {
					this.dateInfo.date = nowDate;
					this.dateInfo.startDate = nowDate;
					this.dateInfo.endDate = endDate;
					this.selectDate = nowDate;
					let timeStamp = new Date((new Date()).getTime() + 3 * 24 * 3600 * 1000);
					let selectDate = this.$util.getCurrentDate(timeStamp)
					this.dateInfo.selected.push({
						date: nowDate
					})
					this.dateInfo.selected.push({
						date: selectDate
					})

					this.showCalendar = true
				})
			},
			// 默认点击
			normalClick(canId) {
				console.log("未选择" + canId + ' ' + this.selectDate);
				this.checkCanInfo(canId)
			},
			// 已选择点击
			selectClick(canId) {
				console.log("已选择" + canId + ' ' + this.selectDate);
				this.checkCanInfo(canId)
			},
			// 取消点击
			cancleClick(canId) {
				console.log("取消" + canId + ' ' + this.selectDate);
			},
			checkCanInfo(canId) {
				var dateItem;
				for (let di in this.healthDateList) {
					if (this.healthDateList[di].date == this.selectDate) {
						dateItem = this.healthDateList[di];
						break;
					}
				}

				var canItem;
				if (dateItem) {
					for (let ci in dateItem.canItem) {
						if (dateItem.canItem[ci].canId == canId) {
							canItem = dateItem.canItem[ci];
							break;
						}
					}
				}
				var item = {}
				if (canItem) {
					item.canItem = canItem;
				}
				switch (canId) {
					case 1:
						item.canName = "早餐";
						break;
					case 2:
						item.canName = "午餐";
						break;
					case 3:
						item.canName = "晚餐";
						break;
					default:
						break;
				}
				item.canId = canId;
				item.date = this.selectDate;
				item.storeInfoModel = this.storeInfoModel;

				var item = JSON.stringify(item);
				item = this.$util.aesEncrypt(item);
				uni.navigateTo({
					url: './health_shop?item=' + item
				})
			}



		}
	}
</script>

<style lang="scss" scoped>
	.check-food {
		display: flex;
		flex-direction: row;
		justify-content: center;
	}

	.main {
		height: 100vh;
		width: 100%;


	}
</style>
