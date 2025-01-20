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
  selectBillGeneral(currentPage, limit, billQuaryCondition) {
    return request({
      url: `${BASE_URL}/bill/selectBillGeneral/${currentPage}/${limit}`,
      method: "post",
      data: billQuaryCondition,
      ...getAuthConfig(),
    });
  },

  //通过id查询
  selectBillBillDeltById(billNo) {
    return request({
      url: `${BASE_URL}/bill/selectBillBillDeltById/${billNo}`,
      method: "get",
      ...getAuthConfig(),
    });
  },

  //删除
  deleteBillById(billNo) {
    return request({
      url: `${BASE_URL}/bill/deleteBillById/${billNo}`,
      method: "delete",
      ...getAuthConfig(),
    });
  },

  //查询所有账单
  selectAllBill() {
    return request({
      url: `${BASE_URL}/bill/selectAllBill`,
      method: "get",
      ...getAuthConfig(),
    });
  },

  //导出pdf
  generateSesBillPdf(billNo) {
    return request({
      url: `${BASE_URL}/bill/generateSesBill/${billNo}`,
      method: "get",
      responseType: "blob",
      ...getAuthConfig(),
    });
  },
  
  //批量导出pdf
  generateBatchSesBillPdfs(billNos) {
    return request({
      url: `${BASE_URL}/bill/generateSesBill/billNos`,
      method: "post",
      data: billNos,
      responseType: "blob",
      ...getAuthConfig(),
    });
  },

  //新增
  insertBillBillDetl(billBillDtelRequest) {
      return request({
        url: `${BASE_URL}/bill/insertBillBillDetl`,
        method: "post",
        data: billBillDtelRequest,
        ...getAuthConfig(),
      });
    },

    //单独新增明细
    insertBillDetl(billdetlNo, billDetl) {
      return request({
        url: `${BASE_URL}/bill/insertBillDetl/${billdetlNo}`,
        method: "post",
        data: billDetl,
        ...getAuthConfig(),
      });
    },

    //单独更新明细
    updateBillDetl(billDetls) {
      return request({
        url: `${BASE_URL}/bill/updateBillDetl`,
        method: "put",
        data: billDetls,
        ...getAuthConfig(),
      });
    },

    //单独更新账单
    updateBill(bill) {
      return request({
        url: `${BASE_URL}/bill/updateBill`,
        method: "put",
        data: bill,
        ...getAuthConfig(),
      });
    },
};
