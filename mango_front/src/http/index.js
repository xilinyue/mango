import Vue from "vue"
import api from "./api.js"

const install = Vue => {
    if (install.installed)
        return;
    install.installed = true;
    Object.defineProperties(Vue.prototype, {
        // 注意，此处挂载在Vue原型的$api 对象上
        $api: {
            get() {
                return api;
            }
        }
    })
}
export default install