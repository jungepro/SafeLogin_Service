import request from '@/utils/request'

// 查询厂家信息列表
export function listFactory(query) {
  return request({
    url: '/shuai/factory/list',
    method: 'get',
    params: query
  })
}

// 查询厂家信息详细
export function getFactory(id) {
  return request({
    url: '/shuai/factory/' + id,
    method: 'get'
  })
}

// 新增厂家信息
export function addFactory(data) {
  return request({
    url: '/shuai/factory',
    method: 'post',
    data: data
  })
}

// 修改厂家信息
export function updateFactory(data) {
  return request({
    url: '/shuai/factory',
    method: 'put',
    data: data
  })
}

// 删除厂家信息
export function delFactory(id) {
  return request({
    url: '/shuai/factory/' + id,
    method: 'delete'
  })
}

// 导出厂家信息
export function exportFactory(query) {
  return request({
    url: '/shuai/factory/export',
    method: 'get',
    params: query
  })
}