// vue.config.js 配置说明
// 这里只列一部分，具体配置参考文档
module.exports = {
  // baseUrl : '/'
  // 将部署应用程序的基本Url
  // 默认情况下，Vue Cli假设您的应用将部署在根目录下。
  // https://www.my-app.com/.如果应用程序部署在子路径上。则需要使用此选项指定子路径，例如：...app.com/,集baseUrl到'/my-app/'
  // baseUrl : '/',
  // lintOnSave:{type:Boolean default:true}是否使用eslint
  lintOnSave: false,
  // devServer:{type:Object}三个属性host,port,https
  
  productionSourceMap:false,
  // 它支持webPack-dev-server的所有选项
  devServer:{
    port: 8085,//端口号
    // https:false,//https:{type:Boolean}
    open:true,//配置自动启动浏览器
    // proxy:"http://localhost:8080", //配置跨域处理，只有一个代理
    proxy:{
      '/':{
        target:'http://localhost:8080',
        ws:true,
        changeOrigin:true
      },
      '/foo':{
        target: '<other_url>'
      }
    }
  }
}