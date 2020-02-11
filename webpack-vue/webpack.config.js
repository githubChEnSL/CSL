// 导入路径处理的模块
var path = require('path')
// 导入自动生成html文件的插件
var htmlWebpackPlugin = require('html-webpack-plugin')
// vue-loader15.x之后，需要引入VueLoaderPlugin
var VueLoaderPlugin = require('vue-loader/lib/plugin')

/*
 * 由于每一次修改代码之后，都需要手动运行webpack打包命令会比较麻烦；所有使用webpack-dev-server来实现代码实时打包编译
 * 当修改代码之后会自动进行打包构建。
 * 使用webpack运行项目，burdle.js时打包到内存中，由于需要实时的打包编译，放到内存中会非常快
 * 由于使用--contentBase指令过程比较繁琐，需要指定启动目录，同时还需要index.html的src属性；所以推荐使用html-webpack-plugin
 */

// 导出一个配置对象，将来webpack在启动的时候会默认查找webpack.config.js并读取文件中导出的配置对象，并进行打包处理
module.exports = {
  // 配置项目的入口文件
  entry : path.resolve(__dirname,'src/main.js'),
  // 配置输出选项
  output:{
    // 配置输出路径
    path:path.resolve(__dirname,'dist'),
    // 配置输出的文件名
    filename:'burdle.js'
  },
   // 配置插件
  plugins:[
    new htmlWebpackPlugin({
      // index.html(模板路径)
      template : path.resolve(__dirname,'src/index.html'),
      // 自动生成的html名称
      filename:'index.html'
    }),
    new VueLoaderPlugin()
  ],
  // 这个节点作用于第三方模块加载器
  module:{
    // 所有的第三方模块的匹配规则
    rules:[
      // 配置处理css的第三方loader
      {test:/\.css$/,use:['style-loader','css-loader']},
      // 配置处理less的第三方loader
      {test:/\.less$/,use:['style-loader','css-loader','less-loader']},
      // 配置处理scss的第三方loader
      {test:/\.scss$/,use:['style-loader','css-loader','sass-loader']},
      // 配置处理更高级的es6语法编译转换器、排除node_modules文件夹
      {test:/\.js$/,use:['babel-loader'],exclude:/node_modules/},
      // 配置vue-loader
      {test:/\.vue$/,use:'vue-loader'},
      // 配置图片路径url:limit->给定图片的大小，如果图片大于给定的值就不会转为base64的值，否则会转
      {test:/\.(jpg|png|gif|bmp|jpeg)$/,use:'url-loader?limit=7631&name=[hash:8]-[name].[ext]'},
      // 配置字体文件路径
      {test:/\.(ttf|eof|svg|woff|woff2)$/,use:'url-loader'}
    ]
  }
  // ,// 
  // resolve:{
  //   // 配置别名
  //   alias:{
  //     '@':resolve('src')
  //   }
  // }
}