import { axios } from '@/utils/request'

const api = {
    clientPre: '/api/client',
    clientPreMock:'https://mockapi.eolinker.com/FQhNbkYf08624911b4948f01c5051da89f96edd1a24a2a7/api/client'
}
export function registerAPI(data){
    return axios({
        url:`${api.clientPre}/register`,
        method: 'POST',
        data
    })
}

export function getMyBillsAPI(token) {
    return axios({
        url: `${api.clientPre}/getMyBills`,
        method: 'GET',
        params:{token}
    })
}
export function commentAPI(data) {
    return axios({
        url: `${api.clientPre}/comment`,
        method: 'POST',
        data
    })
}
export function editOrderAPI(data) {
    return axios({
        url: `${api.clientPre}/editOrder`,
        method: 'POST',
        data
    })
}
export function checkInAPI(data) {//登记会员
    return axios({
        url: `${api.clientPre}/checkIn`,
        method: 'POST',
        data
    })
}
export function getUserOrdersAPI(token) {
    return axios({
        url: `${api.clientPre}/getUserOrders`,
        method: 'GET',
        params:{token}
    })
}
export function annualOrderAPI(params) {
    return axios({
        url: `${api.clientPre}/annualOrder`,
        method: 'POST',
        params
    })
}
export function orderMatchCouponsAPI(params) {//获取所有匹配订单的优惠券
    return axios({
        url: `${api.clientPre}/orderMatchCoupons`,
        method: 'GET',
        params
    })
}
export function addOrderAPI(data) {
    return axios({
        url: `${api.clientPre}/addOrder`,
        method: 'POST',
        data
    })
}