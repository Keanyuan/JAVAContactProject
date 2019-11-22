import util from './util.js'
// import UserUtil from '../util/UserUtil.js'
import Code from './code.js'
const md5 = require('./md5')
const config = require('./config')

const RepCode = {
	success: '000000',
}

// 展示进度条的网络请求
// url:网络请求的url
// params:请求参数
// message:进度条的提示信息
// success:成功的回调函数
// fail：失败的回调
// needLogin 是否需要跳转登录
// isNeedToken 是否需要token
const doPost = function(url, params, {
	success,
	fail,
	complete
} = {}, message, needLogin = true,isNeedToken = true) {
	// 先检查网络
	util.getNetworkState(function(res) {
		// util.showToast(res.networkType);
	});
	if (message) {
		uni.showLoading({
			title: message,
			mask: true,
		});
	}
	// 将参数克隆一份，不影响传过来的参数，不然转jsonString会影响UI显示，界面可能会卡住
	var copyParam = util.copyObj(params);
	// let time = (new Date()).valueOf().toString();
	let user = util.getStorageSync(Code.user);
	// let sign = getSign(JSON.stringify(copyParam), time, token);
	
	console.log("请求Url:" + config.host + url);

	console.log("请求参数："+ JSON.stringify(copyParam));
	
	var header = {
			// 'Content-type': 'application/json;charset=UTF-8'
		};
	if(isNeedToken){
		if(user != null){
			header["token"] = user.token;
		} else{
			util.showToast("未登录");
			util.logOut();
		}
	}
	
	
	// let postData = {
	// 	reqData: copyParam,
	// 	sign: sign,
	// 	token: token,
	// 	time: time
	// };
	
	uni.request({
		url: config.host + url,
		data: copyParam,
		header: header,
		method: 'POST',
		success: (res) => {
			console.log(res);
			if (message) {
				uni.hideLoading()
			}
			if (res.data.rspCode == RepCode.success) {
				console.log("success");
				
				success(res.data)
			} else {
				console.log("success - fail");
				
				let msg = res.data.rspMsg;
				util.showToast(msg);
				setTimeout(() => {
					if (fail != null) {
						fail(res.data)
					}
					if (res.data.rspCode == "0104" && needLogin) { // 登录过期 需要退出登录
						util.logOut();
					}
				}, 1000);
			}
		},
		fail: (res) => {
			console.log("fail");

			if (message) {
				uni.hideLoading()
			}
			let msg = res.data.rspMsg;
			util.showToast(msg);

			if (fail != null) {
				fail(res.data);
			}
		},
		complete: () => {
			console.log("complete");
			if (complete != null) {
				complete();
			}
		}
	});
}

const download = function(url, success, fail, complete) {
	uni.showLoading({
		title: '正在下载...',
		mask: true,
	})
	var downloadTask = uni.downloadFile({
		url: config.host + url,
		success: (res) => {
			if (res.statusCode === 200) {
				success(res);
			}
		},
		fail: (res) => {
			fail(res);
		},
		complete: () => {
			uni.hideLoading();
			complete();
		}
	});

	if (downloadTask == null) {
		return;
	}

	downloadTask.onProgressUpdate((res) => {
		// console.log('下载进度' + res.progress);
		// console.log('已经下载的数据长度' + res.totalBytesWritten);
		// console.log('预期需要下载的数据总长度' + res.totalBytesExpectedToWrite);

		// 测试条件，取消下载任务。
		// if (res.progress > 50) {
		// 	downloadTask.abort();
		// }
	});
}

// 复杂对象中的对象数组需要转成jsonString
const formatParam = function(param) {
	console.log("formatParam");
	Object.keys(param).forEach(function(key) {
		if (typeof param[key] == "object") {
			param[key] = JSON.stringify(param[key]);
		}
	});
	return param;
}

/**
 * 对sign进行md5加密
 *
 * @return 获取签名
 */
const getSign = function(jsonStr, time, token) {
	let baseSignMsg = 'reqData' + jsonStr + 'time' + time + 'token' + token;
	// let content = md5.hex_md5(util.encodeUtf8(baseSignMsg));
	console.log(baseSignMsg);

	let content = md5(util.encodeUtf8(baseSignMsg));
	console.log(content);
	return content;
}

export default {
	doPost,
	download,
}
