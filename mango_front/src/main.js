import Vue from 'vue'
import App from './App.vue'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import api from "./http/index.js"
import global from "@/utils/global.js"
import store from './store'
import i18n from "@/i18n";
import "font-awesome/css/font-awesome.min.css";

Vue.config.productionTip = false

Vue.use(ElementUI)
Vue.use(api)

Vue.prototype.global = global //挂在全局配置模块

new Vue({
  router,
  store,
  i18n,
  render: h => h(App)
}).$mount('#app')
