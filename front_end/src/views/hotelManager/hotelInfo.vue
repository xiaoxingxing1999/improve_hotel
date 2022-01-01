<!-- 待修改, 维护酒店信息,显示信息修改申请状态-->
<template>
  <div class="manageHotel-wrapper">
    <a-tabs>
      <a-tab-pane tab="酒店信息" key="1">
        <div class="hotelInfo">
          <div class="hotelInfo-img">
            <img
              v-if="hotelInfo.url"
              alt="example"
              :src="hotelInfo.url"
              referrerpolicy="no-referrer"
            />
            <img v-else alt="example" src="@/assets/cover.jpeg" referrerpolicy="no-referrer" />
          </div>
          <div class="hotelInfo-text">
            <a-row type="flex" justify="start" align="middle">
              <a-col
                :span="16"
                style="font-size:30px;font-weight:600;letter-spacing:5px"
              >{{hotelInfo.hotelName}}</a-col>
            </a-row>
            <a-row type="flex" justify="start" align="middle">
              <a-col :span="24" style="font-size:16px;">{{hotelInfo.hotelDescription}}</a-col>
            </a-row>
            <a-row type="flex" justify="start" align="middle">
              <a-col :span="12">
                <span class="label">星级</span>
                <span class="text">
                  <a-rate :value="getHotelStar" disabled />
                </span>
              </a-col>
              <a-col :span="2">
                <span class="label" style="display:block">评分</span>
              </a-col>
              <a-col :span="10">
                <div style="display:flex;color:black">
                  <div style="font-size:26px;font-weight:600;">{{hotelInfo.rate}}</div>
                  <div style="font-size:24px;font-weight:600;margin:7px 0 0 5px">/</div>
                  <div style="font-size:22px;font-weight:400;margin:10px 0 0 5px">5</div>
                </div>
              </a-col>
            </a-row>
            <a-row type="flex" justify="start" align="middle">
              <a-col :span="12">
                <span class="label">商圈</span>
                <span class="text">{{hotelInfo.bizRegion}}</span>
              </a-col>
              <a-col :span="2">
                <span class="label">地址</span>
              </a-col>
              <a-col :span="8">
                <span class="text">{{hotelInfo.address}}</span>
              </a-col>
            </a-row>
            <a-row type="flex" justify="start" align="middle">
              <a-col :span="12">
                <span class="label">服务</span>
                <span
                  class="text"
                >{{hotelInfo.hotelServe?hotelInfo.hotelServe.split('|').join(' '):'暂无'}}</span>
              </a-col>
              <a-col :span="3">
                <span class="label">联系经理</span>
              </a-col>
              <a-col :span="9">
                <span class="text">{{hotelInfo.phoneNum}}</span>
              </a-col>
            </a-row>
          </div>
        </div>
      </a-tab-pane>
      <a-tab-pane tab="修改申请" key="2">
        <a-button type="primary" @click="onModifyInfo" style="margin:20px">申请修改酒店信息</a-button>
        <a-table :columns="columns" :dataSource="hotelEdit" bordered>
          <!-- 按钮设置 -->
          <span slot="action" slot-scope="text,record">
            <a-button type="primary" @click="checkEditInfo(record)">查看详细修改信息</a-button>
          </span>
          <a-tag
            slot="editState"
            :color="editStateColor(text)"
            slot-scope="text"
            style="cursor:default "
          >{{ editStateText(text) }}</a-tag>
          <span slot="createTime" slot-scope="text">
            <span>{{ transDateFrom(text) }}</span>
          </span>
          <span slot="replyTime" slot-scope="text">
            <span>{{ transDateFrom(text) }}</span>
          </span>
        </a-table>
        <addHotelEdit :visible="addHotelEditVisible" @close="closeModal" />
        <a-drawer
          :visible="editInfoVisible"
          title="详细修改信息"
          placement="top"
          :closable="true"
          @close="closeDrawer"
          width="1000"
          height="600"
        >
          <div class="drawer">
            <div class="hotelInfoContainer">
              <hotelInfo :hotelInfo="hotelInfo" />
              <a-divider type="vertical" style="height:100%"></a-divider>
              <hotelEditInfo :hotelEdit="editInfo" />
            </div>
            <div v-if="editInfo.status!='WAIT'" class="reply" style="width:50%">
              <a-row>
                <a-col :span="4" class="label">审核状态</a-col>
                <a-col :span="8">
                  <a-tag
                    :color="editStateColor(editInfo.status)"
                    style="cursor:default "
                  >{{ editStateText(editInfo.status) }}</a-tag>
                </a-col>
                <a-col :span="4" class="label">审核时间</a-col>
                <a-col :span="8" class="text">{{transDateFrom(editInfo.replyTime)}}</a-col>
              </a-row>
              <a-row style="margin-top:20px">
                <a-col :span="4" class="label">审核回复</a-col>
                <a-col :span="20">{{editInfo.replyContent}}</a-col>
              </a-row>
            </div>
          </div>
        </a-drawer>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import { message } from "ant-design-vue";
import moment from "moment";
import hotelInfoPanel from "../../component/hotelInfo";
import addHotelEdit from "./components/addHotelEdit";
import hotelEditInfo from "../../component/hotelEditInfo";
import hotelInfo from "../../component/hotelInfo";
const columns = [
  {
    title: "创建时间",
    dataIndex: "createTime",
    scopedSlots: { customRender: "createTime" }
  },
  {
    title: "审核时间",
    dataIndex: "replyTime",
    scopedSlots: { customRender: "replyTime" }
  },
  {
    title: "状态",
    dataIndex: "status",
    scopedSlots: { customRender: "editState" }
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];
export default {
  name: "maintainHotelInfo",
  data() {
    return {
      columns,
      editInfo: {},
      formItemLayout: {
        labelCol: {
          xs: { span: 12 },
          sm: { span: 6 }
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 }
        }
      },
      addHotelEditVisible: false,
      editInfoVisible: false
    };
  },
  components: {
    addHotelEdit,
    hotelEditInfo,
    hotelInfo
  },
  computed: {
    ...mapGetters(["hotelInfo", "userInfo", "hotelEdit", "allServes"]),

    getHotelStar: function() {
      let starStr = this.hotelInfo.hotelStar;
      if (starStr == "Five") {
        return 5;
      } else if (starStr == "Four") {
        return 4;
      } else if (starStr == "Three") {
        return 3;
      }
      return 0;
    },
    canEdit() {
      if (this.hotelEdit.length == 0) {
        return true;
      }
      return this.hotelEdit[this.hotelEdit.length - 1].status != "WAIT";
    }
  },
  async mounted() {
    await this.getHotelInfo();
    await this.checkEdit();
    this.getAllServe();
  },
  methods: {
    ...mapMutations(["set_hotelEdit"]),
    ...mapActions(["getHotelInfo", "checkEdit", "getAllServe"]),
    checkEditInfo(record) {
      this.editInfoVisible = true;
      this.editInfo = record;
    },
    onModifyInfo() {
      if (!this.canEdit) {
        message.error("上次修改申请还未受理！");
        return;
      }
      this.addHotelEditVisible = true;
    },
    closeModal() {
      this.addHotelEditVisible = false;
    },
    closeDrawer() {
      this.editInfoVisible = false;
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
        WAIT: "blue",
        PASS: "green",
        REFUSE: "red",
        ERROR: "red"
      };
      return trans[status];
    },
    transDateFrom(date) {
      if (!date) {
        return "待审核";
      }
      return moment(date).format("YYYY-MM-DD, HH:mm");
    }
  }
};
</script>
<style lang="less" scoped>
.hotelInfo {
  display: flex;
  justify-content: space-between;
  margin-top: 40px;
  .hotelInfo-img {
    width: 30%;
    height: 300px;

    img {
      object-fit: cover;
      height: 100%;
      width: 100%;
      border-radius: 10px;
    }
  }
  .hotelInfo-text {
    width: 65%;
    .label {
      font-size: 16px;
      font-weight: 600;
      margin-right: 15px;
    }
    .text {
      font-size: 16px;
    }
  }
}

.info-wrapper {
  display: flex;
  justify-content: space-between;
  width: 80%;
  margin-left: 20px;
  .info-from {
    flex-grow: 1;
  }
}
.ant-row-flex {
  margin-top: 20px;
}
.label {
  font-size: 16px;
  font-weight: 600;
}
.text {
  font-size: 16px;
}
.hotelInfoContainer {
  width: 100%;
  height: 350px;
  display: flex;
  justify-content: space-evenly;
}
.drawer {
  height: 500px;
  width: 100%;
  overflow: auto;
}
.reply {
  width: 40%;
  margin: 30px auto 0px auto;
  height: 100px;
  .button-div {
    width: 100%;
    display: flex;
    justify-content: center;
  }
}
</style>
<style lang="less">
.uploadImg-container {
  .avatar-img {
    width: 128px;
    height: 128px;

    margin-top: 30px;
  }
}
.manageHotel-wrapper {
  .ant-row {
    margin-bottom: 30px;
  }
  .ant-tabs-bar {
    padding-left: 30px;
  }
}
.manageHotel-wrapper {
  padding: 50px;
  .chart {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 20px;
  }
}
</style>