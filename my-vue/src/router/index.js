import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    redirect:'/departmentList'
  },
   {
    path: '/departmentList',
    component: () => import('../views/department/department-list.vue')
  },
  {
    path: '/userList',
    component: () => import('../views/user/user-list.vue')
  }
]

const router = new VueRouter({
  routes
})

export default router
