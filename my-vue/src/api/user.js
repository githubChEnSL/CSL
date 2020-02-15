import request from '../utils/request'
const group_name = '/user'
export default {
  userList(page){
    return request({
      url : `${group_name}/page`,
      method : 'post',
      data:page
    })
  },
  update(user){
    return request({
      url: `${group_name}/update`,
      method : 'put',
      data : user
    })
  },
  deleteById(id){
    return request({
      url: `${group_name}/delete/${id}`,
      method : 'delete',
    })
  },
  save(user){
    return request({
      url: `${group_name}/save`,
      method : 'post',
      data : user
    })
  },
  getbyId(id){
    return request({
      url: `${group_name}/get/${id}`,
      method : 'get',
    })
  }
}