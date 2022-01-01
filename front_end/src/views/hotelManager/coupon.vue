<template>
  <!-- 把coupon组件内容直接放在这里好了 -->
  <!--  <div class>-->
  <!--    优惠券制定、撤销、采用-->
  <!--    <a-button type="info" size="small" @click="showCoupon">优惠策略</a-button>-->
  <!--    <Coupon></Coupon>-->
  <div class="coupon-container">
    <!--      <a-table :columns="columns" :dataSource="couponList" :pagination="pagination">-->
    <!--        {{couponList}}-->
    <!--      </a-table>-->
    <!--        {{couponList}}-->
    <!--        <Coupon-->
    <!--            :couponList="couponList"-->
    <!--        >-->
    <div style="width: 100%; text-align: right; margin:20px 0">
      <a-button type="primary" @click="add">
        <a-icon type="plus" />添加满减优惠策略
      </a-button>
      <a-divider type="vertical"></a-divider>
      <a-button type="primary" @click="add1">
        <a-icon type="plus" />添加生日优惠策略
      </a-button>
      <a-divider type="vertical"></a-divider>
      <a-button type="primary" @click="add2">
        <a-icon type="plus" />添加多间优惠策略
      </a-button>
      <a-divider type="vertical"></a-divider>
      <a-button type="primary" @click="add3">
        <a-icon type="plus" />添加限时优惠策略
      </a-button>
    </div>
    <!--        <a-tabs>-->
    <!--            <a-tab-pane tab="本酒店优惠券" key="1"></a-tab-pane>-->
    <div>
      <div class="font1">本店优惠券:</div>
      <a-table :columns="columns1" :dataSource="couponListHotel" :pagination="pagination" bordered>
        <a-tag
          slot="status"
          slot-scope="text"
          :color="couponStateColor(text)"
          style="cursor:default "
        >{{couponStateText(text)}}</a-tag>

        <a-tag :color="typeColor(text)" slot="type" slot-scope="text">{{text?'折扣优惠':'满减优惠'}}</a-tag>
        <span slot="action" slot-scope="record">
          <a-button type="primary" size="small" @click="changStatus(record)">使失效</a-button>
        </span>
        <span slot="discountMoney" slot-scope="text">
          <span>{{text==0?'-':text}}</span>
        </span>
        <span slot="discount" slot-scope="text">
          <span>{{text==0?'-':text}}</span>
        </span>
      </a-table>
    </div>
    <!--            <a-tab-pane tab="全局优惠券" key="2"></a-tab-pane>-->
    <div>
      <div class="font2">全局优惠券:</div>

      <a-table :columns="columns2" :dataSource="couponListSite" :pagination="pagination" bordered>
        <a-tag
          slot="status"
          slot-scope="text"
          :color="couponStateColor(text)"
          style="cursor:default "
        >{{couponStateText(text)}}</a-tag>
        <a-tag :color="typeColor(text)" slot="type" slot-scope="text">{{text?'折扣优惠':'满减优惠'}}</a-tag>
        <span slot="discountMoney" slot-scope="text">
          <span>{{text==0?'-':text}}</span>
        </span>
        <span slot="discount" slot-scope="text">
          <span>{{text==0?'-':text}}</span>
        </span>
      </a-table>
    </div>
    <!--        </a-tabs>-->

    <!--        <addCoupon :addCouponVisible="addCoupon_Visible" @onClose="close"></addCoupon>-->
    <!--        </Coupon>-->
    <a-modal
      :visible="addCoupon_Visible"
      title="添加满减优惠策略"
      cancelText="取消"
      okText="确定"
      @cancel="cancel"
      @ok="handleSubmit"
    >
      <!-- #点确定的响应⽅法handleSubmit -->
      <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
        <a-form-item label="优惠券类型" v-bind="formItemLayout">满减优惠券</a-form-item>
        <a-form-item label="券名" v-bind="formItemLayout">
          <a-input
            placeholder="请填写券名"
            v-decorator="['name', { rules: [{ required: true, message:'请输入券名' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠简介" v-bind="formItemLayout">
          <a-input
            type="textarea"
            :rows="4"
            placeholder="请填写优惠简介"
            v-decorator="['description', { rules: [{ required: true,message: '请填写优惠简介' }] }]"
          ></a-input>
        </a-form-item>
         <a-form-item label="达标⾦额" >
          <a-input
            placeholder="请填写达标⾦额"
            v-decorator="['targetMoney', { rules: [{ required: true,message: '请填写达标金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠⾦额" v-bind="formItemLayout" v-if="couponType==0">
          <a-input
            placeholder="请填写优惠⾦额"
            v-decorator="['discountMoney', { rules: [{ required: true,message: '请填写优惠金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
      </a-form>
    </a-modal>
    <a-modal
      :visible="addCoupon_Visible1"
      title="添加生日优惠策略"
      cancelText="取消"
      okText="确定"
      @cancel="cancel1"
      @ok="handleSubmit1"
    >
      <!-- #点确定的响应⽅法handleSubmit -->
      <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
        <a-form-item label="优惠券类型" v-bind="formItemLayout">生日优惠券</a-form-item>
        <a-form-item label="券名" v-bind="formItemLayout">
          <a-input
            placeholder="请填写券名"
            v-decorator="['name', { rules: [{ required: true, message:'请输入券名' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠简介" v-bind="formItemLayout">
          <a-input
            type="textarea"
            :rows="4"
            placeholder="请填写优惠简介"
            v-decorator="['description', { rules: [{ required: true,message: '请填写优惠简介' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠类型" v-bind="formItemLayout">
          <a-radio-group @change="changeType" v-decorator="['type', { rules: [{ required: true,message: '请填写优惠金额' }],initialValue:0 }]" button-style="solid">
            <a-radio-button :value="0">满减</a-radio-button>
            <a-radio-button :value="1">折扣</a-radio-button>
          </a-radio-group>
        </a-form-item>
        <a-form-item label="达标⾦额" >
          <a-input
            placeholder="请填写达标⾦额"
            v-decorator="['targetMoney', { rules: [{ required: true,message: '请填写达标金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
        <a-form-item label="折扣" v-bind="formItemLayout" v-if="couponType==1">
          <a-input
            placeholder="请填写折扣"
            v-decorator="['discount', { rules: [{ required: true,message: '请填写折扣' }] ,initialValue:1}]"
          ></a-input>
          
        </a-form-item>
        <a-form-item label="优惠⾦额" v-bind="formItemLayout" v-if="couponType==0">
          <a-input
            placeholder="请填写优惠⾦额"
            v-decorator="['discountMoney', { rules: [{ required: true,message: '请填写优惠金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
      </a-form>
    </a-modal>
    <a-modal
      :visible="addCoupon_Visible2"
      title="添加多间优惠策略"
      cancelText="取消"
      okText="确定"
      @cancel="cancel2"
      @ok="handleSubmit2"
    >
      <!-- #点确定的响应⽅法handleSubmit -->
      <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
        <!--                <a-form-item label="优惠券类型" v-bind="formItemLayout">-->
        <!--                    <a-select-->
        <!--                            v-decorator="['type',{ rules: [{ required: true, message: '请选择类型' }] }]"-->
        <!--                            @change="changeType"-->
        <!--                    >-->
        <!--                        <a-select-option value="1">生日特惠</a-select-option>-->
        <!--                        <a-select-option value="2">多间特惠</a-select-option>-->
        <!--                        <a-select-option value="3">满减特惠</a-select-option>-->
        <!--                        <a-select-option value="4">限时特惠</a-select-option>-->
        <!--                    </a-select>-->
        <!--                </a-form-item>-->
        <a-form-item label="优惠券类型" v-bind="formItemLayout">多间优惠券</a-form-item>
        <a-form-item label="券名" v-bind="formItemLayout">
          <a-input
            placeholder="请填写券名"
            v-decorator="['name', { rules: [{ required: true, message:'请输入券名' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠简介" v-bind="formItemLayout">
          <a-input
            type="textarea"
            :rows="4"
            placeholder="请填写优惠简介"
            v-decorator="['description', { rules: [{ required: true,message: '请填写优惠简介' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠类型" v-bind="formItemLayout">
          <a-radio-group @change="changeType" v-decorator="['type', { rules: [{ required: true,message: '请填写优惠金额' }],initialValue:0 }]" button-style="solid">
            <a-radio-button :value="0">满减</a-radio-button>
            <a-radio-button :value="1">折扣</a-radio-button>
          </a-radio-group>
        </a-form-item>
        <a-form-item label="达标⾦额" >
          <a-input
            placeholder="请填写达标⾦额"
            v-decorator="['targetMoney', { rules: [{ required: true,message: '请填写达标金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
        <a-form-item label="折扣" v-bind="formItemLayout" v-if="couponType==1">
          <a-input
            placeholder="请填写折扣"
            v-decorator="['discount', { rules: [{ required: true,message: '请填写折扣' }] ,initialValue:1}]"
          ></a-input>
          
        </a-form-item>
        <a-form-item label="优惠⾦额" v-bind="formItemLayout" v-if="couponType==0">
          <a-input
            placeholder="请填写优惠⾦额"
            v-decorator="['discountMoney', { rules: [{ required: true,message: '请填写优惠金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
        
      </a-form>
    </a-modal>
    <a-modal
      :visible="addCoupon_Visible3"
      title="添加限时优惠策略"
      cancelText="取消"
      okText="确定"
      @cancel="cancel3"
      @ok="handleSubmit3"
    >
      <!-- #点确定的响应⽅法handleSubmit -->
      <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
        <a-form-item label="优惠券类型" v-bind="formItemLayout">限时优惠券</a-form-item>
        <a-form-item label="券名" v-bind="formItemLayout">
          <a-input
            placeholder="请填写券名"
            v-decorator="['name', { rules: [{ required: true, message:'请输入券名' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠简介" v-bind="formItemLayout">
          <a-input
            type="textarea"
            :rows="4"
            placeholder="请填写优惠简介"
            v-decorator="['description', { rules: [{ required: true,message: '请填写优惠简介' }] }]"
          ></a-input>
        </a-form-item>
        <a-form-item label="优惠类型" v-bind="formItemLayout">
          <a-radio-group @change="changeType" v-decorator="['type', { rules: [{ required: true,message: '请填写优惠金额' }],initialValue:0 }]" button-style="solid">
            <a-radio-button :value="0">满减</a-radio-button>
            <a-radio-button :value="1">折扣</a-radio-button>
          </a-radio-group>
        </a-form-item>
        <a-form-item label="达标⾦额" >
          <a-input
            placeholder="请填写达标⾦额"
            v-decorator="['targetMoney', { rules: [{ required: true,message: '请填写达标金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
        <a-form-item label="折扣" v-bind="formItemLayout" v-if="couponType==1">
          <a-input
            placeholder="请填写折扣"
            v-decorator="['discount', { rules: [{ required: true,message: '请填写折扣' }] ,initialValue:1}]"
          ></a-input>
          
        </a-form-item>
        <a-form-item label="优惠⾦额" v-bind="formItemLayout" v-if="couponType==0">
          <a-input
            placeholder="请填写优惠⾦额"
            v-decorator="['discountMoney', { rules: [{ required: true,message: '请填写优惠金额' }] ,initialValue:0}]"
          ></a-input>
        </a-form-item>
        <a-form-item label="有效时段" v-bind="formItemLayout">
          <a-range-picker
      :ranges="{ '今日有效': [moment(), moment()], '本月有效': [moment(), moment().endOf('month')] }"
      show-time
      format="YYYY/MM/DD HH:mm:ss"
      v-decorator="['timeSpan', { rules: [{ required: true,message: '请填写优惠金额' }] ,initialValue:[moment(), moment()]}]"
    />
        </a-form-item>
        
      </a-form>
    </a-modal>
  </div>
  <!--  </div>-->
</template>


<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
// import Coupon from "../hotelManager/components/coupon";
import addCoupon from "./components/addCoupon";
import moment from 'moment'
const renderContent = (value, row, index) => {
  const obj = {
    type: value,
    props: {}
  };
  if (index === 5) {
    obj.props.colSpan = 0;
  }
  return obj;
};
const columns1 = [
  {
    title: "优惠类型",
    dataIndex: "type",
    scopedSlots: { customRender: "type" }
  },
  {
    title: "优惠名称",
    dataIndex: "couponName"
    // scopedSlots:{customRender:'type'}
  },
  {
    title: "优惠简介",
    dataIndex: "description"
  },
  {
    title: "折扣",
    dataIndex: "discount",
    scopedSlots: { customRender: "discount" },
    render: renderContent
  },
  {
    title: "优惠金额",
    dataIndex: "discountMoney",
    scopedSlots: { customRender: "discountMoney" }
  },
  {
    title: "是否失效",
    dataIndex: "status",
    scopedSlots: { customRender: "status" }
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
];
const columns2 = [
  {
    title: "优惠类型",
    dataIndex: "type",
    scopedSlots: { customRender: "type" }
  },
  {
    title: "优惠名称",
    dataIndex: "couponName"
    // scopedSlots:{customRender:'type'}
  },
  {
    title: "优惠简介",
    dataIndex: "description"
  },
  {
    title: "折扣",
    dataIndex: "discount",
    scopedSlots: { customRender: "discount" },
    render: renderContent
  },
  {
    title: "优惠金额",
    dataIndex: "discountMoney",
    scopedSlots: { customRender: "discountMoney" }
  },
  {
    title: "是否失效",
    dataIndex: "status",
    scopedSlots: { customRender: "status" }
  },
];
export default {
  data() {
    return {
      columns1,
      columns2,
      couponType:0,
      addCoupon_Visible: false,
      addCoupon_Visible1: false,
      addCoupon_Visible2: false,
      addCoupon_Visible3: false,
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
      value: [],
      form: this.$form.createForm(this), //注册后，才能通过表单拉取数据
      pagination: {
        pageSize: 4, //每页中显示10条数据
        showSizeChanger: true
        // pageSizeOptions: ["5","6", "7", "8"],//每页中显示的数据
      }
    };
  },
  components: {
    // Coupon
    //   addCoupon
  },
  computed: {
    ...mapGetters(["couponVisible", "couponList", "hotelInfo"]),
    couponListHotel() {
      return this.couponList.filter(c => c.hotelId > -1);
    },
    couponListSite() {
      return this.couponList.filter(c => c.hotelId == -1);
    },
  },
  async mounted() {
    await this.getHotelCoupon();
  },
  methods: {
      moment,
    ...mapMutations(["set_couponVisible"]),
    ...mapActions([
      "getHotelCoupon",
      "unableCoupon",
      "addTargetMoneyCoupon",
      "addBirthdayCoupon",
      "addTimeCoupon",
      "addRoomCoupon"
    ]),
    changeType(e){
        this.couponType=e.target.value
    },
    // showCoupon() {
    //   this.set_couponVisible(true);
    //editStateText(status) {
    couponStateText(status) {
      const trans = {
        VALID: "已激活",
        INVALID: "失效"
      };
      return trans[status];
    },
    couponStateColor(status) {
      const trans = {
        VALID: "blue",
        INVALID: "red"
      };
      return trans[status];
    },
    typeColor(status) {
      const trans = {
        1: "orange",
        0: "blue"
      };
      return trans[status];
    },
    add() {
      this.addCoupon_Visible = true;
      // },
      // close(){
      //   this.addCoupon_Visible=false
    },
    add1() {
      this.addCoupon_Visible1 = true;
    },
    add2() {
      this.addCoupon_Visible2 = true;
    },
    add3() {
      this.addCoupon_Visible3 = true;
    },
    beforeCreate() {
      // 表单名默认为“form”
      this.form = this.$form.createForm(this, { name: "addCouponModal" });
    },
    resetValues(){
        this.form.resetFields();
      this.couponType=0;
    },
    cancel() {
      this.addCoupon_Visible = false;
      this.resetValues();
    },
    cancel1() {
      this.addCoupon_Visible1 = false;
      this.resetValues();
    },
    cancel2() {
      this.addCoupon_Visible2 = false;
      this.resetValues();
    },
    cancel3() {
      this.addCoupon_Visible3 = false;
      this.resetValues();
    },
    // changeType(v) {
    //   if (v == "3") {
    //   } else {
    //     this.$message.warning("请实现");
    //   }
    // },
    async changStatus(value) {
      this.value = value;
      console.log(this.value.id);
      await this.unableCoupon({
        couponId: this.value.id
      });
      await this.getHotelCoupon();
    },
    handleSubmit(e) {
      console.log(e);
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            couponName: this.form.getFieldValue("name"),
            description: this.form.getFieldValue("description"),
            // type: Number(this.form.getFieldValue('type')),
            target: Number(this.form.getFieldValue("targetMoney")),
            discountMoney: Number(this.form.getFieldValue("discountMoney")),
            hotelId: Number(this.hotelInfo.id)
            // status: 1,
          };
          this.addTargetMoneyCoupon(data);
          this.resetValues();
        }
      });
      this.addCoupon_Visible = false;
    },
    //生日
    handleSubmit1(e) {
      console.log(e);
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            couponName: this.form.getFieldValue("name"),
            description: this.form.getFieldValue("description"),
            // type: Number(this.form.getFieldValue('type')),
            target: Number(this.form.getFieldValue("targetMoney")),
            discountMoney: Number(this.form.getFieldValue("discountMoney")),
            discount: Number(this.form.getFieldValue("discount")),
            type: Number(this.form.getFieldValue("type")),
            hotelId: Number(this.hotelInfo.id)
            // status: 1,
          };
          this.addBirthdayCoupon(data);
          this.resetValues();
        }
      });
      this.addCoupon_Visible = false;
    },
    //多间
    handleSubmit2(e) {
      console.log(e);
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            couponName: this.form.getFieldValue("name"),
            description: this.form.getFieldValue("description"),
            // type: Number(this.form.getFieldValue('type')),
            target: Number(this.form.getFieldValue("targetMoney")),
            discountMoney: Number(this.form.getFieldValue("discountMoney")),
            discount: Number(this.form.getFieldValue("discount")),
            type: Number(this.form.getFieldValue("type")),
            hotelId: Number(this.hotelInfo.id)
            // status: 1,
          };
          this.addRoomCoupon(data);
         this.resetValues();
        }
      });
      this.addCoupon_Visible = false;
    },
    handleSubmit3(e) {
      console.log(e);
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            couponName: this.form.getFieldValue("name"),
            description: this.form.getFieldValue("description"),
            // type: Number(this.form.getFieldValue('type')),
            target: Number(this.form.getFieldValue("targetMoney")),
            discountMoney: Number(this.form.getFieldValue("discountMoney")),
            discount: Number(this.form.getFieldValue("discount")),
            type: Number(this.form.getFieldValue("type")),
            startTime: this.form.getFieldValue("timeSpan")[0].valueOf(),
            endTime: this.form.getFieldValue("timeSpan")[1].valueOf(),
            hotelId: Number(this.hotelInfo.id)
            // status: 1,
          };
          this.addTimeCoupon(data);
          this.resetValues();
        }
      });
      this.addCoupon_Visible = false;
    }
  }
};
</script>
<style scoped lang="less">
.font1 {
  font-size: 20px;
}
.font2 {
  font-size: 20px;
}
.coupon-container {
  padding: 40px;
}
</style>