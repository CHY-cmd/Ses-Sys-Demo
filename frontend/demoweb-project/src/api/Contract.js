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
  insertContrOper(contrOperRequest) {
    return request({
      url: `${BASE_URL}/contract/insertContrOper`,
      method: "post",
      data: contrOperRequest,
      ...getAuthConfig(),
    });
  },

  //单独新增工程师
  insertOper(operNo, operate) {
    return request({
      url: `${BASE_URL}/contract/insertOper/${operNo}`,
      method: "post",
      data: operate,
      ...getAuthConfig(),
    });
  },

  //单独更新工程师
  updateOper(operates) {
    return request({
      url: `${BASE_URL}/contract/updateOper`,
      method: "put",
      data: operates,
      ...getAuthConfig(),
    });
  },

  //单独更新合同
  updateContr(contract) {
    return request({
      url: `${BASE_URL}/contract/updateContr`,
      method: "put",
      data: contract,
      ...getAuthConfig(),
    });
  },
};
