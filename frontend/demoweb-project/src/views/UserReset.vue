<template>
    <v-form>
        <v-container>
            <div
                style="background-color: rgb(0, 114, 159);color: aliceblue;height: 6vh;margin: 3%;border-radius: 10px;display: flex;justify-content: center;">
                <span style="align-self: center;font-size: 20px;font-weight: bold;">バスワードを変更する</span>
            </div>

            <v-btn icon color="rgb(0, 114, 159)" @click="handleClose"
                style="position: absolute; top: 30px; right: 30px;">
                <v-icon>mdi-close</v-icon>
            </v-btn>
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">ユーザー名:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userNm" label="氏名" required disabled="true"></v-text-field>
                </v-col>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">メールアドレス:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userMailaddress" label="e-mail" disabled="true"
                        required></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">パスワード:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userPwd" :rules="passwordRules" label="password" type="password"
                        required></v-text-field>
                </v-col>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">パスワード確認:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field :rules="confirmPasswordRules" label="confirm password" required
                        type="password"></v-text-field>
                </v-col>
            </v-row>
            <v-card-actions style="margin-top: 50px;" class="d-flex justify-center">
                <v-btn color="rgb(0, 114, 159)" variant="elevated" width="300" density="default" size="large"
                    @click="submitForm">変更</v-btn>
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
    name: 'UserReset',
    components: {

    },
    mixins: [],
    props: {
        //获取父组件传入的id
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
                userPwd: '',
                managerCrdUsr: '',
                managerUpdUsr: '',
            },
            items: [],
            passwordRules: [
                v => !!v || 'パスワードは必須です',
                v => /^(?=.*[A-Za-z0-9]).{8,}$/.test(v) || '「バスワード」は英数字「8」文字以上にしてください'
            ],
            confirmPasswordRules: [
                v => !!v || 'パスワード確認は必須です',
                v => (v && v === this.user.userPwd) || 'パスワードが一致しません'
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

    },
    created() {
        this.fetchUserById();
    },

    mounted() {
        this.fetchUserById();
    },
    methods: {
        submitForm() {
            userApi.updatePwdByUserId(this.user).then(response => {
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
                this.user.userPwd = '';
            }).catch((err) => {
                console.error('Error:', err);
            });
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