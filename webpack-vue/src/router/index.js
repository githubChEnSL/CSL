// 路由操作
// 安装：npm i vue-router

import Vue from 'vue'
// 引入vue-router
import VueRouter from 'vue-router'
// 手动安装vue-router
Vue.use(VueRouter)

import PasswordLogin from "../view/PasswordLogin.vue"
import SmsLogin from "../view/SmsLogin.vue"

var router =new VueRouter({
  routes:[
    {path:'/loginByPassword',component:PasswordLogin},
    {path:'/loginBySms',component:SmsLogin}
  ]
})

export default router