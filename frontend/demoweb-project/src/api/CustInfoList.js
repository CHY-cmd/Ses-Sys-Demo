import request from '@/utils/request'
export default{
//查询一览
selectGeneral(currentPage,limit,generalQuaryCondition) {
  return request({
    url:`http://localhost:8080/general/selectGeneral/${currentPage}/${limit}`,
    method:'post',
    data:generalQuaryCondition
  })
},

//删除
deleteCustByID(custId){
  return request({
    url:`http://localhost:8080/details/deleteCustByID/${custId}`,
    method:'delete'
  })
},

}