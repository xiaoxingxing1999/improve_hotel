import {
    addRoomAPI,
    addRoomTypeAPI,
    addServeAPI,
    checkEditAPI,
    editHotelAPI,
    confirmAPI,
    delRoomAPI,
    getAllRoomTypeAPI,
    
    hotelSituationAPI,
    getHotelOrdersAPI,
    hotelAllCouponsAPI,
    getHotelInfoAPI
} from '@/api/hotelManager'
import{getAllServeAPI}from '@/api/user'
import { message } from 'ant-design-vue'
import { getToken } from '../../utils/auth'
const hotelManager = {
    state: {
        orderList: [],
        addRoomParams: {
            price:0,
            total:0,
            hotelId:0,
            roomType:'',
            
        },
        roomTypes:[
            // {
            //     id:0,
            //     name:'',
            //     uid:0,
            //     creatTime:0
            // }
        ],
        addHotelModalVisible: false,
        addRoomModalVisible: false,
        //addHotelEditVisible:false,
        couponList: [],
        hotelInfo:{
            address:'',
            bizRegion:'',
            hotelDescription:'',
            hotelName:'',
            hotelServe:'',
            hotelStar:'',
            id:0,
            managerId:0,
            phoneNum:'',
            picture:'',
            rate:0,
            room:[]
        },
        hotelEdit:{
            hotelName:'',
            hotelServe:'',
            hotelDescription:'',
            managerId:0,
            phoneNum:'',
            picture:'',
            replyContent:'',
            replyTime:''
        },
        allServes:[],
        hotelSituation:{},

    },
    mutations: {
        set_hotelSituation: function(state, data) {
            state.hotelSituation = data
        },
        set_allServes: function(state, data) {
            state.allServes = data
        },
        set_hotelEdit: function(state, data) {
            state.hotelEdit = data
        },
        set_addHotelEditParams: function(state, data) {
            state.addHotelEditParams = {
                ...state.addHotelEditParams,
                ...data,
            }
        },
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_roomTypes: function(state, data) {
            state.roomTypes = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },

        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_hotelInfo: function(state, data) {
            state.hotelInfo = data
        },
        
    },
    actions: {
        getHotelOrders: async({ state, commit }) => {
            const res = await getHotelOrdersAPI(getToken())
            if(res){
                commit('set_orderList', res)
            }
        },
        getHotelInfo: async({ state, commit,dispatch }) => {
            const res=await getHotelInfoAPI(getToken())
            if(res){
                commit('set_hotelInfo', res)
            }
        },
        getAllRoomType:async({ state, commit }) => {
            const res=await getAllRoomTypeAPI(getToken())
            if(res){
                commit('set_roomTypes', res)
            }
        },
        addRoomType:async({ state, commit,dispatch },data) => {
            const res = await addRoomTypeAPI({...data,token:getToken()})
            if(res) {
                await dispatch('getAllRoomType')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            //判断房间类型是否已存在
            let hasRoomType=false;
            state.roomTypes.forEach(e=>{
                if(e.name===state.addRoomParams.roomType){
                    hasRoomType=true;
                    console.log('房间类型存在：'+state.addRoomParams.roomType);
                }
            })
            if(!hasRoomType){
                console.log('房间类型不存在，尝试添加：'+state.addRoomParams.roomType)
                await dispatch("addRoomType",{name:state.addRoomParams.roomType})
            }
            //判断服务类型是否已存在
            let allServes=[]
            state.allServes.forEach(e=>{
                allServes.push(e.name)
            })
            let hasServe=false;
            let newServes=[];
            state.addRoomParams.serve.split('|').forEach(e=>{
                if(!allServes.includes(e)){
                    newServes.push(e)
                    console.log('服务类型不存在：'+e);
                }
            })
            newServes.forEach(e=>{
                dispatch('addServe',{name:e})
            })
            //
            const res = await addRoomAPI({
                ...state.addRoomParams,
                token:getToken(),
            })
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {})
                await dispatch("getHotelInfo");//刷新房间列表
                message.success('添加成功')
            }else{
                if(hasRoomType){
                    message.error('添加失败,存在同类型房间')
                }
                else{
                    message.error('添加失败')
                }
                
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(getToken())
            if(res) {
                commit('set_couponList', res)
            }
        },
        addServe:async({ state, commit,dispatch }, data) => {//POST
            const res = await addServeAPI({...data,token:getToken()}).catch(err=>{
                console.log('添加服务失败')
                console.log(err)
                message.error('添加服务失败')
            })
            if(res){
                message.success('添加服务成功')
                console.log(res)
                await dispatch('getAllServe')
            }
        },
        checkEdit:async({ state, commit }) => {//GET
            const res = await checkEditAPI(getToken()).catch(err=>{
                console.log('查看酒店信息修改情况失败')
                console.log(err)
                message.error('查看酒店信息修改情况失败')
            })
            if(res){
                message.success('查看酒店信息修改情况成功')
                console.log(res)
                commit('set_hotelEdit',res)
            }
        },
        editHotel:async({ state, commit,dispatch }, data) => {//POST
             //判断服务类型是否已存在
             console.log('申请修改',data)
             let allServes=[]
             state.allServes.forEach(e=>{
                 allServes.push(e.name)
             })
             let newServes=[];
             data.hotelServe.split('|').forEach(e=>{
                 if(!allServes.includes(e)){
                     newServes.push(e)
                     console.log('服务类型不存在：'+e);
                 }
             })
             newServes.forEach(e=>{
                 dispatch('addServe',{name:e})
             })
            const res = await editHotelAPI({...data,token:getToken()}).catch(err=>{
                console.log('申请修改酒店信息失败')
                console.log(err)
                message.error('申请修改酒店信息失败')
            })
            if(res){
                message.success('申请修改酒店信息成功')
                console.log(res)
                await dispatch('checkEdit')
            }
        },
        handleOrder:async({ state, commit,dispatch }, data) => {//POST
            const res = await confirmAPI({...data,token:getToken()}).catch(err=>{
                console.log('处理订单失败')
                console.log(err)
                message.error('处理订单失败')
            })
            if(res){
                message.success('处理订单成功')
                console.log(res)
                await dispatch('getHotelOrders')
            }
        },
        delRoom:async({ state, commit,dispatch }, data) => {//POST
            const res = await delRoomAPI({...data,token:getToken()}).catch(err=>{
                console.log('删除房间失败')
                console.log(err)
                message.error('删除房间失败')
            })
            if(res){
                message.success('删除房间成功')
                console.log(res)
                await dispatch('getHotelInfo')
            }
        },
        getAllServe:async({ state, commit }) => {//GET
            const res = await getAllServeAPI(getToken()).catch(err=>{
                console.log('获取所有服务失败')
                console.log(err)
                message.error('获取所有服务失败')
            })
            if(res){
                let data=[]
                res.forEach(s=>{
                    if(s.name){
                        data.push(s)
                    }
                })

                message.success('获取所有服务成功')
                console.log(res)
                commit("set_allServes",data)
            }
        },
        getHotelSituation:async({ state, commit }) => {//GET
            const res = await hotelSituationAPI(getToken()).catch(err=>{
                console.log('查看酒店经营情况失败')
                console.log(err)
                message.error('查看酒店经营情况失败')
            })
            if(res){
                message.success('查看酒店经营情况成功')
                console.log(res)
                commit('set_hotelSituation',res)
            }
        },

}
}
export default hotelManager