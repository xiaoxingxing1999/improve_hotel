const getters = {
  //user
  userId: state => state.user.userInfo.userId,
  userInfo: state => state.user.userInfo,
  userOrderList: state => state.user.userOrderList,
  userReplies: state => state.user.userReplies,
  orderComment: state => state.user.orderComment,
  commentReplies: state => state.user.commentReplies,
  subReplies:state => state.user.subReplies,
  bizRegions: state => state.user.bizRegions,
  orderShown:(state) => (id) => {
    return state.user.userOrderList.find(order => order.id === id)
  },
  currentEditOrder:state => state.user.currentEditOrder,
  hotelComments:state => state.user.hotelComments,
  //hotel
  currentHotelInfo: state => state.hotel.currentHotelInfo,
  currentHotelId: state => state.hotel.currentHotelId,
  hotelListLoading: state => state.hotel.hotelListLoading,
  currentOrderRoom: state => state.hotel.currentOrderRoom,

  hotelList: state => state.hotel.hotelList,
  //client
  orderModalVisible: state => state.client.orderModalVisible,
  orderMatchCouponList: state => state.client.orderMatchCouponList,
  userBills:state => state.client.userBills,
  //admin
  managerList: state => state.admin.managerList,
  hotelEdits:state => state.admin.hotelEdits,
  addManagerModalVisible: state => state.admin.addManagerModalVisible,
  addManagerParams: state => state.admin.addManagerParams,
  //siteMarket
  bills:state=> state.siteMarket.bills,
  webSituations:state=> state.siteMarket.webSituation,
  webSituationCur:state => state.siteMarket.webSituation[state.siteMarket.webSituation.length-1],
  siteAllCoupons:state=>state.siteMarket.siteAllCoupons,
  //hotelManager
  orderList: state => state.hotelManager.orderList,
  addHotelModalVisible: state => state.hotelManager.addHotelModalVisible,
  addRoomModalVisible: state => state.hotelManager.addRoomModalVisible,
  hotelInfo:state => state.hotelManager.hotelInfo,
  rooms:state => state.hotelManager.hotelInfo.room,
  couponList: state => state.hotelManager.couponList,
  roomTypes:state => state.hotelManager.roomTypes,
  allServes:state => state.hotelManager.allServes,
  hotelSituationCur:state => state.hotelManager.hotelSituation[state.hotelManager.hotelSituation.length-1],
  hotelSituations:state => state.hotelManager.hotelSituation,
  hotelEdit:state => state.hotelManager.hotelEdit,
  //coupon
  couponVisible: state => state.hotelManager.couponVisible,
  addCouponVisible: state => state.hotelManager.addCouponVisible,
}

export default getters