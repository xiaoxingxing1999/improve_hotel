import { axios } from '@/utils/request'
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(token){
    return axios({
        url: `${api.adminPre}/getAllManagers`,
        method: 'GET',
        params:{token}
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `${api.adminPre}/addHotel`,
        method: 'POST',
        data,
    })
}

export function checkHotelEditAPI(data) {
    return axios({
        url: `${api.adminPre}/checkHotelEdit`,
        method: 'POST',
        data,
    })
}

export function getAllHotelEditAPI(token) {
    return axios({
        url: `${api.adminPre}/getAllHotelEdit`,
        method: 'GET',
        params:{token},
    })
}