<!-- 会员分级策略展示、充值记录 -->
<template>
  <div class="info-wrapper">
    <a-tabs tab-position="left" size="large">
      <a-tab-pane tab="我的信息" key="1">
        <infoPanel />
      </a-tab-pane>
      <!--  -->
      <a-tab-pane tab="信用记录" key="2">
        <a-timeline style="margin-top:50px">
          <a-timeline-item
            v-for="bill in userBills"
            :key="bill.createTime"
            style="width:60%;margin:0px auto"
          >{{transDateFrom(bill.createTime)}} {{bill.content}}</a-timeline-item>
        </a-timeline>
      </a-tab-pane>
      <!--  -->
      <a-tab-pane tab="会员中心" key="3" class="vip">
        <a-row type="flex" justify="space-between" align="middle">
          <a-col
            :span="5"
            v-if="!vipModify||userInfo.vipType=='NOT_VIP'||userInfo.vipType=='ENTERPRISE'"
          >
            <a-statistic title="会员等级" :value="vipType" style="margin-right: 50px">
              <template #prefix>
                <div :class="['vip-level-icon',iconText]">{{iconText}}</div>
              </template>
            </a-statistic>
          </a-col>
          <a-col :span="5" v-else>
            <a-radio-group :default-value="false" v-model="vipModifyType">
              <a-radio :value="false">不变</a-radio>
              <a-radio :value="true">改变为企业会员</a-radio>
            </a-radio-group>
          </a-col>

          <a-col :span="5">
            <a-statistic
              title="会员信息"
              :value="userInfo.vipType=='NOT_VIP'||!userInfo.vipInfo? '无':userInfo.vipInfo"
              class="demo-class"
              v-if="!vipModify"
            ></a-statistic>
            <a-input
              v-else-if="userInfo.vipType=='ENTERPRISE'||vipModifyType==true"
              placeholder="修改企业名称"
              v-model="vipModifyVal"
            />
            <a-date-picker v-else placeholder="修改会员生日" @change="onVIPBirthChange" />
          </a-col>

          <a-col :span="5">
            <a-statistic title="当前信用值" :value="userInfo.credit" class="demo-class" :precision="2"></a-statistic>
          </a-col>

          <a-col :span="5">
            <a-statistic
              title="总信用值"
              :value="userInfo.totalcredit"
              class="demo-class"
              :precision="2"
            ></a-statistic>
          </a-col>
          <a-col :span="3" v-if="!vipModify&&userInfo.vipType!='NOT_VIP'">
            
            <a-tooltip title="修改会员信息">
              <a-icon class="modify" type="setting" theme="filled" @click="onVIPModify" style="margin-right:10px"/><span>修改信息</span>
            </a-tooltip>
          </a-col>

          <a-col :span="2" v-if="vipModify">
            <a-tooltip title="确定修改">
              <a-icon class="modify" v-if="vipModify" type="check" @click="doneVIPModify" />
            </a-tooltip>
            <a-divider type="vertical"></a-divider>
            <a-tooltip title="取消修改">
              <a-icon class="modify" v-if="vipModify" type="close" @click="cancelVIPModify" />
            </a-tooltip>
          </a-col>
        </a-row>
        <a-row :gutter="16">
          <a-table
            :columns="VIPcol"
            :data-source="VIPval"
            :pagination="false"
            style="margin-top: 30px"
          ></a-table>
        </a-row>
      </a-tab-pane>
    </a-tabs>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import { message } from "ant-design-vue";
import infoPanel from "../../component/infoPanel";
import moment from 'moment'
const VIPcol = [
  {
    title: "会员等级",
    dataIndex: "level"
  },
  {
    title: "信用值",
    dataIndex: "credit"
  },
  {
    title: "优惠折扣",
    dataIndex: "discount"
  }
];
const VIPval = [
  {
    level: "不是会员",
    credit: "0",
    discount: "无"
  },
  {
    level: "一级普通会员",
    credit: "200",
    discount: "九五折"
  },
  {
    level: "二级普通会员",
    credit: "1500",
    discount: "九折"
  },
  {
    level: "三级普通会员",
    credit: "4500",
    discount: "八五折"
  },
  {
    level: "四级普通会员",
    credit: "10800",
    discount: "八折"
  },
  {
    level: "五级普通会员",
    credit: "28800",
    discount: "七五折"
  },
  {
    level: "企业会员",
    credit: "0",
    discount: "特殊"
  }
];

export default {
  name: "info",
  data() {
    return {
      modify: false,
      form: this.$form.createForm(this, { name: "coordinated" }),
      VIPcol,
      VIPval,
      vipModify: false,
      vipModifyVal: "",
      vipModifyType: false
    };
  },
  components: { infoPanel },
  computed: {
    ...mapGetters(["userId", "userInfo", "userBills"]),
    vipType() {
      const en2chin = {
        NOT_VIP: "零级会员",
        NORMAL_LEVEL1: "一级会员",
        NORMAL_LEVEL2: "二级会员",
        NORMAL_LEVEL3: "三级会员",
        NORMAL_LEVEL4: "四级会员",
        NORMAL_LEVEL5: "五级会员",
        ENTERPRISE: "企业会员"
      };
      return en2chin[this.userInfo.vipType];
    },
    iconText() {
      const en2chin = {
        NOT_VIP: "Lv0",
        NORMAL_LEVEL1: "Lv1",
        NORMAL_LEVEL2: "Lv2",
        NORMAL_LEVEL3: "Lv3",
        NORMAL_LEVEL4: "Lv4",
        NORMAL_LEVEL5: "Lv5",
        ENTERPRISE: "Ent"
      };
      return en2chin[this.userInfo.vipType];
    }
  },
  async mounted() {
    await this.getUserInfo();
    await this.getUserBills();
  },

  methods: {
    ...mapActions([
      "getUserInfo",
      "getUserBills",
      "updateUserInfo",
      "cancelOrder",
      "registerVIP"
    ]),
    saveModify() {
      this.form.validateFields((err, values) => {
        if (!err) {
          const data = {
            userName: this.form.getFieldValue("userName"),
            phoneNumber: this.form.getFieldValue("phoneNumber"),
            password: this.form.getFieldValue("password")
          };
          this.updateUserInfo(data).then(() => {
            this.modify = false;
          });
        }
      });
    },
    modifyInfo() {
      this.modify = true;
      this.form.setFieldsValue({
        userName: this.userInfo.userName,
        phoneNumber: this.userInfo.phoneNumber
      });
    },
    cancelModify() {
      this.modify = false;
    },
    confirmCancelOrder(orderId) {
      this.cancelOrder(orderId);
    },
    cancelCancelOrder() {},
    onVIPModify() {
      this.vipModify = true;
    },
    async doneVIPModify() {
      if (this.vipModifyVal == "") {
        message.error("修改信息不能为空");
        return;
      }

      await this.registerVIP({
        vipInfo: this.vipModifyVal,
        vipType: this.vipModifyType ? "ENTERPRISE" : this.userInfo.vipType
      });
      this.vipModify = false;
      this.vipModifyVal == "";
      this.vipModifyType == false;
    },
    cancelVIPModify() {
      this.vipModify = false;
    },
    onVIPBirthChange(val) {
      console.log(val);
      this.vipModifyVal = val.format("YYYY-MM-DD");
    },
    transDateFrom(date){
      return moment(date).format("YYYY-MM-DD, HH:mm")
    },
  }
};
</script>

<style lang="less">

</style>
<style lang="less" scoped>
.info-wrapper {
  margin-top: 150px;
  width: 80%;
  min-height: 65%;
  margin: auto;
  text-align: center;
  padding: 0px 0px 0px 0px;

  .ant-tabs {
    margin-top: 1px;
    margin: auto;

    width: 100%;
    height: 100%;
    padding: 20px;
    border-radius: 10px;

    .vip {
      padding-top: 20px;
      .modify {
        font-size: 20px;
      }
    }
  }
  .ant-tabs-bar {
    padding-left: 30px;
  }
}
.ant-timeline-item-head {
  margin-left: 150px;
}
.vip-level-icon{
  border: 2px solid ;
  padding: 0px 4px;
  border-radius: 10px;
  
  font-size: 16px;
  color: aliceblue;
}
.Lv0{
  background-color: rgb(145, 156, 156);
}
.Lv1{
  background-color: rgb(158, 165, 165);
}
.Lv2{
  background-color: rgb(101, 173, 173);
}
.Lv3{
  background-color: rgb(59, 101, 179);
}
.Lv4{
  background-color: rgb(141, 140, 67);
}
.Lv5{
  background-color: rgb(196, 132, 37);
}
.Ent{
  background-color: rgb(168, 47, 31);
}
</style>