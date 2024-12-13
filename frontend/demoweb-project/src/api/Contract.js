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
  selectContrGeneral(currentPage, limit, contractQuaryCondition) {
    return request({
      url: `${BASE_URL}/contract/selectContrGeneral/${currentPage}/${limit}`,
      method: "post",
      data: contractQuaryCondition,
      ...getAuthConfig(),
    });
  },

  //通过id查询
  selectContrOperById(contrNo) {
    return request({
      url: `${BASE_URL}/contract/selectContrOperById/${contrNo}`,
      method: "get",
     ...getAuthConfig(),
    });
  },

  //删除
  deleteContrById(contrNo) {
    return request({
      url: `${BASE_URL}/contract/deleteContrById/${contrNo}`,
      method: "delete",
      ...getAuthConfig(),
    });
  },

  //新增
//   insertStaff(staff) {
//     return request({
//       url: `${BASE_URL}/staff/insertStaff`,
//       method: "post",
//       data: staff,
//       ...getAuthConfig(),
//     });
//   },

  //更新
//   updateStaffById(staff) {
//     return request({
//       url: `${BASE_URL}/staff/updateStaffById`,
//       method: "put",
//       data: staff,
//       ...getAuthConfig(),
//     });
//   },
};
