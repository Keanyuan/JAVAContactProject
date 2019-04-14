<template>
  <div class="div-body">
    this is my {{$route.params.pageName}}
    <!--{{msg}}-->
    <ul class="cookList">
      <li v-for="goods in typeGoods">
        <div class="container">
          <div class="div-top">
            <img
              src="https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=2662577439,1853685025&fm=27&gp=0.jpg"></img>
            <div class="div-radius">
              <img src="http://img5.imgtn.bdimg.com/it/u=3434217050,2664507085&fm=26&gp=0.jpg">
            </div>
          </div>
          <div class="div-bottom">
            <p class="title">{{goods.title}}</p>
            <p class="title">{{goods.subtitle}}</p>
            <div class="name">
              <span>✔{{goods.address}}</span>
              <span>👍{{goods.praise}}</span>
            </div>
          </div>
        </div>
      </li>
    </ul>

    <div id="box"></div>

  </div>
</template>

<script>
  export default {
    name: "Page",
    data() {
      return {
        msg: 'this is my store.',
        typeGoods: [
          {
            title: '阿雪平平',
            subtitle: '拉市海湿地的候鸟们',
            address: '丽江',
            praise: 131241
          },
          {
            title: '徐莲花',
            subtitle: '天山上的珍惜雪莲',
            address: '天山',
            praise: 24124
          },
        ]
      }
    },
    beforeRouteEnter: (to, from, next) => {
      console.log('beforeRouteEnter');
      this.msg = to.params.pageName;
      console.log(this.msg);
      console.log('beforeRouteEnter');

      next();
    },
    beforeRouteUpdate: (to, form, next) => {
      console.log('beforeRouteUpdate');
      this.msg = 'this is my ' + to.params.pageName + '.';
      console.log(this.msg);
      console.log('beforeRouteUpdate');
      next();
    },
    beforeCreate: function () {
      console.log('1- beforeCreate 初始化之后 ' + this.msg)
    },
    created: function () {
      console.log('2-created 创建完成 ' + this.msg);
    },
    beforeMount: function () {
      console.log('3-beforeMount 挂载之前 ' + this.msg);

    },
    mounted: function () {
      console.log('4-mounted 被创建 ' + this.msg);
    },
    beforeUpdate: function () {
      console.log('5-beforeUpdate 数据更新前 ' + this.msg);

    },
    updated: function () {
      console.log('6-updated 被更新后 ' + this.msg);

    },
    activated: function () {
      console.log('7-activated 激活 ' + this.msg);

    },
    deactivated: function () {
      console.log('8-deactivated 停用 ' + this.msg);

    },
    beforeDestroy: function () {
      console.log('9-beforeDestroy 销毁之前 ' + this.msg);

    },
    destroyed: function () {
      console.log('10-destroyed 销毁之后 ' + this.msg)

    }
  }
</script>

<style scoped>
  /*伪元素清理浮动*/
  .clearfix::after {
    /*overflow: hidden;*/
    content: '';
    height: 0;
    visibility: hidden;
    clear: both;
  }
  .cookList li{
    list-style: none;
    float: left;
    overflow: hidden;
    margin: 2px;
    cursor: pointer;
  }

  .container {
    width: 200px;
    height: 260px;
    margin-left: 30px;
    margin-top: 30px;
    position: relative;
    background-color: white;
  }

  .div-top {
    width: 200px;
    height: 200px;
    background-color: white;
    border-radius: 10px;
    position: relative;
    z-index: 1000;
  }

  .div-top > img {
    width: 200px;
    /*border-radius: 40px;*/
  }

  .div-radius {
    width: 50px;
    height: 50px;
    /*border-radius: 50px;*/
    position: absolute;
    bottom: -20px;
    left: 10px;
    background-color: rebeccapurple;

  }

  .div-radius img {
    width: 50px;
    height: 50px;
    border-radius: 50px;
  }

  /*.div-radius img{*/
  /*width: 50px;*/
  /*height: 50px;*/
  /*clip: rect(0px, 0px, 50px, 50px);*/
  /*position: absolute;*/
  /*}*/
  .div-bottom {
    width: 200px;
    height: 60px;
    position: absolute;
    bottom: 0px;
    /*background-color: rebeccapurple;*/
    text-align: center;
  }

  .div-bottom .title {
    font-size: 10px;
    margin: 0px;
    padding: 0px;
  }

  .div-bottom .name {
    font-size: 10px;
  }

</style>
