import request from '@/utils/request'

// 查询task列表
export function listTask(query) {
  return request({
    url: '/biz/task/list',
    method: 'get',
    params: query
  })
}

// 查询task详细
export function getTask(id) {
  return request({
    url: '/biz/task/' + id,
    method: 'get'
  })
}

// 新增task
export function addTask(data) {
  return request({
    url: '/biz/task',
    method: 'post',
    data: data
  })
}

// 修改task
export function updateTask(data) {
  return request({
    url: '/biz/task',
    method: 'put',
    data: data
  })
}

// 删除task
export function delTask(id) {
  return request({
    url: '/biz/task/' + id,
    method: 'delete'
  })
}
