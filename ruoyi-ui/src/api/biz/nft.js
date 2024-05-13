import request from '@/utils/request'

// 查询nft列表
export function listNft(query) {
  return request({
    url: '/biz/nft/list',
    method: 'get',
    params: query
  })
}

// 查询nft详细
export function getNft(id) {
  return request({
    url: '/biz/nft/nfts/' + id,
    method: 'get'
  })
}

export function  getStaticNft() {
  return request({
    url: '/biz/nft/shredByDay',
    method: 'get'
  })

}

// 新增nft
export function addNft(data) {
  return request({
    url: '/biz/nft',
    method: 'post',
    data: data
  })
}

// 修改nft
export function updateNft(data) {
  return request({
    url: '/biz/nft',
    method: 'put',
    data: data
  })
}

// 删除nft
export function delNft(id) {
  return request({
    url: '/biz/nft/' + id,
    method: 'delete'
  })
}
