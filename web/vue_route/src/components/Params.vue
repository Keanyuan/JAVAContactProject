<template>
    <div>
      <h1>Params Page</h1>
      <p>{{msg}} {{$route.params.newsId}} {{$route.params.newsTitle}}</p>
    </div>
</template>

<script>
    export default {
        name: "Params",
      data(){
          return {
            msg: 'Params pages',
          }
      },
      mounted(){
          console.log('mounted');
      },
      beforeRouteEnter: (to, form, next) => {
          console.log('准备进入params路由模板');
          next();
      },
      beforeRouteUpdate:(to, form, next) => {
        console.log('更新params路由模板');
        next();
      },
      beforeRouteLeave:(to, form, next) => {
        console.log('准备离开params路由模板');
        next();
      },
    }
</script>

<style scoped>

</style>
