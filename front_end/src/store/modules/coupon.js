import {
    hotelTargetMoneyAPI,
    birthdayAPI,
    roomAPI,
    timeAPI,
    unableAPI
} from '@/api/coupon'
import { message } from 'ant-design-vue'
import { getToken } from '../../utils/auth'

const coupon = {
    state: {
        couponVisible: false,
        addCouponVisible: false,
    },
    mutations: {
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        }
    },
    actions: {
        addTargetMoneyCoupon: async({ commit, dispatch }, data) => {
            const res = await hotelTargetMoneyAPI({...data,token:getToken()})
            if(res){
                if(data.hotelId>-1){
                    dispatch('getHotelCoupon')
                }else{
                    dispatch('getAllCoupons')
                }
                
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                message.error('添加失败')
            }
        },//异步的⽅法调⽤，访问服务器api，以及回调⽅法（包括新的dispatch 去取酒店的所有coupon信息和commit view跳转）
        addBirthdayCoupon: async({ commit, dispatch }, data) => {
            const res = await birthdayAPI({...data,token:getToken()})
            if(res){
                if(data.hotelId>-1){
                    dispatch('getHotelCoupon')
                }else{
                    dispatch('getAllCoupons')
                }
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                message.error('添加失败')
            }
        },
        addTimeCoupon: async({ commit, dispatch }, data) => {
            const res = await timeAPI({...data,token:getToken()})
            if(res){
                if(data.hotelId>-1){
                    dispatch('getHotelCoupon')
                }else{
                    dispatch('getAllCoupons')
                }
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                message.error('添加失败')
            }
        },
        addRoomCoupon: async({ commit, dispatch }, data) => {
            const res = await roomAPI({...data,token:getToken()})
            if(res){
                if(data.hotelId>-1){
                    dispatch('getHotelCoupon')
                }else{
                    dispatch('getAllCoupons')
                }
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                message.error('添加失败')
            }
        },
        unableCoupon:async({ state, commit }, data) => {//POST
            const res = await unableAPI({...data,token:getToken()}).catch(err=>{
                console.log('停用策略失败')
                console.log(err)
                message.error('停用策略失败')
            })
            if(res){
                message.success('停用策略成功')
                console.log(res)
            }
        },
    }
}
export default coupon