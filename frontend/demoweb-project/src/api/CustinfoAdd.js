import request from '@/utils/request'
export default{
//单独更新客户
updateCust(custInfo) {
  return request({
    url:`http://localhost:8080/details/updateCust`,
    method:'put',
    data:custInfo
  })
},

//新增
insertCustMang(custMangRequest) {
    return request({
      url:`http://localhost:8080/details/insertCustMang`,
      method:'post',
      data:custMangRequest
    })
  },

//单独新增经理
insertMang(managerCustId,mangInfo) {
  return request({
    url:`http://localhost:8080/general/insertMang/${managerCustId}`,
    method:'post',
    data:mangInfo
  })
},

//单独更新经理
updateMang(mangInfo) {
  return request({
    url:`http://localhost:8080/general/updateMang`,
    method:'put',
    data:mangInfo
  })
},

//查询银行
selectBank() {
    return request({
      url:`http://localhost:8080/details/selectBank`,
      method:'get'
    })
  },

//查客户银行
selectCustBank(){
    return request({
        url:`http://localhost:8080/details/selectCustBank`,
        method:'get'
      })
  },

//通过id查所有
selectDetailByCustId(custId) {
  return request({
    url:`http://localhost:8080/details/selectDetailByCustId/${custId}`,
    method:'get',
  })
},  
}