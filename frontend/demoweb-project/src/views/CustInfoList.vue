<template>
    <v-app >
        <navigation-drawer style="height: 100%;display: flex;">
            <div style="background-color:rgb(217, 217, 217) ;display: flex; flex-direction: column; height: 100%;">
                <div style="margin-top: 0px;padding-top: 0px;">
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">取引先情報一覧
                        </v-app-bar-title>
                        <div style="color: aliceblue;">{{ username }}</div>
                    </v-app-bar>
                </div>

                <!-- 查询部分 -->
                <div>
                    <div class="select-area">
                        <v-row style="margin-top: 20px;">
                            <v-col>
                                <div style="height: 135px;width: 350px;background-color: rgb(217, 217, 217);">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 5%;color: rgb(40, 40, 40);">取引先名</p>
                                        <p style="padding: 5%;color: rgb(40, 40, 40);">他社担当者</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="8" sm="2">
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="generalQuaryCondition.custNm"></v-text-field>
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="generalQuaryCondition.managerNm"></v-text-field>
                            </v-col>
                            <v-col>
                                <div
                                    style="height: 135px;width: 300px;background-color: rgb(217, 217, 217);margin-left: 120px;">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">Pマーク / ISMS</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">派遣資格</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col>
                                <v-btn-toggle v-model="generalQuaryCondition.custInforSecurity"
                                    class="custom-btn-toggle">
                                    <v-btn value="true">あり</v-btn>
                                    <v-btn value="false">なし</v-btn>
                                </v-btn-toggle>
                                <v-btn-toggle v-model="generalQuaryCondition.custDispatch" class="custom-btn-toggle">
                                    <v-btn value="true">あり</v-btn>
                                    <v-btn value="false">なし</v-btn>
                                </v-btn-toggle>
                            </v-col>
                        </v-row>
                    </div>
                    <div
                        style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);">
                        <v-btn class="btn" @click="selectGeneral">検索</v-btn>
                        <v-btn class="btn" @click="addCust">新規登録</v-btn>
                        <v-btn class="btn" @click="reset">リセット</v-btn>
                    </div>
                </div>

                <!-- 表格部分 -->
                <div style="background-color: rgb(242, 242, 242); ;margin-top: 0px;height: 100%;display: flex;">
                    <v-table style="width: 100%;height: 100%;">
                        <thead>
                            <tr class="thead">
                                <th class="text-right" style="width: 120px;">
                                    表示件数
                                </th>
                                <th class="text-left" style="width: 200px;">
                                    <select v-model="limit" class="limitSelect">
                                        <option v-for="size in pageSizes" :value="size">{{ size }}</option>
                                    </select>
                                </th>
                                <th class="text-left" style="width: 180px;">
                                    No.
                                </th>
                                <th class="text-left">
                                    取引先名
                                </th>
                                <th class="text-left">
                                    電話番号
                                </th>
                                <th class="text-left">
                                    他社担当者
                                </th>
                                <th class="text-left">
                                    住所
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in items" :key="index">
                                <!-- 删除编辑入口 -->
                                <td>
                                    <router-link :to="'/CustinfoAdd/' + item.custId"><v-btn text
                                            style="width: 70px;height: 35px;background-color: rgb(189, 215, 238);color: rgb(255, 255, 255)">編集</v-btn></router-link>
                                </td>
                                <td><v-btn text @click="deleteCustByID(item.custId)"
                                        style="width: 70px;height: 35px;background-color: rgb(217, 217, 217);color: rgb(255, 255, 255)">削除</v-btn>
                                </td>
                                <td>{{ (currentPage - 1) * limit + index + 1 }}</td>
                                <td @click="toDetail(item.custId)">{{ item.custNm }}</td>
                                <td @click="toDetail(item.custId)">{{ item.custPhone }}</td>
                                <td @click="toDetail(item.custId)">{{ item.managerNm }}</td>
                                <td @click="toDetail(item.custId)">{{ item.custAddress }}</td>
                            </tr>
                        </tbody>
                    </v-table>
                </div>


                <!-- 页码部分 -->
                <div style="background-color:  rgb(242, 242, 242);">
                    <v-snackbar v-model="snackbar" :timeout="3000" top right v-if="noDataMessage">
                        {{ noDataMessage }}
                    </v-snackbar>
                    <div class="text-center">
                        <v-pagination v-model="currentPage" :length="totalPages" @update:modelValue="updatePage"
                            :total-visible="10"></v-pagination>
                        <!-- <div class="pagination">
                        <Pagination v-model="currentPage" :total-pages="totalPages" :page-size="pageSize"
                            @update:currentPage="updatePage" />
                    </div> -->
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
import CustInfoListApi from '@/api/CustInfoList.js';
import Pagination from '@/components/Pagination.vue';
import NavigationDrawer from "@/components/NavigationDrawer.vue";

export default {
    name: 'CustInfoList',

    components: {
        Pagination,
        NavigationDrawer
    },

    data() {
        return {
            currentPage: 1,
            limit: 10,
            generalQuaryCondition: {
                custNm: '',
                managerNm: '',
                custInforSecurity: null,
                custDispatch: null
            },
            items: [],
            totalItems: 0,
            noDataMessage: '',
            snackbar: false,
            pageSizes: [10, 30, 50],
            totalPages: 1,
            pageSize: 10
        };
    },

    // 计算总页数
    computed: {
        totalPages() {
            return Math.ceil(this.totalItems / this.limit);
        },
        username() {
            return this.$store.state.username;
        }
    },

    created() {
        this.selectGeneral();
    },

    updated() {
        this.selectGeneral();
    },

    mounted() {

    },

    // 监听分页limit变化
    watch: {
        limit: {
            handler() {
                this.currentPage = 1;
                this.selectGeneral();
            },
            immediate: true
        }
    },

    methods: {
        // 控制页码
        updatePageNum(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.selectGeneral();
        },
        updatePage(currentPage) {
            console.log('Updating page to:', currentPage);
            this.currentPage = currentPage;
            this.selectGeneral();
        },

        // 获取一览结果
        selectGeneral() {
            console.log('Fetching data for page:', this.currentPage, 'with limit:', this.limit);
            CustInfoListApi.selectGeneral(this.currentPage, this.limit, this.generalQuaryCondition)
                .then(response => {
                    this.items = response.data.items || [];
                    this.totalItems = response.data.itemsNum || 0;
                    this.totalPages = Math.ceil(this.totalItems / this.pageSize);
                    if (this.items.length === 0) {
                        this.updateNoDataMessage('検索結果は0件です。');
                        this.$nextTick(() => {
                            this.snackbar = true;
                        });
                    } else {
                        this.noDataMessage = '';
                        this.snackbar = false;
                    }
                    console.log(response.data.items);
                })
                .catch(error => {
                    console.log(error);
                });
        },

        // 新增
        addCust() {
            this.$router.push('/CustInfoAdd');
        },

        // 删除
        deleteCustByID(custId) {
            if (window.confirm('削除を確認してください')) {
                if (custId) {
                    CustInfoListApi.deleteCustByID(custId)
                        .then(response => {
                            this.selectGeneral();
                        })
                        .catch(error => {
                            console.error('削除に失敗しました:', error);
                        });
                }
                else {
                    console.log('キャンセル');
                }
            }
        },

        updateNoDataMessage(message) {
            this.noDataMessage = message;
        },

        // 重置
        reset() {
            this.generalQuaryCondition.custNm = '';
            this.generalQuaryCondition.managerNm = '';
            this.generalQuaryCondition.custInforSecurity = null;
            this.generalQuaryCondition.custDispatch = null;
            this.currentPage = 1;
        },

        //跳转详情页
        toDetail(custId) {
            this.$router.push({ path: `/CustInfoDetail/${custId}` });
        }

    }
};
</script>
<style scoped>
.custom-btn-toggle {
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    margin: 3%;
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
    width: 90px;
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
}

.limitSelect {
    color: rgb(32, 32, 32);
    background-color: #f8f8f8;
    width: 50px;
    height: 30px;
    font-size: 14px;
    text-align: center;
}

.custom-table tbody tr:nth-child(even) {
    background-color: #f2f2f2;
}

td {
    cursor: pointer;
}
</style>