
import { createRouter, createWebHistory } from 'vue-router'
import CustInfoList from '../views/CustInfoList.vue'
import CustInfoAdd from '../views/CustInfoAdd.vue'
import CustInfoDetail from '../views/CustInfoDetail.vue'
import Login from '../views/Login.vue'
import store from '@/store';
import UserList from '../views/UserList.vue'
import UserAdd from '../views/UserAdd.vue'
import UserUpdate from '../views/UserUpdate.vue'
import UserReset from '../views/UserReset.vue'
import UserSelfReset from '../views/UserSelfReset.vue'
import StaffInfoList from '../views/StaffInfoList.vue'


const routes = [
  { path: '/', redirect: '/Login' },
  {
    path: '/CustInfoList',
    name: 'CustInfoList',
    component: CustInfoList,
    meta: { requiresAuth: true, title: '基本情報—取引先情報一覧'  }
  },
  {
    path: '/CustInfoAdd',
    name: 'CustInfoAdd',
    component: CustInfoAdd,
    meta: { requiresAuth: true, title: '基本情報—取引先情報'  }
  },
  {
    path: '/CustInfoAdd/:custId',
    name: 'CustInfoEdit',
    component: CustInfoAdd,
    hidden:true,
    meta: { requiresAuth: true, title: '基本情報—取引先情報詳細'  }
  },
  {
    path: '/CustInfoDetail/:custId',
    name: 'CustInfoDetail',
    component: CustInfoDetail,
    hidden:true,
    meta: { requiresAuth: true, title: '基本情報—取引先情報詳細'  }
  },
  {
    path: '/Login',
    name: 'Login',
    component: Login,
    meta: { title: 'ITEC社内総合管理' }
  },
  {
    path: '/UserList',
    name: 'UserList',
    component: UserList,
    meta: { requiresAuth: true, title: 'ユーザー管理—ユーザー情報管理'  }
  },
  {
    path: '/UserAdd',
    name: 'UserAdd',
    component: UserAdd,
    hidden:true,
    meta: { requiresAuth: true}
  },
  {
    path: '/UserUpdate/:userId',
    name: 'UserUpdate',
    component: UserUpdate,
    hidden:true,
    meta: { requiresAuth: true }
  },
  {
    path: '/UserReset/:userId',
    name: 'UserReset',
    component: UserReset,
    hidden:true,
    meta: { requiresAuth: true }
  },
  {
    path: '/UserSelfReset',
    name: 'UserSelfReset',
    component: UserSelfReset,
    hidden:true,
    meta: { requiresAuth: true,title: 'ユーザー管理—バスワードの変更'  }
  },
  {
    path: '/StaffInfoList',
    name: 'StaffInfoList',
    component: StaffInfoList,
    meta: { requiresAuth: true, title: '基本情報—スタッフ情報一覧'  }
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
