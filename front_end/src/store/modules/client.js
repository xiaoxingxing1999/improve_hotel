import {
    addOrderAPI,
    orderMatchCouponsAPI,
    annualOrderAPI,
    registerAPI,
    getMyBillsAPI,
    commentAPI,
    editOrderAPI,
    checkInAPI,
} from '@/api/client'
import { message } from 'ant-design-vue'
import { getToken } from '../../utils/auth'

const client = {
    state: {
        orderModalVisible: false,
        orderEditModalVisible:false,
        registerVIPVisible:false,
        orderMatchCouponList: [],
        userBills:[],
    },
    mutations: {
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_orderEditModalVisible: function(state, data) {
            state.orderEditModalVisible = data
        },
        set_registerVIPVisible: function(state, data) {
            state.registerVIPVisible = data
        },
        set_orderMatchCouponList: function(state, data) {

            state.orderMatchCouponList = data
        },

        set_userBills: (state, data) => {
            state.userBills = data
        },

    },
    actions: {
        addOrder: async({ state, commit }, data) => {
            const res = await addOrderAPI({...data,token:getToken()})
            console.log(res)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI({...data,token:getToken()})
            if(res){
                commit('set_orderMatchCouponList', res)
            }
        },
        
        getUserBills: async ({state,commit}) => {
            const res = await getMyBillsAPI(getToken())
            if (res) {
                commit('set_userBills', res)
                console.log('userBills:')
                console.log(state.userBills)
            }
        },
        cancelOrder: async ({state,dispatch}, orderId) => {
            const res = await annualOrderAPI({orderId,token:getToken()}).catch(err=>{
                console.log('订单撤销失败')
                console.log(err)
                message.error('订单撤销失败')
            })
            if (res) {
                dispatch('getUserOrders')
                message.success('撤销成功')
            }
        },
        register: async ({commit}, data) => {
            const res = await registerAPI(data).catch(err=>{
                console.log('注册失败')
                console.log(err)
                message.error('注册失败')
            })
            if (res) {
                message.success('注册成功')
            }
        },
        
        editOrder:async({ state, commit }, data) => {
            const res = await editOrderAPI({...data,token:getToken()}).catch(err=>{
                console.log('订单修改失败')
                console.log(err)
                message.error('订单修改失败')
            })
            console.log(res)
            if(res){
                message.success('订单修改成功')
                commit('set_orderEditModalVisible', false)
            }
        },
        
        
    }
}
export default client;