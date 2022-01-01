import { axios } from '@/utils/request'
import qs from 'qs'
require('qs')
const api = {
    hotelPre: '/api/hotel'
}
export function getAllHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}
export function getHotelByIdAPI(params) {
    return axios({
        url: `${api.hotelPre}/detail`,
        method: 'GET',
        params
    })
}
export function searchHotelAPI(params) {
    return axios({
        url: `${api.hotelPre}/search`,
        method: 'GET',
        params,
        paramsSerializer: params => {
            return qs.stringify(params, {
                indices: false
            })
            }
    })
}

