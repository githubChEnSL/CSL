// 前端项目的入口
// 引入jquery : import 接收 from "模块"
// import $ from "jquery"

// 默认只能打包js类型的文件,无法处理其他类型的文件
// 如果想要处理非js文件，需要手动安装一些第三方loader加载器
// 1.打包css文件：安装style-loader和css-loader  :npm i --save-dev style-loader css-loader
// 打开webpack.config.js文件，在里边配置一个module节点

// import './css/index.css'
// import './css/index.less'
// import './css/index.scss'

// class关键字是es6中提供的新语法，是用来实现面向对象编程方式
// es5:基于对象
// class Person{
//   static info={name:'张三',age:232}
// }
// console.log(Person.info)
// 在webpack中，默认只能处理一部分的es6新语法一些更高级的es6新语法或者es7语法，webpack是处理不了的，需要第三方loader
// 通过Babel去处理ES6语法，转换成更低级的语法
// 1.安装两套包:
// ①npm i --save-dev babel-core babel-loader babel-plugin-transform-runtime
// ②npm i --save-dev babel-preset-env babel-preset-stage-0
// 打开webpack.config.js文件，在里边配置一个module节点
// {test:/\.js$/,use:['babel-loader'],exclude:/\node_modules/}
// 在项目根目录创建一个.babelrc的文件,写入配置
// {
  // "presets": ["env","stage-0"],
  // "pulgins":["transform-runtime"]
// }

// $(function(){
//   $("li:odd").css("backgroundColor","yellow")
//   $("li:even").css("backgroundColor","blue")
// })




// webpack使用vue(这里引入的vue是不完整的,至提供了runtime-only方式,没有提供网页中的其他方式)
// 使用.vue后缀需要安装两个包
// 安装loader:npm i --save-dev vue-loader
// 安装vue-template-compiler:npm i --save-dev vue-template-compiler
import Vue from 'vue'
// import login from './login.vue'
import app from "./App.vue"

// es6箭头函数
// 无参:function(){console.log('123')}   ===>  ()=>{console.log('123')}  或 ()=>console.log('123')   一行代码
// 一参:function(item){console.log(item)}  ===>  item=>{console.log(item)}  或  item=>console.log(item)
// 返回数据:function(item){return item}  ===>  item=>item
// 多参:function(item1,item2){console.log(item1,item2)} ===> (item1,item2)=>console.log(item1,item2)
import router from './router/index.js'


// 使用ui(引入)
import { Grid, GridItem} from 'vant'
Vue.use(Grid).use(GridItem)


/**
 * Vuex是一个专门为vue应用程序开发的状态管理（数据管理），模式。它采用了集中式存储管理应用的状态，并以相应的规则保证状态一种可预测的方式发生变化
 * VUEX就是配套的公用数据管理工具，它可以把一些共享的数据，保存到Vuex中，方便整个程序中的任何组件直接获取或者修改我们的公共数据
 * Vuex就是为了保存组件之间共享数据而诞生的，如果组件之间有要共享的数据，可以挂载载vuex中，而不必父组件之间传值了
 * 如果组件的数据不需要共享，此时应该放到的每个组件的data中，不要放到vuex中
 * 只有需要共享的数据，才有权力放到xuex中，组件中私有的数据，只需要放到data中即可
 * 
 * data:存放组件私有的数据
 * props:存放父组件的数据
 * vuex:存放共享数据
 * 使用场景:存放全局变量，存放登陆信息，存放三方结果的一些固定参数
 * （页面页面数据会丢失：解决->使用vuex-persistedstate）
 */
import Vuex from 'vuex'
Vue.use(Vuex)

  // 使用方式：this.$store.state.count(不建议)
const store = new Vuex.Store({
  // 相当于data
  state:{
    count:0
  },
   // 推荐使用：获取vuex里的数据 this.$store.getters.getCount
  getters:{
    getCount(){
      return store.state.count
    }
  },
  // 推荐使用:操作vuex里的数据 this.$store.commit('方法名,参数1，参数2')
  mutations:{
    setCount(state,val){
      state.count=val
    }
  }
})

var vue = new Vue({
  el : '#app',
  // 渲染页面   render:createElements =>createElements(login)
  render :c => c(app),
  router,
  store
})

