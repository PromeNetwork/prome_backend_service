import request from '@/utils/request'

// 查询账户列表
export function listAccount(query) {
  return request({
    url: '/biz/account/list',
    method: 'get',
    params: query
  })
}

export function getBlockToken(email) {
  return request({
    url:`https://api.ambrus.studio/v2/account/token/blockus?email=${email}`,
    method: 'get'
  })

}
// 查询账户详细
export function getAccount(id) {
  return request({
    url: '/biz/account/' + id,
    method: 'get'
  })
}

// 新增账户
export function addAccount(data) {
  return request({
    url: '/biz/account',
    method: 'post',
    data: data
  })
}

// 修改账户
export function updateAccount(data) {
  return request({
    url: '/biz/account',
    method: 'put',
    data: data
  })
}

// 删除账户
export function delAccount(id) {
  return request({
    url: '/biz/account/' + id,
    method: 'delete'
  })
}
