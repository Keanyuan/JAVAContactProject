<template>
  <div id="app">
    <!--左侧导航-->
    <leftNav></leftNav>
    <div class="main">
      <router-view/>
    </div>
  </div>
</template>

<script>
  import leftNav from '@/components/common/LeftNav'

  export default {
    name: 'App',
    components:{leftNav},
  }
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: left;
    color: #2c3e50;
    height: 100%;
  }

  .main{
    float: left;
    width: 95%;
    background-color: #eff2f7;
    height: 100%;
    overflow: auto;
  }

</style>
