import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login.vue'
import test from '../views/test.vue'
Vue.use(VueRouter) //模块化开发用到这一句
const routes = [{
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/test',
    name: 'test',
    component: test
  },
  {
    //这里是父级路由，展示的是框架（就是sidebar、header这些），直接redirect到第一个子路由（即具体内容）
    //“重定向（redirct）”的意思是，当用户访问 /a时，URL 将会被替换成 /b，然后匹配路由为 /b
    path: '/admin/home',
    name: 'adminHome',
    component: () => import('@/views/admin/home'),
    redirect: '/admin/hotelAndManager',
    children: [{
        // 当 /admin/home 匹配成功，redirect到/admin/hotelAndManager
        // hotelAndManager 会被渲染在 adminHome 的 <router-view> 中
        path: '/admin/hotelAndManager',
        name: 'hotelAndManager',
        component: () => import('@/views/admin/hotelAndManager')
      },
      {
        path: '/admin/hotelEdit',
        name: 'hotelEdit',
        component: () => import('@/views/admin/hotelEdit')
      },
      {
        path: '/admin/info',
        name: 'info',
        component: () => import('@/views/admin/info')
      },
      // {
      //   path: '/admin/webSituation',
      //   name: 'webSituation',
      //   component: () => import('@/views/admin/webSituation')
      // },
    ]
  },
  {
    path: '/siteMarket/home',
    name: 'siteMarketHome',
    component: () => import('@/views/siteMarket/home'),
    redirect: '/siteMarket/webSituation',
    children: [
      {
        path: '/siteMarket/webSituation',
        name: 'webSituation',
        component: () => import('@/views/siteMarket/webSituation')
      },
      {
        path: '/siteMarket/addCredit',
        name: 'addCredit',
        component: () => import('@/views/siteMarket/addCredit')
      },
      {
        path: '/siteMarket/bizRegion',
        name: 'bizRegion',
        component: () => import('@/views/siteMarket/bizRegion')
      },
      {
        path: '/siteMarket/coupon',
        name: 'coupon',
        component: () => import('@/views/siteMarket/coupon')
      },
      {
        path: '/siteMarket/info',
        name: 'info',
        component: () => import('@/views/siteMarket/info')
      },

    ]
  },
  {
    path: '/hotelManager/home',
    name: 'hotelManagerHome',
    component: () => import('@/views/hotelManager/home'),
    redirect: '/hotelManager/hotelSituation',
    children: [
      {
        path: '/hotelManager/hotelInfo',
        name: 'hotelInfo',
        component: () => import('@/views/hotelManager/hotelInfo')
      },
      {
        path: '/hotelManager/coupon',
        name: 'coupon',
        component: () => import('@/views/hotelManager/coupon')
      },
      {
        path: '/hotelManager/handleOrder',
        name: 'handleOrder',
        component: () => import('@/views/hotelManager/handleOrder')
      },
      {
        path: '/hotelManager/info',
        name: 'info',
        component: () => import('@/views/hotelManager/info')
      },
      {
        path: '/hotelManager/hotelSituation',
        name: 'hotelSituation',
        component: () => import('@/views/hotelManager/hotelSituation')
      },
      {
        path: '/hotelManager/replyComment',
        name: 'replyComment',
        component: () => import('@/views/hotelManager/replyComment')
      },
      {
        path: '/hotelManager/updateRoom',
        name: 'updateRoom',
        component: () => import('@/views/hotelManager/updateRoom')
      },

    ]
  },
  {
    path: '/client/home',
    name: 'clientHome',
    redirect: '/client/hotelList',
    component: () => import('@/views/client/home'),
    children: [{
        path: '/client/hotelList',
        name: 'hotelList',
        component: () => import('@/views/client/hotelList')
      },
      {
        path: '/client/hotelDetail/:hotelId', //动态路由参数，用$route.params.hotelId使用
        name: 'hotelDetail',
        component: () => import('@/views/client/hotelDetail')
      },
      {
        path: '/client/info',
        name: 'userInfo',
        component: () => import('@/views/client/info')
      },
      {
        path: '/client/orders',
        name: 'orders',
        component: () => import('@/views/client/orders')
      },
      {
        path: '/client/orderDetail/:orderId',
        name: 'orderDetail',
        component: () => import('@/views/client/orderDetail')
      },



    ]
  },
]
const createRouter = () => new VueRouter({
  // mode: 'history', // require service support
  scrollBehavior: () => ({
    y: 0
  }),
  routes
})
const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router