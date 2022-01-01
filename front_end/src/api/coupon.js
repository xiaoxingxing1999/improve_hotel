import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelTargetMoneyAPI(data) {//满减优惠
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}
export function birthdayAPI(data) {//生日优惠
    return axios({
        url: `${api.couponPre}/birthday`,
        method: 'POST',
        data,
    })
}
export function roomAPI(data) {//多间优惠
    return axios({
        url: `${api.couponPre}/room`,
        method: 'POST',
        data,
    })
}
export function timeAPI(data) {//限时优惠
    return axios({
        url: `${api.couponPre}/time`,
        method: 'POST',
        data,
    })
}
export function unableAPI(params) {//撤销优惠策略
    return axios({
        url: `${api.couponPre}/unable`,
        method: 'POST',
        params,
    })
}



