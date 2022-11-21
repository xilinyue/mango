import axios from "../axios";

/*
 * 菜单管理模块
 */
// 查找导航菜单数据
export const findNavTree = (params) => {
    return axios({
        url: "/menu/findNavTree",
        method: "get",
        params
    })
}
// 查找导航菜单树
export const findMenuTree = () => {
    return axios({
        url: '/menu/findMenuTree',
        method: 'get'
    })
}