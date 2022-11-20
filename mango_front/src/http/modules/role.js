import axios from '../axios'

/*
 * 角色管理模块
 */

// 查询全部
export const findAll = () => {
    return axios({
        url: '/role/findAll',
        method: 'get'
    })
}