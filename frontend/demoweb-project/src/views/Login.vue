<template>
    <div>
        <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
            <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">ITEC社内総合管理システム
            </v-app-bar-title>
        </v-app-bar>

        <v-container>
            <v-row justify="center" align="center" style="height: 100vh;">
                <v-col cols="12" sm="8" md="5">
                    <v-card class="elevation-10">
                        <div
                            style="background-color: rgb(0, 114, 159);color: aliceblue;height: 6vh;margin: 3%;border-radius: 10px;display: flex;justify-content: center;">
                            <span style="align-self: center;font-size: 20px;font-weight: bold;">ユーザーログイン</span>
                        </div>
                        <v-divider></v-divider>
                        <v-card-text>
                            <v-form ref="loginForm" v-model="valid" lazy-validation>
                                <v-text-field v-model="loginForm.userMailaddress" :rules="usernameRules" label="ユーザーID"
                                    type="email" required></v-text-field>
                                <v-text-field v-model="loginForm.userPwd" :rules="passwordRules" label="パスワード"
                                    type="password" required></v-text-field>
                            </v-form>
                        </v-card-text>
                        <v-card-actions>
                            <v-btn color="blue" @click="handleLogin" height="45" variant="outlined" block>ログイン</v-btn>
                        </v-card-actions>
                    </v-card>
                </v-col>
            </v-row>
        </v-container>
        <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
            {{ snackbar.message }}
        </v-snackbar>
    </div>
</template>
<script>
import userApi from '@/api/User.js';
import { setToken,getToken } from '@/utils/auth';

export default {
    name: 'Login',
    components: {

    },
    props: {

    },
    data() {
        return {
            valid: true,
            token: '',
            loginForm: {
                userMailaddress: '',
                userPwd: ''
            },
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            data: {
                username: "",
            },
            usernameRules: [
                v => !!v || 'ユーザーIDは必須です',
                v => /.+@.+/.test(v) || '有効なメールアドレスを入力してください'
            ],
            passwordRules: [
                v => !!v || 'パスワードは必須です',
                v => /^(?=.*[A-Za-z0-9]).{8,}$/.test(v) || '「バスワード」は英数字「8」文字以上にしてください'
            ],
            loginAttempts: 0,
            lockTime: 60 * 1000,
        }
    },
    computed: {

    },
    watch: {

    },
    mounted() {

    },
    methods: {
        async handleLogin() {
            if (this.$refs.loginForm.validate()) {
                try {
                    const response = await userApi.login(this.loginForm);
                    this.$store.commit('login'); // 标记已登录
                    const token = response.data.token;
                    setToken(token);

                    // 获取用户信息
                    const storedToken = getToken();
                    const userInfoResponse = await userApi.getInfo(storedToken);
                    this.username = userInfoResponse.data.username;
                    this.$store.commit('setUsername', this.username); // 存储用户名
                    this.$router.push({ path: this.redirect || '/CustInfoList' });
                } catch (err) {
                    this.snackbar.show = true;
                    this.snackbar.color = 'error';
                    this.snackbar.message = err.message || 'ログインに失敗しました';
                    console.error('Error:', err);
                }
            } else {
                console.log('error submit');
            }
        },

    }
};
</script>
<style scoped></style>