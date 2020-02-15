<template>
  <div>
    <h1>这是密码登录组件 ---count  : {{this.$store.getters.getCount}}</h1>
    <button @click="addOne">点我+1</button>
  </div>
</template>
<script>
import Vue from 'vue'
import {Toast} from 'vant'
    export default{
      data(){
        return {

        }
      },
      created(){
        Toast("欢迎进入密码登录")
      },
      methods:{
        addOne(){
          this.$store.commit('setCount',this.$store.getters.getCount+1)
        }
      }
    }
</script>
<style scoped>

</style>