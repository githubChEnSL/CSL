import axios from 'axios'
import {Message} from 'element-ui'
const service =axios.create({
  baseURL:'/',
  timeout:10000//默认请求时间
})

// request 拦截器:可以在请求发送前进行一些处理  例：在请求头上加token;对请求参数进行统一加密等等
service.interceptors.request.use(
  config => {
    // console.log(config)
    return config
  },
  error => {
    return Promise.reject(error)
  }
)

// response 拦截器：可以在接口响应之后页面获取到响应结果之前对响应结果进行统一处理 例：对返回结果进行解密等等
service.interceptors.response.use(
  response => {
    const res=response.data
    if(res.code === 200){
      // code为200表示接口是通的，直接返回res
      Message({
        type:'success',
        message:res.msg
      })
      return res
    }else{
      // else:非200的情况表示接口异常
      Message({
        type:'error',
        message:res.msg
      })
    }
  },
  // http请求报错的情况下
  error => {
    Message({
      type:'error',
      message:'系统异常，请及时联系管理员！'
    })
  }
)
export default service