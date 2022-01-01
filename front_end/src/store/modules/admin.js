import {
    getManagerListAPI,
    addManagerAPI,
    addHotelAPI,
    checkHotelEditAPI,
    getAllHotelEditAPI,

} from '@/api/admin'

import {
    message
} from 'ant-design-vue'
import {
    getToken
} from '../../utils/auth'

const admin = {
    state: {
        managerList: [

        ],
        addManagerModalVisible: false,
        addManagerParams: {
            email: '',
            password: '',
            phoneNumber: '',
            userName: '',
            hotelId: '',
            url: '',
        },

        addHotelParams: {
            hotelName: '',
            hotelDescription: '',
            address: '',
            bizRegion: 'XiDan',
            hotelStar: '',
            phoneNum: '',
            hotelServe: '',
            picture: '',
        },
        hotelEdits: [{
            id: 0,
            hotelName: "WF*u*[e",
            hotelDescription: "[Hq!Ujd",
            phoneNum: "K1n7",
            managerId: -0,
            hotelServe: "ysh1",
            picture: "PZMLeJ",
            createTime: 6686857482962668,
            status: "n20qd$p",
            replyContent: "Tf0mJ",
            replyTime: -8890536501765448
        }],
    },
    mutations: {
        set_managerList: function (state, data) {
            state.managerList = data
        },
        set_addManagerModalVisible: function (state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function (state, data) {
            console.log('set_addManagerParams')
            console.log(data, state.addManagerParams)
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_addHotelParams: function (state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_hotelEdits:function (state, data) {
            state.hotelEdits = data;
        },
    },
    actions: {
        //解构赋值
        getManagerList: async ({
            commit
        }) => {
            const res = await getManagerListAPI(getToken())
            if (res) {
                commit('set_managerList', res)
            }
        },
        getAllHotelEdit:async ({commit}) => {
            const res = await getAllHotelEditAPI(getToken())
            if (res) {
                commit('set_hotelEdits', res)
            }
        },

        addHotel: async ({
            state,
            dispatch,
            commit
        }) => {
            const res = await addHotelAPI({
                token: getToken(),
                ...state.addHotelParams
            })
            if (res) {
                dispatch('getHotelList')
                commit('set_addHotelParams', {
                    hotelName: '',
                    hotelDescription: '',
                    address: '',
                    bizRegion: 'XiDan',
                    hotelStar: '',
                    phoneNum: '',
                    hotelServe: '',
                    picture: '',
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            } else {
                message.error('添加失败')
            }
        },
        addManager: async ({
            state,
            commit,
            dispatch
        }) => {
            const res = await addManagerAPI({
                token: getToken(),
                ...state.addManagerParams
            })
            if (res) {
                commit('set_addManagerParams', {
                    email: '',
                    password: '',
                    phoneNumber: '',
                    userName: '',

                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
                dispatch('getHotelList') //更新对应hotel的managerId
            } else {
                message.error('添加失败')
            }
        },
        checkHotelEdit: async({ state, commit }, data) => {
            const res = await checkHotelEditAPI({...data,token:getToken()})
            console.log(res)
            if(res){
                message.success('处理成功')
            }
        },

    }
}
export default admin