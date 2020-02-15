import Vue from 'vue'
import App from '@/App'
import router from '@/router'

import ElementUI from 'element-ui'
import "element-ui/lib/theme-chalk/index.css"

Vue.config.productionTip = false
Vue.use(ElementUI)


// 使用vuex
import Vuex from 'vuex'
Vue.use(Vuex)
const store=new Vuex.Store({
  state:{
    departmentList : []
  },
  mutations:{
    // 规范（全部大下划线规则）
    SET_DEPARTMENT_LIST(state,val){
      state.departmentList=val
    }
  },
  getters:{
    getDepartmentList(){
      return store.state.departmentList
    }
  }
})

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')
