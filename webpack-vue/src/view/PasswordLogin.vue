<template>
  <div>
    <h1>这是密码登录组件</h1>
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
      }
    }
</script>
<style scoped>

</style>