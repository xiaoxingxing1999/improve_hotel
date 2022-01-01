import {
    getUserInfoAPI,
    loginAPI,
    updateUserInfoAPI,
    addAPI,
    changePwdAPI,
    getOrderCommentsAPI,
    getAllRepliesAPI,
    getReplyReplyAPI,
    getMyReplyAPI,
    getBizRegionsAPI,
    uploadFileAPI,
    deleteFileAPI,
    getHotelCommentsAPI
} from '@/api/user'
import {
    getUserOrdersAPI,
    checkInAPI,
    commentAPI,
} from '@/api/client'
import router, {
    resetRouter
} from '@/router'
import {
    removeToken,
    setToken,
    getToken
} from '@/utils/auth'
import {
    message
} from 'ant-design-vue'



const user = {
    state: {
        userInfo: {
            userId: 0,
            email: '',
            password: '',
            userName: '',
            phoneNumber: '',
            credit: 0.0,
            userType: 'Client',
            url: 'https://software3.oss-cn-beijing.aliyuncs.com/2020-06-28/1593351539438-a3b864b0dfe84abaa0.jpeg',
            totalcredit:0.0,
            vipInfo:'',
            vipType:'',
        },
        userOrderList: [],
        orderShown:[],
        userReplies: [],
        orderComment: {},
        commentReplies: [],
        subReplies:[],
        bizRegions: [],
        hotelComments:[],
    },

    mutations: {
        set_hotelComments: (state, data) => {
            state.hotelComments = data
        },
        set_orderShown: (state, data) => {
            state.orderShown = data
        },
        set_bizRegions: (state, data) => {
            state.bizRegions = data
        },
        set_userReplies: (state, data) => {
            state.userReplies = data
        },
        set_orderComment: (state, data) => {
            state.orderComment = data
        },
        set_commentReplies: (state, data) => {
            state.commentReplies = data
        },
        set_subReplies: (state, data) => {
            state.subReplies=data
            // state.commentReplies.forEach(reply => {
            //     if (reply.id === data[0].rid) {
            //         reply.subReplies = data;
            //     }
            // })
        },
        set_ReplyReplies: (state, data) => {
            state.CommentReplies = data
        },
        reset_state: function (state) {
            state.userInfo.userId = 0,
                state.userInfo.email = '',
                state.userInfo.password = '',
                state.userInfo.userName = '',
                state.userInfo.phoneNumber = '',
                state.userInfo.credit = 0.0,
                state.userInfo.userType = 'Client',
                state.userInfo.url = '',
                state.userInfo.userOrderList = []
        },
        set_AllInfo: function (state, info) {
            state.userInfo.userId = info.id
            state.userInfo.email = info.email
            state.userInfo.password = info.password
            state.userInfo.userName = info.userName
            state.userInfo.phoneNumber = info.phoneNumber
            state.userInfo.credit = info.credit
            state.userInfo.url = info.url?info.url:'https://software3.oss-cn-beijing.aliyuncs.com/2020-06-28/1593351539438-a3b864b0dfe84abaa0.jpeg'
            state.userInfo.userType = info.userType
            //          userTypes = ['Client', 'HotelManager', 'SiteMarket', 'Manager']
            state.userType = info.userType
            state.userInfo.totalcredit=info.totalcredit
            state.userInfo.vipInfo=info.vipInfo
            state.userInfo.vipType=info.vipType
            //todo: orderlist
        },
        set_userName: (state, data) => {
            state.userInfo.userName = data
        },

        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },

    },

    actions: {
        login: async ({
            dispatch,
            state,
            commit
        }, userData) => {
            const res = await loginAPI(userData).catch(err => {
                console.log('登陆失败')
                console.log(err)
                message.error('登陆失败')
            })
            console.log('获取到登陆token :')
            console.log(res)
            if (res) {
                setToken(res.token)
                commit('set_userName', res.userName)
                await dispatch('getUserInfo')
                //判断用户类别，推送到不同主页
                if (state.userType === "Manager") {
                    router.push('/admin/home')
                } else if (state.userType === "SiteMarket") {
                    router.push('/siteMarket/home')
                } else if (state.userType === "HotelManager") {
                    router.push('/hotelManager/home')
                } else {
                    router.push('/client/home')
                }

            }
        },
        getUserOrders: async ({
            state,
            commit,
            rootState
        }) => {
            const res = await getUserOrdersAPI(getToken())
            if (res) {
                commit('set_userOrderList', res)
                console.log(state.userOrderList)
            }
        },

        getUserInfo: async ({
            state,
            commit
        }) => {
            const res = await getUserInfoAPI(getToken())
            console.log('获取到用户信息：')
            console.log(res)
            commit('set_AllInfo', res)
        },
        updateUserInfo: async ({
            state,
            dispatch
        }, data) => {
            const params = {
                ...state.userInfo,
                token:getToken(),
                ...data,
            }
            return await updateUserInfoAPI(params)
            
        },


        logout: async ({
            commit
        }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
        // remove token
        resetToken({
            commit
        }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },

        registerVIP: async ({
            state,
            commit,
            dispatch
        }, data) => {
            const res = await checkInAPI({
                ...data,
                token: getToken()
            })
            console.log(res)
            if (res) {
                message.success('会员信息登记成功')
                commit('set_registerVIPVisible', false)
                await dispatch('getUserInfo')
            }
        },
        comment: async ({
            state,
            commit,
            dispatch
        }, data) => {
            const res = await commentAPI({
                ...data,
                token: getToken()
            }).catch(err => {
                console.log('订单评价失败')
                console.log(err)
                message.error('订单评价失败')
            })
            if (res) {
                message.success('订单评价成功')
                console.log(res)
                
            }
        },
        getOrderComment: async ({
            state,
            commit
        }, data) => { //GET
            const res = await getOrderCommentsAPI({
                ...data,
                token: getToken()
            }).catch(err => {
                console.log('获取订单评论失败')
                console.log(err)
                message.error('获取订单评论失败')
            })
            if (res) {
                message.success('获取订单评论成功')
                console.log(res)
                commit('set_orderComment', res)
            }else{
                message.success('订单未评论')
                console.log(res)
                commit('set_orderComment', {})
            }
        },
        getCommentReplies: async ({
            state,
            commit
        }, data) => { //GET-获取订单的Comment的10条评论
            const res = await getAllRepliesAPI({
                ...data,
                token: getToken()
            }).catch(err => {
                console.log('获取订单评论的回复失败')
                console.log(err)
                message.error('获取订单评论的回复失败')
            })
            if (res) {
                message.success('获取订单评论的回复成功')
                console.log(res)
                commit('set_commentReplies', res)
            }
        },
        getMyReply: async ({
            state,
            commit
        }, data) => { //GET-用户查看自己的回复
            const res = await getMyReplyAPI({
                ...data,
                token: getToken()
            }).catch(err => {
                console.log('获取用户回复失败')
                console.log(err)
                message.error('获取用户回复失败')
            })
            if (res) {
                message.success('获取用户回复成功')
                console.log(res)
                commit('set_userReplies', res)
            }
        },

        initReplies: async ({
            state,
            commit,
            dispatch
        }) => { //初始给所有一级评论拉取第一页二级评论
            //todo
        },

        getReplyReplies: async ({
            state,
            commit
        }, data) => { //GET-获取二级回复-

            const res = await getReplyReplyAPI({
                ...data,
                token: getToken()
            }).catch(err => {
                console.log('获取二级回复失败')
                console.log(err)
                message.error('获取二级回复失败')
            })
            if (res) {
                message.success('获取二级回复成功')
                console.log(res)
                commit('set_subReplies', res)
            }
        },
        getBizRegions: async ({
            state,
            commit
        }) => { //GET
            const res = await getBizRegionsAPI().catch(err => {
                console.log('获取商圈失败')
                console.log(err)
                message.error('获取商圈失败')
            })
            if (res) {
                message.success('获取商圈成功')
                console.log(res)
                commit('set_bizRegions', res)
            }
        },
        replyComment: async ({
            state,
            commit,
            dispatch
        }, data) => { //POST
            const res = await addAPI({
                ...data,
                token: getToken()
            }).catch(err => {
                console.log('回复失败')
                console.log(err)
                message.error('回复失败')
            })
            if (res) {
                message.success('回复成功')
                console.log(res)
                await dispatch('initReplies')
            }
        },
        changePwd: async ({
            state,
            commit,
            dispatch
        }, data) => { //POST
            return await changePwdAPI({
                ...data,
                oldPassword:state.userInfo.password,
                token: getToken()
            })
        },
        uploadFile: async ({
            state,
            commit,
            dispatch
        }, data) => { //POST
            console.log('尝试上传图片')
            console.log(data)
            const res = await uploadFileAPI(data).catch(err => {
                console.log('上传图片失败')
                console.log(err)
                message.error('上传图片失败')
            })
            if (res) {
                message.success('上传图片成功')
                console.log(res)
            }
        },
        deleteFile: async ({
            state,
            commit,
            dispatch
        }, data) => { //POST
            console.log(data)
            let names=data.url.split('/')
            const res = await deleteFileAPI({url:names[names.length-1]}).catch(err => {
                console.log('删除图片失败')
                console.log(err)
                message.error('删除图片失败')
            })
            if (res) {
                message.success('删除图片成功')
                console.log(res)
            }
        },

        getHotelComments: async ({
            state,
            commit
        },data) => { //GET
            const res = await getHotelCommentsAPI({token:getToken(),...data}).catch(err => {
                console.log('获取酒店评论失败')
                console.log(err)
                message.error('获取酒店评论失败')
            })
            if (res) {
                message.success('获取酒店评论成功')
                console.log(res)
                commit('set_hotelComments', res)
            }
        },

    }
}

export default user