import { axios } from '@/utils/request'
const api = {
    hotelManagerPre: '/api/hotelManager'
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelManagerPre}/addRoom`,
        method: 'POST',
        data,
    })
}
export function addRoomTypeAPI(data) {
    return axios({
        url: `${api.hotelManagerPre}/addRoomType`,
        method: 'POST',
        data,
    })
}
export function addServeAPI(data) {
    return axios({
        url: `${api.hotelManagerPre}/addServe`,
        method: 'POST',
        data,
    })
}

export function getHotelOrdersAPI(token) {
    return axios({
        url: `${api.hotelManagerPre}/getHotelOrders`,
        method: 'GET',
        params:{token}
    })
}
export function hotelAllCouponsAPI(token) {
    return axios({
        url: `${api.hotelManagerPre}/hotelAllCoupon`,
        method: 'GET',
        params: {token},
    })
}
export function checkEditAPI(token) {//查看酒店信息修改情况
    return axios({
        url: `${api.hotelManagerPre}/checkEdit`,
        method: 'GET',
        params: {token},
    })
}

export function editHotelAPI(data) {//申请修改酒店信息
    return axios({
        url: `${api.hotelManagerPre}/editHotel`,
        method: 'POST',
        data,
    })
}
export function confirmAPI(data) {//处理订单
    return axios({
        url: `${api.hotelManagerPre}/confirm`,
        method: 'POST',
        data,
    })
}
export function delRoomAPI(params) {//删除房间
    return axios({
        url: `${api.hotelManagerPre}/delRoom`,
        method: 'POST',
        params,
    })
}

export function getAllRoomTypeAPI(token) {//查看所有房间类型
    return axios({
        url: `${api.hotelManagerPre}/getAllRoomType`,
        method: 'GET',
        params: {token},
    })
}

export function hotelSituationAPI(token) {//查看酒店经营情况
    return axios({
        url: `${api.hotelManagerPre}/hotelSituation`,
        method: 'GET',
        params: {token},
    })
}
export function getHotelInfoAPI(token) {
    return axios({
        url: `${api.hotelManagerPre}/getHotelInfo`,
        method: 'GET',
        params: {token},
    })
}