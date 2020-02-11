<template>
  <div>
    <h1>这是App组件</h1>
    <!-- 
    <router-link to="loginByPassword">账密登录</router-link>
    <router-link to="loginBySms">验证码登录</router-link> 
    
    -->
    <router-view></router-view>
    <van-grid clickable :column-num="4">
      <van-grid-item icon="home-o" text="首页" to="/loginByPassword" />
      <van-grid-item icon="shop-o" text="商城" to="/loginByPassword" />
      <van-grid-item icon="chat-o" text="消息" to="/loginByPassword" />
      <van-grid-item icon="search" text="我的" to="/loginBySms"/>
    </van-grid>
    
  </div>
</template>
<script>

</script>
<style scoped>

</style>