
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
import StaffInfoAdd from '../views/StaffInfoAdd.vue'
import StaffInfoDetail from '../views/StaffInfoDetail.vue'
import ContrInfoList from '../views/ContrInfoList.vue'
import ContrSesAdd from '../views/ContrSesAdd.vue'
import ContrSesDetail from '../views/ContrSesDetail.vue'
import ContrCrtAdd from '../views/ContrCrtAdd.vue'
import ContrCrtDetail from '../views/ContrCrtDetail.vue'
import BillInfoList from '../views/BillInfoList.vue'
import BillSesAdd from '../views/BillSesAdd.vue'

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
  {
    path: '/StaffInfoAdd',
    name: 'StaffInfoAdd',
    component: StaffInfoAdd,
    meta: { requiresAuth: true, title: '基本情報—スタッフ情報管理'  }
  },
  {
    path: '/StaffInfoAdd/:staffId',
    name: 'StaffInfoEdit',
    component: StaffInfoAdd,
    hidden:true,
    meta: { requiresAuth: true, title: '基本情報—スタッフ情報管理'  }
  },
  {
    path: '/StaffInfoDetail/:staffId',
    name: 'StaffInfoDetail',
    component: StaffInfoDetail,
    meta: { requiresAuth: true, title: '基本情報—スタッフ情報詳細'  }
  },
  {
    path: '/ContrInfoList',
    name: 'ContrInfoList',
    component: ContrInfoList,
    meta: { requiresAuth: true, title: '営業管理—契約情報一覧'  }
  },
  {
    path: '/ContrSesAdd',
    name: 'ContrSesAdd',
    component: ContrSesAdd,
    meta: { requiresAuth: true, title: '営業管理—契約情報管理'  }
  },
  {
    path: '/ContrSesAdd/:contrNo',
    name: 'ContrSesEdit',
    component: ContrSesAdd,
    meta: { requiresAuth: true, title: '営業管理—契約情報管理'  }
  },
  {
    path: '/ContrSesDetail/:contrNo',
    name: 'ContrSesDetail',
    component: ContrSesDetail,
    meta: { requiresAuth: true, title: '営業管理—契約情報詳細'  }
  },
  {
    path: '/ContrCrtAdd',
    name: 'ContrCrtAdd',
    component: ContrCrtAdd,
    meta: { requiresAuth: true, title: '営業管理—契約情報管理'  }
  },
  {
    path: '/ContrCrtAdd/:contrNo',
    name: 'ContrCrtEdit',
    component: ContrCrtAdd,
    meta: { requiresAuth: true, title: '営業管理—契約情報管理'  }
  },
  {
    path: '/ContrCrtDetail/:contrNo',
    name: 'ContrCrtDetail',
    component: ContrCrtDetail,
    meta: { requiresAuth: true, title: '営業管理—契約情報詳細'  }
  },
  {
    path: '/BillInfoList',
    name: 'BillInfoList',
    component: BillInfoList,
    meta: { requiresAuth: true, title: '経理管理-請求書一覧'  }
  },
  {
    path: '/BillSesAdd',
    name: 'BillSesAdd',
    component: BillSesAdd,
    meta: { requiresAuth: true, title: '経理管理-請求書情報管理'  }
  },
  {
    path: '/BillSesAdd/:billNo',
    name: 'BillSesEdit',
    component: BillSesAdd,
    meta: { requiresAuth: true, title: '経理管理-請求書情報管理'  }
  },
]
const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

//判断是否已授权，无授权跳转到登录
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
