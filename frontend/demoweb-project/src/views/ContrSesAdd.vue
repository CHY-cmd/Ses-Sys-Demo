<template>
    <v-app>
        <navigation-drawer style="height: 100%;display: flex;">
            <div>
                <div>
                    <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                        <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">契約情報
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
            </div>
            <!-- 表单 基本情報 -->
            <div style="width: auto;background-color:rgb(242, 242, 242);">
                <v-container fluid style="margin-left: 90px;padding-top: 0px;">
                    <v-row style="margin-top: 0px;">
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3"
                                    style="background-color: rgb(217, 217, 217);margin: 0px;"><span>契約番号</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="a" type="text" required class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span>取引先名</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="a" type="text" class="custom-input">
                                    <v-btn style="width: 40px;height: 30px;" class="btn" @click="openCust()">検索</v-btn>
                                    <v-dialog v-model="openCustDialog" max-width="1100">
                                        <v-card>
                                            <v-card-title>取引先情報</v-card-title>
                                            <hr>
                                            <v-data-table :headers="custHeaders" :items="custList" item-key="id">
                                                <template v-slot:item="{ item }">
                                                    <tr>
                                                        <td><v-btn text @click="fillCust(item)"
                                                                style="color: white;background-color: green;">選択</v-btn>
                                                        </td>
                                                        <td>{{ item.custNm }}</td>
                                                        <td>{{ item.custKn }}</td>
                                                        <td>{{ item.custPhone }}</td>
                                                        <td>{{ item.custAddress }}</td>
                                                        <td>{{ item.custType }}</td>
                                                    </tr>
                                                </template>
                                            </v-data-table>
                                            <v-card-actions>
                                                <v-spacer></v-spacer>
                                                <v-btn text @click="openCustDialog = false" class="btn">キャンセル</v-btn>
                                            </v-card-actions>
                                        </v-card>
                                    </v-dialog>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>件名</span></v-col>
                                <v-col cols="8"><input v-model="a" type="text" class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額小計</span></v-col>
                                <v-col cols="8"><input v-model="a" type="text" class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>消費税</span></v-col>
                                <v-col cols="8"><input v-model="a" type="text" class="custom-input"
                                        @input="getAddress"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>金額合計</span></v-col>
                                <v-col cols="8"><input v-model="a" type="text" class="custom-input"></v-col>
                            </v-row>
                        </v-col>
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>契約期間</span>
                                    <span style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col>
                                    <v-menu ref="menu1" v-model="menus.menu1" :close-on-content-click="false"
                                        transition="scale-transition" style="position: absolute;left:60%;top: 30%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date1" class="custom-input"
                                                    @click="toggleMenu(1)" type="text" v-bind="attrs" />
                                                <v-icon :color="menus.menu1 ? 'error' : 'primary'"
                                                    @click="toggleMenu(1)">
                                                    {{ menus.menu1 ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="a" @input="handleDateInput(1)"></v-date-picker>
                                    </v-menu>
                                </v-col>
                                <span style="font-size: x-large;align-content: center;margin-bottom: 5%;">~</span>
                                <v-col>
                                    <v-menu ref="menu2" v-model="menus.menu2" :close-on-content-click="false"
                                        transition="scale-transition" style="position: absolute;left:70%;top: 30%;">
                                        <template v-slot:activator="{ attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDates.date2" type="text" class="custom-input"
                                                    readonly @click="toggleMenu(2)" v-bind="attrs" />
                                                <v-icon :color="menu ? 'error' : 'primary'" @click="toggleMenu(2)">
                                                    {{ menu ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="a" @input="handleDateInput(2)"></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>契約ステータス</span></v-col>
                                <v-col cols="5">
                                    <select v-model="a" id="staOptions" class="custom-select">
                                        <option value="1">契約中</option>
                                        <option value="2">契約終了</option>
                                        <option value="3">契約予定</option>
                                    </select></v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="1"><span>備考</span></v-col>
                        <v-col style="margin-left: 2%;"><textarea v-model="a"
                                class="custom-textarea"></textarea></v-col>
                        <v-col></v-col>
                    </v-row>
                </v-container>
            </div>
            <!-- 表单 詳細情報 -->
            <div style="background-color: rgb(191, 191, 191);margin: 0px;">
                <v-container>
                    <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">詳細情報</p>
                </v-container>
            </div>
            <div style="background-color:rgb(242, 242, 242) ;">
                <v-table fluid>
                    <v-row style="height: 50px;margin-bottom: 15px;"></v-row>
                    <v-row style="height: 50px;">
                        <v-col><v-btn class="btn" @click="addRow">行追加</v-btn></v-col>
                        <v-col class="d-flex justify-center align-center"><span>No.</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>技術者名</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>契約単価</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>仕入単価</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>稼働開始日</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>稼働ステータス</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>下限（H）</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>上限（H）</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>精算方法</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>控除単価</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>超過単価</span></v-col>
                        <v-col class="d-flex justify-center align-center"><span>備考</span></v-col>
                    </v-row>
                    <v-row>
                        <hr style="width: auto;margin: 20px;">
                    </v-row>
                    <v-row v-for="(row, index) in a" :key="index" style="margin-left: 18px;">
                        <v-col>{{ index + 1 }}</v-col>
                        <v-col><input v-model="row.a" class="custom-input"></v-col>
                        <v-col><input v-model="row.a" class="custom-input"></v-col>
                        <v-col><input v-model="row.a" class="custom-input"></v-col>
                        <v-col>
                            <v-menu ref="menu3" v-model="menus.menu3" :close-on-content-click="false"
                                transition="scale-transition" offset-y min-width="auto">
                                <template v-slot:activator="{ attrs }">
                                    <div class="d-flex align-center input-with-icon">
                                        <input v-model="formattedDates.date3" type="text" class="custom-input" readonly
                                            @click="toggleMenu(3)" v-bind="attrs" />
                                        <v-icon :color="menu ? 'error' : 'primary'" @click="toggleMenu(3)">
                                            {{ menu ? 'mdi-close' : 'mdi-calendar' }}
                                        </v-icon>
                                    </div>
                                </template>
                                <v-date-picker v-model="a" @input="handleDateInput(3)"></v-date-picker>
                            </v-menu>
                        </v-col>
                        <v-col><select v-model="row.a" id="options" class="custom-select">
                                <option value="1">稼働中</option>
                                <option value="2">稼働予定</option>
                                <option value="3">終了予定</option>
                                <option value="4">契約終了</option>
                            </select></v-col>
                        <v-col><input v-model="row.a" class="custom-input"></v-col>
                        <v-col><input v-model="row.a" class="custom-input"></v-col>
                        <v-col><select v-model="row.a" id="options" class="custom-select">
                                <option value="1">切捨て(10円)</option>
                                <option value="2">切捨て(100円)</option>
                                <option value="3">切上げ(10円)</option>
                                <option value="4">切上げ(100円)</option>
                            </select></v-col>
                        <v-col><input v-model="row.a" class="custom-input" readonly placeholder="自動入力"></v-col>
                        <v-col><input v-model="row.a" class="custom-input" readonly placeholder="自動入力"></v-col>
                        <v-col><v-btn class="btn" @click="delRow(index)">X</v-btn></v-col>
                    </v-row>
                </v-table>
            </div>
            <v-row align="center" justify="center">
                <v-col cols="2">
                    <span>作成者：</span>
                    {{ a }}
                </v-col>
                <v-col cols="2">
                    <span>作成日:</span>
                    {{ formatDate(a) }}
                </v-col>
                <v-col cols="2">
                    <span>更新者:</span>
                    {{ a }}
                </v-col>
                <v-col cols="2">
                    <span>更新日:</span>
                    {{ formatDate(a) }}
                </v-col>
            </v-row>

            <v-snackbar v-model="snackbar.show" :timeout="snackbar.timeout" :color="snackbar.color" top>
                {{ snackbar.message }}
            </v-snackbar>
        </navigation-drawer>
    </v-app>
</template>
<script>
import NavigationDrawer from "@/components/NavigationDrawer.vue";
import CustInfoListApi from "@/api/CustInfoList";

export default {
    name: 'ContrSesAdd',
    components: {
        NavigationDrawer
    },
    mixins: [],
    props: {

    },
    data() {
        return {
            a: null, startDate: null, endDate: null, operStartDate: null,

            custList: [],
            menuStart: false,
            menuStart2: false,
            menuEnd: false,
            menu: false,
            openCustDialog: false,
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            custHeaders: [
                { title: '選択', sortable: false },
                { title: '取引先名', key: 'custNm', sortable: true },
                { title: 'カナ', key: 'custKn', sortable: true },
                { title: '電話番号', key: 'custPhone', sortable: true },
                { title: '住所', key: 'custAddress', sortable: true },
                { title: '取引先種別', key: 'custType', sortable: true },
            ],
            formattedDates: {
                date1: '',
                date2: '',
                date3: '',
            },
            menus: {
                menu1: false,
                menu2: false,
                menu3: false,
            },
        }
    },
    computed: {
        username() {
            return this.$store.state.username;
        },

    },
    watch: {
        'a': function (newVal) {
            this.handleDateInput(1);
        },
        'a': function (newVal) {
            this.handleDateInput(2);
        },
        'a': function (newVal) {
            this.handleDateInput(3);
        },
    },
    mounted() {

    },
    methods: {

        //增减行并标记
        addRow() {
            push({
                a: '',
                a: '',
                a: '',
                a: '',
                a: '',
                isNew: true
            });
        },
        delRow(index) {
            if (this.a.length > 1) {
                this.a.splice(index, 1);//删除index位置的1个元素
            } else {
                this.a[0] = {
                    a: '',
                    a: '',
                    a: '',
                    a: '',
                    a: '',
                    isNew: true
                };
            }
        },

        //查询客户信息
        selectAllCust() {
            CustInfoListApi.selectAllCustInfos().then(response => {
                this.custList = response.data.items;
            }).catch(error => {
                console.error(error);
                this.snackbar.message = 'error';
                this.snackbar.color = 'red';
                this.snackbar.show = true;
            });
        },

        //打开页面同时查询
        openCust() {
            this.openCustDialog = true;
            this.selectAllCust();
        },

        fillCust(rowData) {
            this.a = rowData.custNm;
            this.openCustDialog = false;
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
        },
        //控制日历
        toggleMenu(index) {
            this.menus[`menu${index}`] = !this.menus[`menu${index}`];
        },

        //路由
        toList() {
            this.$router.push('/ContrInfoList');
        }
    }
};
</script>
<style scoped>
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
    padding-left: 10px;
    font-size: 15px;
    width: 80%;
    height: 35px;
    line-height: 35px;
    border: 1px solid black;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}

.custom-select {
    padding-left: 10px;
    font-size: 15px;
    width: 80%;
    height: 35px;
    line-height: 35px;
    border: 1px solid black;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
    background: url('data:image/svg+xml;utf8,<svg xmlns="http://www.w3.org/2000/svg"><path fill="%23495057" d="M1 1l4 4 4-4"/></svg>') no-repeat right 0.75rem center / 10px 6px;
}

.custom-textarea {
    padding-left: 10px;
    height: 120px;
    width: 1170px;
    border: 1px black solid;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}
</style>