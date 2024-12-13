import request from '@/utils/request'
const BASE_URL = process.env.VUE_APP_API_URL;
const API_USERNAME = process.env.VUE_APP_API_USERNAME;
const API_PASSWORD = process.env.VUE_APP_API_PASSWORD;

const getAuthConfig = () => {
  if (API_USERNAME && API_PASSWORD) {
    return {
      auth: {
        username: API_USERNAME,
        password: API_PASSWORD
      }
    };
  }
  return {};
};

export default{
//单独更新客户
updateCust(custInfo) {
  return request({
    url:`http://localhost:8081/details/updateCust`,
    method:'put',
    data:custInfo,
    ...getAuthConfig()
  })
},

//新增
insertCustMang(custMangRequest) {
    return request({
      url:`http://localhost:8081/details/insertCustMang`,
      method:'post',
      data:custMangRequest,
      ...getAuthConfig()
    })
  },

//单独新增经理
insertMang(managerCustId,mangInfo) {
  return request({
    url:`http://localhost:8081/general/insertMang/${managerCustId}`,
    method:'post',
    data:mangInfo,
    ...getAuthConfig()
  })
},

//单独更新经理
updateMang(mangInfo) {
  return request({
    url:`http://localhost:8081/general/updateMang`,
    method:'put',
    data:mangInfo,
    ...getAuthConfig()
  })
},

//查询银行
selectBank() {
    return request({
      url:`http://localhost:8081/details/selectBank`,
      method:'get',
      ...getAuthConfig()
    })
  },

//查客户银行
selectCustBank(){
    return request({
        url:`http://localhost:8081/details/selectCustBank`,
        method:'get',
        ...getAuthConfig()
      })
  },

//通过id查所有
selectDetailByCustId(custId) {
  return request({
    url:`http://localhost:8081/details/selectDetailByCustId/${custId}`,
    method:'get',
    ...getAuthConfig()
  })
},  
}