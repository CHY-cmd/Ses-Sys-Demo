<template>
    <v-app>
        <navigation-drawer> 
        <div>
            <div>
                <v-app-bar :elevation="1" style="background-color: rgb(0, 114, 159);height: 64px">
                    <v-app-bar-title style="font-size: 30px;color: aliceblue" class="d-flex align-center">取引先情報
                    </v-app-bar-title>
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
            <div style="height: 600px;width: auto;background-color:rgb(242, 242, 242);">
                <v-container fluid style="margin-left: 90px;padding-top: 0px;">
                    <v-row style="margin-top: 0px;">
                        <v-col cols="5">
                            <v-row>
                                <v-col cols="3"
                                    style="background-color: rgb(217, 217, 217);margin: 0px;"><span>取引先名</span><span
                                        style="color:brown;font-size: 20px;">*</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custNm" type="text" required
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color: rgb(217, 217, 217);"><span>カナ</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custKn" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>Webサイト</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custWeb" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>郵便番号</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custPostno" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>住所</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custAddress" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>電話番号</span></v-col>
                                <v-col cols="8"><input id="custPhone" v-model="custMangRequest.custInfo.custPhone"
                                        @input="formatPhoneNumber($event)" @blur="blurPhone" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>FAX</span></v-col>
                                <v-col cols="8"><input id="fax" v-model="custMangRequest.custInfo.custFax"
                                        @input="formatFaxNumber($event)" @blur="blurFax" type="text"
                                        class="custom-input"></v-col>
                            </v-row>
                        </v-col>
                        <v-col></v-col>
                        <v-col cols="6">
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>資本金</span></v-col>
                                <v-col cols="8"><input id="custMoney" v-model="custMangRequest.custInfo.custMoney"
                                        @input="formatCustMoney" class="custom-input"><span> 円</span></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>取引先種別</span></v-col>
                                <v-col cols="8">
                                    <select v-model="custMangRequest.custInfo.custType" id="options"
                                        class="custom-select">
                                        <option value="顧客">顧客</option>
                                        <option value="パートナー">パートナー</option>
                                        <option value="顧客&パートナー">顧客&パートナー</option>
                                    </select>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>取引開始日</span></v-col>
                                <v-col cols="8">
                                    <v-menu ref="menu" v-model="menu" :close-on-content-click="false"
                                        transition="scale-transition"
                                        style="position: absolute;left:1050px;top: 400px;">
                                        <template v-slot:activator="{ on, attrs }">
                                            <div class="d-flex align-center input-with-icon">
                                                <input v-model="formattedDate" type="date" class="custom-input" readonly
                                                    @click="toggleMenu" v-bind="attrs" v-on="on" />
                                                <v-icon :color="menu ? 'error' : 'primary'" @click="toggleMenu">
                                                    {{ menu ? 'mdi-close' : 'mdi-calendar' }}
                                                </v-icon>
                                            </div>
                                        </template>
                                        <v-date-picker v-model="custMangRequest.custInfo.custStartdt"
                                            @click="handleDateInput(custMangRequest.custInfo.custStartdt)" no-title
                                            scrollable></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>登録番号</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custInvoiceNo"
                                        class="custom-input"></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3"
                                    style="background-color:rgb(217, 217, 217);"><span>Pマーク/ISMS</span></v-col>
                                <v-col cols="8">
                                    <v-btn-toggle v-model="custMangRequest.custInfo.custInforSecurity"
                                        class="custom-btn-toggle">
                                        <v-btn value="true">あり</v-btn>
                                        <v-btn value="false">なし</v-btn>
                                    </v-btn-toggle></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>派遣資格</span></v-col>
                                <v-col cols="8"> <v-btn-toggle v-model="custMangRequest.custInfo.custDispatch"
                                        class="custom-btn-toggle">
                                        <v-btn value="true">あり</v-btn>
                                        <v-btn value="false">なし</v-btn>
                                    </v-btn-toggle></v-col>
                            </v-row>
                            <v-row>
                                <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>自社担当者</span></v-col>
                                <v-col cols="8"><input v-model="custMangRequest.custInfo.custCompanyrep"
                                        class="custom-input"></v-col>
                            </v-row>
                        </v-col>
                    </v-row>
                    <v-row>
                        <v-col cols="1"><span>備考</span></v-col>
                        <v-col style="margin-left: 30px;"><textarea v-model="custMangRequest.custInfo.custRmk1"
                                class="custom-textarea"></textarea></v-col>
                        <v-col></v-col>
                    </v-row>
                </v-container>
            </div>
        </div>

        <div style="background-color: rgb(191, 191, 191);margin: 0px;">
            <v-container>
                <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">担当者</p>
            </v-container>
        </div>

        <!-- 表单 担当者 -->
        <div style="background-color:rgb(242, 242, 242) ;">
            <v-container fluid style="margin-left: 80px;">
                <v-row style="height: 50px;margin-bottom: 15px;"><v-btn class="btn" @click="addRow">行追加</v-btn></v-row>
                <v-row style="height: 50px;">
                    <v-col class="d-flex justify-center align-center"><span>担当者名</span><span
                            style="color:brown;font-size: 20px;">*</span></v-col>
                    <v-col class="d-flex justify-center align-center">
                        <p>電話番号</p>
                    </v-col>
                    <v-col class="d-flex justify-center align-center">
                        <p>メール</p>
                    </v-col>
                    <v-col class="d-flex justify-center align-center">
                        <p>役職</p>
                    </v-col>
                    <v-col class="d-flex justify-center align-center">
                        <p>備考</p>
                    </v-col>
                    <v-col cols="2"></v-col>
                </v-row>
                <v-row>
                    <hr style="width: 1350px;margin: 10px;">
                </v-row>
                <v-row v-for="(row, index) in custMangRequest.mangInfo" :key="index" style="margin-left: 18px;">
                    <v-col><input v-model="row.managerNm" class="custom-input"></v-col>
                    <v-col><input id="managerPno" v-model="row.managerPno" @input="formatPhoneNumber($event)"
                            @blur="blurPhone" type="text" class="custom-input"></v-col>
                    <v-col><input v-model="row.managerMail" class="custom-input"></v-col>
                    <v-col><select v-model="row.managerJob" id="options" class="custom-select">
                            <option value="営業">営業</option>
                            <option value="総務">総務</option>
                            <option value="その他">その他</option>
                        </select></v-col>
                    <v-col><input v-model="row.managerRmk" class="custom-input"></v-col>
                    <v-col><v-btn class="btn" @click="delRow(index)">X</v-btn></v-col>
                </v-row>
            </v-container>
        </div>

        <div style="background-color: rgb(191, 191, 191);margin: 0px;">
            <v-container>
                <p style="font-size: 24px;margin-left: 0px;margin-top: 0px;font-weight: bold;">支払サイト</p>
            </v-container>
        </div>

        <!-- 表单 支払サイト-->
        <div style="background-color:rgb(242, 242, 242) ;">
            <v-container fluid style="margin-left: 90px;margin-top: 0px;padding-top: 12px;">
                <v-row>
                    <v-col cols="5" style="margin: 0px;">
                        <v-row v-if="showGetRow">
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>入金日</span></v-col>
                            <v-col cols="2" style="padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custGetdt" id="getoptions" class="custom-select">
                                    <option value="当月">当月</option>
                                    <option value="翌月">翌月</option>
                                    <option value="翌々月">翌々月</option>
                                </select></v-col>
                            <v-col cols="3" style="padding-left: 0px;padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custGetcdt" id="getdoptions"
                                    class="custom-select">
                                    <option value="10日">10日</option>
                                    <option value="15日">15日</option>
                                    <option value="末日">末日</option>
                                    <option value=" ">指定</option>
                                </select></v-col>
                            <v-col cols="2" style="padding-left: 0px;padding-right: 20px;"><input v-if="showGet"
                                    v-model="custMangRequest.custInfo.custOrdergdt" class="custom-input">
                            </v-col>
                            <v-col cols="2">
                                <div class="custom-input" v-if="showGet"
                                    style="width: 40px;text-align: center;margin-left: 0px;padding-left: 0px;">日</div>
                            </v-col>
                        </v-row>
                        <v-row v-if="showPayRow">
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支払日</span></v-col>
                            <v-col cols="2" style="padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custPaydt" id="payoptions" class="custom-select">
                                    <option value="当月">当月</option>
                                    <option value="翌月">翌月</option>
                                    <option value="翌々月">翌々月</option>
                                </select></v-col>
                            <v-col cols="3" style="padding-left: 0px;padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custClosedt" id="paydoptions"
                                    class="custom-select">
                                    <option value="10日">10日</option>
                                    <option value="15日">15日</option>
                                    <option value="末日">末日</option>
                                    <option value=" ">指定</option>
                                </select></v-col>
                            <v-col cols="2" style="padding-left: 0px;padding-right: 20px;"><input
                                    v-model="custMangRequest.custInfo.custOrderdt" class="custom-input" v-if="showPay">
                            </v-col>
                            <v-col cols="2">
                                <div class="custom-input" v-if="showPay"
                                    style="width: 40px;text-align: center;margin-left: 0px;padding-left: 0px;">日</div>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>銀行名</span><span
                                    style="color:brown;font-size: 20px;">*</span></v-col>
                            <v-col cols="8">
                                <div style="display: flex;align-items: center;"><input
                                        v-model="custMangRequest.custInfo.custBankNm" class="custom-input" required>
                                    <v-btn v-if="showBankBtn" style="width: 40px;" class="btn" @click="openBank()">検索</v-btn>
                                </div>
                                <v-dialog v-model="openBankDialog" max-width="1100">
                                    <v-card>
                                        <v-card-title>銀行メッセージ</v-card-title>
                                        <hr>
                                        <v-data-table :headers="bankHeaders" :items="banks" item-key="id">
                                            <template v-slot:item="{ item }">
                                                <tr>
                                                    <td>
                                                        <v-btn text @click="fillBank(item)"
                                                            style="color: white;background-color: green;">選択</v-btn>
                                                    </td>
                                                    <td>{{ item.custBankNm }}</td>
                                                    <td>{{ item.custBranchcd }}</td>
                                                    <td>{{ item.custBranchNm }}</td>
                                                    <td>{{ item.custAccountno }}</td>
                                                    <td>{{ item.custNominee }}</td>
                                                </tr>
                                            </template>
                                        </v-data-table>
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn text @click="openBankDialog = false" class="btn">キャンセル</v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店名</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custBranchNm"
                                    class="custom-input"></v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>預金種別</span></v-col>
                            <v-col cols="8"><select v-model="custMangRequest.custInfo.custDeposittype" id="typeoptions"
                                    class="custom-select">
                                    <option value="普通預金">普通預金</option>
                                    <option value="その他">その他</option>
                                </select></v-col>
                        </v-row>
                    </v-col>
                    <v-col></v-col>
                    <v-col cols="6">
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店番号</span><span
                                    style="color:brown;font-size: 20px;">*</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custBranchcd" class="custom-input"
                                    required></v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>口座番号</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custAccountno"
                                    class="custom-input"></v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>名義人</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custNominee"
                                    class="custom-input"></v-col>
                        </v-row>
                    </v-col>
                </v-row>

                <v-row v-if="showPayArea">
                    <v-col cols="5" style="margin: 0px;">
                        <v-row v-if="showGetRow2">
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>入金日</span></v-col>
                            <v-col cols="2" style="padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custGetdt" id="getoptions" class="custom-select">
                                    <option value="当月">当月</option>
                                    <option value="翌月">翌月</option>
                                    <option value="翌々月">翌々月</option>
                                </select></v-col>
                            <v-col cols="3" style="padding-left: 0px;padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custGetcdt" id="getdoptions"
                                    class="custom-select">
                                    <option value="10日">10日</option>
                                    <option value="15日">15日</option>
                                    <option value="末日">末日</option>
                                    <option value=" ">指定</option>
                                </select></v-col>
                            <v-col cols="2" style="padding-left: 0px;padding-right: 20px;"><input v-if="showGet"
                                    v-model="custMangRequest.custInfo.custOrdergdt" class="custom-input">
                            </v-col>
                            <v-col cols="2">
                                <div class="custom-input" v-if="showGet"
                                    style="width: 40px;text-align: center;margin-left: 0px;padding-left: 0px;">日</div>
                            </v-col>
                        </v-row>
                        <v-row v-if="showPayRow2">
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支払日</span></v-col>
                            <v-col cols="2" style="padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custPaydt" id="payoptions" class="custom-select">
                                    <option value="当月">当月</option>
                                    <option value="翌月">翌月</option>
                                    <option value="翌々月">翌々月</option>
                                </select></v-col>
                            <v-col cols="3" style="padding-left: 0px;padding-right: 0px;"><select
                                    v-model="custMangRequest.custInfo.custClosedt" id="paydoptions"
                                    class="custom-select">
                                    <option value="10日">10日</option>
                                    <option value="15日">15日</option>
                                    <option value="末日">末日</option>
                                    <option value=" ">指定</option>
                                </select></v-col>
                            <v-col cols="2" style="padding-left: 0px;padding-right: 20px;"><input
                                    v-model="custMangRequest.custInfo.custOrderdt" class="custom-input" v-if="showPay">
                            </v-col>
                            <v-col cols="2">
                                <div class="custom-input" v-if="showPay"
                                    style="width: 40px;text-align: center;margin-left: 0px;padding-left: 0px;">日</div>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>銀行名</span><span
                                    style="color:brown;font-size: 20px;">*</span></v-col>
                            <v-col cols="8">
                                <div style="display: flex;align-items: center;"><input
                                        v-model="custMangRequest.custInfo.custBankNm" class="custom-input" required>
                                    <v-btn v-if="showBankBtn2" style="width: 40px;" class="btn" @click="openBank()">検索</v-btn>
                                </div>
                                <v-dialog v-model="openBankDialog" max-width="1100">
                                    <v-card>
                                        <v-card-title>銀行メッセージ</v-card-title>
                                        <hr>
                                        <v-data-table :headers="bankHeaders" :items="banks" item-key="id">
                                            <template v-slot:item="{ item }">
                                                <tr>
                                                    <td>
                                                        <v-btn text @click="fillBank(item)"
                                                            style="color: white;background-color: green;">選択</v-btn>
                                                    </td>
                                                    <td>{{ item.custBankNm }}</td>
                                                    <td>{{ item.custBranchcd }}</td>
                                                    <td>{{ item.custBranchNm }}</td>
                                                    <td>{{ item.custAccountno }}</td>
                                                    <td>{{ item.custNominee }}</td>
                                                </tr>
                                            </template>
                                        </v-data-table>
                                        <v-card-actions>
                                            <v-spacer></v-spacer>
                                            <v-btn text @click="openBankDialog = false" class="btn">キャンセル</v-btn>
                                        </v-card-actions>
                                    </v-card>
                                </v-dialog>
                            </v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店名</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custBranchNm"
                                    class="custom-input"></v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>預金種別</span></v-col>
                            <v-col cols="8"><select v-model="custMangRequest.custInfo.custDeposittype" id="typeoptions"
                                    class="custom-select">
                                    <option value="普通預金">普通預金</option>
                                    <option value="その他">その他</option>
                                </select></v-col>
                        </v-row>
                    </v-col>
                    <v-col></v-col>
                    <v-col cols="6">
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>支店番号</span><span
                                    style="color:brown;font-size: 20px;">*</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custBranchcd" class="custom-input"
                                    required></v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>口座番号</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custAccountno"
                                    class="custom-input"></v-col>
                        </v-row>
                        <v-row>
                            <v-col cols="3" style="background-color:rgb(217, 217, 217);"><span>名義人</span></v-col>
                            <v-col cols="8"><input v-model="custMangRequest.custInfo.custNominee"
                                    class="custom-input"></v-col>
                        </v-row>
                    </v-col>
                </v-row>

                <v-row>
                    <v-col cols="1"><span>備考</span></v-col>
                    <v-col style="margin-left: 30px;"><textarea v-model="custMangRequest.custInfo.custRmk2"
                            class="custom-textarea"></textarea></v-col>
                    <v-col></v-col>
                </v-row>
                <v-row align="center" justify="center">
                    <v-col cols="2">
                        <span>作成者：</span>
                        {{ custMangRequest.custInfo.custCrdUsr }}
                    </v-col>
                    <v-col cols="2">
                        <span>作成日:</span>
                        {{ formatDate(custMangRequest.custInfo.custCrdDt) }}
                    </v-col>
                    <v-col cols="2">
                        <span>更新者:</span>
                        {{ custMangRequest.custInfo.custUpdUsr }}
                    </v-col>
                    <v-col cols="2">
                        <span>更新日:</span>
                        {{ formatDate(custMangRequest.custInfo.custUpdDt) }}
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
import CustinfoAddApi from '@/api/CustinfoAdd.js';
import NavigationDrawer from "@/components/NavigationDrawer.vue";

export default {
    name: 'CustInfoAdd',
    components: {
        NavigationDrawer
    },
    data: () => {
        return {
            custMangRequest: {
                custInfo: {
                    custNm: '', custKn: '', custPhone: '', custFax: '', custWeb: '', custPostno: '', custAddress: '',
                    custType: '', custMoney: '', custInvoiceNo: '', custInforSecurity: false, custDispatch: false, custEmpnum: '', custStartdt: new Date(),
                    custAnnualsale: '', custCompanyrep: '', custRmk1: '', custRmk2: '', custClosedt: '', custPaydt: '', custOrderdt: '', custGetdt: '', custGetcdt: '',
                    custOrdergdt: '', bankCd: '', bankChrcd: '', custBankNm: '',
                    custBranchNm: '', custDeposittype: '', custBranchcd: '', custAccountno: '', custNominee: '',
                    custCrdUsr: 'USER', custUpdUsr: 'USER', custCrdDt: '', custUpdDt: '',
                },
                mangInfo: [{ managerNm: '', managerPno: '', managerMail: '', managerJob: '', managerRmk: '', managerCrdUsr: 'USER', managerUpdUsr: 'USER', isNew: false, managerCustId: '' }],
            },
            banks: [],
            custBank: [],
            menu: false,
            showGet: false,
            showPay: false,
            payMonth: '',
            payDay: '',
            payDate: '',
            getMonth: '',
            getDay: '',
            getDate: '',
            openBankDialog: false,
            showGetRow: false,
            showPayRow: false,
            showPayArea:false,
            showBankBtn:true,
            formattedDate: '',
            bankHeaders: [
                { title: '選択', sortable: false },
                { title: '銀行名', key: 'custBankNm', sortable: true },
                { title: '支店番号', key: 'custBranchcd', sortable: true },
                { title: '支店名', key: 'custBranchNm', sortable: true },
                { title: '口座番号', key: 'custAccountno', sortable: true },
                { title: '名義人', key: 'custNominee', sortable: true },
            ],
            snackbar: {
                show: false,
                message: '',
                color: '',
                timeout: 3000
            },
            custInfo: null,
            mangInfos: [],
            selectedDate: '',
        }
    },

    computed: {

    },

    watch: {
        //监听顾客类型选项
        'custMangRequest.custInfo.custType'(newVal) {
            if (newVal === '顧客') {
                console.log('custBank', this.custBank);
                this.handleCustSelected()
                this.showGetRow = true;
                this.showPayRow = false;
            } else if (newVal === 'パートナー') {
                this.handleOptionPartnerSelected();
                this.showGetRow = false;
                this.showPayRow = true;
                this.showBankBtn=false;
            } else if (newVal === '顧客&パートナー') {
                this.handleOptionPartnerSelected()
                this.showGetRow = true;
                this.showPayRow = false;
                this.showPayArea=true;
                this.showPayRow2 = true;
                this.showBankBtn=true;
                this.showBankBtn2=false;
            };
        },
        //转为后端可接收类型
        'this.custMangRequest.custInfo.custStartdt'(newVal) {
            this.custMangRequest.custInfo.custStartdt.toISOString();
        },
        //监听收付选项
        'custMangRequest.custInfo.custGetcdt'(newVal) {
            if (newVal === ' ') {
                this.showGet = true
            } else {
                this.showGet = false
            }
        },
        'custMangRequest.custInfo.custClosedt'(newVal) {
            if (newVal === ' ') {
                this.showPay = true
            } else {
                this.showPay = false
            }
        },

        'custMangRequest.mangInfo': {
            handler(newVal) {
                newVal.forEach(row => {
                    row.managerCrdUsr = this.$store.state.username;
                    row.managerUpdUsr = this.$store.state.username;
                });
            },
            deep: true
        }
    },

    mounted() {
        this.selectDetailByCustId(this.$route.params.custId);
        console.log('Mounted component with managerCustId:', this.$route.params.custId);
    },

    created() {
        this.selectDetailByCustId(this.$route.params.custId);
        this.selectCustBank();
    },

    methods: {
        //通过id查询并回显
        async selectDetailByCustId(custId) {
            const response = await CustinfoAddApi.selectDetailByCustId(this.$route.params.custId);
            if (response.data.items.length > 0) {
                this.custMangRequest = { ...response.data.items[0], mangInfo: [] };//建立对象结构，所有属性与items相同，并添加一个mangInfo
                response.data.items.forEach(item => {
                    this.custMangRequest.mangInfo.push(...item.mangInfo);//遍历数据放入item，再从item中提取mangInfo（同时展开其结构属性）并push到custMangRequest.mangInfo
                });
                if (this.custMangRequest.custInfo.custStartdt) {
                    const dateObj = new Date(this.custMangRequest.custInfo.custStartdt);
                    if (!isNaN(dateObj.getTime())) {//gettime返回一个时间，判断是否isNaN
                        this.custMangRequest.custInfo.custStartdt = dateObj;
                        this.formattedDate = this.formatDate(dateObj);
                    } else {
                        console.error('date error', this.custMangRequest.custInfo.custStartdt);
                    }
                }
            }
        },

        //判断登录按钮更新or新增
        insertOrUpdate() {
            if (this.$route.params.custId) {
                try {
                    this.updateCust();
                    this.savaMang();
                    this.snackbar.show = true;
                    this.snackbar.message = '更新成功';
                    this.snackbar.color = 'success';
                    location.reload()
                } catch (error) {
                    this.snackbar.show = true;
                    this.snackbar.message = '更新エラー';
                    this.snackbar.color = 'error';
                }
            } else {
                try {
                    this.insertCustMang();
                    this.snackbar.show = true;
                    this.snackbar.message = '登録成功';
                    this.snackbar.color = 'success';
                    location.reload()
                } catch (error) {
                    this.snackbar.show = true;
                    this.snackbar.message = '登録エラー';
                    this.snackbar.color = 'error';
                }
            }
        },

        //同时新增两表
        insertCustMang() {
            CustinfoAddApi.insertCustMang(this.custMangRequest)
        },

        //单独更新客户表
        updateCust() {
            CustinfoAddApi.updateCust(this.custMangRequest.custInfo)
        },

        //根据增减行判断经理部分增行新增修改
        savaMang() {
            const updateMangRows = this.custMangRequest.mangInfo.filter(row => !row.isNew);
            const newMangRows = this.custMangRequest.mangInfo.filter(row => row.isNew);
            const managerCustId = this.$route.params.custId;

            if (updateMangRows.length > 0) {
                this.updateMang(updateMangRows);
            }
            if (newMangRows.length > 0) {
                newMangRows.forEach(row => { row.managerCustId = managerCustId; });
                this.insertMang(managerCustId, newMangRows);
            }
        },

        //经理单独新增
        insertMang(managerCustId, newMangRows) {
            CustinfoAddApi.insertMang(managerCustId, newMangRows)
                .then(response => {
                    newMangRows.forEach(row => row.isNew = false);
                    console.log('insertMang success', response);
                })
                .catch(error => {
                    console.error('insertMang error', error);
                });
        },


        //经理单独更新
        updateMang() {
            CustinfoAddApi.updateMang(this.custMangRequest.mangInfo).then(response => {
                console.log('updateMang success', response);
            })
                .catch(error => {
                    console.error('updateMang error', error);
                });
        },

        //查询银行
        selectBank() {
            CustinfoAddApi.selectBank().then(response => { this.banks = response.data.items }).catch(error => { console.log(error) });
        },

        //查询客户银行
        selectCustBank() {
            CustinfoAddApi.selectCustBank().then(response => { this.custBank = response.data.items }).catch(error => { console.log(error) });
        },

        //快速填充银行信息
        fillBank(rowData) {
            this.custMangRequest.custInfo.bankCd = rowData.bankCd;
            this.custMangRequest.custInfo.bankChrcd = rowData.bankChrcd;
            this.custMangRequest.custInfo.custBankNm = rowData.custBankNm;
            this.custMangRequest.custInfo.custBranchcd = rowData.custBranchcd;
            this.custMangRequest.custInfo.custBranchNm = rowData.custBranchNm;
            this.custMangRequest.custInfo.custAccountno = rowData.custAccountno;
            this.custMangRequest.custInfo.custNominee = rowData.custNominee;
            this.custMangRequest.custInfo.custDeposittype = rowData.custDeposittype;
            this.openBankDialog = false;
        },

        //担当者增减行并标记
        addRow() {
            this.custMangRequest.mangInfo.push({
                managerNm: '',
                managerPno: '',
                managerMail: '',
                managerJob: '',
                managerRmk: '',
                isNew: true
            });
        },
        delRow(index) {
            if (this.custMangRequest.mangInfo.length > 1) {
                this.custMangRequest.mangInfo.splice(index, 1);//删除index位置的1个元素
            } else {
                this.custMangRequest.mangInfo[0] = {
                    managerNm: '',
                    managerPno: '',
                    managerMail: '',
                    managerJob: '',
                    managerRmk: '',
                    isNew: true
                };
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

        //格式化金额
        formatCustMoney() {
            let amount = this.custMangRequest.custInfo.custMoney.replace(/\D/g, '');
            this.custMangRequest.custInfo.custMoney = amount.replace(/\B(?=(\d{3})+(?!\d))/g, ',');
        },

        //控制日历
        toggleMenu(date) {

            date = new Date(date);

            this.menu = !this.menu;
        },

        //格式化日期
        formatDate(date) {
            let dateObj;
            if (date instanceof Date) {
                dateObj = date;
            }
            else {
                dateObj = new Date(date);
            }
            if (isNaN(dateObj.getTime())) {
                return '';
            }
            const year = dateObj.getFullYear();
            const month = String(dateObj.getMonth() + 1).padStart(2, '0');
            const day = String(dateObj.getDate()).padStart(2, '0');
            const formattedDate = `${year}-${month}-${day}`;
            return formattedDate
        },
        handleDateInput(selectedDate) {
            if (selectedDate instanceof Date && !isNaN(selectedDate.getTime())) {
                this.formattedDate = this.formatDate(selectedDate);
            } else {
                this.formattedDate = '';
            }
        },

        //选顾客时调用
        handleCustSelected() {
            console.log('custBankNm', this.custMangRequest.custInfo.custBankNm)
            this.custMangRequest.custInfo.custBankNm = this.custBank.custBankNm;
            this.custMangRequest.custInfo.custBranchNm = this.custBank.custBranchNm;
            this.custMangRequest.custInfo.custDeposittype = this.custBank.custDeposittype;
            this.custMangRequest.custInfo.custAccountno = this.custBank.custAccountno;
            this.custMangRequest.custInfo.custNominee = this.custBank.custNominee;
            this.custMangRequest.custInfo.custBranchcd = this.custBank.custBranchcd;
        },

        //打开银行页面同时查询
        openBank() {
            this.openBankDialog = true;
            this.selectBank();
        },

        handleOptionPartnerSelected() {
            this.custMangRequest.custInfo.custBankNm = '';
            this.custMangRequest.custInfo.custBranchcd = '';
            this.custMangRequest.custInfo.custBranchNm = '';
            this.custMangRequest.custInfo.custAccountno = '';
            this.custMangRequest.custInfo.custDeposittype = '';
            this.custMangRequest.custInfo.custNominee = '';
        },

        //路由
        toList() {
            this.$router.push('/CustInfoList');
        }

    },
}

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

.custom-textarea {
    height: 120px;
    width: 1170px;
    border: 1px black solid;
    border-radius: 6px;
    box-shadow: 2px 2px 2px rgba(62, 62, 62, 0.2);
}
</style>