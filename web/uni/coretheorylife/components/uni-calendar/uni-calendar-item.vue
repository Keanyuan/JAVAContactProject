<template>
	<view class="uni-calendar-item__weeks-box" :class="{
		'uni-calendar-item--disable':weeks.disable,
		'uni-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay,
		'uni-calendar-item--checked':(calendar.fullDate === weeks.fullDate && !weeks.isDay) ,
		'uni-calendar-item-border--checked':(calendar.fullDate === weeks.fullDate) ,
		'uni-calendar-item--multiple': weeks.multiple
		}"
	 @click="choiceDate(weeks)">
		<view class="uni-calendar-item__weeks-box-item">
			<!-- 日期 -->
			<text class="uni-calendar-item__weeks-box-text" :class="{
				'uni-calendar-item--isDay-text': weeks.isDay,
				'uni-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay,
				'uni-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay,
				'uni-calendar-item--multiple': weeks.multiple,
				'uni-calendar-item--disable':weeks.disable,
				}">{{weeks.date}}</text>
			<text v-if="!lunar&&!weeks.extraInfo && weeks.isDay" class="uni-calendar-item__weeks-lunar-text" :class="{
				'uni-calendar-item--isDay-text':weeks.isDay,
				'uni-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay,
				'uni-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay,
				'uni-calendar-item--multiple': weeks.multiple,
				}">今天</text>
			<text v-if="lunar&&!weeks.extraInfo" class="uni-calendar-item__weeks-lunar-text" :class="{
				'uni-calendar-item--isDay-text':weeks.isDay,
				'uni-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay,
				'uni-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay,
				'uni-calendar-item--multiple': weeks.multiple,
				'uni-calendar-item--disable':weeks.disable,
				}">{{weeks.isDay?'今天': weeks.lunar.IDayCn}}</text>
			<text v-if="weeks.extraInfo&&weeks.extraInfo.info" class="uni-calendar-item__weeks-lunar-text" :class="{
				'uni-calendar-item--extra':weeks.extraInfo.info,
				'uni-calendar-item--isDay-text':weeks.isDay,
				'uni-calendar-item--isDay':calendar.fullDate === weeks.fullDate && weeks.isDay,
				'uni-calendar-item--checked':calendar.fullDate === weeks.fullDate && !weeks.isDay,
				'uni-calendar-item--multiple': weeks.multiple,
				'uni-calendar-item--disable':weeks.disable,
				}">{{weeks.extraInfo.info}}</text>
			<view v-if="selected&&weeks.extraInfo" class="uni-calendar-item__weeks-box-circle">
				<view class="uni-calendar-item__weeks-box_child-circle"><text>{{weeks.isDay?'今天': weeks.lunar.cDay}}</text></view>
			</view>
		</view>
	</view>
</template>

<script>
	export default {
		props: {
			weeks: {
				type: Object,
				default () {
					return {}
				}
			},
			calendar: {
				type: Object,
				default: () => {
					return {}
				}
			},
			selected: {
				type: Array,
				default: () => {
					return []
				}
			},
			lunar: {
				type: Boolean,
				default: false
			}
		},
		methods: {
			choiceDate(weeks) {
				this.$emit('change', weeks)
			}
		}
	}
</script>

<style lang="scss" scoped>
	.uni-calendar-item__weeks-box {
		flex: 1;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		justify-content: center;
		align-items: center;
	}

	.uni-calendar-item__weeks-box-text {
		font-size: $uni-font-size-base;
		color: $uni-text-color;
	}

	.uni-calendar-item__weeks-lunar-text {
		font-size: $uni-font-size-sm;
		color: $uni-text-color;
	}

	.uni-calendar-item__weeks-box-item {
		position: relative;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		justify-content: center;
		align-items: center;
		width: 100rpx;
		height: 100rpx;
	}

	.uni-calendar-item__weeks-box-circle {
		position: absolute;
		top: 0;
		right: 0;
		width: 100%;
		height: 100%;
		padding: 10rpx;
	}
	.uni-calendar-item__weeks-box_child-circle{
		width: 100%;
		height: 100%;
		border-radius: 100px;
		// opacity: 0.4;
		display: flex;
		justify-content: center;
		align-items: center;
		// border: 2rpx solid #007AFF;
		color: #FFFFFF;
		background-color: $mainColor;
		// background: linear-gradient(to right, rgba(255, 54, 96, 0.8), rgba(255, 54, 96, 0.6));
		
	}

	.uni-calendar-item--disable {
		background-color: rgba(249, 249, 249, $uni-opacity-disabled);
		color: $uni-text-color-disable;
	}

	.uni-calendar-item--isDay-text {
		// color: $uni-color-primary;
	}

	.uni-calendar-item--isDay {
		// background-color: #FF3660;
		opacity: 0.8;
		color: #FF3660;
		border-radius: 100px;
	}

	.uni-calendar-item--extra {
		color: $uni-color-error;
		opacity: 0.8;
	}

	.uni-calendar-item--checked {
		// background-color: #007AFF;
		
		// color: #ebedf0;
		
		font-weight: bold;
		opacity: 0.8;
	}
	
	.uni-calendar-item-border--checked{
		width: 100%;
		height: 100%;
		border-radius: 100px;
		border: 2rpx solid $mainColor;
	}

	.uni-calendar-item--multiple {
		background-color: #FF9700;
		color: #fff;
		opacity: 0.8;
	}
</style>
