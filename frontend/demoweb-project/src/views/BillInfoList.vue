<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div style="background-color:rgb(217, 217, 217) ;display: flex; flex-direction: column; height: 100%;">
                <div style="margin-top: 0px;padding-top: 0px;">
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">請求書一覧
                        </v-app-bar-title>
                        <div style="color: aliceblue;">{{ username }}</div>
                    </v-app-bar>
                </div>
                <!-- 查询部分 -->
                <div>
                    <div class="select-area">
                        <v-row style="margin-top: 20px;">
                            <v-col>
                                <div style="height: 280px;width: 350px;background-color: rgb(217, 217, 217);">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">請求区分</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">請求先名</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">件名</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">技術者名</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="8" sm="2">
                                <v-col style="padding-left: 0px;">
                                    <v-btn-toggle v-model="billQuaryCondition.billCls" class="custom-btn-toggle">
                                        <v-btn value="1">SES</v-btn>
                                        <v-btn value="2">請負</v-btn>
                                    </v-btn-toggle>
                                </v-col>
                                <v-col cols="12" style="display: flex; align-items: center;padding: 0%;">
                                    <v-text-field variant="outlined" bg-color="white"
                                        v-model="billQuaryCondition.billNm"></v-text-field>
                                    <v-icon color="blue-darken-2" icon="mdi-card-search" size="large"
                                        @click="openBill()" style="margin-left: 8px;padding: 10px;"></v-icon>
                                </v-col>
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="billQuaryCondition.billProjectNm"></v-text-field>
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="billQuaryCondition.billdetlEngineerNm"
                                    :disabled="isEngiDisabled"></v-text-field>
                            </v-col>
                            <v-col>
                                <div
                                    style="height: 220px;width: 350px;background-color: rgb(217, 217, 217);margin-left: 100px;">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">請求期間</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">請求ステータス</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="8" sm="3" style="margin-right: 5%;">
                                <v-row>
                                    <v-col>
                                        <v-menu ref="menuStart" v-model="menuStart" :close-on-content-click="false"
                                            transition="scale-transition" offset-y min-width="auto">
                                            <template v-slot:activator="{ props }">
                                                <v-text-field v-model="formatDatedStartDt" label="請求開始日"
                                                    bg-color="white" append-icon="mdi-calendar" readonly v-bind="props"
                                                    variant="outlined"></v-text-field>
                                            </template>
                                            <v-date-picker v-model="billQuaryCondition.billStartdt" no-title scrollable
                                                @update:modelValue="menuStart = false"></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                    <span style="font-size: x-large;align-content: center;margin-bottom: 5%;">~</span>
                                    <v-col>
                                        <v-menu ref="menuEnd" v-model="menuEnd" :close-on-content-click="false"
                                            transition="scale-transition" offset-y min-width="auto">
                                            <template v-slot:activator="{ props }">
                                                <v-text-field v-model="formatDatedEndDt" label="請求終了日" bg-color="white"
                                                    append-icon="mdi-calendar" readonly v-bind="props"
                                                    variant="outlined"></v-text-field>
                                            </template>
                                            <v-date-picker v-model="billQuaryCondition.billEndtdt" no-title scrollable
                                                @update:modelValue="menuEnd = false"></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                </v-row>
                                <v-select :items="billStatus" v-model="billQuaryCondition.billStatus" variant="outlined"
                                    bg-color="white">
                                </v-select>
                            </v-col>
                        </v-row>
                    </div>
                    <div
                        style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);">
                        <v-btn class="btn" @click="selectBillGeneral">検索</v-btn>
                        <v-btn class="btn" @click="toSesAdd">SES新規登録</v-btn>
                        <v-btn class="btn" @click="toCtrAdd">請負新規登録</v-btn>
                        <v-btn class="btn" @click="reset">リセット</v-btn>
                    </div>
                </div>
                <div style="background-color: rgb(217, 217, 217) ;margin-top: 0px;display: flex;align-items: center">
                    <v-icon style="margin-left: 1%; margin-right: 5px;">mdi-file-document-multiple-outline</v-icon>
                    <p style="margin: 1%;font-size: 20px;">基本情報一覧</p>
                </div>
                <div style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);"
                    v-if="isBtnShow">
                    <v-btn class="btn" @click="generateBatchPdf"
                        :disabled="!selectedItems || selectedItems.length === 0">請求書一括作成</v-btn>
                    <v-btn class="btn" @click=""
                        :disabled="!selectedItems || selectedItems.length === 0">一括メール送信</v-btn>
                    <v-btn class="btn" @click="printSelectedSesBill"
                        :disabled="!selectedItems || selectedItems.length === 0">一括印刷</v-btn>
                </div>
                <!-- 表格部分 -->
                <div style="background-color: rgb(242, 242, 242) ;margin-top: 0px;display: flex;height: 100%;">
                    <v-table style="height: 100%;width: 100%;">
                        <thead>
                            <tr>
                                <th style="width: 50px;">
                                    <input type="checkbox" v-model="selectAll" @click="toggleAllSelections"
                                        :disabled="isCheckboxDisabled">
                                </th>
                                <th style="width: 200px;">
                                    <span style="margin-right: 30px;">表示件数</span>
                                    <select v-model="limit" class="limitSelect">
                                        <option v-for="size in pageSizes" :value="size">{{ size }}</option>
                                    </select>
                                </th>
                                <th style="text-align: center;">
                                    No.
                                </th>
                                <th style="text-align: center;">
                                    請求書番号
                                </th>
                                <th style="text-align: center;">
                                    取引先名
                                </th>
                                <th style="text-align: center;">
                                    件名
                                </th>
                                <th style="text-align: center;">
                                    請求ステータス
                                </th>
                                <th style="text-align: center;">
                                    支払期限
                                </th>
                                <th style="text-align: center;">
                                    請求金額（円）
                                </th>
                                <th style="text-align: center;">
                                    PDF
                                </th>
                                <th style="text-align: center;">
                                    作成者
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in items" :key="item.billNo">
                                <!-- 删除入口 -->
                                <td style="text-align: center;">
                                    <input type="checkbox" :value="item.billNo" v-model="selectedItems"
                                        :disabled="isCheckboxDisabled">
                                </td>
                                <td>
                                    <div style="display: flex;margin-top: 5px;margin-bottom: 5px;">

                                        <v-btn text @click="deleteBillById(item.billNo)"
                                            style="width: 70px;height: 35px;background-color: rgb(217, 217, 217);color: rgb(255, 255, 255);margin-right: 5px;">削除</v-btn>
                                    </div>
                                </td>
                                <td style="text-align: center;">{{ (currentPage - 1) * limit + index + 1 }}</td>
                                <td style="text-align: center;" @click="toCrontrDetail(item.billNo)">{{
                                    item.billNo }}</td>
                                <td style="text-align: center;" @click="toDetail(item.billNo, item.billCls)">{{
                                    item.billCustNm }}</td>
                                <td style="text-align: center;" @click="toDetail(item.billNo, item.billCls)">{{
                                    formatBillNm(item)
                                }}</td>
                                <td style="text-align: center;" @click="toDetail(item.billNo, item.billCls)">{{
                                    getBillstatus(item.billStatus) }}
                                </td>
                                <td style="text-align: center;" @click="toDetail(item.billNo, item.billCls)">{{
                                    formatDate(item.billPaymentTerm)
                                }}</td>
                                <td style="text-align: center;" @click="toDetail(item.billNo, item.billCls)">{{
                                    item.billChargedAmount }}</td>
                                <td style="text-align: center;">
                                    <v-btn text @click.stop="downloadSesBill(item.billNo)">PDFダウンロード</v-btn>
                                </td>
                                <td style="text-align: center;" @click="toDetail(item.billNo, item.billCls)">{{
                                    item.billCrdUsr }}
                                </td>
                            </tr>
                        </tbody>
                    </v-table>
                </div>
                <!-- 页码部分 -->
                <div style="background-color:  rgb(242, 242, 242);">
                    <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
                        {{ snackbar.message }}
                    </v-snackbar>
                    <div class="text-center">
                        <v-pagination v-model="currentPage" :length="totalPages" @update:modelValue="updatePage"
                            :total-visible="10"></v-pagination>
                    </div>
                    <div
                        style="margin-top: 10px; text-align:center;background-color: white;width: 60px; margin-left: 48%">
                        {{ totalItems }}件
                    </div>
                </div>
                <!-- 查询所有对话框 -->
                <v-dialog v-model="openBillNmDialog" max-width="1100">
                    <v-card>
                        <v-card-title>請求書情報</v-card-title>
                        <hr>
                        <v-data-table :headers="billHeaders" :items="billList" item-key="id">
                            <template v-slot:item="{ item }">
                                <tr>
                                    <td><v-btn text @click="fillBillNm(item)"
                                            style="color: white;background-color: green;">選択</v-btn>
                                    </td>
                                    <td>{{ item.billNm }}</td>
                                    <td>{{ item.billCustNm }}</td>
                                    <td>{{ item.billProjectNm }}</td>
                                    <td>{{ formatDate(item.billStartdt) }}</td>
                                    <td>{{ formatDate(item.billEndtdt) }}</td>
                                </tr>
                            </template></v-data-table><v-card-actions><v-spacer></v-spacer>
                            <v-btn @click="openBillNmDialog = false" class="btn">キャンセル</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-dialog>

            </div>
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import moment from 'moment';
import BillApi from "@/api/Bill.js";
import { printPdfFromBlob } from '@/utils/pdfPrint.js';

export default {
    name: 'BillInfoList',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            username: "",
            billQuaryCondition: { billCls: '', billNm: '', billProjectNm: '', billdetlEngineerNm: '', billStartdt: null, billEndtdt: null, billStatus: '' },
            billStatus: [
                { title: '作成済', value: '1' },
                { title: '確認済', value: '2' },
                { title: '送信済', value: '3' },
                { title: '廃止', value: '4' },
            ],
            selectedItems: [], // 存储选中的项
            selectAll: false, // 控制全选框的状态
            menuStart: false,
            menuEnd: false,
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            items: [],
            billList: [],
            currentPage: 1,
            limit: 10,
            totalItems: 0,
            totalPages: 1,
            pageSize: 10,
            pageSizes: [10, 30, 50],
            billStatusMap: {
                1: '作成済',
                2: '確認済',
                3: '送信済',
                4: '廃止',
            },
            openBillNmDialog: false,
            billHeaders: [
                { title: '選択', sortable: false },
                { title: '請求先名', key: 'billNm', sortable: true },
                { title: '取引先名', key: 'billCustNm', sortable: true },
                { title: '案件名', key: 'billProjectNm', sortable: true },
                { title: '請求開始日', key: 'billStartdt', sortable: true },
                { title: '請求終了日', key: 'billEndtdt', sortable: true },
            ],
            isBtnShow: true,
        }
    },
    computed: {
        username() {
            return this.$store.state.username;
        },
        formatDatedStartDt() { return this.formatDate(this.billQuaryCondition.billStartdt) },
        formatDatedEndDt() { return this.formatDate(this.billQuaryCondition.billEndtdt) },
        totalPages() {
            return Math.ceil(this.totalItems / this.limit);
        },
        isEngiDisabled() {
            return this.billQuaryCondition.billCls === "2";
        },
        isCheckboxDisabled() {
            return this.billQuaryCondition.billCls === "2";
        }
    },
    watch: {
        limit: {
            handler() {
                this.currentPage = 1;
                this.selectBillGeneral();
            },
            immediate: true
        },
        selectedItems: {
            handler(newVal) {
                this.selectAll = newVal.length === this.items.length;
            },
            deep: true
        },
        'billQuaryCondition.billCls': function (newVal) {
            if (newVal === "2") {
                this.isBtnShow = false;
                this.selectedItems = []; // 清空已选项
                this.selectAll = false;  // 取消全选
            } else {
                this.isBtnShow = true;
            }
        }
    },
    created() {
        this.selectBillGeneral();
    },

    updated() {
        this.selectBillGeneral();
    },
    mounted() {

    },
    methods: {
        // 控制页码
        updatePageNum(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.selectBillGeneral();
        },
        updatePage(currentPage) {
            console.log('Updating page to:', currentPage);
            this.currentPage = currentPage;
            this.selectBillGeneral();
        },

        selectBillGeneral() {
            BillApi.selectBillGeneral(this.currentPage, this.limit, this.billQuaryCondition).then(response => {
                this.items = response.data.items || []; //无结果则为空
                this.totalItems = response.data.itemsNum || 0; //无结果则为0
                this.totalPages = Math.ceil(this.totalItems / this.pageSize);
                if (this.items.length === 0) {
                    this.snackbar.show = true;
                    this.snackbar.color = 'error';
                    this.snackbar.message = response.message
                    this.$nextTick(() => {
                        this.snackbar.show = true;
                        this.snackbar.color = 'error';
                        this.snackbar.message = response.message
                    });
                } else {
                    this.snackbar.show = false;
                }
                console.log(response.data.items);
            })
                .catch(error => {
                    console.log(error);
                });
        },

        //查询所有
        selectAllBill() {
            BillApi.selectAllBill()
                .then(response => {
                    this.billList = response.data.items || [];
                })
        },

        //删除
        deleteBillById(billNo) {
            if (window.confirm('削除を確認してください')) {
                if (billNo) {
                    BillApi.deleteBillById(billNo)
                        .then(response => {
                            this.snackbar.show = true;
                            this.snackbar.color = 'success';
                            this.snackbar.message = response.message
                            this.selectBillGeneral();
                        })
                        .catch(response => {
                            this.snackbar.show = true;
                            this.snackbar.color = 'error';
                            this.snackbar.message = response.message
                        });
                }
                else {
                    console.log('キャンセル');
                }
            }
        },

        openBill() {
            this.openBillNmDialog = true;
            this.selectAllBill();
        },
        fillBillNm(rowData) {
            this.billQuaryCondition.billNm = rowData.billNm;
            this.openBillNmDialog = false;
        },

        //格式化名称以显示
        formatBillNm(item) {
            if (!item.billStartdt) return item.billProjectNm; // 如果没有日期，只返回项目名称
            const date = new Date(item.billStartdt);
            const month = date.toLocaleString('default', { month: 'numeric' }).slice(0, 1); // 获取完整的月份名的第一个字符
            return `${item.billProjectNm}${month}月`;
        },

        //格式化日期以显示
        formatDate(date) {
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
            const formattedDate = moment(dateObj).format('YYYY/MM/DD');
            return formattedDate;
        },

        async generateBatchPdf() {
            console.log('Selected Items:', this.selectedItems); // 调试信息
            try {
                // 发送请求
                const response = await BillApi.generateBatchSesBillPdfs(this.selectedItems);

                // 处理文件下载
                const url = window.URL.createObjectURL(response.data);
                const a = document.createElement('a');
                a.style.display = 'none';
                a.href = url;
                a.download = '請求書一括.zip';
                document.body.appendChild(a);
                a.click();
                window.URL.revokeObjectURL(url);
                document.body.removeChild(a);
            } catch (error) {
                console.error('There was a problem with the generate batch PDF operation:', error);
            }
        },
        async downloadSesBill(billNo) { //直接跳转url下载
            const a = document.createElement('a');
            a.href = `http://localhost:8081/bill/generateSesBill/${billNo}`;
            a.style.display = 'none';
            document.body.appendChild(a);
            a.click();
            document.body.removeChild(a);
        },

        async printSelectedSesBill() {
            try {
                for (const item of this.selectedItems) {
                    const response = await BillApi.generateSesBillPdf(item);
                    const blob = new Blob([response.data], { type: 'application/pdf' });
                    await printPdfFromBlob(blob, `請求書_${item}.pdf`);
                }
            } catch (error) {
                console.error('error batch print operation:', error.message || error);
            }
        },



        getBillstatus(statusId) {
            return this.billStatusMap[statusId];
        },

        reset() {
            this.billQuaryCondition.billCls = '';
            this.billQuaryCondition.billNm = '';
            this.billQuaryCondition.billProjectNm = '';
            this.billQuaryCondition.billdetlEngineerNm = '';
            this.billQuaryCondition.billStatus = '';
            this.billQuaryCondition.billStartdt = null;
            this.billQuaryCondition.billEndtdt = null;
        },

        toggleAllSelections() {
            this.selectAll = !this.selectAll;
            if (this.selectAll) {
                // 如果是全选，则将所有 billNo 添加到 selectedItems
                this.selectedItems = this.items.map(item => item.billNo);
            } else {
                // 如果是取消全选，则清空 selectedItems
                this.selectedItems = [];
            }
        },

        //路由
        toSesAdd() {
            this.$router.push('/BillSesAdd');
        },
        toDetail(billNo, billCls) {
            if (billCls == '1') {
                this.$router.push({ path: `/ContrSesDetail/${billNo}` });
            } else if (billCls == '2') {
                this.$router.push({ path: `/ContrCrtDetail/${billNo}` });
            } else {
                console.error('Unexpected billCls value:', billCls);
            }
        },
        toCrontrDetail(billNo){
            this.$router.push({ path: `/ContrSesDetail/${billNo}` });
        },
        toEdit(billNo, billCls) {
            if (billCls == '1') {
                this.$router.push({ path: `/BillSesAdd/${billNo}` });
            } else if (billCls == '2') {
                this.$router.push({ path: `/BillSesAdd/${billNo}` });
            } else {
                console.error('Unexpected billCls value:', billCls);
            }
        },
        toCtrAdd() {
            this.$router.push('/ContrCrtAdd');
        },

    }
};
</script>
<style scoped>
.custom-btn-toggle {
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    margin: 0%;
    display: flex;
    width: 130px;
}

.custom-btn-toggle .v-btn {
    border: none;
    border-right: 1px solid #ccc;
    border-radius: 0;
    transition: background-color 0.3s, color 0.3s;
}

.custom-btn-toggle .v-btn:last-child {
    border-right: none;
}

.custom-btn-toggle .v-btn--active {
    background-color: hsl(206, 78%, 60%);
    color: white;
}

.btn {
    margin-left: 3%;
    margin-right: 3%;
    margin-bottom: 2%;
    margin-top: 1%;
    width: 120px;
    height: 40px;
    background-color: rgb(189, 215, 238);
    color: rgb(255, 255, 255) !important;
}

.select-area {
    margin-top: 64px;
    display: flex;
    justify-content: left;
    align-items: start;
    background-color: rgb(242, 242, 242);
    height: auto;
}

.v-table thead th {
    position: sticky;
    top: 0;
    background-color: rgb(217, 217, 217);
    z-index: 1;
    font-size: 16px;
    ;
}

.limitSelect {
    color: rgb(32, 32, 32);
    background-color: #f8f8f8;
    width: 50px;
    height: 30px;
    font-size: 14px;
    text-align: center;
}

td {
    cursor: pointer;
}
</style>