<template>
  <!-- todo:换成卡片！！！ -->
  <div class="info-wrapper">
    <a-table :columns="columns" :dataSource="userOrderList" bordered>
      <span slot="price" slot-scope="text">
        <span>{{ text }}</span>
      </span>
      <span slot="checkInDate" slot-scope="text">
        <span>{{ transDateFrom(text) }}</span>
      </span>
      <span slot="checkOutDate" slot-scope="text">
        <span>{{ transDateFrom(text) }}</span>
      </span>
      <a-tag slot="orderState" :color="orderStatusColor(text)" slot-scope="text" style="cursor:default ">{{ text }}</a-tag>
      <span slot="action" slot-scope="record">
        <a-button type="primary" size="small" @click="showOrderInfo(record)">查看</a-button>
        <a-divider type="vertical"></a-divider>
        <a-popconfirm
          title="你确定撤销该笔订单吗？"
          @confirm="confirmCancelOrder(record.id)"
          @cancel="cancelCancelOrder"
          okText="确定"
          cancelText="取消"
        >
          <a-button type="danger" size="small">撤销</a-button>
        </a-popconfirm>
      </span>
    </a-table>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import moment from 'moment';
const columns = [
  {
    title: "订单号",
    dataIndex: "id"
  },
  {
    title: "酒店名",
    dataIndex: "hotelName"
  },
  {
    title: "房型",
    dataIndex: "roomType",
  },
  {
    title: "入住时间",
    dataIndex: "checkInDate",
    scopedSlots: { customRender: "checkInDate" }
  },
  {
    title: "离店时间",
    dataIndex: "checkOutDate",
    scopedSlots: { customRender: "checkOutDate" }
  },
  {
    title: "入住人数",
    dataIndex: "peopleNum"
  },
  {
    title: "总价",
    dataIndex: "price"
  },
  {
    title: "状态",
    dataIndex: "orderState",
    scopedSlots: { customRender: "orderState" }
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];
export default {
  name: "orders",
  data() {
    return {
      pagination: {},
      columns,
      data: [],
      form: this.$form.createForm(this, { name: "coordinated" }),
      drawerVisible: false,
    };
  },
  components: {
  },
  computed: {
    ...mapGetters(["userId", "userInfo", "userOrderList"])
  },
  async mounted() {
    await this.getUserInfo();
    await this.getUserOrders();
    
  },
  methods: {
    ...mapActions([
      "getUserInfo",
      "getUserOrders",
      "cancelOrder",
      "getOrderComment"
    ]),
    ...mapMutations(["set_orderShown"]),
    confirmCancelOrder(orderId) {
      this.cancelOrder(orderId);
    },
    cancelCancelOrder() {},
    showOrderInfo(record) {
      this.set_orderShown(record)
      console.log("订单展开", record);
      this.$router.push({ name: "orderDetail", params: { orderId: record.id } });
    },
    closeDrawer() {
      this.drawerVisible = false;
    },
    transDateFrom(date){
      return moment(date).format("YYYY-MM-DD, HH:mm")
    },
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
    }
  }
};
</script>
<style scoped lang="less">
.info-wrapper {
  padding: 50px;
  .ant-tabs-bar {
    padding-left: 30px;
  }
  .chart {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 20px;
  }
}
.pstyle {
  font-size: 16px;
  color: rgba(0, 0, 0, 0.85);
  line-height: 24px;
  display: block;
  margin-bottom: 16px;
}
</style>
