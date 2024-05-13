import request from '@/utils/request'

// 查询e4c列表
export function listE4c(query) {
  return request({
    url: '/biz/e4c/list',
    method: 'get',
    params: query
  })
}

// 查询e4c详细
export function getE4c(id) {
  return request({
    url: '/biz/e4c/' + id,
    method: 'get'
  })
}

// 新增e4c
export function addE4c(data) {
  return request({
    url: '/biz/e4c',
    method: 'post',
    data: data
  })
}

// 修改e4c
export function updateE4c(data) {
  return request({
    url: '/biz/e4c',
    method: 'put',
    data: data
  })
}

// 删除e4c
export function delE4c(id) {
  return request({
    url: '/biz/e4c/' + id,
    method: 'delete'
  })
}
