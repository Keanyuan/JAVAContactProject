import Vue from 'vue'
import App from './App'

import cuCustom from "./components/cu-custom.vue"
import code from "@/tools/code.js"
import util from "@/tools/util.js"
import request from "@/tools/request.js"
import config from "@/tools/config.js"
import api from "@/tools/api.js"


Vue.component('cu-custom', cuCustom)
Vue.config.productionTip = false
// 全局挂载后使用
Vue.prototype.$request = request
Vue.prototype.$api = api
Vue.prototype.$config = config
Vue.prototype.$util = util
Vue.prototype.$code = code
App.mpType = 'app'

const app = new Vue({
	...App
})
app.$mount()
