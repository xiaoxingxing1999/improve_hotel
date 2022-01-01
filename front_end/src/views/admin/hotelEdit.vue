<template>
  <div class="hotelEdit-wrapper">
    <div style="width: 100%; text-align: right; margin:20px 0"></div>
    <!-- 主表格 -->
    <a-table :columns="columns" :dataSource="hotelEdits" bordered>
      <!-- 按钮设置 -->
      <span slot="action" slot-scope="text,record">
        <a-button type="primary" @click="see(record)">查看详细修改信息</a-button>
      </span>
      <a-tag
        slot="editState"
        :color="editStateColor(text)"
        slot-scope="text"
        style="cursor:default "
      >{{ editStateText(text) }}</a-tag>
    </a-table>
    <a-drawer
      :visible="visible"
      title="详细修改信息"
      placement="top"
      :closable="true"
      @close="cancel"
      width="1000"
      height="600"
    >
      <div class="drawer">
        <div class="hotelInfoContainer">
          <hotelInfo :hotelInfo="currentHotelInfo" />
          <a-divider type="vertical" style="height:100%"></a-divider>
          <hotelEditInfo :hotelEdit="value" />
        </div>
        <div v-if="value.status=='WAIT'" class="reply">
          <a-textarea
            v-model="replyContent"
            placeholder="填写审核回复内容"
            :auto-size="{ minRows: 3, maxRows: 5 }"
            style="margin-bottom:20px"
          />
          <div class="button-div">
            <a-button type="primary" @click="handleSubmit('PASS')" style="margin-right:10px">通过</a-button>
            <a-button type="danger" @click="handleSubmit('REFUSE')" style="margin-left:10px">拒绝</a-button>
          </div>
        </div>
        <div v-else class="reply" style="width:50%">
          <a-row>
            <a-col :span="4" class="label">审核状态</a-col>
            <a-col :span="8">
              <a-tag
                :color="editStateColor(value.status)"
                style="cursor:default "
              >{{ editStateText(value.status) }}</a-tag>
            </a-col>
            <a-col :span="4" class="label">审核时间</a-col>
            <a-col :span="8" class="text">{{transDateFrom(value.replyTime)}}</a-col>
          </a-row>
          <a-row style="margin-top:20px">
            <a-col :span="4" class="label">审核回复</a-col>
            <a-col :span="20">{{value.replyContent}}</a-col>
          </a-row>
        </div>
      </div>
    </a-drawer>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import hotelEditInfo from "../../component/hotelEditInfo";
import hotelInfo from "../../component/hotelInfo";
import moment from "moment";
const columns = [
  {
    title: "酒店ID",
    dataIndex: "hotelId"
  },
  {
    title: "酒店名称",
    dataIndex: "hotelName"
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
  name: "hotelEdit",
  data() {
    return {
      value: {},
      replyContent: "",

      columns,
      visible: false,
      form: this.$form.createForm(this),
      formItemLayout: {
        labelCol: {
          xs: { span: 12 },
          sm: { span: 6 }
        },
        wrapperCol: {
          xs: { span: 24 },
          sm: { span: 16 }
        }
      }
    };
  },
  components: { hotelEditInfo, hotelInfo },
  computed: {
    ...mapGetters(["managerList", "hotelEdits", "currentHotelInfo"])
  },
  async mounted() {
    await this.getAllHotelEdit();
  },
  methods: {
    ...mapActions(["checkHotelEdit", "getAllHotelEdit", "getHotelById"]),
    ...mapMutations([]),
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
        WAIT: "blue",
        PASS: "green",
        REFUSE: "red",
        ERROR: "red"
      };
      return trans[status];
    },
    editStateType(status) {
      const trans = {
        WAIT: "processing",
        PASS: "success",
        REFUSE: "warning",
        ERROR: "error"
      };
      return trans[status];
    },

    async see(val) {
      this.visible = true;
      this.value = val;
      await this.getHotelById({ hotelId: val.hotelId });
    },
    async handleSubmit(status) {
      await this.checkHotelEdit({
        id: this.value.id,
        status,
        replyContent: this.replyContent
      });

      this.visible = false;
      await this.getAllHotelEdit();
      this.replyContent = "";
    },

    getManagerById(id) {
      let managers = this.managerList;
      let res;
      managers.forEach(e => {
        if (e.id === id) {
          res = e;
        }
      });
      console.log("getManagerById");
      console.log(res);
      if (res) {
        return res;
      } else {
        return null;
      }
    },
    cancel() {
      this.visible = false;
    }
  }
};
</script>
<style scoped lang="less">

.hotelEdit-wrapper {
  padding: 50px;
  .chart {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 20px;
  }
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
.hotelEdit-wrapper {
  .ant-tabs-bar {
    padding-left: 30px;
  }
  .ant-drawer-wrapper-body {
    height: 100%;
  }
}
</style>