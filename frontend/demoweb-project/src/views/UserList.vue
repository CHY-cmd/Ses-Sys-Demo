<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div style="background-color:rgb(217, 217, 217) ;display: flex; flex-direction: column; height: 100%;">
                <div style="margin-top: 0px;padding-top: 0px;">
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">ユーザー情報管理
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
                                        <p style="padding: 5%;color: rgb(40, 40, 40);">ユーザー名</p>
                                        <p style="padding: 5%;color: rgb(40, 40, 40);">権限</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col cols="8" sm="2">
                                <v-text-field variant="outlined" bg-color="white"
                                    v-model="userQuaryCondition.userNm"></v-text-field>
                                <v-select :items="selectItems" v-model="userQuaryCondition.userRole" variant="outlined"
                                    bg-color="white"></v-select>
                            </v-col>
                            <v-col>
                                <div
                                    style="height: 135px;width: 300px;background-color: rgb(217, 217, 217);margin-left: 120px;">
                                    <div style="text-align: center;font-size: 18px;">
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">メールアドレス</p>
                                        <p style="padding: 6%;color: rgb(40, 40, 40);">ロック状態</p>
                                    </div>
                                </div>
                            </v-col>
                            <v-col>
                                <v-text-field variant="outlined" bg-color="white" width="250"
                                    v-model="userQuaryCondition.userMailaddress"></v-text-field>
                                <v-checkbox v-model="isUserLocked" color="primary"></v-checkbox>
                            </v-col>
                        </v-row>
                    </div>
                    <div
                        style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);height: 60px;">
                        <v-btn class="btn" @click="selectUserInfo">検索</v-btn>
                        <v-btn class="btn" @click="showAddDialog = true">新規作成</v-btn>
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
                                    番号
                                </th>
                                <th style="text-align: center;">
                                    ユーザー名
                                </th>
                                <th style="text-align: center;">
                                    権限
                                </th>
                                <th style="text-align: center;">
                                    メールアドレス
                                </th>
                                <th style="text-align: center;">
                                    パスワード期限
                                </th>
                                <th style="text-align: center;">
                                    ロック状態
                                </th>
                                <th style="text-align: center;">
                                    作成者
                                </th>
                                <th style="text-align: center;">
                                    作成日時
                                </th>
                                <th style="text-align: center;">
                                    更新者
                                </th>
                                <th style="text-align: center;">
                                    更新日時
                                </th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr v-for="(item, index) in items" :key="index">
                                <!-- 删除编辑修改入口 -->
                                <td>
                                    <div style="display: flex;margin-top: 5px;margin-bottom: 5px;">
                                        <v-btn text @click="openUpdateDialog(item.userId)"
                                            style="width: 70px;height: 35px;background-color: rgb(189, 215, 238);color: rgb(255, 255, 255);margin-right: 5px;">編集</v-btn>
                                        <v-btn text @click="deleteUserByID(item.userId)"
                                            style="width: 70px;height: 35px;background-color: rgb(217, 217, 217);color: rgb(255, 255, 255);margin-right: 5px;">削除</v-btn>
                                    </div>
                                    <div style="margin-bottom: 5px;"><v-btn text @click="openResetDialog(item.userId)"
                                            style="width: 145px;height: 35px;background-color: rgb(189, 215, 238);color: rgb(255, 255, 255);">レセットバスワード</v-btn>
                                    </div>
                                </td>
                                <td style="text-align: center;">{{ (currentPage - 1) * limit + index + 1 }}</td>
                                <td style="text-align: center;">{{ item.userNm }}</td>
                                <td style="text-align: center;">{{ getRoleName(item.userRole) }}</td>
                                <td style="text-align: center;">{{ item.userMailaddress }}</td>
                                <td style="text-align: center;">{{ item.userPwExpiration }}</td>
                                <td style="text-align: center;">{{ getUserLock(item.userLockFlg) }}</td>
                                <td style="text-align: center;">{{ item.managerCrdUsr }}</td>
                                <td style="text-align: center;"> {{ item.managerCrdDt }}</td>
                                <td style="text-align: center;">{{ item.managerUpdUsr }}</td>
                                <td style="text-align: center;">{{ item.managerUpdDt }}</td>
                            </tr>
                        </tbody>
                    </v-table>
                </div>

                <!-- 新增 -->
                <V-dialog v-model="showAddDialog" persistent min-height="600">
                    <v-card min-height="600"><user-add @close="closeAddDialog"></user-add></v-card>
                </V-dialog>

                <!-- 编辑 -->
                <V-dialog v-model="showUpdateDialog" v-if="showUpdateDialog && selectedUserId !== null" persistent
                    min-height="600">
                    <v-card min-height="600"><user-update @close="closeUpdateDialog"
                            :userId="selectedUserId"></user-update></v-card>
                </V-dialog>

                <!-- 密码重置 -->
                <V-dialog v-model="showResetDialog" v-if="showResetDialog && selectedUserId !== null" persistent
                    min-height="600">
                    <v-card min-height="600"><user-reset @close="closeResetDialog"
                            :userId="selectedUserId"></user-reset></v-card>
                </V-dialog>

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
import UserAdd from "@/views/UserAdd.vue";
import UserUpdate from "@/views/UserUpdate.vue";
import UserReset from "@/views/UserReset.vue";
import UserApi from '@/api/User.js';

export default {
    name: 'UserList',
    components: {
        NavigationDrawer,
        UserAdd,
        UserUpdate,
        UserReset
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            currentPage: 1,
            limit: 10,
            userQuaryCondition: {
                userMailaddress: '',
                userNm: '',
                userRole: '',
                userLockFlg: '0',
            },
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            username: "",
            items: [],
            totalItems: 0,
            totalPages: 1,
            pageSize: 10,
            pageSizes: [10, 30, 50],
            selectItems: [
                { title: '管理', value: '1' },
                { title: '営業', value: '2' },
                { title: '総務', value: '3' },
                { title: '一般', value: '4' },
            ],
            showAddDialog: false,
            showUpdateDialog: false,
            showResetDialog: false,
            selectedUserId: null,
            roleMap: {
                1: '管理',
                2: '営業',
                3: '総務',
                4: '一般',
            },
            lockMap: {
                0: '未ロック',
                1: 'ロック済み',
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
        //数据库为char类型，checkbox的boolean转换成字符串
        isUserLocked: {
            get() {
                return this.userQuaryCondition.userLockFlg === '1';
            },
            set(value) {
                this.userQuaryCondition.userLockFlg = value ? '1' : '0';
            }
        }
    },
    watch: {
        limit: {
            handler() {
                this.currentPage = 1;
                this.selectUserInfo();
            },
            immediate: true
        }
    },
    created() {
        this.selectUserInfo();
    },

    updated() {
        this.selectUserInfo();
    },
    mounted() {

    },
    methods: {
        // 控制页码
        updatePageNum(limit) {
            this.limit = limit;
            this.currentPage = 1;
            this.selectUserInfo();
        },
        updatePage(currentPage) {
            console.log('Updating page to:', currentPage);
            this.currentPage = currentPage;
            this.selectUserInfo();
        },

        //查询
        selectUserInfo() {
            UserApi.selectUserInfo(this.currentPage, this.limit, this.userQuaryCondition).then(response => {
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
        deleteUserByID(userId) {
            if (window.confirm('削除を確認してください')) {
                if (userId) {
                    UserApi.deleteUserByID(userId)
                        .then(response => {
                            this.snackbar.show = true;
                            this.snackbar.color = 'success';
                            this.snackbar.message = response.message
                            this.selectUserInfo();
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

        getRoleName(roleId) {
            return this.roleMap[roleId];
        },

        getUserLock(lockFlg) {
            return this.lockMap[lockFlg];
        },

        closeAddDialog() {
            this.showAddDialog = false;
            this.selectUserInfo();
        },

        openUpdateDialog(userId) {
            console.log('Opening dialog with userId:', userId);
            this.selectedUserId = userId;
            console.log(' selectedUserId:', this.selectedUserId);
            this.showUpdateDialog = true;
        },

        closeUpdateDialog() {
            this.showUpdateDialog = false;
            this.selectUserInfo();
        },

        openResetDialog(userId) {
            console.log('Opening dialog with userId:', userId);
            this.selectedUserId = userId;
            console.log(' selectedUserId:', this.selectedUserId);
            this.showResetDialog = true;
        },
        closeResetDialog() {
            this.showResetDialog = false;
            this.selectUserInfo();
        },

        reset() {
            this.userQuaryCondition.userMailaddress = '';
            this.userQuaryCondition.userNm = '';
            this.userQuaryCondition.userRole = '';
            this.userQuaryCondition.userLockFlg = '';
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
</style>