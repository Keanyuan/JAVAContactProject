<template>
  <div>
    <h2>{{msg}}</h2>
    页面不存在
  </div>
</template>

<script>
  export default {
    name: 'Error',
    data() {
      return {
        msg: 'Error: 404'
      }
    }
  }
</script>

<style scoped>

</style>
