<template>
    <div>
        <v-app>
            <navigation-drawer style="height: 100%;display: flex;">
                <div style="background-color:white ;display: flex; flex-direction: column; height: 100%;">
                    <div style="margin-top: 0px;padding-top: 0px;">
                        <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                            <v-app-bar-title style="font-size: 30px;color: aliceblue"
                                class="d-flex align-center">バスワードの変更
                            </v-app-bar-title>
                            <div style="color: aliceblue;">{{ username }}</div>
                        </v-app-bar>
                    </div>
                    <v-card height="60vh" style="margin: 10%;">
                        <div
                            style="background-color: rgb(0, 114, 159);color: aliceblue;height: 6vh;margin: 3%;border-radius: 10px;display: flex;justify-content: center;">
                            <span style="align-self: center;font-size: 20px;font-weight: bold;">バスワードを変更する</span>
                        </div>
                        <v-row justify="center">
                            <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                                    style="font-size: large;">パスワード:</span></v-col>
                            <v-col cols="12" md="4">
                                <v-text-field v-model="user.userPwd" :rules="passwordRules" label="password"
                                    type="password" required></v-text-field>
                            </v-col>
                        </v-row>
                        <v-row justify="center">       
                            <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                                    style="font-size: large;">パスワード確認:</span></v-col>
                            <v-col cols="12" md="4">
                                <v-text-field :rules="confirmPasswordRules" label="confirm password" required
                                    type="password"></v-text-field>
                            </v-col>
                        </v-row>
                        <v-card-actions style="margin-top: 50px;" class="d-flex justify-center">
                            <v-btn color="rgb(0, 114, 159)" variant="elevated" width="300" density="default"
                                size="large" @click="submitForm">変更</v-btn>
                        </v-card-actions>
                    </v-card>
                    <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
                        {{ snackbar.message }}
                    </v-snackbar>
                </div>
            </navigation-drawer>
        </v-app>
    </div>
</template>
<script>
import userApi from '@/api/User.js';
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import { setToken,getToken } from '@/utils/auth';

export default {
    name: 'UserSelfReset',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            passwordRules: [
                v => !!v || 'パスワードは必須です',
                v => /^(?=.*[A-Za-z0-9]).{8,}$/.test(v) || '「バスワード」は英数字「8」文字以上にしてください'
            ],
            confirmPasswordRules: [
                v => !!v || 'パスワード確認は必須です',
                v => (v && v === this.user.userPwd) || 'パスワードが一致しません'
            ],
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            user: {
                userPwd: '',
                managerCrdUsr: '',
                managerUpdUsr: '',
                userId: '',
            },
            username: '',
            token: '',
        }
    },
    computed: {
        username() {
            this.user.managerCrdUsr = this.username;
            this.user.managerUpdUsr = this.username;
        }
    },
    watch: {

    },
    created() {
        this.getInfo();
    },

    mounted() {
        this.getInfo();
    },
    methods: {
        submitForm() {
            userApi.updatePwdByUserId(this.user).then(response => {
                this.snackbar.show = true;
                this.snackbar.color = 'success';
                this.snackbar.message = response.message
            }).catch((err) => {
                this.snackbar.show = true;
                this.snackbar.color = 'error';
                this.snackbar.message = err.message || '失敗しました';
                console.error('Error:', err);
            });
        },


        //通过token获取信息
        async getInfo() {
            try {
                const storedToken = getToken();
                const userInfoResponse = await userApi.getInfo(storedToken);
                this.username = userInfoResponse.data.username;
                this.user.userId = userInfoResponse.data.userId;
            } catch (error) {
                console.error('Error:', error);
            }
        },

    }
};
</script>
<style lang='' scoped>
</style>