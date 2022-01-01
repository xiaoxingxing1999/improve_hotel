import {
    getAllBillsAPI,
    bizRegionAPI,
    addCreditAPI,
    situationAPI,
    getAllCouponsAPI,

} from '@/api/siteMarket'
import { message } from 'ant-design-vue'
import { getToken } from '../../utils/auth'

const siteMarket = {
    state: {
        bills:[],
        webSituation:{},
        siteAllCoupons:[],
    },
    mutations: {
        set_bills: function(state, data) {
            state.bills = data
        },
        set_webSituation: function(state, data) {
            state.webSituation = data
        },
        set_siteAllCoupons:function(state,data){
            state.siteAllCoupons=data
        },
    },
    actions: {
        addBizRegion:async({ state, commit ,dispatch}, data) => {//POST
            const res = await bizRegionAPI({...data,token:getToken()}).catch(err=>{
                console.log('添加商圈失败')
                console.log(err)
                message.error('添加商圈失败')
            })
            if(res){
                message.success('添加商圈成功')
                await dispatch('getBizRegions')
                console.log(res)
            }
        },
        addCredit:async({ state, commit,dispatch }, data) => {//POST
            const res = await addCreditAPI({...data,token:getToken()}).catch(err=>{
                console.log('信用充值失败')
                console.log(err)
                message.error('信用充值失败')
            })
            if(res){
                message.success('信用充值成功')
                console.log(res)
                await dispatch('getAllBills')
            }
        },
        getAllBills:async({ state, commit }) => {//GET
            const res = await getAllBillsAPI(getToken()).catch(err=>{
                console.log('获取信用充值记录失败')
                console.log(err)
                message.error('获取信用充值记录失败')
            })
            if(res){
                message.success('获取信用充值记录成功')
                console.log(res)
                commit('set_bills',res)
            }
        },
        getWebSituation:async({ state, commit }) => {//GET
            const res = await situationAPI(getToken()).catch(err=>{
                console.log('查看网站经营情况失败')
                console.log(err)
                message.error('查看网站经营情况失败')
            })
            if(res){
                message.success('查看网站经营情况成功')
                console.log(res)
                commit('set_webSituation',res)
            }
        },
        getAllCoupons:async({state,commit})=>{//GET
            const res=await getAllCouponsAPI(getToken()).catch(err=>{
                console.log('获取优惠券信息失败')
                console.log(err)
                message.error('获取优惠券信息失败')
            })
            if(res){
                message.success('获取优惠券信息成功')
                console.log(res)
                commit('set_siteAllCoupons',res)
            }
        }
    }
}
export default siteMarket