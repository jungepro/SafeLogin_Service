import request from '@/utils/request'

// 查询应用信息列表
export function listSoftware(query) {
  return request({
    url: '/shuai/software/list',
    method: 'get',
    params: query
  })
}

// 查询应用信息详细
export function getSoftware(id) {
  return request({
    url: '/shuai/software/' + id,
    method: 'get'
  })
}

// 新增应用信息
export function addSoftware(data) {
  return request({
    url: '/shuai/software',
    method: 'post',
    data: data
  })
}

// 修改应用信息
export function updateSoftware(data) {
  return request({
    url: '/shuai/software',
    method: 'put',
    data: data
  })
}

// 删除应用信息
export function delSoftware(id) {
  return request({
    url: '/shuai/software/' + id,
    method: 'delete'
  })
}

// 导出应用信息
export function exportSoftware(query) {
  return request({
    url: '/shuai/software/export',
    method: 'get',
    params: query
  })
}