<template>
	<view class="content">
		<image src="../../static/icons/icon_login_logo.png" class="header-img" mode="aspectFit"></image>
		<view class="list">
			<view class="list-call">
				<image class="img" src="../../static/icons/login_phone.png"></image>
				<input class="biaoti" v-model="phoneno" type="number" maxlength="11" placeholder="请输入手机号" />
			</view>
			<view class="list-call">
				<image class="img" src="../../static/icons/login_verify.png"></image>
				<input class="biaoti" v-model="verco" type="text" placeholder="请输入图形验证码" />
				<image class="vercoimg" :src="vercoimgSrc" @click="getRandomPicVercode"></image>
			</view>
			<view class="list-call">
				<image class="img" src="../../static/icons/login_verify.png"></image>
				<input class="biaoti" v-model="vernum" type="text" maxlength="6" placeholder="请输入验证码" />
				<view class="yzm" :class="{ yzms: second>0 }" @tap="getcode">{{yanzhengma}}</view>
			</view>
		</view>

		<view :class="['dlbutton', isShow ? 'normal-bgColor' : 'none-bgColor']" :hover-class="isShow ? 'dlbutton-hover' : 'none-bgColor'"
		 @tap="bindLogin">
			<text>确定</text>
		</view>
		<text class="text-font-size-10 text-level-two-color margin-top-20">点击确定，即表示已阅读并同意《注册会员服务条款》</text>
	</view>
</template>

<script>
	var timer_js, mself;

	export default {
		data() {
			return {
				phoneno: '',
				verco: '',
				vernum: '',
				baseUrl: "http://www.coretheorylife.com/ctfbe/user/verificationCode?codeId=",
				codeId: "",
				vercoimgSrc: "",
				second: 0,
			}
		},
		computed: {
			yanzhengma() {
				if (this.second == 0) {
					return '获取验证码';
				} else {
					if (this.second < 10) {
						return '重新获取0' + this.second;
					} else {
						return '重新获取' + this.second;
					}
				}
			},
			isShow() {
				if (this.phoneno.length > 0 && this.verco.length > 0 && this.vernum.length > 0) {
					return true
				} else {
					return false;
				}
			}
		},
		onLoad() {
			mself = this;
			this.getRandomPicVercode();
		},
		onUnload() {
			clearInterval(timer_js)
			this.second = 0;
		},
		methods: {
			getRandomPicVercode() {
				this.codeId = this.getRandomMd5()
				this.vercoimgSrc = this.baseUrl + this.codeId;
			},
			getRandomMd5() {
				let randomIndex = this.$util.random(100000, 100000000);
				let r = this.$util.getMd5Info(randomIndex)
				return r
			},
			getcode() {
				if (this.second > 0) {
					return;
				}

				this.$api.getSendIdenfity({
					mobile: this.phoneno,
					randCode: this.verco,
					codeId: this.codeId,
					success: res => {
						console.log(res);
						this.second = 60;
						timer_js = setInterval(function() {
							mself.second--;
							if (mself.second == 0) {
								clearInterval(timer_js)
							}
						}, 1000)
					}
				})
			},
			bindLogin() {				
				this.$api.userLogin({
					mobile: this.phoneno,
					operPassword: this.vernum,
					success: (res)=>{
						this.$util.setStorage(this.$code.user, res.data, ()=>{
							uni.navigateBack()
						})
					}
				})
			}
		}
	}
</script>

<style>
	.content {
		background-color: #FFFFFF;
		height: 100vh;
		width: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
	}

	.header-img {
		padding-top: 20rpx;
		width: 200rpx;
		height: 200rpx;
		/* background:rgba(63,205,235,1); */
		/* box-shadow:0upx 12upx 13upx 0upx rgba(63,205,235,0.47); */
	}

	.list {
		display: flex;
		width: 100%;
		flex-direction: column;
		padding-top: 50upx;
		padding-left: 70upx;
		padding-right: 70upx;
	}

	.list-call {
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		height: 100upx;
		color: #333333;
		border-bottom: 1upx solid rgba(230, 230, 230, 1);
	}

	.list-call .img {
		width: 40upx;
		height: 40upx;
	}

	.list-call .biaoti {
		flex: 1;
		text-align: left;
		font-size: 32upx;
		line-height: 100upx;
		margin-left: 16upx;
	}

	.list-call .vercoimg {
		width: 240upx;
		height: 60upx;
	}

	.yzm {
		color: #FF3660;
		font-size: 24upx;
		line-height: 64upx;
		padding-left: 20upx;
		padding-right: 20upx;
		width: auto;
		height: 64upx;
		/* border:1upx solid #FFA800; */
		/* border-radius: 50upx; */
	}

	.yzms {
		color: #999999 !important;
		/* border:1upx solid #999999; */
	}

	
</style>
