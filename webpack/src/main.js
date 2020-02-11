// 前端项目的入口
// 引入jquery : import 接收 from "模块"
import $ from "jquery"

// 默认只能打包js类型的文件,无法处理其他类型的文件
// 如果想要处理非js文件，需要手动安装一些第三方loader加载器
// 1.打包css文件：安装style-loader和css-loader  :npm i --save-dev style-loader css-loader
// 打开webpack.config.js文件，在里边配置一个module节点

import './css/index.css'
import './css/index.less'
import './css/index.scss'

// class关键字是es6中提供的新语法，是用来实现面向对象编程方式
// es5:基于对象
class Person{
  static info={name:'张三',age:232}
}
console.log(Person.info)
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

$(function(){
  $("li:odd").css("backgroundColor","yellow")
  $("li:even").css("backgroundColor","blue")
})