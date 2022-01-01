<!--更新房间数量，设置房间类型 -->
<template>
  <!-- 更新房间数量(登入，登出)，设置房间类型，展开查看剩余房间（日历显示？）-->
  <div class="wrapper">
    <div class="content">
      <a-button type="primary" @click="addRoom" class="addRoom">
        <a-icon type="plus" />添加房间
      </a-button>
    </div>
    <!-- 主表格 -->
    <a-table :columns="columns" :dataSource="rooms" bordered>
      <!-- 按钮设置 -->
      <span slot="action" slot-scope="text, record">
         <a-button type="primary" @click="showRoom(record)" style="margin-right:10px">查看详情</a-button>
        <a-popconfirm
          title="您确定撤销该房间吗？"
          @confirm="deleteRoom(record)"
          @cancel="cancelCancelDelete"
          okText="确定"
          cancelText="取消"
        >
          <a-button type="danger"  >删除房间</a-button>
        </a-popconfirm>
       
      </span>

      <span slot="price" slot-scope="text">
        <span>￥{{ text }}</span>
      </span>
    </a-table>
    <!-- 展开部分 -->
    <a-drawer
      title="订单详情"
      placement="right"
      :closable="true"
      :visible="drawerVisible"
      @close="onClose"
      width="800"
    >
      <div class="ant-descriptions ant-descriptions-bordered">
        <div class="ant-descriptions-view">
          <table>
            <tbody>
              <tr class="ant-descriptions-row" >
                <td colspan="2" rowspan="2" class="ant-descriptions-item-content imgContainer">
                  <img v-if="roomShown.url" :src="roomShown.url" />
                  <img v-else src="@/assets/cover.jpeg" alt="暂无"/>
                </td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间ID</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.id}}</td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店ID</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.hotelId}}</td>
              </tr>
              <tr class="ant-descriptions-row">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间类型</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.roomType}}</td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间价格</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.price}}</td>
                
              </tr>
              <tr class="ant-descriptions-row" style="height:100px">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间数量</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.total}}</td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">房间服务</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.serve?roomShown.serve.split('|').join(', '):'暂无'}}</td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">容纳人数</th>
                <td colspan="1" class="ant-descriptions-item-content">{{roomShown.validPeople?roomShown.validPeople:'暂无'}}</td>
              </tr>
              
            </tbody>
          </table>
        </div>
      </div>
      <a-divider />
      <!-- 渲染时可能还没有数据，加一个判断避免报错 -->
      <roomInfo :curNum="roomShown&&roomShown.curNum" :totalNum="roomShown&&roomShown.total"/>
    </a-drawer>

    <AddRoomModal />
  </div>
</template>
<script>
const columns = [
  {
    title: "房间ID",
    dataIndex: "id"
  },
  {
    title: "房间价格",
    dataIndex: "price",
    scopedSlots: { customRender: "price" }
  },
  {
    title: "房间类型",
    dataIndex: "roomType"
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];
import { mapGetters, mapMutations, mapActions } from "vuex";
import AddRoomModal from "./components/addRoomModal";
import roomInfo from "../../component/roomInfo";

export default {
  data() {
    return {
      columns,
      roomShown: {},
      drawerVisible: false
    };
  },
  components: {
    AddRoomModal,
    roomInfo
  },
  computed: {
    ...mapGetters(["rooms"])
  },
  mounted() {
    this.getAllRoomType();
  },
  methods: {
    ...mapMutations(["set_addRoomModalVisible"]),
    ...mapActions(["getAllRoomType", "delRoom"]),
    addRoom() {
      this.set_addRoomModalVisible(true);
    },
    
    deleteRoom(record) {
      this.delRoom({ roomId: record.id });
      console.log("删除房间");
      console.log(record);
    },
    // handleClickRow(record, index) {
    //   return {
    //     on: {
    //       click: () => {
    //         this.drawerVisible = true;
    //         this.roomShown = index;
    //         console.log("行点击", record, index);
    //       }
    //     }
    //   };
    // },
    showRoom(record) {
      this.roomShown = record;
      this.drawerVisible = true;
      console.log("行点击", record);
    },
    onClose() {
      this.drawerVisible = false;
    },
    cancelCancelDelete(){}
  }
};
</script>
<style scoped lang="less">
img {
    object-fit: cover;
    height: 100%;
    width: 100%;
  }
  .imgContainer{
    height: 200px;
    width: 150px;
    padding: 10px;
  }
.wrapper {
  padding: 50px;
  .content {
    width: 100%;
    text-align: right;
    margin: 20px 0;
  }
  .chart {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 20px;
  }
}
</style>
<style lang="less">
.wrapper {
  .ant-tabs-bar {
    padding-left: 30px;
  }
}
</style>
