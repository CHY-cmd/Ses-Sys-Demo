
import { createRouter, createWebHistory } from 'vue-router'
import CustInfoList from '../views/CustInfoList.vue'
import CustInfoAdd from '../views/CustInfoAdd.vue'
import CustInfoDetail from '../views/CustInfoDetail.vue'
import Login from '../views/Login.vue'
import store from '@/store';


const routes = [
  {
    path: '/CustInfoList',
    name: 'CustInfoList',
    component: CustInfoList,
    meta: { requiresAuth: true }
  },
  {
    path: '/CustInfoAdd',
    name: 'CustInfoAdd',
    component: CustInfoAdd,
    meta: { requiresAuth: true }
  },
  {
    path: '/CustInfoAdd/:custId',
    name: 'CustInfoEdit',
    component: CustInfoAdd,
    hidden:true,
    meta: { requiresAuth: true }
  },
  {
    path: '/CustInfoDetail/:custId',
    name: 'CustInfoDetail',
    component: CustInfoDetail,
    hidden:true,
    meta: { requiresAuth: true }
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login
  },
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach((to, from, next) => {
  if (to.matched.some(record => record.meta.requiresAuth)) {
    if (!store.state.isLoggedIn) {
      next({
        path: '/login',
        query: { redirect: to.fullPath }
      });
    } else {
      next();
    }
  } else {
    next();
  }
});



export default router
