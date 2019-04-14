Hi.vue<template>
  <div>
    <h2>{{message}}  - {{$route.params.username}} - {{$route.params.id}}</h2>
  </div>
</template>

<script>
  export default {
    name: "Hi1",
    data() {
      return {
        message: 'I am Hi1 page',
      }
    }

  }
</script>

<style scoped>

</style>
