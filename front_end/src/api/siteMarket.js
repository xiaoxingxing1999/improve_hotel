import { axios } from '@/utils/request'
const api = {
    siteMarketPre: '/api/siteMarket'
}
export function getAllBillsAPI(token){
    return axios({
        url: `${api.siteMarketPre}/getAllBills`,
        method: 'GET',
        params:{token}
    })
}
export function bizRegionAPI(params){//网站营销人员新建商圈
    return axios({
        url: `${api.siteMarketPre}/bizRegion`,
        method: 'POST',
        params
    })
}
export function addCreditAPI(data){//网站营销人员为用户充值
    return axios({
        url: `${api.siteMarketPre}/addCredit`,
        method: 'POST',
        data
    })
}
export function situationAPI(token){//网站管理人员查看网站经营情况
    return axios({
        url: `${api.siteMarketPre}/situation`,
        method: 'GET',
        params:{token}
    })
}
export function getAllCouponsAPI(token){
    return axios({
        url: `${api.siteMarketPre}/getAllCoupons`,
        method: 'GET',
        params:{token}
    })
}