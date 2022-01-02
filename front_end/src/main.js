import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Antd, {message} from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import store from './store'
import '@/permission'
import echarts from 'echarts'
import VueLazyload from 'vue-lazyload'
Vue.prototype.$echarts = echarts
Vue.config.productionTip = false
const options = {
  namespace: 'pro__',
  name: 'ls',
  storage: 'local'
}
Vue.use(Antd)
Vue.use(VueLazyload)
Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})

//创建并挂载Vue实例
new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')

message.config({
  duration: 5,// 持续时间
  top:`60px`, // 到页面顶部距离
  maxCount: 3 // 最大显示数, 超过限制时，最早的消息会被自动关闭
})
