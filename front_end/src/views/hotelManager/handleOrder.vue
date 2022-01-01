<template>
  <div class="handleOrder">
    <!--    订单处理（可以根据状态分类）-->
    <a-table :columns="columns" :dataSource="orderList" bordered>
      <span slot="price" slot-scope="text">
        <span>￥{{ text }}</span>
      </span>
        <a-tag slot="orderState" :color="orderStatusColor(text)" slot-scope="text" style="cursor:default ">{{ text }}</a-tag>
      <span slot="action" slot-scope="record">
        <a-button type="primary" size="small" @click="see(record)" >订单详情</a-button>
        <a-divider type="vertical"></a-divider>
        <a-button type="primary" size="small" @click="see5(record)" >订单状态</a-button>
      </span>
    </a-table>
    <a-modal
            :visible="visible"
            title="订单详情"
            cancelText="返回"
            @cancel="cancel"
            footer=''
    >
      <div>客户昵称：{{value.clientName}}</div>
      <div>房型：{{value.roomType}}</div>
      <div>创建时间：{{value.createDate}}</div>
      <div>入住时间：{{value.checkInDate}}</div>
      <div>是否有孩子：{{value.haveChild}}</div>
      <div>客户人数：{{value.peopleNum}}</div>
      <div>总价：{{value.price}}</div>
      <div>客户手机号：{{value.phoneNumber}}</div>
      <div>订单状态：{{value.orderState}}</div>
    </a-modal>

    <!--    订单操作-->
    <a-modal
            :visible="visible1"
            title="订单状态操作"
            cancelText="返回"
            @cancel="cancel1"
            footer=''
    >
<!--        <a-button type="primary" size="small" @click="se(value)" >重置</a-button>-->
      <a-button type="primary" shape="round" size="small" @click="see1(value)" >通过预定</a-button>
      <a-divider type="vertical"></a-divider>
      <a-button type="primary" shape="round" size="small" @click="see2(value)" >已经入住</a-button>
      <a-divider type="vertical"></a-divider>
      <a-button type="primary" shape="round" size="small" @click="see3(value)" >已经退房</a-button>
      <a-divider type="vertical"></a-divider>
      <a-button type="text" shape="round" size="small" @click="see4(value)">状态异常</a-button>
      <a-divider type="vertical"></a-divider>
<!--      <a-button type="primary" size="small" @click="see6(value)" >拒绝订单</a-button>-->
      <a-popconfirm title="确定想拒绝该订单吗？" @confirm="deleteOrder(value)" okText="确定" cancelText="取消">
        <a-button type="danger" size="small">拒绝订单</a-button>
      </a-popconfirm>
    </a-modal>
  </div>
</template>
<script>
  import { mapGetters, mapMutations, mapActions } from "vuex";
    import moment from 'moment'
  const columns = [
    {
      title: '订单号',
      dataIndex: 'id',
    },
    {
      title: '房型',
      dataIndex: 'roomType',

    },
    {
      title:'客户ID',
      dataIndex:'userId'
    },
    {
      title: '入住时间',
      dataIndex: 'checkInDate',
      scopedSlots: { customRender: 'checkInDate' },
        customRender: (text, row, index) => {
            return moment(text).format("YYYY-MM-D  HH:mm")
        },
    },
    {
      title: '离店时间',
      dataIndex: 'checkOutDate',
      scopedSlots: { customRender: 'checkOutDate' },
        customRender: (text, row, index) => {
            return moment(text).format("YYYY-MM-D  HH:mm")
        },
    },
    {
      title: '入住人数',
      dataIndex: 'peopleNum',
    },
    {
      title: '总价',
      dataIndex: 'price',
    },
    {
      title: '订单状态',
      dataIndex: 'orderState',
        filters: [
            { text: "待接受", value: "WAIT" },
            { text: "待入住", value: "PASS" },
            { text: "已拒绝", value: "REFUSE" },
            { text: "已撤销", value: "CANCEL" },
            { text: "已入住", value: "CHECKIN" },
            { text: "已退房", value: "CHECKOUT" },
            { text: "异常", value: "ERROR" }
        ],
        onFilter: (value, record) => record.orderState.includes(value),
        scopedSlots: { customRender: "orderState" },
    },
    {
      title: '操作',
      key: 'action',
      scopedSlots: { customRender: 'action' },
    },
  ];
  export default {
    data() {
      return {
        columns,
        cur_OrderId:0,
        value:[],
      };
    },
    components: {},
    computed: {
      ...mapGetters([
        'orderList',
      ]),

    },
    props:{
      visible:{
        type:Boolean,
        default:false,
      },
      visible1:{
        type:Boolean,
        default:false,
      }
    },
    async mounted() {
      await this.getHotelOrders();
    },
    methods: {
      ...mapActions(["getHotelOrders","handleOrder"]),
        orderStatusColor(status){
            const trans={
                WAIT:"blue",
                PASS:"green",
                REFUSE:"red",
                CANCEL:"grey",
                CHECKIN:"green",
                CHECKOUT:"orange",
                ERROR:"red"
            }
            return trans[status]
        },
      cancel(){
        this.visible=false
      },
      cancel1(){
        this.visible1=false
      },
      see(value){
        console.log(value)
        this.visible=true
        this.value=value
      },
      see5(value){
        this.visible1=true
        this.value=value
      },
        // se(value){
        //     this.handleOrder({
        //         id:value.id,
        //         orderState:"WAIT"
        //     })
        // },
      see1(value){
        console.log(value)
        console.log(value.id)
        console.log(value.orderState)
        this.handleOrder({
          id:value.id,
          orderState:"PASS"
        })
      },
      see2(value){
        this.handleOrder({
          id:value.id,
          orderState:"CHECKIN"
        })
      },
      see3(value){
        this.handleOrder({
          id:value.id,
          orderState:"CHECKOUT"
        })
      },
      see4(value){
        this.handleOrder({
          id:value.id,
          orderState:"ERROR"
        })
      },
      deleteOrder(value) {
          console.log(value.id)
        this.handleOrder({
          id: value.id,
          orderState: "REFUSE"
        })
      },
    }
  };
</script>
<style scoped lang="less">
.handleOrder{
  padding: 30px;
}
</style>

