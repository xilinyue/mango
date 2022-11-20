import axios from "../axios"

/** 用户管理模块 */
// 保存
export const save = (data) => {
    return axios({url: "/user/save", method: 'post', data});
};

// 删除
export const batchDelete = (data) => {
    return axios({
        url: "/user/delete",
        method: "post",
        data
    })
};

// 根据用户名查找
export const findByName = (params) => {
    return axios({
        url: "/user/findByName",
        method: 'get',
        params
    })
};

// 查找用户的菜单权限标识集合
export const findPermissions = (params) => {
    return axios({
        url: "/user/findPermissions",
        method: "get",
        params
    })
};

// 分页查询用户数据
export const findPage = (data) => {
    return axios({
        url: '/user/findPage',
        method: 'post',
        data
    })
};

// 导出Excel用户信息
export const exportUserExcelFile = (data) => {
    return axios({
        url: '/user/exportExcelUser',
        method: 'post',
        responseType: 'blob',
        data,
    })
};

// 更新用户密码
export const updatePassword = (params) => {
    return axios({
        url: "/user/updatePassword",
        method: "get",
        params
    })
};