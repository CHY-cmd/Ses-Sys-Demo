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
//查询一览
selectGeneral(currentPage,limit,generalQuaryCondition) {
  return request({
    url:`http://localhost:8081/general/selectGeneral/${currentPage}/${limit}`,
    method:'post',
    data:generalQuaryCondition,
    ...getAuthConfig()
  })
},

//删除
deleteCustByID(custId){
  return request({
    url:`http://localhost:8081/details/deleteCustByID/${custId}`,
    method:'delete',
    ...getAuthConfig()
  })
},

//查询所有客户
selectAllCustInfos(){
  return request({
    url:`http://localhost:8081/details/selectAllCustInfos`,
    method:'get',
    ...getAuthConfig()
  })
},

//客户名查信息
selectCustByNm(custNm){
  return request({
    url:`http://localhost:8081/details/selectCustByNm/${custNm}`,
    method:'get',
    ...getAuthConfig()
  })
},

}