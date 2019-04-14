<template>
  <div id="app">
    <div>
      <button @click="goback"><---后退</button>
      <button @click="goForward"> 前进---> </button>
      <button @click="goHome"> 返回首页 </button>
    </div>
    <router-link to="/">home</router-link> |
    <router-link to="/params/9876/my name is kean">params</router-link> |
    <router-link to="/gohome">gohome</router-link> |
    <router-link to="/goparams/5198/I want go params.">goParams</router-link> |
    <router-link to="/hi1">Hi1</router-link> |
    <router-link to="/jspan">jspan</router-link> |
    <router-link to="/home">home1</router-link> |

    <!--
    过渡效果
fade-enter:进入过渡的开始状态，元素被插入时生效，只应用一帧后立刻删除。
fade-enter-active:进入过渡的结束状态，元素被插入时就生效，在过渡过程完成后移除。
fade-leave:离开过渡的开始状态，元素被删除时触发，只应用一帧后立刻删除。
fade-leave-active:离开过渡的结束状态，元素被删除时生效，离开过渡完成后被删除。
-->
    <transition name="fade" mode="out-in">
      <router-view/>
    </transition>

    <!--<router-view name="left" style="float: left; width: 50%;background-color: #ccc; height: 300px;"/>-->
    <!--<router-view name="right" style="float: left; width: 50%;background-color: #c0c; height: 300px;"/>-->
  </div>
</template>

<script>
export default {
  name: 'App',
  methods:{
    goback(){
      this.$router.go(-1);
    },
    goForward(){
      this.$router.go(1);
    },
    goHome(){
      this.$router.push('/');
    },
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
  .fade-enter{
    opacity: 0;
  }
  .fade-leave{
    opacity: 1;
  }
  .fade-enter-active{
    transition: opacity 0.5s;
  }
  .fade-leave-active{
    opacity: 0;
    transition: opacity 0.5s;
  }
</style>
