// axios拦截器
import axios from 'axios'
import config from './config.js'
import Cookies from 'js-cookie'
import router from "@/router/index.js"

export default function $axios(options) {
    return new Promise((resolve, reject) => {
        const instance = axios.create({
            baseURL: config.baseUrl,
            headers: config.headers,
            timeout: config.timeout,
            withCredentials: config.withCredentials
        })
        instance.defaults.withCredentials = true;
        // request 请求拦截器
        instance.interceptors.request.use(config => {
            let token = Cookies.get("token")
            if (token) {  // 发送请求时携带token
                config.headers.token = token
            } else{  // 重定向到登录界面
                router.push("/login")
            }
            return config
        },
            error => {
                return Promise.reject(error)
            }
        )

        // response 相应拦截器
        instance.interceptors.response.use(
            response => {
                return response.data
            },
            err => {
                return Promise.reject(err)
            }
        )

        // 请求处理
        instance(options).then(res => {
            resolve(res)
            return false
        }).catch(error => {
            reject(error)
        })
    })
}