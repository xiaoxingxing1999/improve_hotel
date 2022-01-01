<!--  todo:hotelServe输入，bizRegion，picture -->
<template>
  <a-modal
    :visible="visible"
    title="添加酒店"
    cancelText="取消"
    okText="确定"
    @cancel="cancel"
    @ok="handleSubmit"
  >
    <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
      <a-form-item label="酒店名">
        <a-input
          placeholder="请填写酒店名称"
          v-decorator="['hotelName', { rules: [{ required: true, message: '请填写酒店名称' }] }]"
        />
      </a-form-item>
      <a-form-item label="酒店地址" v-bind="formItemLayout">
        <a-input
          placeholder="请填写酒店地址"
          v-decorator="['address', { rules: [{ required: true, message: '请填写酒店地址' }] }]"
        />
      </a-form-item>
      <a-form-item label="酒店商圈" v-bind="formItemLayout">
        <a-select
          v-decorator="[
                    'bizRegion',
                    { rules: [{ required: true, message: '请选择酒店商圈' }] }]"
        >
          <a-select-option
            v-for="region in bizRegions"
            :key="region.name"
            :value="region.name"
          >{{region.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="酒店星级" v-bind="formItemLayout">
        <a-select
          v-decorator="[
                    'hotelStar',
                    { rules: [{ required: true, message: '请选择酒店星级' }] }]"
        >
          <a-select-option value="One">一星级</a-select-option>
          <a-select-option value="Two">二星级</a-select-option>
          <a-select-option value="Three">三星级</a-select-option>
          <a-select-option value="Four">四星级</a-select-option>
          <a-select-option value="Five">五星级</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="手机号" v-bind="formItemLayout">
        <a-input
          placeholder="请填写手机号"
          v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }, { validator: this.handlePhone }], validateTrigger: 'blur'}]"
        />
      </a-form-item>
      <a-form-item label="酒店简介" v-bind="formItemLayout">
        <a-input
          type="textarea"
          :rows="4"
          placeholder="请填写酒店简介"
          v-decorator="['description', { rules: [{ required: false, message: '请填写酒店简介' }] }]"
        ></a-input>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "addHotelModal",
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
      }
    };
  },
  computed: {
    ...mapGetters(["userId", "addHotelParams", "bizRegions"])
  },
  props: {
    visible: {
      type: Boolean,
      default: false
    }
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "addHotelModal" });
  },
  async mounted() {
    await this.getBizRegions();
  },
  methods: {
    ...mapMutations(["set_addHotelParams"]),
    ...mapActions(["addHotel", "getBizRegions"]),
    cancel() {
      this.$emit("closeHotelModal");
      this.form.resetFields();
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            hotelName: this.form.getFieldValue("hotelName"),
            hotelDescription: this.form.getFieldValue("description"),
            address: this.form.getFieldValue("address"),
            // bizRegion待定
            hotelStar: this.form.getFieldValue("hotelStar"),
            phoneNum: this.form.getFieldValue("phoneNumber"),
            hotelServe: "", //todo
            picture: "", //todo
            bizRegion: this.form.getFieldValue("bizRegion")
          };
          this.set_addHotelParams(data);
          this.addHotel();
          this.form.resetFields();
          this.$emit("closeHotelModal");
        }
      });
    },
    handlePhone(rule, value, callback) {
      if (value.length != 8 && value.length != 11) {
        callback(new Error("电话长度应该为8或者11位"));
      }
      callback();
    }
  }
};
</script>