<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div>
                <div>
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">契約情報詳細
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
                                <v-col cols="8"><span>{{ contrOperRequest.contract.contrNo }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span>取引先名</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><span>{{ displayCustNm }}</span> </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>件名</span></v-col>
                                <v-col cols="8"><span>{{ contrOperRequest.contract.contrProjectNm }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額小計</span></v-col>
                                <v-col cols="8"><span>{{ contrOperRequest.contract.contrAmountSubtotal }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>消費税</span></v-col>
                                <v-col cols="8"><span>{{ contrOperRequest.contract.consumptionTax }}</span> </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額合計</span></v-col>
                                <v-col cols="8"><span>{{ contrOperRequest.contract.totalAmount }}</span> </v-col>
                            </v-row>
                        </v-col>
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>契約期間</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="2"><span>{{ formattedDates.date1 }}</span> </v-col>
                                <span style="font-size: x-large;align-content: center;">~</span>
                                <v-col cols="2"><span>{{ formattedDates.date2 }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>契約ステータス</span></v-col>
                                <v-col cols="6"><span>{{ contrStatusMap[contrOperRequest.contract.contrStatus] }}</span></v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="1"><span>備考</span></v-col>
                        <v-col style="margin-left: 2%;"><span>{{ contrOperRequest.contract.contrRmk }}</span></v-col>
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
            <div style="background-color: rgb(242, 242, 242); overflow-x: auto; max-width: 100%; display: block;margin-bottom: 50px;">
                <v-table style="width: auto; min-width: 100%;">
                    <thead>
                        <tr style="height: 50px; margin-bottom: 15px;">
                            <th>No.</th>
                            <th style="text-align: center;">作業明細</th>
                            <th style="text-align: center;">契約単価</th>
                            <th style="text-align: center;">備考</th>
                            <th></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr v-for="(row, index) in contrOperRequest.operates" :key="index">
                            <td>{{ index + 1 }}</td>
                            <td style="text-align: center;"><span>{{ row.operProjectNm }}</span></td>
                            <td style="text-align: center;"><span>{{ row.operPricePer }}</span></td>
                            <td style="text-align: center;"><span>{{ row.operRmk }}</span></td>
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
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import CustInfoAddApi from "@/api/CustinfoAdd";
import ContractApi from "@/api/Contract";
import moment from 'moment';

export default {
    name: 'ContrCrtDetail',
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
                    contrNo: '', contrCustId: '', contrEngineerNm: '', contrProjectNm: '', quotDelflg: '1', contrStartdt: null, contrEnddt: null, contrStatus: '',
                    contrRmk: '', contrPricePer: '', purchasePrice: '', excessUnitprice: '', deduUnitprice: '',
                    settlementUnit: '', contrUpperLimit: '', contrLowerLimit: '', contrAmountSubtotal: '', consumptionTax: '', totalAmount: '',
                    contrCrdUsr: this.$store.state.username, contrUpdUsr: this.$store.state.username, contrCrdDt: null, contrUpdDt: null,
                },
                operates: [{
                    operProjectNm: '',operEngineerNm: '', operPricePer: '', operPurchasePrice: '', operStartdt: null, operEnddt: null, operStatus: '',
                    operExcessUnitprice: '', operDeductionUnitprice: '', operSettlementUnit: '', operUpperLimit: '', operLowerLimit: '', operRmk: '',
                    operCrdUsr: this.$store.state.username, operUpdUsr: this.$store.state.username, operCrdDt: null, operUpdDt: null, isNew: false, operNo: '', menuOperStartdt: false, formattedOperStartdt: ''
                }],
            },
            displayCustNm: '',
            custList: [],
            staffList: [],
            custDetail: [],
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
            selectedRowIndex: -1,
            contrStatusMap: {
                1: '契約中',
                2: '契約終了',
            },

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
        
    },
    mounted() {
        this.selectContrOperById(this.$route.params.contrNo);
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
                            this.formattedDates.date1 = this.formatDate(dateObj);
                        } else {
                            console.error('date error', this.contrOperRequest.contract.contrStartdt);
                        }
                    };
                    if (this.contrOperRequest.contract.contrEnddt) {
                        const dateObj = new Date(this.contrOperRequest.contract.contrEnddt);
                        if (!isNaN(dateObj.getTime())) {//gettime返回一个时间，判断是否isNaN
                            this.contrOperRequest.contract.contrEnddt = dateObj;
                            this.formattedDates.date2 = this.formatDate(dateObj);
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
       
        //路由
        toList() {
            this.$router.push('/ContrInfoList');
        },
        toUpdate() {
            const contrNo = this.$route.params.contrNo;
            this.$router.push({ path: `/ContrCrtAdd/${contrNo}`});
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