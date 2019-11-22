<template>
	<movable-area>
		<movable-view
			class="scroll"
			id="movableview"
			:style="{ height : 'calc(100vh + '+ refreshSize +'rpx)'}"
			@change="change"
			@touchend="touchend"
			direction="vertical"
			:out-of-bounds="true"
			:disabled="refreshStatus >= 3"
			:y='move'>
			<scroll-view
				class="scroll__view"
				:style="{'padding-bottom': bottomSize + 'rpx', 'padding-top': (hasTop ? refreshSize : 0) + 'rpx'}"
				:scroll-y="refreshStatus == 1"
				@scroll="scroll"
				:scroll-top="scrollTop"
				:lower-threshold="0"
				@scrolltolower="more">
				<view id="refresh"
					  :class="['scroll__refresh', successShow ? 'scroll__refresh--hidden': '' ]"
					  :style="{height: refreshSize + 'rpx' , padding: (refreshSize - 50) / 2 + 'rpx 0'}">
					<view class="la-square-jelly-box" :style="{color: color}">
						<view></view>
						<view></view>
					</view>
				</view>
				<view id="success"
					  :class="['scroll__success', successShow ? 'scroll__success--show' : '', successTran ? 'scroll__success--tran' : '']"
					  :style="{top: refreshSize - 60 + 'rpx', color: color}">
					<view>刷新成功</view>
				</view>
				
				<view id="container">
					<slot></slot>
				</view>
				
				<view v-if="listCount === 0 && emptyShow" class="scroll__empty">
					<image :src="emptyUrl"></image>
					<view>{{emptyText}}</view>
				</view>
				<view v-if="listCount !== 0" class="scroll__bottom">
					<view class="scroll__loading" v-if="end">
						<view class="text">已全部加载</view>
					</view>
					<view class="scroll__loading" v-else>
						<view class="la-line-spin-fade-rotating">
							<view></view>
							<view></view>
							<view></view>
							<view></view>
							<view></view>
							<view></view>
							<view></view>
							<view></view>
						</view>
						<view class="scroll__text">加载中...</view>
					</view>
				</view>
			</scroll-view>
		</movable-view>
	</movable-area>
</template>

<script>
	export default {
		props: {
			// 加载中
			requesting: {
				type: Boolean,
				value: false,
			},
			// 加载完毕
			end: {
				type: Boolean,
				value: false
			},
			// 控制空状态的显示
			emptyShow: {
				type: Boolean,
				value: false,
			},
			// 当前列表长度
			listCount: {
				type: Number,
				default () {
					return 0
				}
			},
			// 空状态的图片
			emptyUrl: {
				type: String,
				default () {
					return "/static/icons/wushuju.png"
				}
			},
			// 空状态的文字提示
			emptyText: {
				type: String,
				default () {
					return "未找到数据"
				}
			},
			// 是否有header
			hasTop: {
				type: Boolean,
				value: false
			},
			// 下拉刷新的高度
			refreshSize: {
				type: Number,
				default () {
					return 90
				}
			},
			// 底部高度
			bottomSize: {
				type: Number,
				default () {
					return 0
				}
			},
			// 颜色
			color: {
				type: String,
				default () {
					return "#FF3660"
				}
			},
		},
		data() {
			return {
				mode: 'refresh', // refresh 和 more 两种模式
				successShow: false, // 显示success
				successTran: false, // 过度success
				refreshStatus: 0, // 1: 下拉刷新, 2: 松开刷新, 3: 加载中, 4: 加载完成
				move: -80, // movable-view 偏移量
				scrollHeight1: 0, // refresh view 高度负值
				scrollHeight2: 0, // refresh view - success view 高度负值
				scrollTop: 0,
				oldX: 0,
			}
		},
		watch: {
			requesting(newVal, oldVal) {
				this.requestingEnd(newVal, oldVal);
			},
			listCount(newVal, oldVal) {
				if (newVal > 0) {
					this.$nextTick(() => {
						this.isFullScreen();
					});
				}
			}
		},
		mounted() {
			this.initScroll()
		},
		methods: {
			/**
			 * 处理 bindscrolltolower 失效情况
			 */
			scroll(e) {
				clearTimeout(this.timer)
				this.timer = setTimeout(() => {
					this.scrollTop = e.detail.scrollTop;
				}, 100)
			},
			/**
			 * movable-view 滚动监听
			 */
			change(e) {
				// 1: 下拉刷新, 2: 松开刷新, 3: 加载中, 4: 加载完成
				const refreshStatus = this.refreshStatus;

				// 判断如果状态大于3则返回
				if (refreshStatus >= 3) {
					return;
				}

				let diff = e.detail.y;

				if (diff > -10) {
					this.refreshStatus = 2;
				} else {
					this.oldX = diff;
					this.refreshStatus = 1;
				}
			},
			/**
			 * movable-view 触摸结束事件
			 */
			touchend(e) {
				// 1: 下拉刷新, 2: 松开刷新, 3: 加载中, 4: 加载完成
				const refreshStatus = this.refreshStatus;

				if (refreshStatus >= 3) {
					return;
				}

				if (refreshStatus == 2) {
					// 使手机发生较短时间的振动
					uni.vibrateShort();
					// 改变状态
					this.refreshStatus = 3;
					this.move = 0;
					this.mode = 'refresh';
					this.$emit('refresh');
				} else if (refreshStatus == 1) {
					this.move = this.oldX;
					this.$nextTick(() => {
						this.move = this.scrollHeight1;
					})
				}
			},
			/**
			 * 加载更多
			 */
			more() {
				if (!this.end) {
					this.mode = 'more';
					this.$emit('more');
				}
			},
			/**
			 * 监听 requesting 字段变化, 来处理下拉刷新对应的状态变化
			 */
			requestingEnd(newVal, oldVal) {
				if (this.mode == 'more') {
					return
				}

				if (oldVal === true && newVal === false) {
					this.refreshStatus = 4;
					this.move = this.scrollHeight2;

					setTimeout(() => {
						this.successShow = true;
						setTimeout(() => {
							this.successTran = true;
							this.move = this.scrollHeight1;
							setTimeout(() => {
								this.refreshStatus = 1;
								this.successShow = false;
								this.successTran = false;
								this.move = this.scrollHeight1;
							}, 350);
						}, 1000);
					}, 650);

				} else {
					if (this.refreshStatus != 3) {
						this.refreshStatus = 3;
						this.move = 0;
					}
				}
			},
			/**
			 * 计算内容高度，有没有满屏，没有满屏的话自动加载下一页
			 */
			isFullScreen() {
				let query = this.createSelectorQuery();
				let _this = this;

				query.select("#movableview").boundingClientRect();
				query.select("#container").boundingClientRect();

				query.exec(function(res) {
					if (res[0].height > res[1].height) {
						_this.more();
					}
				});
			},
			/**
			 * 初始化scroll组件参数, 动态获取 下拉刷新区域 和 success 的高度
			 */
			initScroll() {
				let query = this.createSelectorQuery();
				let _this = this;

				query.select("#refresh").boundingClientRect();
				query.select("#success").boundingClientRect();

				query.exec(function(res) {
					_this.scrollHeight1 = -res[0].height;
					_this.scrollHeight2 = res[1].height - res[0].height;
					_this.move = -res[0].height;
				});
			}

		}
	}
</script>

<style lang="scss">
	$refresh-height: 90rpx;
	$success-height: 60rpx;
	$success-top: $refresh-height - $success-height;
	$refresh-padding: ($refresh-height - 50rpx) / 2;
	
	movable-area {
		width: 100%;
		height: 100vh;
	}
	
	.scroll {
		width: 100%;
		height: calc(100vh + #{$refresh-height});
	
		&__view {
			height: 100%;
			position: relative;
			box-sizing: border-box;
		}
	
		&__success {
			position: absolute;
			z-index: 9;
			top: $success-top;
			left: 0;
			width: 100%;
			height: $success-height;
			line-height: $success-height;
			font-size: 24rpx;
			text-align: center;
			opacity: 0;
			color: $mainColor;
	
			&:after {
				content: " ";
				position: absolute;
				top: 0;
				left: 0;
				width: 100%;
				height: 100%;
				background-color: currentColor;
				opacity: 0.7;
				transform: scaleX(0);
				transition: transform 0.15s ease-in-out;
				z-index: 0;
			}
	
			& > view {
				position: absolute;
				top: 0;
				left: 0;
				width: 100%;
				height: 100%;
				z-index: 1;
				color: #ffffff;
			}
	
			&--show {
				opacity: 1;
	
				&:after {
					transform: scaleX(1);
				}
			}
	
			&--tran {
				opacity: 0;
				transition: opacity 0.3s linear;
			}
		}
	
		&__refresh {
			height: $refresh-height;
			padding: $refresh-padding 0;
			box-sizing: border-box;
	
			&--hidden {
				visibility: hidden;
			}
		}
	
		&__empty {
			padding: 30rpx;
			text-align: center;
	
			image {
				width: 200rpx;
				height: 200rpx;
				margin: 160rpx auto 60rpx;
			}
	
			view {
				color: #999999;
			}
		}
	
		&__bottom {
			height: 40rpx;
			padding: 30rpx 0;
		}
	
		&__bottom > &__loading {
			text-align: center;
			color: #999999;
			font-size: 30rpx;
		}
	
		&__bottom > &__loading > &__text {
			display: inline-block;
			vertical-align: middle;
		}
	}
	
	/* start: refresh */
	
	.la-square-jelly-box, .la-square-jelly-box > view {
		position: relative;
		box-sizing: border-box
	}
	
	.la-square-jelly-box {
		width: 50rpx;
		height: 50rpx;
		margin: 0 auto;
		margin-top: -6rpx;
		display: block;
		font-size: 0;
		color: $mainColor;
	}
	
	.la-square-jelly-box > view {
		display: inline-block;
		float: none;
		background-color: currentColor;
		opacity: 0.5;
	}
	
	.la-square-jelly-box > view:nth-child(1), .la-square-jelly-box > view:nth-child(2) {
		position: absolute;
		left: 0;
		width: 100%
	}
	
	.la-square-jelly-box > view:nth-child(1) {
		top: -25%;
		z-index: 1;
		height: 100%;
		border-radius: 10%;
		animation: square-jelly-box-animate .6s -.1s linear infinite
	}
	
	.la-square-jelly-box > view:nth-child(2) {
		bottom: -9%;
		height: 10%;
		background: #000;
		border-radius: 50%;
		opacity: .2;
		animation: square-jelly-box-shadow .6s -.1s linear infinite
	}
	
	@keyframes square-jelly-box-shadow {
		50% {
			transform: scale(1.25, 1)
		}
	}
	
	@keyframes square-jelly-box-animate {
		17% {
			border-bottom-right-radius: 10%
		}
	
		25% {
			transform: translateY(25%) rotate(22.5deg)
		}
	
		50% {
			border-bottom-right-radius: 100%;
			transform: translateY(50%) scale(1, .9) rotate(45deg)
		}
	
		75% {
			transform: translateY(25%) rotate(67.5deg)
		}
	
		100% {
			transform: translateY(0) rotate(90deg)
		}
	}
	
	/* end: refresh */
	
	/* start: more */
	.la-line-spin-fade-rotating, .la-line-spin-fade-rotating > view {
		position: relative;
		box-sizing: border-box
	}
	
	.la-line-spin-fade-rotating {
		vertical-align: middle;
		display: inline-block;
		font-size: 0;
		color: currentColor;
		margin-right: 10rpx;
	}
	
	.la-line-spin-fade-rotating > view {
		display: inline-block;
		float: none;
		background-color: currentColor;
		border: 0 solid currentColor
	}
	
	.la-line-spin-fade-rotating {
		width: 32rpx;
		height: 32rpx;
		animation: ball-spin-fade-rotating-rotate 6s infinite linear
	}
	
	.la-line-spin-fade-rotating > view {
		position: absolute;
		width: 2rpx;
		height: 8rpx;
		margin: 4rpx;
		margin-top: -4rpx;
		margin-left: 0;
		border-radius: 0;
		animation: line-spin-fade-rotating 1s infinite ease-in-out
	}
	
	.la-line-spin-fade-rotating > view:nth-child(1) {
		top: 15%;
		left: 50%;
		transform: rotate(0deg);
		animation-delay: -1.125s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(2) {
		top: 25.2512626585%;
		left: 74.7487373415%;
		transform: rotate(45deg);
		animation-delay: -1.25s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(3) {
		top: 50%;
		left: 85%;
		transform: rotate(90deg);
		animation-delay: -1.375s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(4) {
		top: 74.7487373415%;
		left: 74.7487373415%;
		transform: rotate(135deg);
		animation-delay: -1.5s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(5) {
		top: 84.9999999974%;
		left: 50.0000000004%;
		transform: rotate(180deg);
		animation-delay: -1.625s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(6) {
		top: 74.7487369862%;
		left: 25.2512627193%;
		transform: rotate(225deg);
		animation-delay: -1.75s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(7) {
		top: 49.9999806189%;
		left: 15.0000039834%;
		transform: rotate(270deg);
		animation-delay: -1.875s
	}
	
	.la-line-spin-fade-rotating > view:nth-child(8) {
		top: 25.2506949798%;
		left: 25.2513989292%;
		transform: rotate(315deg);
		animation-delay: -2s
	}
	
	@keyframes ball-spin-fade-rotating-rotate {
		100% {
			transform: rotate(360deg)
		}
	}
	
	@keyframes line-spin-fade-rotating {
		50% {
			opacity: .2
		}
	
		100% {
			opacity: 1
		}
	}
	
	
</style>
