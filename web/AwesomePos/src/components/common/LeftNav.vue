<template>
  <div class="lift-nav">
    <ul>
      <li>
        <router-link to="/">
          <i class="icon iconfont icon-goumai"></i>
          <div>收银</div>
        </router-link>
      </li>
      <li>
        <router-link to="/page/store">
          <i class="icon iconfont icon-dianpu"></i>
          <div>店铺</div>
        </router-link>
      </li>
      <li>
        <router-link to="/FlexBox">
          <i class="icon iconfont icon-hanbao"></i>
          <div>FlexBox</div>
        </router-link>
      </li>
      <li>
        <router-link to="/page/associator">
          <i class="icon iconfont icon-huiyuanqia"></i>
          <div>会员</div>
        </router-link>
      </li>
      <li>

        <router-link to="/page/statistics">
          <i class="icon iconfont icon-zuanshi"></i>
          <div>统计</div>
        </router-link>
      </li>
      <li>
        <router-link to="/page/setting">
          <i class="icon iconfont icon-shezhi"></i>
          <div>设置</div>
        </router-link>

      </li>
    </ul>

  </div>

</template>

<script>
  export default {
    name: "LeftNav",
    data() {
      return {};
    },

  }
</script>

<style scoped>
  .lift-nav {
    color: #fff;
    font-size: 10px;
    height: 100%;
    background-color: #1d8ce0;
    float: left;
    width: 5%;
  }

  .lift-nav ul {
    margin: 0;
    padding: 0;
  }

  .iconfont {
    font-size: 24px;
  }

  .lift-nav li {
    list-style: none;
    text-align: center;
    border-bottom: 1px solid #20a0ff;
    padding: 10px;
    cursor: pointer;

  }

  a {
    text-decoration: none;
    color: #fff;
  }

  a:link {
    text-decoration: none;
    color: #fff;
  }

  /* 指正常的未被访问过的链接*/
  a:visited {
    text-decoration: none;
    color: #fff;
  }

  /*指已经访问过的链接*/
  a:active {
    text-decoration: none;
    color: #fff;
  }

  /* 指正在点的链接*/
  a:hover {
    text-decoration: none;
    /*color: #20a0ff;*/
    color: #f0f;
  }

  /*指鼠标在链接*/
</style>
