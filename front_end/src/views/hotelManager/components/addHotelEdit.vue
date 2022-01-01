<template>
  <a-modal
    :visible="visible"
    title="酒店信息修改"
    cancelText="取消"
    okText="确定"
    @cancel="cancel"
    @ok="handleSubmit"
  >
    <a-form :form="form" style="margin-top: 20px" v-bind="formItemLayout">
      <a-form-item label="酒店名称" v-bind="formItemLayout">
        <a-input
          placeholder="请填写酒店名称"
          v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名称' }] ,initialValue:this.hotelInfo.hotelName}]"
        />
      </a-form-item>
      <a-form-item label="酒店电话" v-bind="formItemLayout">
        <a-input
          placeholder="请填写酒店电话"
          v-decorator="['phoneNum', { rules: [{ required: true, message: '请输入酒店电话' }] ,initialValue:this.hotelInfo.phoneNum}]"
        />
      </a-form-item>
      <a-form-item label="酒店服务" v-bind="formItemLayout">
        <a-select
          mode="tags"
          placeholder="添加酒店服务"
          v-decorator="['serve', { rules: [{ required: true, message: '添加房间服务' }] }]"
        >
          <a-select-option
            v-for="serve in allServes"
            :key="serve.createTime"
            :value="serve.name"
          >{{serve.name}}</a-select-option>
        </a-select>
      </a-form-item>
      <a-form-item label="酒店描述" v-bind="formItemLayout">
        <a-input
          placeholder="请填写酒店描述"
          v-decorator="['description', { rules: [{ required: true, message: '请输入酒店描述' }],initialValue:this.hotelInfo.hotelDescription }]"
        />
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 22 }">
        <div class="uploadLmg">
          <addImg @uploadImg="uploadImg" :previewImg="previewImg" />
          <a-input
            @change="preview"
            placeholder="上传酒店图片或直接填入url"
            v-decorator="['url', { rules: [{ required: false, message: '上传房间图片或直接填入url' }] }]"
          />
        </div>
      </a-form-item>
    </a-form>
  </a-modal>
</template>
<script>
let index = 0;
import { mapGetters, mapMutations, mapActions } from "vuex";
import addImg from "../../../component/addImg";
export default {
  name: "addHotelEdit",
  data() {
    return {
        previewImg:'',
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
  components: {
    addImg
  },
  props: ["visible"],
  computed: {
    ...mapGetters(["hotelInfo", "allServes"]),
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "addRoomModal" });
  },
  async mounted() {},
  methods: {
      ...mapActions(['editHotel']),

    cancel() {
      this.$emit("close");
      this.form.resetFields();
    },
    uploadImg(url) {
      console.log("图片url：", url);
      this.form.setFieldsValue({ url: url });
    },
    preview(e) {
      console.log(e);
      this.previewImg = e.target.value;
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            hotelName: this.form.getFieldValue("hotelName"),
            phoneNum: this.form.getFieldValue("phoneNum"),
            hotelServe: this.form.getFieldValue("serve").join('|'),
            hotelDescription: this.form.getFieldValue("description"),
            url: this.form.getFieldValue("url")?this.form.getFieldValue("url"):this.hotelInfo.picture,
          };
          this.editHotel(data);
          this.form.resetFields();
          this.$emit("close");
        }
      });
    }
  }
};
</script>
<style lang="less" scoped>
.uploadLmg {
  display: flex;

  align-items: center;
}
</style>