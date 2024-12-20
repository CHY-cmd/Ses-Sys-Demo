<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div style="background-color:rgb(217, 217, 217) ;display: flex; flex-direction: column; height: 100%;">
                <div style="margin-top: 0px;padding-top: 0px;">
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">契約情報一覧
                        </v-app-bar-title>
                        <div style="color: aliceblue;">{{ username }}</div>
                    </v-app-bar>
                </div>
                <!-- 查询部分 -->
                <div>
                    <div class="select-area">
                        <v-row style="margin-top: 20px;">
                            <v-col>
                                <div style="height: 220px;width: 350px;background-color: rgb(217, 217, 217);">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">取引先名</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">案件名</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">技術者名</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="8" sm="2">
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="contractQuaryCondition.custNm"></v-text-field>
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="contractQuaryCondition.contrProjectNm"></v-text-field>
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="contractQuaryCondition.contrEngineerNm"
                                    :disabled="isEngiDisabled"></v-text-field>
                            </v-col>
                            <v-col>
                                <div
                                    style="height: 220px;width: 350px;background-color: rgb(217, 217, 217);margin-left: 100px;">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">契約区分</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">契約期間</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">契約ステータス</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="8" sm="3" style="margin-right: 5%;">
                                <v-col style="padding-left: 0px;">
                                    <v-btn-toggle v-model="contractQuaryCondition.quotDelflg" class="custom-btn-toggle">
                                        <v-btn value="1">SES</v-btn>
                                        <v-btn value="2">請負</v-btn>
                                    </v-btn-toggle>
                                </v-col>
                                <v-row>
                                    <v-col>
                                        <v-menu ref="menuStart" v-model="menuStart" :close-on-content-click="false"
                                            transition="scale-transition" offset-y min-width="auto">
                                            <template v-slot:activator="{ props }">
                                                <v-text-field v-model="formatDatedStartDt" label="契約開始日"
                                                    bg-color="white" append-icon="mdi-calendar" readonly v-bind="props"
                                                    variant="outlined"></v-text-field>
                                            </template>
                                            <v-date-picker v-model="contractQuaryCondition.contrStartdt" no-title
                                                scrollable @update:modelValue="menuStart = false"></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                    <span style="font-size: x-large;align-content: center;margin-bottom: 5%;">~</span>
                                    <v-col>
                                        <v-menu ref="menuEnd" v-model="menuEnd" :close-on-content-click="false"
                                            transition="scale-transition" offset-y min-width="auto">
                                            <template v-slot:activator="{ props }">
                                                <v-text-field v-model="formatDatedEndDt" label="契約終了日" bg-color="white"
                                                    append-icon="mdi-calendar" readonly v-bind="props"
                                                    variant="outlined"></v-text-field>
                                            </template>
                                            <v-date-picker v-model="contractQuaryCondition.contrEnddt" no-title
                                                scrollable @update:modelValue="menuEnd = false"></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                </v-row>
                                <v-select :items="selectItems" v-model="contractQuaryCondition.contrStatus"
                                    variant="outlined" bg-color="white">
                                </v-select>
                            </v-col>
                        </v-row>
                    </div>
                    <div
                        style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);">
                        <v-btn class="btn" @click="selectContrGeneral">検索</v-btn>
                        <v-btn class="btn" @click="toSesAdd">SES新規登録</v-btn>
                        <v-btn class="btn" @click="">請負新規登録</v-btn>
                        <v-btn class="btn" @click="reset">リセット</v-btn>
                    </div>
                </div>

                <!-- 表格部分 -->
                <div style="background-color: rgb(242, 242, 242); ;margin-top: 0px;display: flex;height: 100%;">
                    <v-table style="height: 100%;width: 100%;">
                        <thead>
                            <tr class="thead">
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
                                    契約番号
                                </th>
                                <th style="text-align: center;">
                                    取引先名
                                </th>
                                <th style="text-align: center;">
                                    案件名
                                </th>
                                <th style="text-align: center;">
                                    技術者名
                                </th>
                                <th style="text-align: center;">
                                    契約ステータス
                                </th>
                                <th style="text-align: center;">
                                    契約開始日
                                </th>
                                <th style="text-align: center;">
                                    備考
                                </th>
                                <th style="text-align: center;">
                                    作成者
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in items" :key="index">
                                <!-- 删除编辑修改入口 -->
                                <td>
                                    <div style="display: flex;margin-top: 5px;margin-bottom: 5px;">
                                        <router-link :to="'/ContrSesAdd/' + item.contrNo"><v-btn text
                                                style="width: 70px;height: 35px;background-color: rgb(189, 215, 238);color: rgb(255, 255, 255);margin-right: 5px;">編集</v-btn></router-link>
                                        <v-btn text @click="deleteContrById(item.contrNo)"
                                            style="width: 70px;height: 35px;background-color: rgb(217, 217, 217);color: rgb(255, 255, 255);margin-right: 5px;">削除</v-btn>
                                    </div>
                                </td>
                                <td style="text-align: center;">{{ (currentPage - 1) * limit + index + 1 }}</td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{ item.contrNo }}</td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{ item.custNm }}</td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{ item.contrProjectNm
                                    }}</td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{ item.contrEngineerNm
                                    }}</td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{
                                    getContrtatus(item.contrStatus) }}
                                </td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{
                                    formatDate(item.contrStartdt) }}</td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)"> {{ item.contrRmk }}
                                </td>
                                <td style="text-align: center;" @click="toDetail(item.contrNo)">{{ item.contrCrdUsr }}
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
            </div>
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import moment from 'moment';
import ContractApi from "@/api/Contract.js";

export default {
    name: 'ContrInfoList',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            username: "",
            contractQuaryCondition: { custNm: '', contrEngineerNm: '', contrProjectNm: '', quotDelflg: '', contrStartdt: null, contrEnddt: null, contrStatus: '' },
            selectItems: [
                { title: '契約中', value: '1' },
                { title: '契約終了', value: '2' },
                { title: '契約予定', value: '3' },
            ],

            menuStart: false,
            menuEnd: false,
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            items: [],
            currentPage: 1,
            limit: 10,
            totalItems: 0,
            totalPages: 1,
            pageSize: 10,
            pageSizes: [10, 30, 50],
            contrStatusMap: {
                1: '契約中',
                2: '契約終了',
                3: '契約予定',
            },
        }
    },
    computed: {
        username() {
            return this.$store.state.username;
        },
        formatDatedStartDt() { return this.formatDate(this.contractQuaryCondition.contrStartdt) },
        formatDatedEndDt() { return this.formatDate(this.contractQuaryCondition.contrEnddt) },
        totalPages() {
            return Math.ceil(this.totalItems / this.limit);
        },
        isEngiDisabled() {
            return this.a === "2";
        },
    },
    watch: {
        limit: {
            handler() {
                this.currentPage = 1;
                this.selectContrGeneral();
            },
            immediate: true
        }
    },
    created() {
        this.selectContrGeneral();
    },

    updated() {
        this.selectContrGeneral();
    },
    mounted() {

    },
    methods: {
        // 控制页码
        updatePageNum(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.selectContrGeneral();
        },
        updatePage(currentPage) {
            console.log('Updating page to:', currentPage);
            this.currentPage = currentPage;
            this.selectContrGeneral();
        },

        selectContrGeneral() {
            ContractApi.selectContrGeneral(this.currentPage, this.limit, this.contractQuaryCondition).then(response => {
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

        //删除
        deleteContrById(contrNo) {
            if (window.confirm('削除を確認してください')) {
                if (contrNo) {
                    ContractApi.deleteContrById(contrNo)
                        .then(response => {
                            this.snackbar.show = true;
                            this.snackbar.color = 'success';
                            this.snackbar.message = response.message
                            this.selectContrGeneral();
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


        getContrtatus(statusId) {
            return this.contrStatusMap[statusId];
        },

        reset() {
            this.contractQuaryCondition.custNm = '';
            this.contractQuaryCondition.contrEngineerNm = '';
            this.contractQuaryCondition.contrProjectNm = '';
            this.contractQuaryCondition.contrStatus = '';
            this.contractQuaryCondition.quotDelflg = '';
            this.contractQuaryCondition.contrStartdt = null;
            this.contractQuaryCondition.contrEnddt = null;
        },
        //路由
        toSesAdd() {
            this.$router.push('/ContrSesAdd');
        },
        toDetail(contrNo) {
            this.$router.push({ path: `/ContrSesDetail/${contrNo}` });
        }
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