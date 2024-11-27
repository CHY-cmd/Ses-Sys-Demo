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
  //查询
  selectStaffInfo(currentPage, limit, staffQuaryCondition) {
    return request({
      url: `${BASE_URL}/staff/staffInfo/${currentPage}/${limit}`,
      method: "post",
      data: staffQuaryCondition,
      ...getAuthConfig(),
    });
  },

  //删除
  deleteStaffByID(staffId) {
    return request({
      url: `${BASE_URL}/staff/deleteStaffByID/${staffId}`,
      method: "delete",
      ...getAuthConfig(),
    });
  },

  //新增
//   insertUser(user) {
//     return request({
//       url: `${BASE_URL}/user/insertUser`,
//       method: "post",
//       data: user,
//       ...getAuthConfig(),
//     });
//   },

  //更新
//   updateUserByUserId(user) {
//     return request({
//       url: `${BASE_URL}/user/updateUserByUserId`,
//       method: "put",
//       data: user,
//       ...getAuthConfig(),
//     });
//   },
};
