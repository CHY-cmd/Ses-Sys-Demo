<template>
   <v-app>
      <navigation-drawer>
         <div>
            <div>
               <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                  <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">取引先情報詳細
                  </v-app-bar-title>
                  <div style="color: aliceblue;">{{ username }}</div>
               </v-app-bar>
            </div>
            <div
               style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);height: 100px;margin-top: 64px;">
               <v-btn class="btn" @click="toUpdate">更新へ</v-btn>
               <v-btn class="btn" @click="toList">キャンセル</v-btn>
            </div>
            <div style="background-color: rgb(191, 191, 191);margin: 0px;">
               <v-container>
                  <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">基本情報</p>
               </v-container>
            </div>

            <!-- 基本情報 -->
            <div style="height: 500px;width: auto;background-color:rgb(242, 242, 242);">
               <v-container fluid style="margin-left: 90px;padding-top: 0px;">
                  <v-row style="margin-top: 0px;">
                     <v-col cols="5">
                        <v-row>
                           <v-col cols="3"
                              style="background-color: rgb(217, 217, 217);margin: 0px;"><span>取引先名</span><span
                                 style="color:brown;font-size: 20px;">*</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custNm }}</span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span>カナ</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custKn }}</span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>Webサイト</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custWeb }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>郵便番号</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custPostno }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>住所</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custAddress }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>電話番号</span></v-col>
                           <v-col cols="8"><span>{{ formatPhoneNumber(custMangRequest.custInfo.custPhone) }}
                              </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>FAX</span></v-col>
                           <v-col cols="8"><span>{{ formatFaxNumber(custMangRequest.custInfo.custFax) }} </span></v-col>
                        </v-row>
                     </v-col>
                     <v-col></v-col>
                     <v-col cols="6">
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>資本金</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custMoney }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>取引先種別</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custType }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>取引開始日</span></v-col>
                           <v-col cols="8"><span>{{ formatDate(custMangRequest.custInfo.custStartdt) }} </span>
                           </v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>登録番号</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custInvoiceNo }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>Pマーク/ISMS</span></v-col>
                           <v-col cols="8">
                              <span>{{ turnBoolean(custMangRequest.custInfo.custInforSecurity) }} </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>派遣資格</span></v-col>
                           <v-col cols="8"> <span>{{ turnBoolean(custMangRequest.custInfo.custDispatch) }}
                              </span></v-col>
                        </v-row>
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>自社担当者</span></v-col>
                           <v-col cols="8"><span>{{ custMangRequest.custInfo.custCompanyrep }} </span></v-col>
                        </v-row>
                     </v-col>
                     <v-col cols="5">
                        <v-row>
                           <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>備考</span></v-col>
                           <v-col cols="8" style="margin-left: 30px;"><span>{{ custMangRequest.custInfo.custRmk1
                                 }}</span></v-col>
                        </v-row>
                     </v-col>
                  </v-row>
               </v-container>
            </div>
         </div>

         <div style="background-color: rgb(191, 191, 191);margin: 0px;">
            <v-container>
               <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">担当者</p>
            </v-container>
         </div>

         <!-- 担当者 -->
         <div style="background-color:rgb(242, 242, 242) ;">
            <v-container fluid style="margin-left: 80px;">
               <v-row style="height: 50px;">
                  <v-col class="d-flex justify-center align-center"><span>担当者名</span><span
                        style="color:brown;font-size: 20px;">*</span></v-col>
                  <v-col class="d-flex justify-center align-center">
                     <p>電話番号</p>
                  </v-col>
                  <v-col class="d-flex justify-center align-center">
                     <p>メール</p>
                  </v-col>
                  <v-col class="d-flex justify-center align-center">
                     <p>役職</p>
                  </v-col>
                  <v-col class="d-flex justify-center align-center">
                     <p>備考</p>
                  </v-col>
               </v-row>
               <v-row>
                  <hr style="width: 1350px;margin: 10px;">
               </v-row>
               <v-row v-for="(row, index) in custMangRequest.mangInfo" :key="index" style="margin-left: 18px;">
                  <v-col><span>{{ row.managerNm }}</span></v-col>
                  <v-col><span>{{ formatPhoneNumber(row.managerPno) }}</span></v-col>
                  <v-col><span>{{ row.managerMail }}</span></v-col>
                  <v-col><span>{{ row.managerJob }}</span></v-col>
                  <v-col><span>{{ row.managerRmk }}</span></v-col>
               </v-row>
            </v-container>
         </div>

         <div style="background-color: rgb(191, 191, 191);margin: 0px;">
            <v-container>
               <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">支払サイト</p>
            </v-container>
         </div>

         <!-- 表单 支払サイト-->
         <div style="background-color:rgb(242, 242, 242) ;">
            <v-container fluid style="margin-left: 90px;margin-top: 0px;padding-top: 12px;">
               <v-row>
                  <v-col cols="5" style="margin: 0px;">
                     <v-row v-if="showGetRow">
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>入金日</span></v-col>
                        <v-col cols="1" style="padding-right: 0px;"><span>{{ custMangRequest.custInfo.custGetdt
                              }}</span></v-col>
                        <v-col v-if="showSelect1" cols="1"
                           style="padding-left: 0px;padding-right: 0px;text-align: right;"><span>{{
                              custMangRequest.custInfo.custGetcdt }}</span></v-col>
                        <v-col cols="1" style="padding-left: 0px;padding-right: 0px; text-align: right;"><span>{{
                           custMangRequest.custInfo.custOrdergdt }}</span>
                        </v-col>
                        <v-col cols="2" style="padding-left: 0px">
                           <div v-if="showGet" style="text-align: left;margin-left: 0px;padding-left: 0px;">日
                           </div>
                        </v-col>
                     </v-row>
                     <v-row v-if="showPayRow">
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支払日</span></v-col>
                        <v-col cols="1" style="padding-right: 0px;"><span>{{
                           custMangRequest.custInfo.custPaydt }}</span></v-col>
                        <v-col v-if="showSelect2" cols="1"
                           style="padding-left: 0px;padding-right: 0px;text-align: right;"><span>{{
                              custMangRequest.custInfo.custClosedt }}</span></v-col>
                        <v-col cols="1" style="padding-left: 0px;padding-right: 0px; text-align: right;"><span>{{
                           custMangRequest.custInfo.custOrderdt }}</span></v-col>
                        <v-col cols="2" style="padding-left: 0px">
                           <div v-if="showPay" style="text-align: left;margin-left: 0px;padding-left: 0px;">日
                           </div>
                        </v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>銀行名</span><span
                              style="color:brown;font-size: 20px;">*</span></v-col>
                        <v-col cols="8"><span>{{
                           custMangRequest.custInfo.custBankNm }}</span>
                        </v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店名</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custBranchNm }}</span></v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>預金種別</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custDeposittype }}</span></v-col>
                     </v-row>
                  </v-col>
                  <v-col></v-col>
                  <v-col cols="6">
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店番号</span><span
                              style="color:brown;font-size: 20px;">*</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custBranchcd }}</span></v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>口座番号</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custAccountno }}</span></v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>名義人</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custNominee }}</span></v-col>
                     </v-row>
                  </v-col>
               </v-row>

               <v-row v-if="showPayArea">
                  <v-col cols="5" style="margin: 0px;">
                     <v-row v-if="showGetRow2">
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>入金日</span></v-col>
                        <v-col cols="1" style="padding-right: 0px;"><span>{{ custMangRequest.custInfo.custGetdt
                              }}</span></v-col>
                        <v-col v-if="showSelect1" cols="1"
                           style="padding-left: 0px;padding-right: 0px;text-align: right;"><span>{{
                              custMangRequest.custInfo.custGetcdt }}</span></v-col>
                        <v-col cols="1" style="padding-left: 0px;padding-right: 0px; text-align: right;"><span>{{
                           custMangRequest.custInfo.custOrdergdt }}</span>
                        </v-col>
                        <v-col cols="2" style="padding-left: 0px">
                           <div v-if="showGet" style="text-align: left;margin-left: 0px;padding-left: 0px;">日
                           </div>
                        </v-col>
                     </v-row>
                     <v-row v-if="showPayRow2">
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支払日</span></v-col>
                        <v-col cols="1" style="padding-right: 0px;"><span>{{
                           custMangRequest.custInfo.custPaydt }}</span></v-col>
                        <v-col v-if="showSelect2" cols="1"
                           style="padding-left: 0px;padding-right: 0px;text-align: right;"><span>{{
                              custMangRequest.custInfo.custClosedt }}</span></v-col>
                        <v-col cols="1" style="padding-left: 0px;padding-right: 0px; text-align: right;"><span>{{
                           custMangRequest.custInfo.custOrderdt }}</span></v-col>
                        <v-col cols="2" style="padding-left: 0px">
                           <div v-if="showPay" style="text-align: left;margin-left: 0px;padding-left: 0px;">日
                           </div>
                        </v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>銀行名</span><span
                              style="color:brown;font-size: 20px;">*</span></v-col>
                        <v-col cols="8"><span>{{
                           custMangRequest.custInfo.custBankNm }}</span>
                        </v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店名</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custBranchNm }}</span></v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>預金種別</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custDeposittype }}</span></v-col>
                     </v-row>
                  </v-col>
                  <v-col></v-col>
                  <v-col cols="6">
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店番号</span><span
                              style="color:brown;font-size: 20px;">*</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custBranchcd }}</span></v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>口座番号</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custAccountno }}</span></v-col>
                     </v-row>
                     <v-row>
                        <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>名義人</span></v-col>
                        <v-col cols="8"><span>{{ custMangRequest.custInfo.custNominee }}</span></v-col>
                     </v-row>
                  </v-col>
               </v-row>

               <v-row>
                  <v-col cols="5">
                     <v-row><v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>備考</span></v-col>
                        <v-col cols="8" style="margin-left: 30px;"><span>{{ custMangRequest.custInfo.custRmk2
                              }}</span></v-col>
                     </v-row>
                  </v-col>
               </v-row>
               <v-row align="center" justify="center">
                  <v-col cols="2">
                     <span>作成者：</span>
                     {{ custMangRequest.custInfo.custCrdUsr }}
                  </v-col>
                  <v-col cols="2">
                     <span>作成日:</span>
                     {{ formatDate(custMangRequest.custInfo.custCrdDt) }}
                  </v-col>
                  <v-col cols="2">
                     <span>更新者:</span>
                     {{ custMangRequest.custInfo.custUpdUsr }}
                  </v-col>
                  <v-col cols="2">
                     <span>更新日:</span>
                     {{ formatDate(custMangRequest.custInfo.custUpdDt) }}
                  </v-col>
               </v-row>
            </v-container>

         </div>

         <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
            {{ snackbar.message }}
         </v-snackbar>
      </navigation-drawer>
   </v-app>
</template>
<script>
import CustinfoAddApi from '@/api/CustinfoAdd.js';
import NavigationDrawer from "@/components/NavigationDrawer.vue";

export default {
   name: 'CustInfoAdd',
   components: {
      NavigationDrawer
   },
   data: () => {
      return {
         custMangRequest: {
            custInfo: {
               custNm: '', custKn: '', custPhone: '', custFax: '', custWeb: '', custPostno: '', custAddress: '',
               custType: '', custMoney: '', custInvoiceNo: '', custInforSecurity: false, custDispatch: false, custEmpnum: '', custStartdt: new Date(),
               custAnnualsale: '', custCompanyrep: '', custRmk1: '', custRmk2: '', custClosedt: '', custPaydt: '', custOrderdt: '', custGetdt: '', custGetcdt: '',
               custOrdergdt: '', bankCd: '', bankChrcd: '', custBankNm: '',
               custBranchNm: '', custDeposittype: '', custBranchcd: '', custAccountno: '', custNominee: '',
               custCrdUsr: 'USER', custUpdUsr: 'USER', custCrdDt: '', custUpdDt: '',
            },
            mangInfo: [{ managerNm: '', managerPno: '', managerMail: '', managerJob: '', managerRmk: '', managerCrdUsr: 'USER', managerUpdUsr: 'USER', isNew: false, managerCustId: '' }],
         },
         payMonth: '',
         payDay: '',
         payDate: '',
         getMonth: '',
         getDay: '',
         getDate: '',
         showGet: false,
         showPay: false,
         showGetRow: false,
         showPayRow: false,
         showSelect1: false,
         showSelect2: false,
         formattedDate: '',
         snackbar: {
            show: false,
            message: '',
            color: '',
            timeout: 3000
         },
         mangInfos: [],
         selectedDate: '',
         showPayArea: false,
      }
   },

   computed: {

   },

   watch: {
      //监听顾客类型选项
      'custMangRequest.custInfo.custType'(newVal) {
         if (newVal === '顧客') {
            console.log('custBank', this.custBank);
            this.handleCustSelected()
            this.showGetRow = true;
            this.showPayRow = false;
         } else if (newVal === 'パートナー') {
            this.showGetRow = false;
            this.showPayRow = true;
         } else if (newVal === '顧客&パートナー') {
            this.showGetRow = true;
            this.showPayRow = false;
            this.showPayArea = true;
            this.showGetRow2 = false;
            this.showPayRow2 = true;
         };
      },
      //转为后端可接收类型
      'this.custMangRequest.custInfo.custStartdt'(newVal) {
         this.custMangRequest.custInfo.custStartdt.toISOString();
      },
      //监听收付选项
      'custMangRequest.custInfo.custGetcdt'(newVal) {
         if (newVal === ' ') {
            this.showGet = true
            this.showSelect1 = false
         } else {
            this.showGet = false
            this.showSelect1 = true
         }
      },
      'custMangRequest.custInfo.custClosedt'(newVal) {
         if (newVal === ' ') {
            this.showPay = true
            this.showSelect2 = false
         } else {
            this.showPay = false
            this.showSelect2 = true
         }
      },

      'custMangRequest.mangInfo': {
         handler(newVal) {
            newVal.forEach(row => {
               row.managerCrdUsr = 'USER';
               row.managerUpdUsr = 'USER';
            });
         },
         deep: true
      }
   },

   mounted() {
      this.selectDetailByCustId(this.$route.params.custId);
   },

   created() {
      this.selectDetailByCustId(this.$route.params.custId);
   },

   methods: {
      //通过id查询并回显
      async selectDetailByCustId(custId) {
         const response = await CustinfoAddApi.selectDetailByCustId(this.$route.params.custId);
         if (response.data.items.length > 0) {
            this.custMangRequest = { ...response.data.items[0], mangInfo: [] };
            response.data.items.forEach(item => {
               this.custMangRequest.mangInfo.push(...item.mangInfo);
            });
            if (this.custMangRequest.custInfo.custStartdt) {
               const dateObj = new Date(this.custMangRequest.custInfo.custStartdt);
               if (!isNaN(dateObj.getTime())) {
                  this.custMangRequest.custInfo.custStartdt = dateObj;
                  this.formattedDate = this.formatDate(dateObj);
               } else {
                  console.error('date error', this.custMangRequest.custInfo.custStartdt);
               }
            }
         }
      },

      //格式化手机号
      formatPhoneNumber(phoneNumber) {
         if (phoneNumber.length === 9) {
            phoneNumber = phoneNumber.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
         } else if (phoneNumber.length === 10) {
            phoneNumber = phoneNumber.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
         } else if (phoneNumber.length === 11) {
            phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
         }
         return phoneNumber;
      },

      //格式化传真号
      formatFaxNumber(faxNumber) {
         if (faxNumber.length === 10) {
            faxNumber = faxNumber.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
         }
         return faxNumber;
      },

      //格式化金额
      formatCustMoney() {
         let amount = this.custMangRequest.custInfo.custMoney.replace(/\D/g, '');
         this.custMangRequest.custInfo.custMoney = amount.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },

      //格式化日期
      formatDate(date) {
         let dateObj;
         if (date instanceof Date) {
            dateObj = date;
         }
         else {
            dateObj = new Date(date);
         }
         if (isNaN(dateObj.getTime())) {
            return '';
         }
         const year = dateObj.getFullYear();
         const month = String(dateObj.getMonth() + 1).padStart(2, '0');
         const day = String(dateObj.getDate()).padStart(2, '0');
         const formattedDate = `${year}-${month}-${day}`;
         return formattedDate
      },

      //显示布尔值
      turnBoolean(value) {
         return value ? 'あり' : 'なし';
      },

      //路由
      toList() {
         this.$router.push('/CustInfoList');
      },
      toUpdate() {
         const custId = this.$route.params.custId;
         this.$router.push({ path: `/CustInfoAdd/${custId}` });
      }
   }
}
</script>

<style scoped>
.btn {
   margin-left: 3%;
   margin-right: 3%;
   margin-bottom: 2%;
   margin-top: 1%;
   width: 90px;
   height: 40px;
   background-color: rgb(189, 215, 238);
   color: rgb(255, 255, 255) !important;
}
</style>