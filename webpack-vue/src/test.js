// 这个是Node中向外暴露成员的方式
// module.exports = {}

// 在ES6中，规定了ES6如何导入和导出模块
// ES6中：import 模块名称 from '模块标识符' 或 import '模块路径' 两种方式导入模块
// ES6中，使用export default和export向外暴露成员

var info = {
  name:'张三',
  age:23
}

var heihei = {
  num:1,
  sre:"aha?"
}
// 注意：export default向外暴露的成员可以用任意的变量接收
// 注意：在一个模块中，export default只允许向外暴露一次
// 注意：在一个模块中，可同时使用export default和export
export default {info,heihei}

export var title = '标题'
export var content = "哈哈哈哈"

// 注意：使用export向外暴露成员，只能使用{}，形式去接收，叫做按需引入
// 注意：export可以向外暴露多个成员，如果有不使用的某些成员，在inport的时候，可以不在{}中定义
// 注意：使用export导出的成员：必须严格按照export导出时候的名称来使用{}接收
// 注意：使用export导出的成员，如果想换个名字接收，可以使用 as 别名

// Node中引入使用的是var 名称 =require('模块标识符')