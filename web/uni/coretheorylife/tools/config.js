const host = "http://www.coretheorylife.com/ctfbe"; //生产环境


const pro_host = "https://www.ajhroro.com/ajhzuul"; //生产环境
const pretest_host = "https://uat.ajhroro.com/roro"; //预发环境
const dev_host = "http://10.108.10.29:28060/tpsroro"; //开发环境
const test_host = "http://10.108.11.39:28060"; //测试环境
const local_host = "http://10.108.10.29:28060/tpsroro"; //本地环境


const userLogin = "/user/noauth/login";//登录
const queryList = "/business/helperApp/queryList";//靠离泊列表
const saveTime = "/business/helperApp/saveTime";//保存
const getStackScanInfo = "/business/appStackScan/getStackScanInfo"; //进出栈




module.exports = {
	host,
	pro_host,
	pretest_host,
	dev_host,
	test_host,
	local_host,
	userLogin,
	queryList,
	saveTime,
	getStackScanInfo,
}
