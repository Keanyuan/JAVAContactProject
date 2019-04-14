<template>
  <div>
    <h2>{{msg}}</h2>
    <h3>{{count}}</h3>
    <h3>{{$store.state.a.count}}</h3>
    <p>
      <!--         <button @click="$store.commit('reduce')">减</button>
-->
    <p>
      <button @click='add(5)'>加5</button>
      <button @click='reduce(3)'>减3</button>
    </p>
    <p>
      <button @click='addAction'>加</button>
      <button @click='reduceAction'>减</button>
    </p>
  </div>
</template>

<script>
  import store from '@/vuex/store';
  import {mapState, mapMutations, mapGetters, mapActions} from 'vuex';

  export default {
    name: "Count",
    data() {
      return {
        msg: 'hello vuex',
      };
    },
    methods: {
      ...mapMutations(['add', 'reduce']),
      ...mapActions(['addAction', 'reduceAction']),
    },
    //通过computed的计算属性直接赋值
    // computed:{
    //     count(){
    //       return this.$store.state.count;
    //     }
    // },

    // 通过mapState的对象来赋值 引入mapState  在computed计算属性里写
    // computed:mapState({
    //   count: state => state.count,
    // }),

    //通过mapState的数组来赋值
    computed: {
      // ...mapState(['count']),
      // count() {
      //   return this.$store.getters.count;
      // },
      // ...mapGetters(['count']),
      count(){
        return this.$store.state.a.count;
      }
    },
    store,


  }
</script>

<style scoped>

</style>
