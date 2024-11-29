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
                    <v-btn class="btn" @click="toUpdate">更新へ</v-btn>
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
                                    <v-col cols="8"><span>{{ staff.staffNo }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span
                                            style="color: brown;">氏名</span></v-col>
                                    <v-col cols="8"><span>{{ staff.staffNm }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>氏名(カナ)</span></v-col>
                                    <v-col cols="8"><span>{{ staff.staffKn }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>氏名(英字)</span></v-col>
                                    <v-col cols="8"><span>{{ staff.staffAlphabet }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>郵便番号</span></v-col>
                                    <v-col cols="8"><span>{{ staff.staffPostno }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="3"
                                        style="background-color:rgb(217, 217, 217);"><span>現住所</span></v-col>
                                    <v-col cols="8"><span>{{ staff.staffAddress }}</span></v-col>
                                </v-row>
                            </v-col>
                            <v-col cols="7">
                                <v-row>
                                    <v-col cols="2" style="background-color:rgb(217, 217, 217);"><span
                                            style="color: brown;">営業ステータス</span></v-col>
                                    <v-col cols="3"><span>{{ salesstatusMap[staff.staffSalesstatus] }}</span></v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span
                                            style="color: brown;">性別：</span></v-col>
                                    <v-col cols="3"> <span>{{ staff.staffSex }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2" style="background-color:rgb(217, 217, 217);"><span
                                            style="color: brown;">年齢</span></v-col>
                                    <v-col cols="3"><span>{{ staff.staffAge }}</span></v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span
                                            style="color: brown;">生年月日：</span></v-col>
                                    <v-col cols="3"><span>{{ formatDate(staff.staffBirthdt) }}</span> </v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2" style="background-color:rgb(217, 217, 217);"><span
                                            style="color: brown;">国籍</span></v-col>
                                    <v-col cols="3"><span>{{ staff.staffCountry }}</span></v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span>出身地：</span></v-col>
                                    <v-col cols="3"><span>{{ staff.staffBirthplace }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2"
                                        style="background-color:rgb(217, 217, 217);"><span>メール</span></v-col>
                                    <v-col cols="9"><span>{{ staff.staffMail }}</span></v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2"
                                        style="background-color:rgb(217, 217, 217);"><span>携帯電話</span></v-col>
                                    <v-col cols="3"><span>{{ staff.staffPhoneno }}</span></v-col>
                                    <v-col cols="2" style="max-width: 15%;"><span>緊急連絡先：</span></v-col>
                                    <v-col cols="3"><span>{{ staff.staffEmergencycall }}</span> </v-col>
                                </v-row>
                                <v-row>
                                    <v-col cols="2"
                                        style="background-color:rgb(217, 217, 217);"><span>最寄駅</span></v-col>
                                    <v-col cols="9"><span>{{ staff.staffNeareststation }}</span></v-col>
                                </v-row>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="5">
                                <v-row>
                                    <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>備考</span></v-col>
                                    <v-col cols="8" style="margin-left: 30px;"><span>{{ staff.staffRmk1}}</span></v-col>
                                </v-row>
                            </v-col>
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
                                <v-col cols="8" style="padding-right: 0px;"><span>{{ staffClsMap[staff.staffCls]
                                        }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>役職</span></v-col>
                                <v-col cols="8" style="padding-right: 0px;"><span>{{ staffPostMap[staff.staffPost]
                                        }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>マイナンバー</span></v-col>
                                <v-col cols="8"><span>{{ staff.staffMyno }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>在留カード</span></v-col>
                                <v-col cols="8"><span>{{ staff.staffResidencecard }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>在留資格</span></v-col>
                                <v-col cols="8"><span>{{ restTypeMap[staff.staffRestype] }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>在留カード有効期限</span></v-col>
                                <v-col cols="8"><span>{{ formatDate(staff.staffCardenddt) }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>写真</span></v-col>
                                <v-col cols="3">
                                    <v-img :src="staff.staffPhoto" max-width="128" max-height="128"></v-img>
                                </v-col>
                            </v-row>
                            <v-row style="height: 30vh;"><v-col cols="3" style="background-color:rgb(217, 217, 217);"></v-col></v-row>
                            
                        </v-col>
                        <v-col cols="6">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>部署</span></v-col>
                                <v-col cols="9"><span>{{ staffGroupMap[staff.staffGroup] }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>所属会社</span></v-col>
                                <v-col cols="9"><span>{{ staff.staffCompany }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>雇用保険<br>被保険者番号</span></v-col>
                                <v-col cols="9"><span>{{ staff.staffInsuranceno }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>保険タイプ</span></v-col>
                                <v-col cols="9"><span>{{ insurancetypeMap[staff.staffInsurancetype] }}</span></v-col>
                            </v-row>
                            <!-- TODO 简历上传 -->
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>履歴書</span></v-col>
                                <v-col cols="9"><span>{{ staff.staffResume }}</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>得意分野</span></v-col>
                                <v-col cols="9" style="margin-bottom: 3%">
                                    <v-row>
                                        <v-col cols="4" v-for="(specialty, index) in specialty" :key="index">
                                            <v-checkbox disabled="true" v-model="selectedSpecialty"
                                                :label="specialty.label" color="primary" :value="specialty.value"
                                                class="custom-checkbox"></v-checkbox></v-col>
                                    </v-row>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span
                                        style="color: brown;">契約日</span></v-col>
                                <v-col cols="3"><span>{{ formatDate(staff.staffIndt) }}</span></v-col>
                                <v-col cols="2" style="max-width: 15%;"><span>退社日：</span></v-col>
                                <v-col cols="3"><span>{{ formatDate(staff.staffOutdt) }}</span> </v-col>
                            </v-row>
                        </v-col>
                    </v-row>

                    <v-row>
                        <v-col cols="5">
                                <v-row>
                                    <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>備考</span></v-col>
                                    <v-col cols="8" style="margin-left: 30px;"><span>{{ staff.staffRmk2}}</span></v-col>
                                </v-row>
                            </v-col>
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
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import StaffApi from '@/api/Staff.js';
import moment from 'moment-timezone';

export default {
    name: 'StaffInfoDetail',
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
            restTypeMap: {
                1: '技術・人文知識・国際業務',
                2: '高度専門職',
                3: '永住者',
                4: '留学（在留審査中）',
                5: '転勤（社内未更新）',
            },
            salesstatusMap: {
                1: '稼働中',
                2: '営業稼働中',
                3: '営業中',
                4: '稼働予定',
                5: '休職',
                6: '退職済',
            },
            staffClsMap: {
                1: '正社員',
                2: '契約社員',
                3: '協力会社',
                4: '個人事業主',
            },
            staffPostMap: {
                1: '一般社員',
                2: '事業部長',
                3: '監査',
                4: '役員',
                5: '取締役',
            },
            staffGroupMap: {
                1: '高速開発部',
                2: 'システム開発部',
                3: '製品開発部',
                4: '管理部',
                5: '営業部',
            },
            insurancetypeMap: {
                1: '国民',
                2: '社会',
            }
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

    },
    mounted() {
        this.selectStaffById(this.$route.params.staffId);
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


        //路由
        toList() {
            this.$router.push('/StaffInfoList');
        },
        toUpdate() {
            const staffId = this.$route.params.staffId;
            this.$router.push({ path: `/StaffInfoAdd/${staffId}` });
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