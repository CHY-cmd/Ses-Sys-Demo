<template>
  <v-card>
    <v-layout>
      <v-navigation-drawer expand-on-hover rail style="border: 0px;background-color: rgb(220, 230, 240);" width="350">
        <div style="background-color: rgb(0, 114, 159);color: aliceblue;font-size: 20px;">
          <v-list>
            <v-list-item prepend-icon="mdi-dots-vertical" title="">
              <template v-slot:title>
                <span style="font-size: 26px;">ITEC社内総合管理</span>
              </template>
            </v-list-item>
          </v-list>
        </div>
        <v-divider></v-divider>
        <div style="color: rgb(64, 64, 64); ">
          <v-list density="compact" nav>
            <v-list-group prepend-icon="mdi-folder" value="info" no-action>
              <template v-slot:activator="{ props }">
                <v-list-item v-bind="props" title="" style="min-height: 60px;">
                  <template v-slot:title>
                    <span style="font-size: 17px;line-height: 1.5;">基本情報</span>
                  </template></v-list-item>
              </template>
              <v-list-item prepend-icon="mdi-account-multiple-outline" title="取引先" value="info1"
                @click="handleClick('info1')"></v-list-item>
              <v-list-item prepend-icon="mdi-account-multiple" title="スタッフ" value="info2"
                @click="handleClick('info2')"></v-list-item>
            </v-list-group>

            <v-list-group prepend-icon="mdi-clipboard-file" value="business" no-action>
              <template v-slot:activator="{ props }">
                <v-list-item v-bind="props" title="" style="min-height: 60px;">
                  <template v-slot:title>
                    <span style="font-size: 17px;line-height: 1.5;">営業管理</span>
                  </template>
                </v-list-item>
              </template>
              <v-list-item prepend-icon="mdi-book-open" title="契約管理" value="business1"
                @click="handleClick('business1')"></v-list-item>
              <!-- <v-list-item prepend-icon="mdi-message-bookmark" title="商談管理" value="business2"
                @click="handleClick('business2')"></v-list-item>
              <v-list-item prepend-icon="mdi-notebook-outline" title="売上管理" value="business3"
                @click="handleClick('business3')"></v-list-item>
              <v-list-item prepend-icon="mdi-bookmark-check-outline" title="目標管理" value="business4"
                @click="handleClick('business4')"></v-list-item> -->
            </v-list-group>

            <v-list-group prepend-icon="mdi-book-account" value="manage" no-action>
              <template v-slot:activator="{ props }">
                <v-list-item v-bind="props" title="" style="min-height: 60px;">
                  <template v-slot:title>
                    <span style="font-size: 17px;line-height: 1.5;">経理管理</span>
                  </template>
                </v-list-item>
              </template>
              <v-list-item prepend-icon="mdi-card-account-details" title="請求書" value="manage1"
                @click="handleClick('manage1')"></v-list-item>
              <v-list-item prepend-icon="mdi-clipboard-account" title="見積書" value="manage2"
                @click="handleClick('manage2')"></v-list-item>
              <v-list-item prepend-icon="mdi-clipboard-account-outline" title="注文書" value="manage3"
                @click="handleClick('manage3')"></v-list-item>
              <!-- <v-list-item prepend-icon="mdi-account-cash" title="入金リスト" value="manage4"
                @click="handleClick('manage4')"></v-list-item>
              <v-list-item prepend-icon="mdi-account-cash-outline" title="出金リスト" value="manage5"
                @click="handleClick('manage5')"></v-list-item> -->
            </v-list-group>

            <v-list-group prepend-icon="mdi-account-multiple" value="user" no-action>
              <template v-slot:activator="{ props }">
                <v-list-item v-bind="props" title="" style="min-height: 60px;">
                  <template v-slot:title>
                    <span style="font-size: 17px;line-height: 1.5;">ユーザー管理</span>
                  </template>
                </v-list-item>
              </template>
              <v-list-item prepend-icon="mdi-account" title="ユーザーリスト" value="user1"
                @click="handleClick('user1')"></v-list-item>
              <v-list-item prepend-icon="mdi-account-key" title="パスワードの変更" value="user2"
                @click="handleClick('user2')"></v-list-item>
            </v-list-group>

            <v-list-item prepend-icon="mdi-account-arrow-left-outline" title="" value="logout" style="min-height: 60px;"
              @click="handleLogout">
              <template v-slot:title>
                <span style="font-size: 17px;line-height: 1.5;">Logout</span>
              </template>
            </v-list-item>
          </v-list>
        </div>
      </v-navigation-drawer>

      <v-main style="padding-top: 0px;">
        <slot></slot>
      </v-main>

    </v-layout>
  </v-card>
</template>
<script>
import { removeToken } from '@/utils/auth';
import store from '@/store';

export default {
  name: 'NavigationDrawer',
  components: {
  },
  mixins: [],
  props: {

  },
  data: () => ({
    drawer: false,
    group: null,
    groupStates: {
      info: false,
      business: false,
      manage: false,
      user: false
    },
  }),

  watch: {
    group() {
      this.drawer = false
    },
  },

  methods: {
    handleClick(value) {
      this.drawer = false;

      this.groupStates.info = false;
      this.groupStates.business = false;
      this.groupStates.manage = false;
      this.groupStates.user = false;

      switch (value) {
        case 'info1':
          this.$router.push({ name: 'CustInfoList' });
          break;
        case 'info2':
          this.$router.push({ name: 'StaffInfoList' });
          break;
        case 'business1':
          this.$router.push({ name: 'ContrInfoList' });
          break;
        case 'business2':
          this.$router.push({ name: 'BillInfoList' });
          break;
        case 'business3':
          this.$router.push({ name: '' });
          break;
        case 'business4':
          this.$router.push({ name: '' });
          break;
        case 'manage1':
          this.$router.push({ name: 'BillInfoList' });
          break;
        case 'manage2':
          this.$router.push({ name: '' });
          break;
        case 'manage3':
          this.$router.push({ name: '' });
          break;
        case 'manage4':
          this.$router.push({ name: '' });
          break;
        case 'manage5':
          this.$router.push({ name: '' });
          break;
        case 'user1':
          this.$router.push({ name: 'UserList' });
          break;
        case 'user2':
          this.$router.push({ name: 'UserSelfReset' });
          break;
        default:
          console.log('error value', value);
      }
    },

    handleLogout() {
      removeToken();
      store.commit('logout'); 
      window.location.href = '/login';
    },
  }


};
</script>
<style scoped></style>