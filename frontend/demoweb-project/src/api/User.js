import request from "@/utils/request";
export default {
  //登录
  login(loginUser) {
    return request({
      url: `http://localhost:8080/user/login`,
      method: "post",
      data: loginUser,
    });
  },

  //用户名获取信息
  getInfo(token) {
    return request({
      url: `http://localhost:8080/user/info`,
      method: "get",
      params: { token },
    });
  },

  //查询
  selectUserInfo(currentPage, limit, userQuaryCondition) {
    return request({
      url: `http://localhost:8080/user/userInfo/${currentPage}/${limit}`,
      method: "post",
      data: userQuaryCondition,
    });
  },

  //删除
  deleteUserByID(userId){
  return request({
    url:`http://localhost:8080/user/deleteUserByID/${userId}`,
    method:'delete'
  })
},
};
