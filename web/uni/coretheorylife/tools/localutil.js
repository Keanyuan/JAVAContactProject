// #ifdef APP-PLUS
import permission from "@/commons/permission.js"
// #endif



const checkPermission = async function() {
	// 判断 是 iOS 还是Android 进行检查权限状态 
	let status = permission.isIOS ? await permission.requestIOS('location') :
		await permission.requestAndroid('android.permission.ACCESS_FINE_LOCATION')
	if (status === null || status === 1) {
		status = 1;
	} else if (status === 2) {
		// 系统定位已关闭
		uni.showModal({
			content: "系统定位已关闭",
			confirmText: "设置",
			success: function(res) {
				if (res.confirm) {
					permission.gotoiOSSetting();
				}
			}
		})
	} else if (status.code) {
		// 其他错误信息
		uni.showModal({
			content: status.message
		})
	} else {
		// 其他
		uni.showModal({
			content: "需要定位权限",
			confirmText: "设置",
			success: function(res) {
				if (res.confirm) {
					permission.gotoAppSetting()
				}
			}
		})
	}
	return status;
}

//需要用户授权位置权限
const needLocation = function() {
	uni.showModal({
		content: "需要用户授权位置权限",
		confirmText: "设置",
		success: function(res) {
			if (res.confirm) {
				permission.gotoiOSSetting();
			}
		}
	})
}

// 获取用户的当前设置  非 APP H5
const getSetting = function() {
	return new Promise((resolve, reject) => {
		uni.getSetting({
			success: (res) => {
				if (res.authSetting['scope.userLocation'] === undefined) {
					resolve(0);
					return;
				}

				if (res.authSetting['scope.userLocation']) {
					resolve(1);

				} else {
					resolve(2);

				}
			}
		})
	});
}

const openSetting = function() {

	return new Promise((resolve, reject) => {
		uni.getSetting({
			success: (res) => {
				if (res.authSetting && res.authSetting['scope.userLocation']) {
					resolve(1);
				} else {
					resolve(0)
				}
			}
		})
	});
}

const getLocation = async function() {

	return new Promise(async (resolve, reject) => {

		// #ifdef APP-PLUS
		let status = await this.checkPermission();
		if (status !== 1) {
			return;
		}
		// #endif
		// #ifdef MP-WEIXIN || MP-TOUTIAO || MP-QQ
		let status1 = await this.getSetting()
		if (status1 == 2) {
			// 需要用户授权位置权限
			this.needLocation();
			return;
		}
		// #endif

		uni.showLoading({
			title: "请求中",
			mask: true,
		});

		// 处理定位方法
		uni.getLocation({
			type: 'gcj02', //返回可以用于uni.openLocation的经纬度  
			geocode: true,
			success: (res) => {
				uni.hideLoading();
				let s = {
					status: 1,
					location: {
						longitude: res.longitude,
						latitude: res.latitude
					}
				}
				
				resolve(s);
				// this.getList('refresh', pageStart);
			},
			fail: (err) => {
				uni.hideLoading();
				// #ifdef MP-BAIDU
				if (err.errCode === 202 || err.errCode === 10003) { // 202模拟器 10003真机 user deny
					this.needLocation();
					return;
				}
				// #endif
				// #ifndef MP-BAIDU
				if (err.errMsg.indexOf("auth deny") >= 0) {
					uni.showToast({
						icon: 'none',
						title: "访问位置被拒绝"
					})
				} else {
					uni.showToast({
						icon: 'none',
						title: "定位失败"
					})
				}
				// #endif
				let s = {
					status: 0,
					location: {
						longitude: 0.0,
						latitude: 0.0
					}
				}
				resolve(s);
			}
		})
	});
}



export default {
	getLocation,
	checkPermission,
	needLocation,
	openSetting,
	getSetting
}
