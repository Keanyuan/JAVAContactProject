<template>
  <div>
    this is my {{$route.params.pageName}}
    <!--{{msg}}-->
  </div>
</template>

<script>
  export default {
    name: "Page",
    data() {
      return {
        msg: 'this is my store.'
      }
    },
    beforeRouteEnter: (to, from, next) => {
      console.log('beforeRouteEnter');
      this.msg = to.params.pageName;
      console.log(this.msg);
      console.log('beforeRouteEnter');

      next();
    },
    beforeRouteUpdate:(to, form, next) => {
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

</style>
