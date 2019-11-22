import request from "./request.js"

//获取验证码
const getSendIdenfity = ({
	mobile,
	randCode,
	codeId,
	success,
	fail,
	complete
} = {}) => {
	let params = {
		"mobile": mobile,
		"randCode": randCode,
		"codeId": codeId,
	};
	request.doPost("/user/sendIdenfity", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "请求中……", false, false)
}



//登录
const userLogin = ({
	mobile,
	operPassword,
	success,
	fail,
	complete
} = {}) => {
	let params = {
		"mobile": mobile,
		"identity": operPassword
	};

	request.doPost("/user/login", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "登陆中……", false, false)
}


//健身店铺列表
const queryHealthShopListByName = ({
	shopName,
	latitude,
	longitude,
	success,
	fail,
	complete
} = {}) => {
	let params = {
		"shopName": shopName,
		"latitude": latitude,
		"longitude": longitude
	};
	request.doPost("/sportShop/queryShopListByName", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "", false, false)
}

//健身店铺列表
const queryShopListByName = ({
	shopName,
	latitude,
	longitude,
	success,
	fail,
	complete
} = {}) => {
	let params = {
		"shopName": shopName,
		"latitude": latitude,
		"longitude": longitude
	};
	request.doPost("/shop/queryShopListByName", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "", false, false)
}


//首页banner
const queryBannerList = ({
	success,
	fail,
	complete
} = {}) => {
	let params = {};
	request.doPost("/banner/queryBannerList", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "", false, false)
}



//店铺详细信息
const queryById = ({
	id,
	success,
	fail,
	complete
} = {}) => {
	let params = {
      "id": id,
    };
	request.doPost("/sportShop/queryById", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "", false, false)
}


//食品列表
const queryFoodTree = ({
	shopId,
	success,
	fail,
	complete
} = {}) => {
	let params = {
      "shopId": shopId,
    };
	request.doPost("/food/queryFoodTree", params, {
		success: success,
		fail: fail,
		complete: complete
	}, "", false, false)
}



module.exports = {
	getSendIdenfity,
	userLogin,
	queryHealthShopListByName,
	queryShopListByName,
	queryBannerList,
	queryById,
	queryFoodTree
}
