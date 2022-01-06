<template>
  <a-drawer
    width="700"
    :closable="true"
    :visible="orderModalVisible"
    @close="cancelOrder"
    style="padding-top:100px"
  >
    <div class="order-container">
      <a-form :form="form">
        <a-form-item v-bind="formItemLayout" label="房型信息">
          <span>{{ currentOrderRoom.roomType }}</span>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="入住人姓名">
          <a-input
            v-decorator="[
                        'clientName',
                        { rules: [{required: true, message: '请填写入住人姓名', }],
                        initialValue:this.userInfo.userName                      
                         }
                    ]"
          />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="手机号">
          <a-input
            v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请填写入住人联系手机', }],
                        initialValue:this.userInfo.phoneNumber
                         }
                    ]"
          />
        </a-form-item>

        <a-form-item v-bind="formItemLayout" label="入住日期" class="date">
          <a-range-picker
            format="YYYY-MM-DD"
            @change="changeDate"
            :disabledDate="dateCanBeChoosed"
            v-decorator="[
                        'date', 
                        {
                            rules: [{ required: true, message: '请选择入住时间' }]   
                        }
                    ]"
            :placeholder="['入住日期','退房日期']"
          />
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="入住人数" class="peopleNum">
          <a-select
            v-decorator="[
                        'peopleNum',
                        { rules: [{ required: true, message: '请选择入住人数' }] },
                    ]"
            placeholder="请选择入住人数"
            @change="changePeopleNum"
          >
            <a-select-option :value="1" class="target">1</a-select-option>
            <a-select-option :value="2">2</a-select-option>
            <a-select-option :value="3">3</a-select-option>
            <a-select-option :value="4">4</a-select-option>
            <a-select-option :value="5">5</a-select-option>
            <a-select-option :value="6">6</a-select-option>
            <a-select-option :value="7">7</a-select-option>
            <a-select-option :value="8">8</a-select-option>
            <a-select-option :value="9">9</a-select-option>
            <a-select-option :value="10">10</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="有无儿童">
          <a-radio-group
            v-decorator="[
                        'haveChild',
                        { rules: [{required: true, message: '请选择有无儿童入住', }],
                        initialValue:0 }
                        
                    ]"
          >
            <a-radio :value="1">有</a-radio>
            <a-radio :value="0">无</a-radio>
          </a-radio-group>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="房间数" class="roomNum">
          <a-select
            v-decorator="[
                        'roomNum',
                        { rules: [{ required: true, message: '请选择房间数' }] },
                    ]"
            placeholder="请选择房间数"
            @change="changeRoomNum"
          >
            <a-select-option :value="1" class="target">1</a-select-option>
            <a-select-option :value="2">2</a-select-option>
            <a-select-option :value="3">3</a-select-option>
            <a-select-option :value="4">4</a-select-option>
            <a-select-option :value="5">5</a-select-option>
            <a-select-option :value="6">6</a-select-option>
          </a-select>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="房间单价">
          <span>￥{{ currentOrderRoom.price }}</span>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="总价">
          <span style="font-size:25px">￥{{ totalPrice }}</span>
        </a-form-item>
        <a-divider></a-divider>
        <h2 v-if="orderMatchCouponList.length>0">优惠券</h2>
        <div class="coupon-container">
          <coupon
            :class="[index==selectedCouponIdx?'selected':'']"
            v-for="(coupon,index) in orderMatchCouponList"
            :key="index"
            :coupon="coupon"
            @click.native="selectCoupon(index,coupon)"
          />
        </div>
        <a-divider></a-divider>
        <h2 v-if="vipDiscount<1">会员折扣: {{vipDiscount*100}}%</h2>
        <a-form-item v-bind="formItemLayout" label="结算后总价">
          <span style="font-size:25px">￥{{ finalPrice }}</span>
        </a-form-item>
        <a-form-item v-bind="formItemLayout" label="预约条款">
          <a-tooltip class="">
          <template slot="title">本系统不处理费用问题，付款由线下完成</template>
            <a-icon type="question-circle" />
        </a-tooltip>
        </a-form-item>
        
        <div style="width:100%;display:flex;justify-content:center">
          <a-button
            style="marginRight: 20px"
            type="primary"
            @click="handleSubmit"
            class="handleSubmit"
          >提交</a-button>
          <a-button @click="cancelOrder">取消</a-button>
        </div>
      </a-form>
    </div>
  </a-drawer>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
const moment = require("moment");
import coupon from "./coupon";
const columns = [
  {
    title: "勾选",
    dataIndex: "id",
    scopedSlots: { customRender: "id" }
  },
  {
    title: "优惠类型",
    dataIndex: "couponName",
    scopedSlots: { customRender: "couponName" }
  },
  {
    title: "折扣",
    dataIndex: "discount"
  },

  {
    title: "优惠简介",
    dataIndex: "description"
  },
  {
    title: "优惠金额",
    dataIndex: "discountMoney"
  }
];
export default {
  name: "orderModal",
  components: {
    coupon
  },
  data() {
    return {
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
      totalPrice: 0,
      columns,
      selectedCouponIdx: -1,
      selectedCoupon: {}
    };
  },
  computed: {
    ...mapGetters([
      "orderModalVisible",
      "currentOrderRoom",
      "currentHotelId",
      "currentHotelInfo",
      "userId",
      "userInfo",
      "orderMatchCouponList"
    ]),

    finalPrice() {
      console.log("-------优惠计算----------");
      console.log("原价", this.totalPrice);
      let res = this.totalPrice;
      if (JSON.stringify(this.selectedCoupon) != "{}") {
        if (this.selectedCoupon.type == 0) {
          //满减
          res -= this.selectedCoupon.discountMoney;
        } else {
          //折扣
          res *= this.selectedCoupon.discount;
        }
      }
      console.log("应用优惠券", res);
      res *= this.vipDiscount;
      console.log("会员折扣", res);
      return res;
    },
    vipDiscount() {
      const dict = {
        NOT_VIP: 1,
        NORMAL_LEVEL1: 0.95,
        NORMAL_LEVEL2: 0.9,
        NORMAL_LEVEL3: 0.85,
        NORMAL_LEVEL4: 0.8,
        NORMAL_LEVEL5: 0.75,
        ENTERPRISE: 1
      };

      return dict[this.userInfo.vipType];
    }
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "orderModal" });
  },
  methods: {
    ...mapMutations(["set_orderModalVisible", "set_orderMatchCouponList"]),
    ...mapActions(["addOrder", "getOrderMatchCoupons"]),
    cancelOrder() {
      this.set_orderModalVisible(false);
      this.set_orderMatchCouponList([]);
      this.form.resetFields();
    },
    changeDate(v) {
      if (this.totalPrice != "") {
        this.totalPrice =
          this.form.getFieldValue("roomNum") *
          moment(v[1]).diff(moment(v[0]), "day") *
          Number(this.currentOrderRoom.price);
      }
    },
    changePeopleNum(v) {},
    changeRoomNum(v) {
      this.totalPrice =
        Number(v) *
        Number(this.currentOrderRoom.price) *
        moment(this.form.getFieldValue("date")[1]).diff(
          moment(this.form.getFieldValue("date")[0]),
          "day"
        );
    },
    selectCoupon(idx, coupon) {
      console.log("select", idx);
      this.selectedCouponIdx = idx;
      this.selectedCoupon = coupon;
    },
    dateCanBeChoosed(now) {
      return now.diff(moment(), "days") < 0;
    },

    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            hotelId: this.currentHotelId,
            hotelName: this.currentHotelInfo.hotelName,
            checkInDate: moment(this.form.getFieldValue("date")[0]).valueOf(),
            checkOutDate: moment(this.form.getFieldValue("date")[1]).valueOf(),
            roomType: this.currentOrderRoom.roomType,
            roomNum: this.form.getFieldValue("roomNum"),
            peopleNum: this.form.getFieldValue("peopleNum"),
            haveChild: this.form.getFieldValue("haveChild"),
            // createDate: '',
            price:this.finalPrice,
            clientName: this.form.getFieldValue("clientName"),
            phoneNumber: this.form.getFieldValue("phoneNumber")
          };
          this.addOrder(data);
          this.form.resetFields();
          this.set_orderMatchCouponList([]);
          //insert
          this.$router.push("/client/orders");
        }
      });
    }
  },
  watch: {
    async totalPrice(val) {
      let data = {
        hotelId: this.currentHotelId,
        orderPrice: this.totalPrice,
        roomNum: this.form.getFieldValue("roomNum"),
        // checkIn: moment(this.form.getFieldValue("date")[0]).format(
        //   "YYYY-MM-DD"
        // ),
        // checkOut: moment(this.form.getFieldValue("date")[1]).format(
        //   "YYYY-MM-DD"
        // )
        checkIn: moment(this.form.getFieldValue("date")[0]).valueOf(),
        checkOut: moment(this.form.getFieldValue("date")[1]).valueOf()
      };
      
      this.getOrderMatchCoupons(data);
      
      
    }
  }
};
</script>
<style lang="less" scoped>
.order-container {
  padding: 70px 0px;
}
.selected {
  background-color: rgb(220, 221, 221);
}
.coupon-container {
  max-height: 500px;
  overflow-x: auto;
  overflow-y: hidden;
  display: flex;
  flex-direction: row;
  align-items: center;
  padding-bottom: 15px;
}
.coupon-container::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 5px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 10px;
}
.coupon-container::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  background-color: rgb(99, 62, 7);
  background-image: -webkit-linear-gradient(
    45deg,
    rgba(255, 255, 255, 0.2) 25%,
    transparent 25%,
    transparent 50%,
    rgba(255, 255, 255, 0.2) 50%,
    rgba(255, 255, 255, 0.2) 75%,
    transparent 75%,
    transparent
  );
}
.coupon-container::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #ededed;
  border-radius: 10px;
}
.moreInfo {
  font-size: 16px;
  position: relative;
  display: block;
}
</style>