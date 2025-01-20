<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div>
                <div>
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">請求書情報(SES)
                        </v-app-bar-title>
                        <div style="color: aliceblue;">{{ username }}</div>
                    </v-app-bar>
                </div>
                <div
                    style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);height: 100px;margin-top: 64px;">
                    <v-btn class="btn" @click="insertOrUpdate">登録</v-btn>
                    <v-btn class="btn" @click="toList">キャンセル</v-btn>
                </div>
                <div style="background-color: rgb(191, 191, 191);margin: 0px;">
                    <v-container>
                        <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">基本情報</p>
                    </v-container>
                </div>
            </div>
            <!-- 表单 基本情報 -->
            <div style="width: auto;background-color:rgb(242, 242, 242);">
                <v-container fluid style="margin-left: 90px;padding-top: 0px;">
                    <v-row style="margin-top: 0px;">
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3"
                                    style="background-color: rgb(217, 217, 217);margin: 0px;"><span>請求書番号</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="billBillDtelRequest.bill.billNo" type="text" required
                                        class="custom-input" placeholder="自動入力" readonly></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span>請求先名</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="billBillDtelRequest.bill.billNm" type="text"
                                        class="custom-input" readonly>
                                    <v-btn style="width: 40px;height: 30px;" class="btn" @click="openBill()">検索</v-btn>
                                    <v-dialog v-model="openBillDialog" max-width="1100">
                                        <v-card>
                                            <v-card-title>請求先情報</v-card-title>
                                            <hr>
                                            <v-data-table :headers="billHeaders" :items="billList" item-key="id">
                                                <template v-slot:item="{ item }">
                                                    <tr>
                                                        <td><v-btn text @click="fillBill(item)"
                                                                style="color: white;background-color: green;">選択</v-btn>
                                                        </td>
                                                        <td>{{ item.billNm }}</td>
                                                        <td>{{ item.billCustNm }}</td>
                                                        <td>{{ item.billStartDt }}</td>
                                                        <td>{{ item.billEndDt }}</td>
                                                        <td>{{ billClsMap[item.billCls] }}</td>
                                                        <td>{{ billStatusMap[item.billStatus] }}</td>
                                                    </tr>
                                                </template>
                                            </v-data-table>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn @click="openBillDialog = false" class="btn">キャンセル</v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>件名</span></v-col>
                                <v-col cols="8"><input v-model="billBillDtelRequest.bill.billProjectNm" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額小計</span></v-col>
                                <v-col cols="8"><input v-model="billBillDtelRequest.bill.billAmountSubtotal"
                                        type="text" class="custom-input" placeholder="自動計算" readonly></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>消費税</span></v-col>
                                <v-col cols="8"><input v-model="billBillDtelRequest.bill.billConsumptionTax" type="text"
                                        class="custom-input" @input="getAddress" placeholder="自動計算" readonly> </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額合計</span></v-col>
                                <v-col cols="8"><input v-model="billBillDtelRequest.bill.billTotalAmount" type="text"
                                        class="custom-input" placeholder="自動計算" readonly></v-col>
                            </v-row>
                        </v-col>
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>請求期間</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col>
                                    <v-menu ref="menu1" v-model="menus.menu1" :close-on-content-click="false"
                                        transition="scale-transition" style="position: absolute;left:70%;top: 40%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date1" class="custom-input"
                                                    @click="toggleMenu(1)" type="text" v-bind="attrs" />
                                                <v-icon :color="menus.menu1 ? 'error' : 'primary'"
                                                    @click="toggleMenu(1)">
                                                    {{ menus.menu1 ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="billBillDtelRequest.bill.billStartdt"
                                            @input="handleDateInput(1)"></v-date-picker>
                                    </v-menu>
                                </v-col>
                                <span style="font-size: x-large;align-content: center;">~</span>
                                <v-col>
                                    <v-menu ref="menu2" v-model="menus.menu2" :close-on-content-click="false"
                                        transition="scale-transition" style="position: absolute;left:70%;top: 40%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date2" class="custom-input"
                                                    @click="toggleMenu(2)" type="text" v-bind="attrs" />
                                                <v-icon :color="menus.menu2 ? 'error' : 'primary'"
                                                    @click="toggleMenu(2)">
                                                    {{ menus.menu2 ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="billBillDtelRequest.bill.billEndtdt"></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>請求ステータス</span></v-col>
                                <v-col cols="6">
                                    <select v-model="billBillDtelRequest.bill.billStatus" id="staOptions"
                                        class="custom-select">
                                        <option value="1">作成済</option>
                                        <option value="2">確認済</option>
                                        <option value="3">送信済</option>
                                        <option value="4">廃止</option>
                                    </select></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支払期限</span></v-col>
                                <v-col cols="2"><input v-model="billBillDtelRequest.bill.billPaymentTerm" type="text"
                                        class="custom-input"></v-col>
                                                          </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支払口座情報</span></v-col>
                                <v-col cols="2"><input v-model="billBillDtelRequest.bill.contrLowerLimit" type="text"
                                        class="custom-input"></v-col>
                                <v-col cols="2"><input v-model="a" type="text"
                                        class="custom-input"></v-col>
                                        <v-col cols="2"><input v-model="a" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>登録番号</span></v-col>
                                <v-col cols="2"><input v-model="a" type="text" class="custom-input"
                                        ></v-col>
                                <span style="font-size: x-large;align-content: center;margin-bottom: 5%;"></span>
                                <v-col cols="3"><input v-model="a" type="text" class="custom-input"
                                        ></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>作業基準時間</span></v-col>
                                <v-col cols="2"><input v-model="a" type="text"
                                        class="custom-input"></v-col>
                                <span style="font-size: x-large;align-content: center;">~</span>
                                <v-col cols="2"><input v-model="a" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="1"><span>備考</span></v-col>
                        <v-col style="margin-left: 2%;"><textarea v-model="a"
                                class="custom-textarea"></textarea></v-col>
                        <v-col></v-col>
                    </v-row>
                </v-container>
            </div>
            <!-- 表单 詳細情報 -->
            <div style="background-color: rgb(191, 191, 191);margin: 0px;">
                <v-container>
                    <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">請求明細情報</p>
                </v-container>
            </div>
            <div style="background-color: rgb(242, 242, 242); overflow-x: auto; max-width: 100%; display: block;">
                <v-table style="width: auto; min-width: 100%;">
                    <thead>
                        <tr style="height: 50px; margin-bottom: 15px;">
                            <th><v-btn class="btn" @click="addRow">行追加</v-btn></th>
                            <th>No.</th>
                            <th class="custom-tb">技術者名</th>
                            <th class="custom-tb">契約単価</th>
                            <th class="custom-tb">数量</th>
                            <th class="custom-tb">控除時間</th>
                            <th class="custom-tb">超過時間</th>
                            <th class="custom-tb">控除単価</th>
                            <th class="custom-tb">超過単価</th>
                            <th class="custom-tb">小計<br>（税前対象）</th>
                            <th class="custom-tb">交通費</th>
                            <th class="custom-tb">合計</th>
                            <th class="custom-tb">備考</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(row, index) in a" :key="index">
                            <td></td>
                            <td>{{ index + 1 }}</td>
                            <td>
                                <input v-model="row.a" class="custom-input">
                                <v-icon color="blue-darken-2" icon="mdi-card-search" size="large"
                                    @click="openStaff(index)"></v-icon>
                            </td>
                            <td><input v-model="row.a" class="custom-input"></td>
                            <td><input v-model="row.a" class="custom-input"></td>
                            <td>
                                <v-menu ref="`menuOperStartdt-${index}`" v-model="row.a"
                                    :close-on-content-click="false" transition="scale-transition"
                                    style="position: absolute;left:25%;top: 55%;" min-width="auto">
                                    <template v-slot:activator="{ props }">
                                        <div class="input-with-icon">
                                            <input v-model="row.formattedOperStartdt" type="text" class="custom-input"
                                                readonly @click="toggleOperMenu(row)" v-bind="props" />
                                            <v-icon :color="row.menuOperStartdt ? 'error' : 'primary'"
                                                @click="toggleOperMenu(row)">
                                                {{ row.menuOperStartdt ? 'mdi-close' : 'mdi-calendar' }}
                                            </v-icon>
                                        </div>
                                    </template>
                                    <v-date-picker v-model="row.a"></v-date-picker>
                                </v-menu>
                            </td>
                            <td>
                                <select v-model="row.a" id="options" class="custom-select">
                                    <option value="1">稼働中</option>
                                    <option value="2">稼働予定</option>
                                    <option value="3">終了予定</option>
                                    <option value="4">契約終了</option>
                                </select>
                            </td>
                            <td><input v-model="row.a" class="custom-input"></td>
                            <td><input v-model="row.a" class="custom-input"></td>
                            <td>
                                <select v-model="row.a" id="options" class="custom-select">
                                    <option value="1">切捨て(10円)</option>
                                    <option value="2">切捨て(100円)</option>
                                    <option value="3">切上げ(10円)</option>
                                    <option value="4">切上げ(100円)</option>
                                </select>
                            </td>
                            <td><input v-model="row.a" class="custom-input" readonly
                                    placeholder="自動入力"></td>
                            <td><input v-model="row.a" class="custom-input" readonly
                                    placeholder="自動入力"></td>
                            <td><input v-model="row.a" class="custom-input"></td>
                            <td><v-btn class="btn" @click="delRow(index)">X</v-btn></td>
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <v-row align="center" justify="center">
                <v-col cols="2">
                    <span>作成者：</span>
                    {{ billBillDtelRequest.bill.billCrdUsr }}
                </v-col>
                <v-col cols="2">
                    <span>作成日:</span>
                    {{ formatDate(billBillDtelRequest.bill.billCrdDt) }}
                </v-col>
                <v-col cols="2">
                    <span>更新者:</span>
                    {{ billBillDtelRequest.bill.billUpdUsr }}
                </v-col>
                <v-col cols="2">
                    <span>更新日:</span>
                    {{ formatDate(billBillDtelRequest.bill.billUpdUsr) }}
                </v-col>
            </v-row>

            <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
                {{ snackbar.message }}
            </v-snackbar>

            <!-- 员工对话框 -->
            <v-dialog v-model="openStaffDialog" max-width="1100">
                <v-card>
                    <v-card-title>スタッフ情報</v-card-title>
                    <hr>
                    <v-data-table :headers="staffHeaders" :items="staffList" item-key="id">
                        <template v-slot:item="{ item }">
                            <tr>
                                <td><v-btn text @click="fillStaff(selectedRowIndex, item)"
                                        style="color: white;background-color: green;">選択</v-btn>
                                </td>
                                <td>{{ item.staffNm }}</td>
                                <td>{{ item.staffSex }}</td>
                                <td>{{ item.staffCls }}</td>
                                <td>{{ item.staffNeareststation }}</td>
                                <td>{{ item.staffSalesstatus }}</td>
                            </tr>
                        </template></v-data-table><v-card-actions><v-spacer></v-spacer>
                        <v-btn @click="openStaffDialog = false" class="btn">キャンセル</v-btn>
                    </v-card-actions>
                </v-card>
            </v-dialog>
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import StaffInfoListApi from "@/api/Staff";
import CustInfoAddApi from "@/api/CustInfoList";
import BillApi from "@/api/Bill";
import moment from 'moment';

export default {
    name: 'ContrSesAdd',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            billBillDtelRequest: {
                bill: {
                    billNo: '', billNm: '', billEngineerNm: '', billCustNm: '', billProjectNm: '', billStartdt: null, billEndtdt: null, billConfirmflg: '',
                    billCls: '', billStatus: '', billPaymentTerm: '', billChargedAmount: '', billTaxRate: '',
                    billAmountSubtotal: '', billConsumptionTax: '', billTotalAmount: '', billRmk: '',
                    billCrdUsr: this.$store.state.username, billUpdUsr: this.$store.state.username, billCrdDt: null, billUpdDt: null,
                },
                billDetls: [{
                    billdetlEngineerNm: '', billdetlPricePer: '', billdetlAmount: '', billdetlDeduTime: '',
                    billdetlExcessTime: '', billdetlDeduUnitprice: '', billdetlExcessUnitprice: '', billdetlPretaxamo: '', billdetlTransExpenses: '', billdetlTotalamo: '', billdetlRmk: '',
                    billdetlCrdUsr: this.$store.state.username, billdetlUpdUsr: this.$store.state.username, billdetlCrdDt: new Date(), billdetlUpdDt: new Date(), isNew: false, billdetlNo: ''
                }],
            },
            a:'',
            billList: [],
            custList: [],
            menuStart: false,
            menuStart2: false,
            menuEnd: false,
            menu: false,
            openBillDialog: false,
            openStaffDialog: false,
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            billHeadersHeaders: [
                { title: '選択', sortable: false },
                { title: '請求先名', key: 'billNm', sortable: true },
                { title: '取引先名', key: 'billCustNm', sortable: true },
                { title: '請求開始日', key: 'billStartDt', sortable: true },
                { title: '請求終了日', key: 'billEndDt', sortable: true },
                { title: '請求区分', key: 'billCls', sortable: true },
                { title: '請求ステータス', key: 'billStatus', sortable: true }
            ],
            billClsMap: {
                1: 'SES',
                2: '請負',
            },
            billStatusMap: {
                1: '作成済',
                2: '確認済',
                3: '送信済',
                4: '廃止',
            },
            formattedDates: {
                date1: '', date2: ''
            },
            menus: {
                menu1: false, menu2: false,
            },
            dailyNumber: 1, // 每日编号
            lastCalculatedDate: '', // 上次重置日期
            selectedRowIndex: -1,
        }
    },
    computed: {
        username() {
            return this.$store.state.username;
        },
        // contrAmountSubtotal() {
        //     // 自动计算
        //     let total = 0;
        //     this.contrOperRequest.operates.forEach(operate => {
        //         if (operate.operPricePer) {
        //             total += parseFloat(operate.operPricePer);
        //         }
        //     });
        //     return total;
        // },
    },
    watch: {
        
    },
    mounted() {

    },
    created() {

    },
    methods: {
        

        //通过客户id查信息
        // async selectCustInfo(custId) {
        //     try {
        //         const response = await CustInfoAddApi.selectDetailByCustId(custId);
        //         if (response.data.items.length > 0) {
        //             const custInfo = response.data.items[0].custInfo;
        //             this.displayCustNm = custInfo.custNm;
        //             this.custList.custGetcdt = custInfo.custGetcdt;
        //             this.custList.custGetdt = custInfo.custGetdt;
        //         }
        //     } catch (error) {
        //         console.error('Error fetching customer data:', error);
        //     }
        // },

        //判断登录按钮更新or新增
        async insertOrUpdate() {
            this.contrOperRequest.contract.quotDelflg = '1';//当前页为SES
            this.contrOperRequest.operates.forEach(operate => {
                operate.operProjectNm = this.contrOperRequest.contract.contrProjectNm;
            });//operProjectNm于contrProjectNm同步
            try {
                if (this.$route.params.contrNo) {
                    // 更新合同表
                    await this.updateContr();
                    // 更新操作表
                    await this.saveOper();
                    this.snackbar.show = true;
                    this.snackbar.message = '更新成功';
                    this.snackbar.color = 'success';
                } else {
                    // 同时新增两表
                    await this.insertContrOper();
                    this.dailyNumber = (this.dailyNumber + 1) % 100; // 提交时递增一次编号
                    this.snackbar.show = true;
                    this.snackbar.message = '登録成功';
                    this.snackbar.color = 'success';
                }
                // 所有操作完成后刷新页面
                window.location.reload();
            } catch (error) {
                this.snackbar.show = true;
                this.snackbar.message = '操作エラー';
                this.snackbar.color = 'error';
            }
        },

        //同时新增两表
        async insertContrOper() {
            await ContractApi.insertContrOper(this.contrOperRequest)
        },

        //单独更新合同表
        async updateContr() {
            await ContractApi.updateContr(this.contrOperRequest.contract)
        },

        //根据增减行判断部分增行新增修改
        async saveOper() {
            const updateOperRows = this.contrOperRequest.operates.filter(row => !row.isNew);
            const newOperRows = this.contrOperRequest.operates.filter(row => row.isNew);
            const operNo = this.$route.params.contrNo;

            if (updateOperRows.length > 0) {
                await this.updateOper(updateOperRows);
            }
            if (newOperRows.length > 0) {
                newOperRows.forEach(row => { row.operNo = operNo; });
                await this.insertOper(operNo, newOperRows);
            }
        },

        //合同单独新增
        async insertOper(operNo, newOperRows) {
            await ContractApi.insertOper(operNo, newOperRows)
                .then(response => {
                    newOperRows.forEach(row => row.isNew = false);
                    console.log('insertOper success', response);
                })
                .catch(error => {
                    console.error('insertOper error', error);
                });
        },


        //合同单独更新
        async updateOper() {
            await ContractApi.updateOper(this.contrOperRequest.operates).then(response => {
                console.log('updateOper success', response);
            })
                .catch(error => {
                    console.error('updateOper error', error);
                });
        },

        //增减行并标记
        addRow() {
            this.contrOperRequest.operates.push({
                operEngineerNm: '',
                operPricePer: '',
                operPurchasePrice: '',
                operStartdt: null,
                operStatus: '',
                operLowerLimit: this.contrOperRequest.contract.contrLowerLimit,
                operUpperLimit: this.contrOperRequest.contract.contrUpperLimit,
                operSettlementUnit: '',
                operDeductionUnitprice: '',
                operExcessUnitprice: '',
                operRmk: '',
                isNew: true
            });
        },
        delRow(index) {
            if (this.contrOperRequest.operates.length > 1) {
                this.contrOperRequest.operates.splice(index, 1);//删除index位置的1个元素
            } else {
                this.contrOperRequest.operates[0] = {
                    operEngineerNm: '',
                    operPricePer: '',
                    operPurchasePrice: '',
                    operStartdt: null,
                    operStatus: '',
                    operLowerLimit: '',
                    operUpperLimit: '',
                    operSettlementUnit: '',
                    operDeductionUnitprice: '',
                    operExcessUnitprice: '',
                    operRmk: '',
                    isNew: true
                };
            }
        },

        //查询员工信息
        selectAllStaff() {
            StaffInfoListApi.selectAllStaff().then(response => {
                this.staffList = response.data.items;
            }).catch(error => {
                console.error(error);
                this.snackbar.message = 'error';
                this.snackbar.color = 'red';
                this.snackbar.show = true;
            });
        },

        //查询账单信息
        selectAllBill() {
            BillApi.selectAllBill().then(response => {
                this.billList = response.data.items;
            }).catch(error => {
                console.error(error);
                this.snackbar.message = 'error';
                this.snackbar.color = 'red';
                this.snackbar.show = true;
            });
        },
        //查询客户信息
        selectCustByNm(custNm) {
            CustInfoAddApi.selectCustByNm(custNm).then(response => {
                this.custList = response.data.items;
            }).catch(error => {
                console.error(error);
                this.snackbar.message = 'error';
                this.snackbar.color ='red';
                this.snackbar.show = true;
            });
        },

        //打开页面同时查询
        openBill() {
            this.openBillDialog = true;
            this.selectAllBill();
        },
        openStaff(rowIndex) {
            console.log("openStaff called with rowIndex:", rowIndex);
            this.openStaffDialog = true;
            this.selectedRowIndex = rowIndex;
            this.selectAllStaff();
        },

        fillBill(rowData) {
            this.billBillDtelRequest.bill.billNm = rowData.billNm;
            this.selectCustByNm(rowData.billCustNm)
            this.billBillDtelRequest.bill.billPaymentTerm=this.custList
            this.openBillDialog = false;
        },

        fillStaff(rowIndex, rowData) {
            console.log("fillStaff called with rowIndex:", rowIndex, "rowData:", rowData);
            if (rowIndex !== -1 && rowData) {
                this.contrOperRequest.operates[rowIndex].operEngineerNm = rowData.staffNm;
                this.openStaffDialog = false;
            }
        },

        //格式化日期以显示
        formatDate(date, options = { timeZone: 'Asia/Tokyo' }) {
            if (!date) {
                return '';
            }
            let dateObj;
            if (date instanceof Date) {
                dateObj = date;
            } else {
                dateObj = new Date(date);
            }
            if (isNaN(dateObj.getTime())) {
                return '';
            }
            const formattedDate = moment.tz(dateObj, options.timeZone).format('YYYY/MM/DD');
            return formattedDate;
        },
        formatDateToBackend(date) {
            if (!date || !(date instanceof Date) || isNaN(date.getTime())) {
                return '';
            }
            const formattedDate = moment(date).tz('Asia/Tokyo').format('YYYY-MM-DD HH:mm:ss');
            return formattedDate;
        },
        handleDateInput(index) {
            const fieldNames = ['Startdt', 'Enddt'];
            const fieldName = fieldNames[index - 1];
            const selectedDate = this.contrOperRequest.contract[`contr${fieldName}`];
            if (selectedDate instanceof Date && !isNaN(selectedDate.getTime())) {
                const formattedDate = this.formatDate(selectedDate);
                this.formattedDates[`date${index}`] = formattedDate;
            } else {
                this.formattedDates[`date${index}`] = '';
                this.contrOperRequest.contract[`contr${fieldName}`] = '';
            }
            this.menus[`menu${index}`] = false;
            if (index === 1) {
                if (!this.contrOperRequest.contract.contrNo) {//回显时为空则不计算
                    this.calculateContrNo();
                }
            }
        },

        //控制日历
        toggleMenu(index) {
            this.menus[`menu${index}`] = !this.menus[`menu${index}`];
        },
        toggleOperMenu(row) {
            row.menuOperStartdt = !row.menuOperStartdt;
        },
        //自动计算合同号
        calculateContrNo() {
            const indt = new Date(this.contrOperRequest.contract.contrStartdt);
            if (indt && !isNaN(indt.getTime())) {
                const year = indt.getFullYear();
                const month = String(indt.getMonth() + 1).padStart(2, '0');
                const day = String(indt.getDate()).padStart(2, '0');
                // 检查是否是新的一天
                const currentDate = new Date().toISOString().split('T')[0]; // 获取当前日期的 YYYY-MM-DD 格式
                if (this.lastCalculatedDate !== currentDate) {
                    this.dailyNumber = 0; // 重置dailyNumber
                    this.lastCalculatedDate = currentDate; // 更新最后计算日期
                }
                // 获取每日编号
                const dailyNumber = String(this.dailyNumber).padStart(2, '0');
                this.contrOperRequest.contract.contrNo = `ITEC-${year}-${month}${day}-${dailyNumber}`;
            } else {
                this.contrOperRequest.contract.contrNo = '';
            }
        },
        //自动赋值上下限
        updateOperLimits(type, value) {
            this.contrOperRequest.operates.forEach(row => {
                if (type === 'lower') {
                    row.operLowerLimit = value;
                } else if (type === 'upper') {
                    row.operUpperLimit = value;
                }
            });
        },

        //计算总和
        updateTotals() {
            let subtotal = 0;
            let pricePerSum = 0;
            let purchasePriceSum = 0;
            this.contrOperRequest.operates.forEach(operate => {
                if (operate.operPricePer) {
                    subtotal += parseFloat(operate.operPricePer);
                    pricePerSum += parseFloat(operate.operPricePer);
                }
                if (operate.operPurchasePrice) {
                    purchasePriceSum += parseFloat(operate.operPurchasePrice);
                }
            });
            this.contrOperRequest.contract.contrAmountSubtotal = subtotal;
            this.contrOperRequest.contract.consumptionTax = Math.floor(subtotal * 0.1);
            this.contrOperRequest.contract.totalAmount = Math.floor(subtotal * 1.1);
            const operatesCount = this.contrOperRequest.operates.length;
            this.contrOperRequest.contract.contrPricePer = operatesCount > 0 ? pricePerSum / operatesCount : 0;
            this.contrOperRequest.contract.purchasePrice = operatesCount > 0 ? purchasePriceSum / operatesCount : 0;
        },
        //监听数组日期 input不起效
        watchOperStartdt(row) {
            this.$watch(
                () => row.operStartdt,
                (newVal, oldVal) => {
                    if (newVal !== oldVal) {
                        row.formattedOperStartdt = this.formatDate(newVal);
                        this.toggleOperMenu(row);
                    }
                },
                { deep: true }
            );
        },

        //计算超出和控除
        calculateUnitPrices(row) {
            if (!row.operPricePer || !row.operLowerLimit || !row.operUpperLimit) {
                // 数据不完整不进行计算
                return;
            }
            let deductionPrice = row.operPricePer / row.operLowerLimit;
            let excessPrice = row.operPricePer / row.operUpperLimit;
            switch (row.operSettlementUnit) {
                case '1': // 切捨て(10円)
                    row.operDeductionUnitprice = Math.floor(deductionPrice / 10) * 10;
                    row.operExcessUnitprice = Math.floor(excessPrice / 10) * 10;
                    break;
                case '2': // 切捨て(100円)
                    row.operDeductionUnitprice = Math.floor(deductionPrice / 100) * 100;
                    row.operExcessUnitprice = Math.floor(excessPrice / 100) * 100;
                    break;
                case '3': // 切上げ(10円)
                    row.operDeductionUnitprice = Math.ceil(deductionPrice / 10) * 10;
                    row.operExcessUnitprice = Math.ceil(excessPrice / 10) * 10;
                    break;
                case '4': // 切上げ(100円)
                    row.operDeductionUnitprice = Math.ceil(deductionPrice / 100) * 100;
                    row.operExcessUnitprice = Math.ceil(excessPrice / 100) * 100;
                    break;
                default:
                    // 结算单位未定义则不进行计算
                    break;
            }
        },

        //路由
        toList() {
            this.$router.push('/ContrInfoList');
        }
    }
};
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

.custom-input {
    padding-left: 10px;
    font-size: 15px;
    width: 80%;
    height: 35px;
    line-height: 35px;
    border: 1px solid black;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}

.custom-select {
    padding-left: 10px;
    font-size: 15px;
    width: 80%;
    height: 35px;
    line-height: 35px;
    border: 1px solid black;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
    background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg"><path fill="%23495057" d="M1 1l4 4 4-4"/></svg>') no-repeat right 0.75rem center / 10px 6px;
}

.custom-textarea {
    padding-left: 10px;
    height: 120px;
    width: 1170px;
    border: 1px black solid;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}

.custom-tb {
    text-align: center !important;
    width: 200px;
    /* 设置输入框的固定宽度 */
    min-width: 200px;
    /* 最小宽度也是200px，防止缩小 */
    max-width: 200px;
    /* 最大宽度也是200px，防止扩大 */
    box-sizing: border-box;
    /* 边框和内边距包含在宽度内 */
    white-space: nowrap;
}

td {
    text-align: center !important;
}
</style>