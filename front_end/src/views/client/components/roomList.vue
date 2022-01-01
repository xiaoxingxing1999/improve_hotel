<template>
  <div class="room-list">
    <!-- 
        <div class="list">
            <a-table
                :columns="columns"
                :dataSource="rooms"
            >
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="text, record">
                    <a-button type="primary" @click="hotelorder(record)">预定</a-button>
                </span>
            </a-table>
    </div>-->
    <div class="room-card" v-for="room in rooms" :key="room.id">
      <div class="img">
        <img v-if="room.url" :src="room.url" referrerpolicy="no-referrer" />
        <img v-else src="@/assets/cover.jpeg" referrerpolicy="no-referrer" />
      </div>
      <div class="info">
        <a-row type="flex" justify="start" align="middle">
          <a-col :span="10" :offset="2">
            <span class="label">类型</span>
            <span class="text">{{room.roomType}}</span>
          </a-col>
          <a-col :span="12" style="text-align:center">
            <span class="label">价格</span>
            <span class="text">{{room.price}} ￥</span>
          </a-col>
        </a-row>
        <a-row type="flex" justify="start" align="middle">
          <a-col :span="4" :offset="2" style="text-align:center">
            <span class="label">服务</span>
          </a-col>
          <a-col :span="18" class="serves">
            <div v-for="serve in room.serve.split('|')" :key="serve">
              <a-tooltip
                v-if="commenServesToLabel(serve)"
                placement="topLeft"
                style="margin-right:10px"
              >
                <template slot="title">{{serve}}</template>
                <a-icon :type="commenServesToLabel(serve)" />
              </a-tooltip>

              <span v-else style="margin-right:10px">{{serve}}</span>
            </div>
          </a-col>
        </a-row>
        <a-row type="flex" justify="start" align="middle">
          <a-col :span="10" :offset="2">
            <a-button type="primary" icon="book" @click="hotelorder(room)" class="reserve">预定房间</a-button>
          </a-col>
          <a-col :span="12" style="text-align:center">
            <a-button type="primary" icon="calendar" @click="showAvailableRoom(room)">查看空房</a-button>
          </a-col>
        </a-row>
      </div>
    </div>
    <OrderModal></OrderModal>
    <a-drawer
      placement="right"
      :visible="availableRoomVisible"
      @close="onClose"
      :width="900"
    >
      <roomInfo  :curNum="curRoom.curNum" :totalNum="curRoom.total" />
    </a-drawer>
  </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import OrderModal from "./orderModal";
import roomInfo from "../../../component/roomInfo";
export default {
  name: "roomList",
  props: {
    rooms: {
      type: Array
    }
  },
  data() {
    return {
      availableRoomVisible:false,
      curRoom:{},
    };
  },
  components: {
    OrderModal,
    roomInfo
  },
  computed: {
    ...mapGetters(["orderModalVisible"])
  },
  monuted() {},
  methods: {
    ...mapMutations(["set_orderModalVisible", "set_currentOrderRoom"]),
    ...mapActions([]),
    hotelorder(record) {
      this.set_currentOrderRoom(record);
      this.set_orderModalVisible(true);
    },
    commenServesToLabel(serve) {
      const rela = {
        WIFI: "wifi",
        热水: "coffee"
      };
      let res = "";
      if (rela[serve]) {
        res = rela[serve];
      }
      return res;
    },
    onClose() {
      this.availableRoomVisible = false;
    },
    showAvailableRoom(room){
        this.curRoom=room;
        this.availableRoomVisible = true;
    }
  }
};
</script>
<style lang="less" scoped>
.room-list {
  width: 100%;
  display: flex;
  flex-wrap: wrap;
  .room-card {
    width: 44%;
    padding: 10px 2%;
    margin: 20px 3%;
    box-shadow: 0px 0px 20px 2px rgba(145, 145, 144, 0.5);
    border-radius: 15px;
    display: flex;
    justify-content: space-between;
    .img {
      width: 40%;
      height: 100px;
      img {
        object-fit: cover;
        height: 100%;
        width: 100%;
        border-radius: 10px;
      }
    }
    .info {
      width: 57%;
      display: flex;
      flex-direction: column;
      justify-content: space-between;
      .label {
        font-size: 16px;
        font-weight: 600;
        margin-right: 15px;
      }
      .text {
        font-size: 16px;
      }
      .serves {
        display: flex;
        justify-content: flex-start;
      }
    }
  }
}
</style>