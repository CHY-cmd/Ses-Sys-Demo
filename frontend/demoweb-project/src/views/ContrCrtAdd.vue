<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div>
                <div>
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">契約情報(請負)
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
                                    style="background-color: rgb(217, 217, 217);margin: 0px;"><span>契約番号</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="contrOperRequest.contract.contrNo" type="text" required
                                        class="custom-input" placeholder="自動入力" readonly></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span>取引先名</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="displayCustNm" type="text" class="custom-input"
                                        readonly>
                                    <v-btn style="width: 40px;height: 30px;" class="btn" @click="openCust()">検索</v-btn>
                                    <v-dialog v-model="openCustDialog" max-width="1100">
                                        <v-card>
                                            <v-card-title>取引先情報</v-card-title>
                                            <hr>
                                            <v-data-table :headers="custHeaders" :items="custList" item-key="id">
                                                <template v-slot:item="{ item }">
                                                    <tr>
                                                        <td><v-btn text @click="fillCust(item)"
                                                                style="color: white;background-color: green;">選択</v-btn>
                                                        </td>
                                                        <td>{{ item.custNm }}</td>
                                                        <td>{{ item.custPhone }}</td>
                                                        <td>{{ item.custAddress }}</td>
                                                        <td>{{ item.custType }}</td>
                                                        <td>{{ item.custGetdt }}</td>
                                                        <td>{{ item.custGetcdt }}</td>
                                                    </tr>
                                                </template>
                                            </v-data-table>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn @click="openCustDialog = false" class="btn">キャンセル</v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>件名</span></v-col>
                                <v-col cols="8"><input v-model="contrOperRequest.contract.contrProjectNm" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額小計</span></v-col>
                                <v-col cols="8"><input v-model="contrOperRequest.contract.contrAmountSubtotal"
                                        type="text" class="custom-input" placeholder="自動計算" readonly></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>消費税</span></v-col>
                                <v-col cols="8"><input v-model="contrOperRequest.contract.consumptionTax" type="text"
                                        class="custom-input" @input="getAddress" placeholder="自動計算" readonly> </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額合計</span></v-col>
                                <v-col cols="8"><input v-model="contrOperRequest.contract.totalAmount" type="text"
                                        class="custom-input" placeholder="自動計算" readonly></v-col>
                            </v-row>
                        </v-col>
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>契約期間</span>
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
                                        <v-date-picker v-model="contrOperRequest.contract.contrStartdt"
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
                                        <v-date-picker v-model="contrOperRequest.contract.contrEnddt"></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>契約ステータス</span></v-col>
                                <v-col cols="6">
                                    <select v-model="contrOperRequest.contract.contrStatus" id="staOptions"
                                        class="custom-select">
                                        <option value="1">契約中</option>
                                        <option value="2">契約終了</option>
                                    </select></v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="1"><span>備考</span></v-col>
                        <v-col style="margin-left: 2%;"><textarea v-model="contrOperRequest.contract.contrRmk"
                                class="custom-textarea"></textarea></v-col>
                        <v-col></v-col>
                    </v-row>
                </v-container>
            </div>
            <!-- 表单 詳細情報 -->
            <div style="background-color: rgb(191, 191, 191);margin: 0px;">
                <v-container>
                    <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">詳細情報</p>
                </v-container>
            </div>
            <div style="background-color: rgb(242, 242, 242); max-width: 100%;">
                <v-table>
                    <thead>
                        <tr style="height: 50px; margin-bottom: 15px;">
                            <th><v-btn class="btn" @click="addRow">行追加</v-btn></th>
                            <th style="text-align: center;">No.</th>
                            <th style="text-align: center;">作業明細</th>
                            <th style="text-align: center;">単価</th>
                            <th style="text-align: center;">備考</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(row, index) in contrOperRequest.operates" :key="index">
                            <td></td>
                            <td style="text-align: center;">{{ index + 1 }}</td>
                            <td style="text-align: center;"><input v-model="row.operProjectNm" class="custom-input">
                            </td>
                            <td style="text-align: center;"><input v-model="row.operPricePer" class="custom-input"></td>
                            <td style="text-align: center;"><input v-model="row.operRmk" class="custom-input"></td>
                            <td style="text-align: center;"><v-btn class="btn" @click="delRow(index)">X</v-btn></td>
                        </tr>
                    </tbody>
                </v-table>
            </div>
            <v-row align="center" justify="center">
                <v-col cols="2">
                    <span>作成者：</span>
                    {{ contrOperRequest.contract.contrCrdUsr }}
                </v-col>
                <v-col cols="2">
                    <span>作成日:</span>
                    {{ formatDate(contrOperRequest.contract.contrCrdDt) }}
                </v-col>
                <v-col cols="2">
                    <span>更新者:</span>
                    {{ contrOperRequest.contract.contrUpdUsr }}
                </v-col>
                <v-col cols="2">
                    <span>更新日:</span>
                    {{ formatDate(contrOperRequest.contract.contrUpdDt) }}
                </v-col>
            </v-row>

            <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
                {{ snackbar.message }}
            </v-snackbar>
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import CustInfoListApi from "@/api/CustInfoList";
import CustInfoAddApi from "@/api/CustinfoAdd";
import ContractApi from "@/api/Contract";
import moment from 'moment';

export default {
    name: 'ContrCrtAdd',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
           
            contrOperRequest: {
                contract: {
                    contrNo: '', contrCustId: '', contrEngineerNm: '', contrProjectNm: '', quotDelflg: '2', contrStartdt: null, contrEnddt: null, contrStatus: '',
                    contrRmk: '', contrPricePer: '', purchasePrice: '', excessUnitprice: '', deduUnitprice: '',
                    settlementUnit: '', contrUpperLimit: '', contrLowerLimit: '', contrAmountSubtotal: '', consumptionTax: '', totalAmount: '',
                    contrCrdUsr: this.$store.state.username, contrUpdUsr: this.$store.state.username, contrCrdDt: null, contrUpdDt: null,
                },
                operates: [{
                    operProjectNm: '', operEngineerNm: '', operPricePer: '', operPurchasePrice: '', operStartdt: null, operEnddt: null, operStatus: '',
                    operExcessUnitprice: '', operDeductionUnitprice: '', operSettlementUnit: '', operUpperLimit: '', operLowerLimit: '', operRmk: '',
                    operCrdUsr: this.$store.state.username, operUpdUsr: this.$store.state.username, operCrdDt: new Date(), operUpdDt: new Date(), isNew: false, operNo: '', menuOperStartdt: false, formattedOperStartdt: ''
                }],
            },
            displayCustNm: '',
            custList: [],
            custDetail: [],
            menuStart: false,
            menuStart2: false,
            menuEnd: false,
            menu: false,
            openCustDialog: false,
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            custHeaders: [
                { title: '選択', sortable: false },
                { title: '取引先名', key: 'custNm', sortable: true },
                { title: '電話番号', key: 'custPhone', sortable: true },
                { title: '住所', key: 'custAddress', sortable: true },
                { title: '取引先種別', key: 'custType', sortable: true },
                { title: '入金日', key: 'custGetdt', sortable: true },
                { title: '入金日', key: 'custGetcdt', sortable: true }
            ],
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
        contrAmountSubtotal() {
            // 自动计算
            let total = 0;
            this.contrOperRequest.operates.forEach(operate => {
                if (operate.operPricePer) {
                    total += parseFloat(operate.operPricePer);
                }
            });
            return total;
        },
    },
    watch: {
        'contrOperRequest.contract.contrStartdt': function () {
            this.handleDateInput(1);
        },
        'contrOperRequest.contract.contrEnddt': function () {
            this.handleDateInput(2);
        },
        'contrOperRequest.operates': {//监听数组变化
            handler() {
                this.updateTotals();
            },
            deep: true
        },
        'contrOperRequest.operates': {//监听数组变化
            handler() {
                this.updateTotals();
                this.contrOperRequest.operates.forEach(row => {
                    row.operCrdUsr = this.$store.state.username;
                    row.operUpdUsr = this.$store.state.username;
                });
            },
            deep: true
        },
    },
    mounted() {
        this.selectContrOperById(this.$route.params.contrNo);
        console.log('Mounted component with managerCustId:', this.$route.params.contrNo);
        console.log('operCrdUsr:',  this.contrOperRequest.operates.operCrdUsr);
        console.log('username:',  this.$store.state.username);
    },
    created() {
        this.selectContrOperById(this.$route.params.contrNo);
    },
    methods: {
        //通过合同号查询并回显
        async selectContrOperById() {
            try {
                const response = await ContractApi.selectContrOperById(this.$route.params.contrNo);
                if (response.data.items.length > 0) {
                    this.contrOperRequest = { ...response.data.items[0], operates: [] };//建立对象结构，所有属性与items相同，并添加一个operates
                    response.data.items.forEach(item => {
                        this.contrOperRequest.operates.push(...item.operates);//遍历数据放入item，再从item中提取operates（同时展开其结构属性）并push
                    });
                    if (this.contrOperRequest.contract.contrStartdt) {
                        const dateObj = new Date(this.contrOperRequest.contract.contrStartdt);
                        if (!isNaN(dateObj.getTime())) {//gettime返回一个时间，判断是否isNaN
                            this.contrOperRequest.contract.contrStartdt = dateObj;
                            this.formattedDate = this.formatDate(dateObj);
                        } else {
                            console.error('date error', this.contrOperRequest.contract.contrStartdt);
                        }
                    };
                    if (this.contrOperRequest.contract.contrEnddt) {
                        const dateObj = new Date(this.contrOperRequest.contract.contrEnddt);
                        if (!isNaN(dateObj.getTime())) {//gettime返回一个时间，判断是否isNaN
                            this.contrOperRequest.contract.contrEnddt = dateObj;
                            this.formattedDate = this.formatDate(dateObj);
                        } else {
                            console.error('date error', this.contrOperRequest.contract.contrEnddt);
                        }
                    };
                    this.contrOperRequest.operates.forEach((operate, index) => {//批量转化日期
                        if (operate.operStartdt) {
                            const dateObj = new Date(operate.operStartdt);
                            if (!isNaN(dateObj.getTime())) {
                                operate.operStartdt = dateObj;
                                operate.formattedOperStartdt = this.formatDate(dateObj);
                            } else {
                                console.error('date error', operate.operStartdt);
                            }
                        }
                    });
                    if (this.contrOperRequest.contract.contrCustId) {
                        await this.selectCustInfo(this.contrOperRequest.contract.contrCustId);
                    }
                }
            } catch (error) {
                console.error('Error data:', error);
            }
        },

        //通过客户id查信息
        async selectCustInfo(custId) {
            try {
                const response = await CustInfoAddApi.selectDetailByCustId(custId);
                if (response.data.items.length > 0) {
                    const custInfo = response.data.items[0].custInfo;
                    this.displayCustNm = custInfo.custNm;
                    this.custList.custGetcdt = custInfo.custGetcdt;
                    this.custList.custGetdt = custInfo.custGetdt;
                }
            } catch (error) {
                console.error('Error fetching customer data:', error);
            }
        },

        //判断登录按钮更新or新增
        async insertOrUpdate() {
            this.contrOperRequest.contract.quotDelflg = '2';//当前页为請負
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
            console.log(operNo);
            if (updateOperRows.length > 0) {
                await this.updateOper(updateOperRows);
            }
            if (newOperRows.length > 0) {
                newOperRows.forEach(row => { row.operNo = operNo; });
                await this.insertOper(operNo, newOperRows);
            }
        },

        //经理单独新增
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


        //经理单独更新
        async updateOper() {
            await ContractApi.updateOper(this.contrOperRequest.operates).then(response => {
                console.log('updateMang success', response);
            })
                .catch(error => {
                    console.error('updateMang error', error);
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

        //查询客户信息
        selectAllCust() {
            CustInfoListApi.selectAllCustInfos().then(response => {
                this.custList = response.data.items;
            }).catch(error => {
                console.error(error);
                this.snackbar.message = 'error';
                this.snackbar.color = 'red';
                this.snackbar.show = true;
            });
        },

        //打开页面同时查询
        openCust() {
            this.openCustDialog = true;
            this.selectAllCust();
        },

        fillCust(rowData) {
            this.contrOperRequest.contract.contrCustId = rowData.custId;
            this.displayCustNm = rowData.custNm;
            this.custList.custGetdt = rowData.custGetdt;
            this.custList.custGetcdt = rowData.custGetcdt;
            this.openCustDialog = false;
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
</style>