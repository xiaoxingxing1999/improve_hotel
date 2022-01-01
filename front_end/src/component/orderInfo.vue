<template>
  <!-- 订单描述 -->
  <div class="ant-descriptions ant-descriptions-bordered" v-if="orderShown">
    <div class="ant-descriptions-view">
      <table>
        <tbody>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店名称</th>
            <td colspan="1" class="ant-descriptions-item-content toHotel">
              <span>{{orderShown.hotelName}}</span>
              <a-tooltip>
                <template slot="title">前往该酒店</template>
                <a-icon
                  type="import"
                  style="float:right;font-size:20px;cursor:pointer"
                  @click="toHotel"
                />
              </a-tooltip>
            </td>

            <th class="ant-descriptions-item-label ant-descriptions-item-colon">预定时间</th>
            <td
              colspan="3"
              class="ant-descriptions-item-content"
            >{{transDateFrom(orderShown.createDate)}}</td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">入住时间</th>
            <td
              colspan="1"
              class="ant-descriptions-item-content"
            >{{transDateFrom(orderShown.checkInDate)}}</td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">退房时间</th>
            <td
              colspan="3"
              class="ant-descriptions-item-content"
            >{{transDateFrom(orderShown.checkOutDate)}}</td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">订单状态</th>
            <td colspan="5" class="ant-descriptions-item-content">
              <a-badge
                :status="orderStatusColor(orderShown.orderState)"
                :text="orderShown.orderState"
              />
            </td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">订单总价</th>
            <td colspan="1" class="ant-descriptions-item-content">￥{{orderShown.price}}</td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间数量</th>
            <td colspan="1" class="ant-descriptions-item-content">{{orderShown.roomNum}} 间</td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间类型</th>
            <td colspan="1" class="ant-descriptions-item-content">{{orderShown.roomType}}</td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">订单ID</th>
            <td colspan="1" class="ant-descriptions-item-content">{{orderShown.id}}</td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">入住人数</th>
            <td colspan="1" class="ant-descriptions-item-content">{{orderShown.peopleNum}} 人</td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">有无儿童</th>
            <td colspan="1" class="ant-descriptions-item-content">{{orderShown.haveChild?'有':'无'}}</td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">订单历史</th>
            <td colspan="5" class="ant-descriptions-item-content">
              <a-timeline>
                <a-timeline-item
                  v-if="orderShown.createDate"
                >创建订单 {{transDateFrom(orderShown.createDate)}}</a-timeline-item>
                <a-timeline-item
                  v-if="orderShown.realPassDate"
                  color="green"
                >订单被接受 {{transDateFrom(orderShown.realPassDate)}}</a-timeline-item>
                <a-timeline-item
                  v-if="orderShown.realCheckInDate"
                  color="green"
                >实际入住 {{transDateFrom(orderShown.realCheckInDate)}}</a-timeline-item>
                <a-timeline-item
                  v-if="orderShown.realCheckOutDate"
                  color="grey"
                >实际退房 {{transDateFrom(orderShown.realCheckOutDate)}}</a-timeline-item>
                <a-timeline-item
                  v-if="orderShown.realCancelDate"
                  color="red"
                >撤销订单 {{transDateFrom(orderShown.realCancelDate)}}</a-timeline-item>
                <a-timeline-item
                  v-if="orderShown.realErrorDate"
                  color="red"
                >订单异常 {{transDateFrom(orderShown.realErrorDate)}}</a-timeline-item>
                <a-timeline-item
                  v-if="orderShown.realRefuseDate"
                  color="red"
                >订单被拒 {{transDateFrom(orderShown.realRefuseDate)}}</a-timeline-item>
              </a-timeline>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import moment from "moment";
export default {
  components: {},
  data() {
    return {};
  },
  computed: {},
  methods: {
    toHotel() {
      this.$router.push({
        name: "hotelDetail",
        params: { hotelId: this.orderShown.hotelId }
      });
    },
    transDateFrom(date) {
      return moment(date).format("YYYY-MM-DD, HH:mm");
    },
    orderStatusColor(status) {
      const trans = {
        WAIT: "processing",
        PASS: "success",
        REFUSE: "error",
        CANCEL: "warning",
        CHECKIN: "success",
        CHECKOUT: "success",
        ERROR: "error"
      };
      return trans[status];
    }
  },
  props: ["orderShown"]
};
</script>
<style lang="less" scoped>
.toHotel {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
</style>