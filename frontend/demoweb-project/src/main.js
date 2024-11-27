import { createApp } from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
import vuetify from "./plugins/vuetify";
import { loadFonts } from "./plugins/webfontloader";
import "@mdi/font/css/materialdesignicons.css";
import { getToken } from '@/utils/auth';

loadFonts();
const app = createApp(App);
const token = getToken();
if (token) {
  store.commit('login'); // 标记已登录
}
app.use(router).use(store).use(vuetify).mount("#app");

router.beforeEach((to, from, next) => {
  document.title = to.meta.title || 'ITEC社内総合管理'; 
  next();
});

