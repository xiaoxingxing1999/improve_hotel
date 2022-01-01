import { axios } from '@/utils/request'

const api = {
    userPre: '/api/user',
}
export function loginAPI(data){
    console.log('loginAPI')
    return axios({
        url:`${api.userPre}/login`,
        method: 'POST',
        data
    })
}

export function getUserInfoAPI(token){
    return axios({
        url:`${api.userPre}/getUserInfo`,
        method: 'GET',
        params:{token}
    })
}
export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/update`,
        method: 'POST',
        data
    })
}
export function addAPI(data) {//用户回复订单评价
    return axios({
        url: `${api.userPre}/add`,
        method: 'POST',
        data
    })
}
export function changePwdAPI(data) {
    return axios({
        url: `${api.userPre}/changePwd`,
        method: 'POST',
        data
    })
}

export function getOrderCommentsAPI(params){//查看订单的评价
    return axios({
        url:`${api.userPre}/getOrderComments`,
        method: 'GET',
        params
    })
}
export function getAllRepliesAPI(params){//获取订单的评价的10条回复
    return axios({
        url:`${api.userPre}/getAllReplies`,
        method: 'GET',
        params
    })
}
export function getReplyReplyAPI(params){//拉取一个回复的回复(二级回复)
    return axios({
        url:`${api.userPre}/getReplyReply`,
        method: 'GET',
        params
    })
}
export function getMyReplyAPI(params){//用户查看自己的回复
    return axios({
        url:`${api.userPre}/getMyReply`,
        method: 'GET',
        params
    })
}
export function getBizRegionsAPI(){
    return axios({
        url:`${api.userPre}/getBizRegions`,
        method: 'GET',
    })
}

export function uploadFileAPI(fromData) {
    return axios({
        url: `/api/oss/upload`,
        method: 'POST',
        data:fromData,
        headers: {'Content-Type': 'multipart/form-data'},
        
    })
}

export function deleteFileAPI(params) {
    return axios({
        url: `/api/oss/delete`,
        method: 'POST',
        params
    })
}

export function getHotelCommentsAPI(params){//用户查看自己的回复
    return axios({
        url:`${api.userPre}/getHotelComments`,
        method: 'GET',
        params
    })
}

export function getAllServeAPI(token) {//查看所有服务类型
    return axios({
        url: `${api.userPre}/getAllServe`,
        method: 'GET',
        params: {token},
    })
}