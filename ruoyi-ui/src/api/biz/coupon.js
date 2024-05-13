import request from '@/utils/request'

// 查询coupon列表
export function listCoupon(query) {
  return request({
    url: '/biz/coupon/list',
    method: 'get',
    params: query
  })
}

// 查询coupon详细
export function getCoupon(id) {
  return request({
    url: '/biz/coupon/' + id,
    method: 'get'
  })
}

// 新增coupon
export function addCoupon(data) {
  return request({
    url: '/biz/coupon',
    method: 'post',
    data: data
  })
}

// 修改coupon
export function updateCoupon(data) {
  return request({
    url: '/biz/coupon',
    method: 'put',
    data: data
  })
}

// 删除coupon
export function delCoupon(id) {
  return request({
    url: '/biz/coupon/' + id,
    method: 'delete'
  })
}
