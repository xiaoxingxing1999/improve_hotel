<template>
  <!-- 酒店信息修改描述 -->
  <div class="ant-descriptions ant-descriptions-bordered">
      <h2>修改前</h2>
    <div class="ant-descriptions-view">
      <table>
        <tbody>
          <tr class="ant-descriptions-row">
            <td colspan="2" rowspan="2" class="ant-descriptions-item-content imgContainer">
              <img v-if="hotelInfo.picture" :src="hotelInfo.picture" />
              <img v-else src="@/assets/cover.jpeg" alt="暂无" />
            </td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店ID</th>
            <td colspan="1" class="ant-descriptions-item-content">{{hotelInfo.id}}</td>
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店名称</th>
            <td colspan="1" class="ant-descriptions-item-content">{{hotelInfo.hotelName}}</td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">电话号码</th>
            <td colspan="3" class="ant-descriptions-item-content">{{hotelInfo.phoneNum}}</td>
          </tr>

          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店服务</th>
            <td
              colspan="5"
              class="ant-descriptions-item-content"
            >{{hotelInfo.hotelServe?hotelInfo.hotelServe.split('|').join(', '):'暂无'}}</td>
          </tr>
          <tr class="ant-descriptions-row">
            <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店描述</th>
            <td colspan="5" class="ant-descriptions-item-content">{{hotelInfo.hotelDescription}}</td>
          </tr>
          <!-- <th class="ant-descriptions-item-label ant-descriptions-item-colon">修改申请状态</th>
            <td colspan="1" class="ant-descriptions-item-content">
              <a-badge
                :status="editStateColor(hotelInfo.status)"
                :text="editStateText(hotelInfo.status)"
              />
          </td>-->
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
        params: { hotelId: this.hotelInfo.hotelId }
      });
    },
    transDateFrom(date) {
      return moment(date).format("YYYY-MM-DD, HH:mm");
    },

    editStateText(status) {
      const trans = {
        WAIT: "等待审核中",
        PASS: "通过申请",
        REFUSE: "拒绝申请",
        ERROR: "出现异常"
      };
      return trans[status];
    },
    editStateColor(status) {
      const trans = {
        WAIT: "processing",
        PASS: "success",
        REFUSE: "warning",
        ERROR: "error"
      };
      return trans[status];
    }
  },
  props: ["hotelInfo"]
};
</script>
<style lang="less" scoped>
.toHotel {
  display: flex;
  align-items: center;
  justify-content: space-between;
}
img {
  object-fit: cover;
  height: 100%;
  width: 100%;
}
.imgContainer {
  height: 200px;
  width: 150px;
  padding: 10px;
}
.hotelInfoItem{
    width: 50%;
    min-height: 100%;
    max-height: 100%;
}
</style>