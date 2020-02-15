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
    component: () => import('@/views/department/department-list')
  },
  {
    path: '/userList',
    component: () => import('@/views/user/user-list')
  },
  {
    path:'/userAdd',
    component:()=>import('@/views/user/user-add')
  }
]

const router = new VueRouter({
  routes
})

export default router
