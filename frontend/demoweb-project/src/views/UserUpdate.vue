<template>
    <v-form ref="form" v-model="valid">
        <v-container>
            <div
                style="background-color: rgb(0, 114, 159);color: aliceblue;height: 6vh;margin: 3%;border-radius: 10px;display: flex;justify-content: center;">
                <span style="align-self: center;font-size: 20px;font-weight: bold;">ユーザー情報編集</span>
            </div>

            <v-btn icon color="rgb(0, 114, 159)" @click="handleClose"
                style="position: absolute; top: 30px; right: 30px;">
                <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">ユーザー名:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userNm" label="氏名" required></v-text-field>
                </v-col>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">メールアドレス:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userMailaddress" :rules="emailRules" label="e-mail" disabled="true"
                        required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">延長パスワード期限:</span></v-col>
                <v-col cols="12" md="4">
                    <v-select :items="selectItemsExp" v-model="daysToAdd" item-title="title"
                        item-value="value"></v-select>
                </v-col>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">部署:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userDepartment" required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">権限:</span></v-col>
                <v-col cols="12" md="4">
                    <v-select :items="selectItemsRole" v-model="user.userRole" required item-title="title"
                        item-value="value"></v-select>
                </v-col>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">ロック状態:</span></v-col>
                <v-col cols="12" md="4">
                    <v-select :items="selectItemsLockFlg" v-model="user.userLockFlg" required></v-select>
                </v-col>
            </v-row>
            <v-card-actions style="margin-top: 50px;" class="d-flex justify-center">
                <v-btn color="rgb(0, 114, 159)" variant="elevated" width="300" density="default" size="large"
                    @click="submitForm">登録</v-btn>
            </v-card-actions>
        </v-container>
    </v-form>
    <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
        {{ snackbar.message }}
    </v-snackbar>
</template>
<script>
import userApi from '@/api/User.js';

export default {
    name: 'UserUpdate',
    components: {

    },
    mixins: [],
    props: {
        userId: {
            type: [String, Number],
            required: true,
        },
        emits: ['close'],
    },
    data() {
        return {
            valid: true,
            username: '',
            user: {
                userNm: '',
                userPwd: '',
                userRole: 0,
                userMailaddress: '',
                userPwExpiration: new Date(),
                userExPwDay: '',
                userDepartment: '',
                userLockFlg: '',
                userDelFlg: '',
                managerCrdUsr: '',
                managerUpdUsr: '',
            },
            items: [],
            daysToAdd: 0,
            selectItemsExp: [
                { title: '延長しない', value: 0 },
                { title: '30日', value: 30 },
                { title: '60日', value: 60 },
                { title: '90日', value: 90 },
            ],
            selectItemsRole: [
                { title: '管理', value: 1 },
                { title: '営業', value: 2 },
                { title: '総務', value: 3 },
                { title: '一般', value: 4 },
            ],
            selectItemsLockFlg: [
                { title: '未ロック', value: '0' },
                { title: 'ロック済み', value: '1' },
            ],
            emailRules: [
                v => !!v || 'メールアドレスは必須です',
                v => /.+@.+/.test(v) || '有効なメールアドレスを入力してください'
            ],
            otherRule: [
                v => !!v || '必須項目',
            ],
            valid: false, // 表单验证状态
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
        }
    },
    computed: {
        username() {
            this.user.managerCrdUsr = this.$store.state.username;
            this.user.managerUpdUsr = this.$store.state.username;
        }
    },
    watch: {
        daysToAdd(newDays) {
            this.updateUserPwExpiration(newDays);
        },
    },
    created() {
        this.fetchUserById();
    },

    mounted() {
        this.fetchUserById();
    },
    methods: {
        submitForm() {
            this.updateUserPwExpiration(this.daysToAdd);
            userApi.updateUserByUserId(this.user).then(response => {
                this.snackbar.show = true;
                this.snackbar.color = 'success';
                this.snackbar.message = response.message
                //延迟关闭弹窗
                setTimeout(() => {
                    this.handleClose();
                }, 1000);
            }).catch((err) => {
                this.snackbar.show = true;
                this.snackbar.color = 'error';
                this.snackbar.message = err.message || '失敗しました';
                console.error('Error:', err);
            });
        },

        //通过id查询回显
        fetchUserById() {
            userApi.selectUserById(this.userId).then(response => {
                this.user = response.data.items;
            }).catch((err) => {
                console.error('Error:', err);
            });
        },

        //格式化传输后端
        formatDateToBackend(date) {
            const year = date.getFullYear();
            const month = String(date.getMonth() + 1).padStart(2, '0');
            const day = String(date.getDate()).padStart(2, '0');
            const hours = String(date.getHours()).padStart(2, '0');
            const minutes = String(date.getMinutes()).padStart(2, '0');
            const seconds = String(date.getSeconds()).padStart(2, '0');
            return `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
        },
        updateUserPwExpiration(days) {
            if (!isNaN(days)) {
                let expirationDate;
                if (this.user.userPwExpiration) {
                    expirationDate = new Date(this.user.userPwExpiration);
                } else {
                    expirationDate = new Date();
                }
                expirationDate.setDate(expirationDate.getDate() + parseInt(days));
                this.user.userPwExpiration = this.formatDateToBackend(expirationDate);
                console.log('userPwExpiration 更新:', this.user.userPwExpiration);
            } else {
                this.user.userPwExpiration = '';
            }
        },

        handleClose() {
            this.$emit('close'); // 触发 close 事件
        },

        resetFrom() {
            this.user.userNm = '',
                this.user.userPwd = '',
                this.user.userRole = '',
                this.user.userMailaddress = '',
                this.user.userPwExpiration = '',
                this.user.userExPwDay = ''
        },

    }
};
</script>
<style scoped></style>