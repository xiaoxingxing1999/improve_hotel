import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getAllHotelsAPI,
    getHotelByIdAPI,
    searchHotelAPI
} from '@/api/hotel'



const hotel = {
    state: {
        hotelList: [
            
        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: false,
        currentHotelId: '',
        currentHotelInfo: {
            
        },
        
        currentOrderRoom: {

        },
        
    },
    mutations: {
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        
    },

    actions: {
        getHotelList: async({commit, state}) => {
            const res = await getAllHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getHotelById: async({commit, state},data) => {
            const res = await getHotelByIdAPI(data)
            if(res){
                commit('set_currentHotelInfo', res)
            }
        },
        searchHotel:async({ state, commit },data) => {//GET
            let params={
                timeStart:data.timeRange[0].valueOf(),
                timeEnd:data.timeRange[1].valueOf(),
                star:data.stars,
                lowerPrice:data.priceRange[0],
                highPrice:data.priceRange[1],
                roomType:data.roomTypes,
                rate:data.lowestRate,
                
                serves:data.serves
            };
            if(data.name){
                params.name=data.name;
            }
            const res = await searchHotelAPI(params).catch(err=>{
                console.log('搜索酒店失败')
                console.log(err)
                message.error('搜索酒店失败')
            })
            commit('set_hotelListLoading', false)
            if(res){
                message.success('搜索酒店成功')
                console.log(res)
                commit('set_hotelList', res)
            }else{
                commit('set_hotelList', [])
            }
        },
        
    }
}

export default hotel