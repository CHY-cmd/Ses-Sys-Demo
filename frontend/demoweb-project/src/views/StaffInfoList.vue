<template>
    <div>
        <v-app>
            <navigation-drawer style="height: 100%;display: flex;">
                <div style="background-color:rgb(217, 217, 217) ;display: flex; flex-direction: column; height: 100%;">
                    <div style="margin-top: 0px;padding-top: 0px;">
                        <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                            <v-app-bar-title style="font-size: 30px;color: aliceblue"
                                class="d-flex align-center">スタッフ情報一覧
                            </v-app-bar-title>
                            <div style="color: aliceblue;">{{ username }}</div>
                        </v-app-bar>
                    </div>

                    <!-- 查询部分 -->
                    <div>
                        <div class="select-area">
                            <v-row style="margin-top: 20px;">
                                <v-col>
                                    <div style="margin-right: 60px;background-color: rgb(217, 217, 217);">
                                        <div style="text-align: center;font-size: 18px;">
                                            <p style="padding: 5%;color: rgb(40, 40, 40);">氏名</p>
                                            <p style="padding: 5%;color: rgb(40, 40, 40);">スタッフ区分</p>
                                            <p style="padding: 5%;color: rgb(40, 40, 40);">営業ステータス</p>
                                        </div>
                                    </div>
                                </v-col>
                                <v-col cols="8" sm="5" style="margin-right: 80px;">
                                    <v-text-field variant="outlined" bg-color="white"
                                        v-model="staffQuaryCondition.staffNm"></v-text-field>
                                    <v-select :items="selectClsItems" v-model="staffQuaryCondition.staffCls"
                                        variant="outlined" bg-color="white"></v-select>
                                    <v-select :items="selectSalesstatusItems"
                                        v-model="staffQuaryCondition.staffSalesstatus" variant="outlined"
                                        bg-color="white"></v-select>
                                </v-col>
                                <v-col cols="8" sm="2"></v-col>
                            </v-row>
                        </div>
                        <div
                            style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);height: 60px;">
                            <v-btn class="btn" @click="selectStaffInfo">検索</v-btn>
                            <v-btn class="btn" @click="addCust">新規入力</v-btn>
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
                                        氏名
                                    </th>
                                    <th class="text-left">
                                        スタッフ区分
                                    </th>
                                    <th class="text-left">
                                        営業ステータス
                                    </th>
                                    <th class="text-left">
                                        備考
                                    </th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr v-for="(item, index) in items" :key="index">
                                    <!-- 删除编辑入口 -->
                                    <td>
                                        <router-link :to="'/StaffAdd/' + item.staffId"><v-btn text
                                                style="width: 70px;height: 35px;background-color: rgb(189, 215, 238);color: rgb(255, 255, 255)">編集</v-btn></router-link>
                                    </td>
                                    <td><v-btn text @click="deleteStaffByID(item.staffId)"
                                            style="width: 70px;height: 35px;background-color: rgb(217, 217, 217);color: rgb(255, 255, 255)">削除</v-btn>
                                    </td>
                                    <td>{{ (currentPage - 1) * limit + index + 1 }}</td>
                                    <td @click="toDetail(item.staffId)">{{ item.staffNm }}</td>
                                    <td @click="toDetail(item.staffId)">{{ getRoleName(item.staffCls) }}</td>
                                    <td @click="toDetail(item.staffId)">{{ getSaleStatus(item.staffSalesstatus) }}</td>
                                    <td @click="toDetail(item.staffId)">{{ item.staffRmk1 }}</td>
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
    </div>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import StaffApi from '@/api/Staff.js';

export default {
    name: 'StaffInfoList',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            items: [],
            currentPage: 1,
            limit: 10,
            totalItems: 0,
            totalPages: 1,
            pageSize: 10,
            pageSizes: [10, 30, 50],
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            staffQuaryCondition: {
                staffNm: '',
                staffCls: '',
                staffSalesstatus: ''
            },
            selectClsItems: [
                { title: '正社員', value: '1' },
                { title: '契約社員', value: '2' },
                { title: '協力会社', value: '3' },
                { title: '個人事業主', value: '4' },
            ],
            selectSalesstatusItems: [
                { title: '稼働中', value: '1' },
                { title: '営業稼働中', value: '2' },
                { title: '営業中', value: '3' },
                { title: '稼働予定', value: '4' },
                { title: '休職', value: '5' },
                { title: '退職済', value: '6' },
            ],
            roleMap: {
                1: '正社員',
                2: '契約社員',
                3: '協力会社',
                4: '個人事業主',
            },
            saleStatusMap: {
                1: '稼働中',
                2: '営業稼働中',
                3: '営業中',
                4: '稼働予定',
                5: '休職',
                6: '退職済',
            },
        }
    },
    computed: {
        totalPages() {
            return Math.ceil(this.totalItems / this.limit);
        },
        username() {
            return this.$store.state.username;
        },
    },
    watch: {
        limit: {
            handler() {
                this.currentPage = 1;
                this.selectStaffInfo();
            },
            immediate: true
        }
    },
    mounted() {
        this.selectStaffInfo();
    },
    created() {
        this.selectStaffInfo();
    },
    methods: {
        // 控制页码
        updatePageNum(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.selectStaffInfo();
        },
        updatePage(currentPage) {
            console.log('Updating page to:', currentPage);
            this.currentPage = currentPage;
            this.selectStaffInfo();
        },

        //查询
        selectStaffInfo() {
            StaffApi.selectStaffInfo(this.currentPage, this.limit, this.staffQuaryCondition).then(response => {
                this.items = response.data.items || [];
                this.totalItems = response.data.itemsNum || 0;
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
        deleteStaffByID(staffId) {
            if (window.confirm('削除を確認してください')) {
                if (staffId) {
                    StaffApi.deleteStaffByID(staffId)
                        .then(response => {
                            this.snackbar.show = true;
                            this.snackbar.color = 'success';
                            this.snackbar.message = response.message
                            this.selectStaffInfo();
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

        //数据匹配
        getRoleName(roleId) {
            return this.roleMap[roleId];
        },
        getSaleStatus(statusId) {
            return this.saleStatusMap[statusId];
        },

        reset() {
            this.staffQuaryCondition.staffNm = '';
            this.staffQuaryCondition.staffCls = '';
            this.staffQuaryCondition.staffSalesstatus = '';
            this.currentPage = 1;
        },
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