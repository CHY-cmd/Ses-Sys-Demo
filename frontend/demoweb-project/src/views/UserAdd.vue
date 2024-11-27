<template>
    <v-form ref="form" v-model="valid">
        <v-container>
            <div
                style="background-color: rgb(0, 114, 159);color: aliceblue;height: 6vh;margin: 3%;border-radius: 10px;display: flex;justify-content: center;">
                <span style="align-self: center;font-size: 20px;font-weight: bold;">新規ユーザー登録</span>
            </div>
            <v-btn icon color="rgb(0, 114, 159)" @click="this.$emit('close');"
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
                    <v-text-field v-model="user.userMailaddress" :rules="emailRules" label="e-mail"
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
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">パスワード期限:</span></v-col>
                <v-col cols="12" md="4">
                    <v-select :items="selectItemsExp" v-model="daysToAdd" required
                        :rules="otherRule"></v-select>
                </v-col>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">部署:</span></v-col>
                <v-col cols="12" md="4">
                    <v-text-field v-model="user.userDepartment" required :rules="otherRule"></v-text-field>
                </v-col>
            </v-row>
            <v-row>
                <v-col cols="12" md="2" class="d-flex align-center justify-end"><span
                        style="font-size: large;">権限:</span></v-col>
                <v-col cols="12" md="4">
                    <v-select :items="selectItemsRole" v-model="user.userRole" required :rules="otherRule"></v-select>
                </v-col>
            </v-row>
            <v-card-actions style="margin-top: 50px;" class="d-flex justify-center">
                <v-btn color="rgb(0, 114, 159)" variant="elevated" width="300" density="default" size="large"
                    @click="submitForm" :disabled="!valid">登録</v-btn>
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
    name: 'UserAdd',
    components: {

    },
    mixins: [],
    props: {

    },
    data() {
        return {
            valid: true,
            username: '',
            user: {
                userNm: '',
                userPwd: '',
                userRole: '',
                userMailaddress: '',
                userPwExpiration: '',
                userExPwDay: '',
                userDepartment: '',
                userLockFlg: '',
                userDelFlg: '',
                managerCrdUsr: '',
                managerUpdUsr: '',
            },
            daysToAdd:30,
            selectItemsExp: [
                { title: '30日', value: 30 },
                { title: '60日', value: 60 },
                { title: '90日', value: 90 },
            ],
            selectItemsRole: [
                { title: '管理', value: '1' },
                { title: '営業', value: '2' },
                { title: '総務', value: '3' },
                { title: '一般', value: '4' },
            ],
            emailRules: [
                v => !!v || 'メールアドレスは必須です',
                v => /.+@.+/.test(v) || '有効なメールアドレスを入力してください'
            ],
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
       
    },
    watch: {

    },
    created() {
        
    },

    mounted() {
        this.user.managerCrdUsr = this.$store.state.username;
        this.user.managerUpdUsr = this.$store.state.username;
        console.log('username:',this.$store.state.username)
        console.log('managerCrdUsr:',this.user.managerCrdUsr)
        console.log('managerUpdUsr:',this.user.managerUpdUsr)
    },
    methods: {
        submitForm() {
            this.updateUserPwExpiration(this.daysToAdd);
            userApi.insertUser(this.user).then(response => {
                this.snackbar.show = true;
                this.snackbar.color = 'success';
                this.snackbar.message = response.message
                this.$refs.form.reset()
            }).catch((err) => {
                this.snackbar.show = true;
                this.snackbar.color = 'error';
                this.snackbar.message = err.message || '登録失敗しました';
                console.error('Error:', err);
            });

        },

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
                const currentDate = new Date();
                const expirationDate = new Date(currentDate);
                expirationDate.setDate(currentDate.getDate() + parseInt(days));
                this.user.userPwExpiration = this.formatDateToBackend(expirationDate);
                console.log('userPwExpiration 更新:', this.user.userPwExpiration);
            } else {
                this.user.userPwExpiration = '';
            }
        },

    }
};
</script>
<style scoped></style>