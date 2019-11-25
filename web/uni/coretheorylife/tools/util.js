import codes from "./code.js"
// #ifdef APP-PLUS
import permission from "@/commons/permission.js"
// #endif

const md5 = require('./md5')
const cryptoJS = require('./crypto-js')


// 加密
const aesEncrypt = function(word) {

	//加密数据 DKFH83247FJKNDJFND为自己定义的随机字符串
	let encJson = cryptoJS.AES.encrypt(word, "CORETHRORY03KEAN").toString();
	//对加密数据进行base64处理, 原理：就是先将字符串转换为utf8字符数组，再转换为base64数据
	let encData = cryptoJS.enc.Base64.stringify(cryptoJS.enc.Utf8.parse(encJson));
	console.log(encData.toString());
	return encData.toString();
}

// 解密
// word 加密后的字符串
const aesDecrypt = function(word) {
	//将数据先base64还原，再转为utf8数据
	let decData = cryptoJS.enc.Base64.parse(word).toString(cryptoJS.enc.Utf8);
	//解密数据
	let decJson = cryptoJS.AES.decrypt(decData, "CORETHRORY03KEAN").toString(cryptoJS.enc.Utf8);
	return decJson;
}


/// 获取网络状态
const getNetworkState = function(complete) {
	uni.getNetworkType({
		success: function(res) {
			// showToast('成功');
			complete(res);
		},
		fail: function(msg) {
			showToast(msg);
		}
	});
}

/// Toast封装
const showToast = function(msg = '', duration = 2000, complete = null) {
	uni.showToast({
		icon: 'none',
		title: msg,
		duration: duration,
		success: complete

	});
}

/// 本地存储
const setStorage = function(key, data, success) {
	uni.setStorage({
		key: key,
		data: data,
		success: function() {
			if (success != null) {
				success();
			}
		},
		fail() {
			showToast('保存缓存失败');
		}
	});
}

// 获取本地数据 - 异步
const getStorage = function(key, success, fail) {
	uni.getStorage({
		key: key,
		success(res) {
			if (success != null) {
				success(res.data);
			}
		},
		fail() {
			if (fail != null) {
				fail();
			} else {
				showToast('读取缓存失败');
			}
		}
	})
}

// 获取本地数据 - 同步
const getStorageSync = function(key) {
	return uni.getStorageSync(key) == null ? "" : uni.getStorageSync(key)
}

/// 获取当前日期
/// -[fields] 有效值 year,month,day,hour,minute,second 表示选择器的粒度 默认day
const getCurrentDate = function(timeStamp = new Date(), fields = 'day') {
	let year = new Date(timeStamp).getFullYear();
	let month = new Date(timeStamp).getMonth() + 1 < 10 ? "0" + (new Date(timeStamp).getMonth() + 1) : new Date(timeStamp)
		.getMonth() + 1;
	let date = new Date(timeStamp).getDate() < 10 ? "0" + new Date(timeStamp).getDate() : new Date(timeStamp).getDate();
	let hh = new Date(timeStamp).getHours() < 10 ? "0" + new Date(timeStamp).getHours() : new Date(timeStamp).getHours();
	let mm = new Date(timeStamp).getMinutes() < 10 ? "0" + new Date(timeStamp).getMinutes() : new Date(timeStamp).getMinutes();
	let ss = new Date(timeStamp).getSeconds() < 10 ? "0" + new Date(timeStamp).getSeconds() : new Date(timeStamp).getSeconds();
	// this.nowTime = year + "年" + month + "月" + date +"日"+" "+hh+":"+mm ;
	if (fields == 'year') {
		return year;
	} else if (fields == 'month') {
		return year + "-" + month
	} else if (fields == 'day') {
		return year + "-" + month + "-" + date;
	} else if (fields == 'hour') {
		return year + "-" + month + "-" + date + " " + hh;
	} else if (fields == 'minute') {
		return year + "-" + month + "-" + date + " " + hh + ":" + mm;
	} else { // second
		return year + "-" + month + "-" + date + " " + hh + ":" + mm + ":" + ss;
	}
}


const getAfterWeekDate = function(times = new Date(), fields = 'day') {
	let timeStamp = new Date(times.getTime() + 7 * 24 * 3600 * 1000);
	let year = new Date(timeStamp).getFullYear();
	let month = new Date(timeStamp).getMonth() + 1 < 10 ? "0" + (new Date(timeStamp).getMonth() + 1) : new Date(timeStamp)
		.getMonth() + 1;
	let date = new Date(timeStamp).getDate() < 10 ? "0" + new Date(timeStamp).getDate() : new Date(timeStamp).getDate();
	let hh = new Date(timeStamp).getHours() < 10 ? "0" + new Date(timeStamp).getHours() : new Date(timeStamp).getHours();
	let mm = new Date(timeStamp).getMinutes() < 10 ? "0" + new Date(timeStamp).getMinutes() : new Date(timeStamp).getMinutes();
	let ss = new Date(timeStamp).getSeconds() < 10 ? "0" + new Date(timeStamp).getSeconds() : new Date(timeStamp).getSeconds();
	// this.nowTime = year + "年" + month + "月" + date +"日"+" "+hh+":"+mm ;
	if (fields == 'year') {
		return year;
	} else if (fields == 'month') {
		return year + "-" + month
	} else if (fields == 'day') {
		return year + "-" + month + "-" + date;
	} else if (fields == 'hour') {
		return year + "-" + month + "-" + date + " " + hh;
	} else if (fields == 'minute') {
		return year + "-" + month + "-" + date + " " + hh + ":" + mm;
	} else { // second
		return year + "-" + month + "-" + date + " " + hh + ":" + mm + ":" + ss;
	}
}

/// 拷贝对象
const copyObj = function(a) {
	var c = {};
	c = JSON.parse(JSON.stringify(a));
	return c;
}

const numToFixed = function(num){
	return Number(num.toString().match(/^\d+(?:\.\d{0,2})?/));
}



/*
 * String转UTF8
 */
const encodeUtf8 = function(text) {
	const code = encodeURIComponent(text);
	const bytes = [];
	for (var i = 0; i < code.length; i++) {
		const c = code.charAt(i);
		if (c === '%') {
			const hex = code.charAt(i + 1) + code.charAt(i + 2);
			const hexVal = parseInt(hex, 16);
			bytes.push(hexVal);
			i += 2;
		} else bytes.push(c.charCodeAt(0));
	}
	return bytes;
}
const logOut = () => {
	setStorage(codes.user, null, function() {
		uni.navigateTo({
			url: "../login/login"
		})
	});
}

/**
 * 产生随机整数，包含下限值，但不包括上限值
 * @param {Number} lower 下限
 * @param {Number} upper 上限
 * @return {Number} 返回在下限到上限之间的一个随机整数
 */
const random = function(lower, upper) {
	let q = Math.floor(Math.random() * (upper - lower)) + lower;
	console.log(q);
	return q;
}

const getMd5Info = function(randomIndex) {
	let baseSignStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ' + randomIndex;
	let content = md5(encodeUtf8(baseSignStr));
	return content;
}


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


// 四舍五入
const format45 = function(val,v2) {
    if (isNaN(val) || val == undefined || val == null) { return null; }
    return Math.round(val * v2) / v2;
}



export default {
	getNetworkState,
	showToast,
	getCurrentDate,
	copyObj,
	setStorage,
	getStorage,
	getStorageSync,
	encodeUtf8,
	logOut,
	getMd5Info,
	random,
	checkPermission,
	needLocation,
	getSetting,
	getAfterWeekDate,
	aesEncrypt,
	aesDecrypt,
	numToFixed,
	format45
}
