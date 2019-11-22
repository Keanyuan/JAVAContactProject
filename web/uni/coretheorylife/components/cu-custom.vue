<template>
	<view>
		<view class="cu-custom" :style="[{height:CustomBar + 'px'}]">
			<view class="cu-bar fixed" :style="style" :class="[bgImage!=''?'none-bg text-white bg-img':'',bgColor]">
				<view class="action" @tap="BackPage" v-if="isBack">
					<text class="cuIcon-back"></text>
					<slot name="backText"></slot>
				</view>
				<view class="content" :style="[{top:StatusBar + 'px'}]">
					<slot name="content"></slot>
				</view>
				<slot name="right"></slot>
			</view>
		</view>
	</view>
</template>


<script>
	export default {
		data() {
			return {
				StatusBar: this.StatusBar,
				CustomBar: this.CustomBar
			};
		},
		name: 'cu-custom',
		computed: {
			style() {
				var StatusBar = this.StatusBar;
				var CustomBar = this.CustomBar;
				var bgImage = this.bgImage;
				var style = `height:${CustomBar}px;padding-top:${StatusBar}px;`;
				if (this.bgImage) {
					style = `${style}background-image:url(${bgImage});`;
				}
				return style
			}
		},
		props: {
			bgColor: {
				type: String,
				default: ''
			},
			isBack: {
				type: [Boolean, String],
				default: false
			},
			bgImage: {
				type: String,
				default: ''
			},
		},
		onLoad() {
			console.log(this.StatusBar);
			console.log(this.CustomBar);
		},
		methods: {
			BackPage() {
				uni.navigateBack({
					delta: 1
				});
			}
		}
	}
</script>

<style>
	.cu-custom {
		display: block;
		position: relative;
	}

	.cu-custom .cu-bar {
		min-height: 0px;
		/* #ifdef MP-WEIXIN */
		padding-right: 220upx;
		/* #endif */
		/* #ifdef MP-ALIPAY */
		padding-right: 150upx;
		/* #endif */
		box-shadow: 0upx 0upx 0upx;
		z-index: 9999;
	}
	.cu-bar.fixed{
		position: fixed;
		width: 100%;
		top: 0;
		z-index: 1024;
		box-shadow: 0 1upx 6upx rgba(0, 0, 0, 0.1);
	}
	.cu-bar .action {
		display: flex;
		align-items: center;
		height: 100%;
		justify-content: center;
		max-width: 100%;
	}
	
	/* #ifdef MP-ALIPAY */
	.cu-custom .cu-bar .action .cuIcon-back {
		opacity: 0;
	}
	
	/* #endif */
	

	.cu-custom .cu-bar .content {
		width: calc(100% - 440upx);
	}
</style>
