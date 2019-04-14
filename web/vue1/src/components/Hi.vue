<template>
  <div>
    <h2>{{message}}</h2>
    <router-view></router-view>
  </div>
</template>

<script>
  export default {
    name: "Hi",
    data() {
      return {
        message: 'I am hi page',
      }
    }

  }
</script>

<style scoped>

</style>
