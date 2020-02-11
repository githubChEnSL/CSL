<template>
  <div>
    <h1>这是验证码登录组件</h1>
  </div>
</template>
<script>
import Vue from 'vue'
import {Toast} from 'vant'
  export default{
    created(){
      Toast.loading({
      message: '加载中...',
      forbidClick: true
      })
    }
  }
</script>
<style scoped>

</style>