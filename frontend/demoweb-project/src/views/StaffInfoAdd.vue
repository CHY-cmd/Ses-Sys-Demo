<template>
    <v-app>
        <navigation-drawer>
            <div>
                <div>
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">スタッフ情報
                        </v-app-bar-title>
                        <div style="color: aliceblue;">{{ username }}</div>
                    </v-app-bar>
                </div>
                <div
                    style="display: flex;justify-content: center;align-items: center;background-color: rgb(242, 242, 242);height: 100px;margin-top: 64px;">
                    <v-btn class="btn" @click="insertOrUpdate">登録</v-btn>
                    <v-btn class="btn" @click="toList">キャンセル</v-btn>
                </div>
                <div style="background-color: rgb(191, 191, 191);margin: 0px;">
                    <v-container>
                        <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">基本情報</p>
                    </v-container>
                </div>

                <!-- 表单 基本情報 -->
                <div style="width: auto;background-color:rgb(242, 242, 242);">
                    <v-container fluid style="margin-left: 90px;padding-top: 0px;">
                        <v-row style="margin-top: 0px;">
                            <v-col cols="5">
                                <v-row>
                                    <v-col cols="3" style="background-color: rgb(217, 217, 217);margin: 0px;"><span
                                            style="color: brown;">社員番号</span></v-col>
                                    <v-col cols="8"><input v-model="staff.staffNo" type="text" required readonly
                                            class="custom-input"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span
                                            style="color: brown;">氏名</span></v-col>
                                    <v-col cols="8"><input v-model="staff.staffNm" type="text"
                                            class="custom-input"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>氏名(カナ)</span></v-col>
                                    <v-col cols="8"><input v-model="staff.staffKn" type="text"
                                            class="custom-input"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>氏名(英字)</span></v-col>
                                    <v-col cols="8"><input v-model="staff.staffAlphabet" type="text"
                                            class="custom-input"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>郵便番号</span></v-col>
                                    <v-col cols="8"><input v-model="staff.staffPostno" type="text" class="custom-input"
                                            @input="getAddress"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>現住所</span></v-col>
                                    <v-col cols="8"><input v-model="staff.staffAddress" type="text"
                                            class="custom-input"></v-col>
                                </v-row>
                            </v-col>
                            <v-col cols="7">
                                <v-row>
                                    <v-col cols="2" style="background-color:rgb(217, 217, 217);"><span
                                            style="color: brown;">営業ステータス</span></v-col>
                                    <v-col cols="3"><select v-model="staff.staffSalesstatus" id="saleOptions"
                                            class="custom-select">
                                            <option value="1">稼働中</option>
                                            <option value="2">営業稼働中</option>
                                            <option value="3">営業中</option>
                                            <option value="4">稼働予定</option>
                                            <option value="5">休職</option>
                                            <option value="6">退職済</option>
                                        </select>
                                    </v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span
                                            style="color: brown;">性別</span></v-col>
                                    <v-col cols="3"> <v-btn-toggle v-model="staff.staffSex" class="custom-btn-toggle">
                                            <v-btn value="男">男</v-btn>
                                            <v-btn value="女">女</v-btn>
                                        </v-btn-toggle></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2" style="background-color:rgb(217, 217, 217);"><span
                                            style="color: brown;">年齢</span></v-col>
                                    <v-col cols="3"><input readonly v-model="staff.staffAge"
                                            class="custom-input"></v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span
                                            style="color: brown;">生年月日</span></v-col>
                                    <v-col cols="3">
                                        <v-menu ref="menu1" v-model="menus.menu1" :close-on-content-click="false"
                                            transition="scale-transition" style="position: absolute;left:70%;top: 40%;">
                                            <template v-slot:activator="{ attrs }">
                                                <div class="d-flex align-center input-with-icon">
                                                    <input v-model="formattedDates.date1" class="custom-input"
                                                        @input="handleDateInput(1)" type="text" v-bind="attrs" />
                                                    <v-icon :color="menus.menu1 ? 'error' : 'primary'"
                                                        @click="toggleMenu(1)">
                                                        {{ menus.menu1 ? 'mdi-close' : 'mdi-calendar' }}
                                                    </v-icon>
                                                </div>
                                            </template>
                                            <v-date-picker v-model="staff.staffBirthdt"
                                                @input="handleDateInput(1)"></v-date-picker>
                                        </v-menu>
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2" style="background-color:rgb(217, 217, 217);"><span
                                            style="color: brown;">国籍</span></v-col>
                                    <v-col cols="3">
                                        <select v-model="staff.staffCountry" id="countryOptions" class="custom-select">
                                            <option value="中国">中国</option>
                                            <option value="日本">日本</option>
                                            <option value="その他">その他</option>
                                        </select>
                                    </v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span>出身地</span></v-col>
                                    <v-col cols="3"><input v-model="staff.staffBirthplace" type="text"
                                            class="custom-input"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2"
                                        style="background-color:rgb(217, 217, 217);"><span>メール</span></v-col>
                                    <v-col cols="9"><input v-model="staff.staffMail" type="email"
                                            class="custom-input"></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2"
                                        style="background-color:rgb(217, 217, 217);"><span>携帯電話</span></v-col>
                                    <v-col cols="3">
                                        <input id="custPhone" v-model="staff.staffPhoneno"
                                            @input="formatPhoneNumber($event)" @blur="blurPhone" type="text"
                                            class="custom-input">
                                    </v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span>緊急連絡先</span></v-col>
                                    <v-col cols="3">
                                        <input id="custPhone" v-model="staff.staffEmergencycall"
                                            @input="formatPhoneNumber($event)" type="text"
                                            class="custom-input">
                                    </v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2"
                                        style="background-color:rgb(217, 217, 217);"><span>最寄駅</span></v-col>
                                    <v-col cols="9">
                                        <input v-model="staff.staffNeareststation" class="custom-input">
                                    </v-col>
                                </v-row>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="1"><span>備考</span></v-col>
                            <v-col style="margin-left: 30px;"><textarea v-model="staff.staffRmk1"
                                    class="custom-textarea"></textarea></v-col>
                            <v-col></v-col>
                        </v-row>
                    </v-container>
                </div>
            </div>

            <div style="background-color: rgb(191, 191, 191);margin: 0px;">
                <v-container>
                    <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">詳細情報</p>
                </v-container>
            </div>

            <!-- 表单 詳細情報-->
            <div style="background-color:rgb(242, 242, 242) ;">
                <v-container fluid style="margin-left: 90px;margin-top: 0px;padding-top: 12px;">
                    <v-row>
                        <v-col cols="5" style="margin: 0px;">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span
                                        style="color: brown;">スタッフ区分</span></v-col>
                                <v-col cols="8" style="padding-right: 0px;">
                                    <select v-model="staff.staffCls" id="clsOptions" class="custom-select">
                                        <option value="1">正社員</option>
                                        <option value="2">契約社員</option>
                                        <option value="3">協力会社</option>
                                        <option value="4">個人事業主</option>
                                    </select></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>役職</span></v-col>
                                <v-col cols="8" style="padding-right: 0px;"><select v-model="staff.staffPost"
                                        id="postOptions" class="custom-select">
                                        <option value="1">一般社員</option>
                                        <option value="2">事業部長</option>
                                        <option value="3">監査</option>
                                        <option value="4">役員</option>
                                        <option value="5">取締役</option>
                                    </select></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>マイナンバー</span></v-col>
                                <v-col cols="8">
                                    <input v-model="staff.staffMyno" class="custom-input">
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>在留カード</span></v-col>
                                <v-col cols="8"><input v-model="staff.staffResidencecard" class="custom-input"
                                        :disabled="isRestDisabled"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>在留資格</span></v-col>
                                <v-col cols="8"><select v-model="staff.staffRestype" id="restTypeOptions"
                                        :disabled="isRestDisabled" class="custom-select">
                                        <option value="1">技術・人文知識・国際業務</option>
                                        <option value="2">高度専門職</option>
                                        <option value="3">永住者</option>
                                        <option value="4">留学（在留審査中）</option>
                                        <option value="5">転勤（社内未更新）</option>
                                    </select></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>在留カード有効期限</span></v-col>
                                <v-col cols="8">
                                    <v-menu ref="menu2" v-model="menus.menu2" :close-on-content-click="false"
                                        transition="scale-transition" style="position: absolute;left:40%;top: 140%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date2" type="text" class="custom-input"
                                                    @click="toggleMenu(2)" v-bind="attrs" :disabled="isRestDisabled" />
                                                <v-icon :color="menus.menu2 ? 'error' : 'primary'"
                                                    @click="toggleMenu(2)" :disabled="isRestDisabled">
                                                    {{ menus.menu2 ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="staff.staffCardenddt"
                                            @input="handleDateInput(2)"></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>写真</span></v-col>
                                <v-col cols="3">
                                    <input type="file" id="upload" accept=".jpg" @change="onFileChange" />
                                    <v-img :src="staff.staffPhoto" max-width="128" max-height="128"></v-img>
                                </v-col>
                            </v-row>
                        </v-col>
                        <v-col cols="6">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>部署</span></v-col>
                                <v-col cols="9"><select v-model="staff.staffGroup" id="typeoptions"
                                        class="custom-select">
                                        <option value="1">高速開発部</option>
                                        <option value="2">システム開発部</option>
                                        <option value="3">製品開発部</option>
                                        <option value="4">管理部</option>
                                        <option value="5">営業部</option>
                                    </select>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>所属会社</span></v-col>
                                <v-col cols="9"><input v-model="staff.staffCompany" class="custom-input"
                                        :disabled="isStaffCompanyDisabled"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>雇用保険<br>被保険者番号</span></v-col>
                                <v-col cols="9"><input v-model="staff.staffInsuranceno" class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>保険タイプ</span></v-col>
                                <v-col cols="9"><select v-model="staff.staffInsurancetype" id="typeoptions"
                                        class="custom-select">
                                        <option value="1">国民</option>
                                        <option value="2">社会</option>
                                    </select>
                                </v-col>
                            </v-row>
                            <!-- TODO 简历上传 -->
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>履歴書</span></v-col>
                                <v-col cols="9"><input type="file" id="upload" accept=".pdf"
                                        @change="handleResumeUpload" /></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>得意分野</span></v-col>
                                <v-col cols="9" style="margin-bottom: 3%">
                                    <v-row>
                                        <v-col cols="4" v-for="(specialty, index) in specialty" :key="index">
                                            <v-checkbox v-model="selectedSpecialty" :label="specialty.label"
                                                color="primary" :value="specialty.value"
                                                class="custom-checkbox"></v-checkbox></v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span
                                        style="color: brown;">契約日</span></v-col>
                                <v-col cols="3">
                                    <v-menu ref="menu3" v-model="menus.menu3" :close-on-content-click="false"
                                        transition="scale-transition" style="position: absolute;left:75%;top: 140%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date3" type="text" class="custom-input"
                                                    @click="toggleMenu(3)" v-bind="attrs" />
                                                <v-icon :color="menus.menu3 ? 'error' : 'primary'"
                                                    @click="toggleMenu(3)">
                                                    {{ menus.menu3 ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="staff.staffIndt"
                                            @input="handleDateInput(3)"></v-date-picker>
                                    </v-menu></v-col>
                                <v-col cols="2" style="max-width: 15%;"><span>退社日</span></v-col>
                                <v-col cols="3"><v-menu ref="menu4" v-model="menus.menu4"
                                        :close-on-content-click="false" transition="scale-transition"
                                        style="position: absolute;left:55%;top: 140%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date4" type="text" class="custom-input"
                                                    @click="toggleMenu(4)" v-bind="attrs" />
                                                <v-icon :color="menus.menu4 ? 'error' : 'primary'"
                                                    @click="toggleMenu(4)">
                                                    {{ menus.menu4 ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="staff.staffOutdt"
                                            @input="handleDateInput(4)"></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="1"><span>備考</span></v-col>
                        <v-col style="margin-left: 30px;"><textarea v-model="staff.staffRmk2"
                                class="custom-textarea"></textarea></v-col>
                        <v-col></v-col>
                    </v-row>
                    <v-row align="center" justify="center">
                        <v-col cols="2">
                            <span>作成者：</span>
                            {{ staff.custCrdUsr }}
                        </v-col>
                        <v-col cols="2">
                            <span>作成日:</span>
                            {{ formatDate(staff.staffCreDt) }}
                        </v-col>
                        <v-col cols="2">
                            <span>更新者:</span>
                            {{ staff.custUpdUsr }}
                        </v-col>
                        <v-col cols="2">
                            <span>更新日:</span>
                            {{ formatDate(staff.staffUpdDt) }}
                        </v-col>
                    </v-row>
                </v-container>
            </div>
            <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
                {{ snackbar.message }}
            </v-snackbar>
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import StaffApi from '@/api/Staff.js';
import moment from 'moment-timezone';

export default {
    name: 'StaffInfoAdd',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            staff: {
                staffNo: '', staffNm: '', staffKn: '', staffAlphabet: '', staffSex: '', staffCountry: '', staffBirthplace: '',
                staffCls: '', staffCompany: '', staffSalesstatus: '', staffGroup: '', staffPost: '', staffRmk1: '', staffAge: '',
                staffMyno: '', staffPhoneno: '', staffResidencecard: '', staffRestype: '', staffCardenddt: null, staffBirthdt: null,
                staffPostno: '', staffAddress: '', staffIndt: null, staffOutdt: null, staffMail: '', staffNeareststation: '',
                staffResume: '', staffInsuranceno: '', staffInsurancetype: '', staffSpecialty: '', staffRmk2: '', staffPhoto: '',
                staffEmergencycall: '', custCrdUsr: this.$store.state.username, staffCreDt: new Date(), custUpdUsr: this.$store.state.username, staffUpdDt: new Date()
            },
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            formattedDates: {
                date1: '',
                date2: '',
                date3: '',
                date4: ''
            },
            menus: {
                menu1: false,
                menu2: false,
                menu3: false,
                menu4: false
            },
            selectedFile: null,
            previewUrl: '',
            rules: {
                required: value => !!value || '.JPG'
            },
            specialty: [
                { label: 'ローコード', value: 'ローコード' },
                { label: 'バックエンド', value: 'バックエンド' },
                { label: 'フロントエンド', value: 'フロントエンド' },
                { label: 'インフラ', value: 'インフラ' },
                { label: 'AI', value: 'AI' },
                { label: 'PM', value: 'PM' },
                { label: 'リーダー', value: 'リーダー' },
                { label: 'SL', value: 'SL' },
                { label: 'SE', value: 'SE' },
                { label: 'PG', value: 'PG' },
                { label: 'BSE', value: 'BSE' },
                { label: 'PMO', value: 'PMO' },
                { label: '要件定義', value: '要件定義' },
                { label: '基本設計', value: '基本設計' },
                { label: '詳細設計', value: '詳細設計' },
                { label: '製造', value: '製造' },
                { label: 'テスト', value: 'テスト' },
            ],
            selectedSpecialty: [],
            dailyNumber: 1, // 每日编号
            lastResetDate: null, // 上次重置日期
        }
    },
    computed: {
        username() {
            return this.$store.state.username;
        },
        isStaffCompanyDisabled() {
            return ['1', '2'].includes(this.staff.staffCls);
        },
        isRestDisabled() {
            return this.staff.staffCountry === "日本";
        },
    },
    watch: {
        'staff.staffBirthdt': function (newVal) {
            this.handleDateInput(1);
        },
        'staff.staffCardenddt': function (newVal) {
            this.handleDateInput(2);
        },
        'staff.staffIndt': function (newVal) {
            this.handleDateInput(3);
        },
        'staff.staffOutdt': function (newVal) {
            this.handleDateInput(4);
        },
        'staff.staffCls': function (newVal) {
            if (this.isStaffCompanyDisabled) {
                this.staff.staffCompany = '';
            }
        },
        'staff.staffCountry': function (newVal) {
            if (this.isRestDisabled) {
                this.staff.staffResidencecard = '';
                this.staff.staffCardenddt = '';
                this.staff.staffRestype = '';
            }
        }
    },
    mounted() {
        this.selectStaffById(this.$route.params.staffId);
        this.resetDailyNumber();
    },
    created() {

    },
    methods: {
        //通过id查询并回显
        async selectStaffById() {
            const response = await StaffApi.selectStaffById(this.$route.params.staffId);
            if (response.data.items.length > 0) {
                this.staff = response.data.items[0];
                console.log('Before formatting:', this.staff.staffBirthdt);
                this.staff.staffCardenddt = this.convertToDate(this.staff.staffCardenddt);
                this.staff.staffBirthdt = this.convertToDate(this.staff.staffBirthdt);
                this.staff.staffIndt = this.convertToDate(this.staff.staffIndt);
                this.staff.staffOutdt = this.convertToDate(this.staff.staffOutdt);
                if (typeof this.staff.staffSpecialty === 'string') {
                    this.selectedSpecialty = this.staff.staffSpecialty.split(',').map(s => s.trim());//字符串转数组
                } else {
                    this.selectedSpecialty = [];
                }
            }
        },
        convertToDate(dateString) {
            if (!dateString) {
                return null;//避免初始为1970
            }
            return new Date(dateString);
        },

        //判断登录按钮更新or新增
        insertOrUpdate() {
            if (this.$route.params.staffId) {
                this.updateStaffById();
                location.reload()
            } else {
                this.insertStaff();
                location.reload()
            }
        },

        //新增
        async insertStaff() {
            delete this.staff.staffCreDt;
            delete this.staff.staffUpdDt;
            this.staff.staffSpecialty = this.selectedSpecialty.join(',');//数组转字符串
            this.staff.staffBirthdt = this.formatDateToBackend(this.staff.staffBirthdt);
            this.staff.staffCardenddt = this.formatDateToBackend(this.staff.staffCardenddt);
            this.staff.staffIndt = this.formatDateToBackend(this.staff.staffIndt);
            this.staff.staffOutdt = this.formatDateToBackend(this.staff.staffOutdt);
            console.log('Formatted staffBirthdt:', this.staff.staffBirthdt);
            const response = await StaffApi.insertStaff(this.staff);
            try {
                this.snackbar.show =true;
                this.snackbar.message = response.data.message;
                this.snackbar.color = 'success';
            } catch (error) {
                this.snackbar.show = true;
                this.snackbar.message = response.data.message;
                this.snackbar.color = 'error';
            }
        },

        //更新
        async updateStaffById() {
            delete this.staff.staffCreDt;
            delete this.staff.staffUpdDt;
            this.staff.staffSpecialty = this.selectedSpecialty.join(',');
            this.staff.staffBirthdt = this.formatDateToBackend(this.staff.staffBirthdt);
            this.staff.staffCardenddt = this.formatDateToBackend(this.staff.staffCardenddt);
            this.staff.staffIndt = this.formatDateToBackend(this.staff.staffIndt);
            this.staff.staffOutdt = this.formatDateToBackend(this.staff.staffOutdt);
            const response = await StaffApi.updateStaffById(this.staff);
            try {
                this.snackbar.show = true;
                this.snackbar.message = response.data.message;
                this.snackbar.color = 'success';
            } catch (error) {
                this.snackbar.show = true;
                this.snackbar.message = response.data.message;
                this.snackbar.color = 'error';
            }
        },

        //图片上传创建URL
        onFileChange(event) {
            const file = event.target.files[0];
            if (file) {
                this.selectedFile = file;
                this.staff.staffPhoto = URL.createObjectURL(file);
            } else {
                this.staff.staffPhoto = '';
            }
        },

        //格式化手机号
        formatPhoneNumber(event) {
            let phoneNumber = event.target.value.replace(/\D/g, '');
            if (phoneNumber.length === 9) {
                phoneNumber = phoneNumber.replace(/(\d{2})(\d{3})(\d{4})/, '$1-$2-$3');
            } else if (phoneNumber.length === 10) {
                phoneNumber = phoneNumber.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            } else if (phoneNumber.length === 11) {
                phoneNumber = phoneNumber.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
            }
            event.target.value = phoneNumber;
        },
        blurPhone(event) {
            let phoneNumber = event.target.value.replace(/\D/g, '');
            if (phoneNumber.length < 9 || phoneNumber.length > 11) {
                alert("電話番号の形式が正しくありません");
            }
        },

        //格式化传真号
        formatFaxNumber(event) {
            let faxNumber = event.target.value.replace(/\D/g, '');
            if (faxNumber.length === 10) {
                faxNumber = faxNumber.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
            }
            event.target.value = faxNumber;
        },
        blurFax(event) {
            let faxNumber = event.target.value.replace(/\D/g, '');
            if (faxNumber.length === !10) {
                alert("FAXの形式が正しくありません");
            }
        },

        //控制日历
        toggleMenu(index) {
            this.menus[`menu${index}`] = !this.menus[`menu${index}`];
        },

        //格式化日期以显示
        formatDate(date, options = { timeZone: 'Asia/Tokyo' }) {
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
            const formattedDate = moment.tz(dateObj, options.timeZone).format('YYYY/MM/DD');
            return formattedDate;
        },
        formatDateToBackend(date) {
            if (!date || !(date instanceof Date) || isNaN(date.getTime())) {
                return '';
            }
            const formattedDate = moment(date).tz('Asia/Tokyo').format('YYYY-MM-DD HH:mm:ss');
            return formattedDate;
        },
        handleDateInput(index) {
            const fieldNames = ['Birthdt', 'Cardenddt', 'Indt', 'Outdt'];
            const fieldName = fieldNames[index - 1];
            const selectedDate = this.staff[`staff${fieldName}`];
            if (selectedDate instanceof Date && !isNaN(selectedDate.getTime())) {
                const formattedDate = this.formatDate(selectedDate);
                this.formattedDates[`date${index}`] = formattedDate;
            } else {
                this.formattedDates[`date${index}`] = '';
                this.staff[`staff${fieldName}`] = '';
            }
            this.menus[`menu${index}`] = false;
            //生日算年龄
            if (index === 1) {
                const birthDate = new Date(this.staff.staffBirthdt);
                const today = new Date();
                let age = today.getFullYear() - birthDate.getFullYear();
                const m = today.getMonth() - birthDate.getMonth();
                if (m < 0 || (m === 0 && today.getDate() < birthDate.getDate())) {
                    age--;
                }
                if (age > 0) {
                    this.staff.staffAge = age;
                } else {
                    this.staff.staffAge = "0";
                }
            }
            if (index === 3) {
                if (!this.staff.staffNo) {//回显时为空则不计算
                    this.calculateStaffNo();
                }
            }
        },

        //入职时间算员工番号
        calculateStaffNo() {
            const indt = new Date(this.staff.staffIndt);
            if (indt && !isNaN(indt.getTime())) {
                const year = indt.getFullYear();
                const month = String(indt.getMonth() + 1).padStart(2, '0');
                const day = String(indt.getDate()).padStart(2, '0');
                // 获取并递增每日编号
                const dailyNumber = String(this.dailyNumber).padStart(2, '0');
                this.dailyNumber = (this.dailyNumber % 99) + 1;
                this.staff.staffNo = `ITEC-${year}${month}${day}${dailyNumber}`;
            } else {
                this.staff.staffNo = '';
            }
        },

        //简历上传
        handleResumeUpload(event) {
            const file = event.target.files[0];
            if (file) {
                this.staff.staffResume = file;
                console.log('Selected file:', this.staff.staffResume);
            } else {
                this.staff.staffResume = null;
                console.log('No file selected.');
            }
        },

        resetDailyNumber() {
            const now = new Date();
            const currentDate = now.toISOString().split('T')[0];
            if (this.lastResetDate !== currentDate) {
                this.dailyNumber = 1;
                this.lastResetDate = currentDate;
            }
        },

        //路由
        toList() {
            this.$router.push('/StaffInfoList');
        }
    }
};
</script>
<style scoped>
.custom-btn-toggle {
    border: 1px solid #ccc;
    border-radius: 8px;
    overflow: hidden;
    margin: 0px;
    display: flex;
    width: 130px;
    padding: 0px !important;
    height: 30px !important;
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

.custom-input {
    font-size: 15px;
    width: 80%;
    height: 35px;
    line-height: 35px;
    border: 1px solid black;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}

.custom-input[disabled] {
    background-color: rgb(232, 232, 232);
    color: #888;
}

.custom-select {
    font-size: 15px;
    width: 80%;
    height: 35px;
    line-height: 35px;
    border: 1px solid black;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
    background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg"><path fill="%23495057" d="M1 1l4 4 4-4"/></svg>') no-repeat right 0.75rem center / 10px 6px;
}

.custom-select[disabled] {
    background-color: rgb(232, 232, 232);
    color: #888;
}

.custom-textarea {
    height: 120px;
    width: 71vw;
    border: 1px black solid;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}

.custom-checkbox {
    padding: 0 !important;
    margin: 0 !important;
    max-width: 160px;
    height: 20px;
}
</style>