import request from "@/utils/request";
const BASE_URL = process.env.VUE_APP_API_URL;
const API_USERNAME = process.env.VUE_APP_API_USERNAME;
const API_PASSWORD = process.env.VUE_APP_API_PASSWORD;

const getAuthConfig = () => {
  if (API_USERNAME && API_PASSWORD) {
    return {
      auth: {
        username: API_USERNAME,
        password: API_PASSWORD,
      },
    };
  }
  return {};
};

export default {
  //登录
  login(loginUser) {
    return request({
      url: `${BASE_URL}/user/login`,
      method: "post",
      data: loginUser,
      ...getAuthConfig(),
    });
  },

  //token获取信息
  getInfo(token) {
    return request({
      url: `${BASE_URL}/user/info`,
      method: "get",
      params: { token },
      ...getAuthConfig(),
    });
  },

  //查询
  selectUserInfo(currentPage, limit, userQuaryCondition) {
    return request({
      url: `${BASE_URL}/user/userInfo/${currentPage}/${limit}`,
      method: "post",
      data: userQuaryCondition,
      ...getAuthConfig(),
    });
  },

  //删除
  deleteUserByID(userId) {
    return request({
      url: `${BASE_URL}/user/deleteUserByID/${userId}`,
      method: "delete",
      ...getAuthConfig(),
    });
  },

  //新增
  insertUser(user) {
    return request({
      url: `${BASE_URL}/user/insertUser`,
      method: "post",
      data: user,
      ...getAuthConfig(),
    });
  },

  //通过id查询
  selectUserById(userId) {
    return request({
      url: `${BASE_URL}/user/selectUserById/${userId}`,
      method: "get",
      ...getAuthConfig(),
    });
  },

  //更新
  updateUserByUserId(user) {
    return request({
      url: `${BASE_URL}/user/updateUserByUserId`,
      method: "put",
      data: user,
      ...getAuthConfig(),
    });
  },

  //修改密码
  updatePwdByUserId(user) {
    return request({
      url: `${BASE_URL}/user/updatePwdByUserId`,
      method: "put",
      data: user,
      ...getAuthConfig(),
    });
  },

  //锁定账户
  updateUserLockFlg(userMailaddress) {
    return request({
      url: `${BASE_URL}/user/updateUserLockFlg`,
      method: "put",
      params: userMailaddress,
      ...getAuthConfig(),
    });
  },
};
