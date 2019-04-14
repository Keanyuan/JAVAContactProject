import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

//声明一个常量
const state = {
  count: 1,
}

//mutations  改变
const mutations = {
  add(state, n) {
    if (isNaN(n)) {
      n = 1;
    }
    state.count += n;
  },
  reduce(state, n) {
    if (isNaN(n)) {
      n = 1;
    }

    state.count -= n;
  }
}

//getters计算过滤操作
const getters = {
  count: function (state) {
    return state.count += 80;
  }
}

//actions异步修改状态
const actions = {
  addAction(context) {
    context.commit('add', 10);
    setTimeout(() => {
      context.commit('add', 3);
    }, 2000);
    console.log('我比reduce提前执行');
  },
  reduceAction({commit}) {
    commit('reduce', 5);
  },
}

const modelA = {
  state,
  mutations,
  getters,
  actions,
}

//用export default 封装代码，让外部可以引用
export default new Vuex.Store({
  modules: {a: modelA},
});
