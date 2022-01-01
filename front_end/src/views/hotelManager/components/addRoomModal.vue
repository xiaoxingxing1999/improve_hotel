<template>
  <a-modal
    :visible="addRoomModalVisible"
    title="录入客房"
    cancelText="取消"
    okText="确定"
    @cancel="cancel"
    @ok="handleSubmit"
  >
    <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
      <a-form-item label="房型" v-bind="formItemLayout" v-if="!newRoomType" style="display:flex">
        <a-select
          style="width:60%"
          :showSearch="true"
          placeholder="请选择房型"
          v-decorator="['roomType', { rules: [{ required: true, message: '请选择房型' }] }]"
        >
          <a-select-option
            v-for="type in roomTypes"
            :key="type.createTime"
            :value="type.name"
          >{{type.name}}</a-select-option>
        </a-select>
        <a-button style="width:40%" @click="addNewType" class="uniqueType">自定义类型</a-button>
      </a-form-item>
      <a-form-item label="房型" v-bind="formItemLayout" v-else class="newRoomType">
        <a-input
          placeholder="请填写房型"
          v-decorator="['roomTypeNew', { rules: [{ required: true, message: '请选择房型' }] }]"
        />
      </a-form-item>
      <a-form-item label="房间数量" v-bind="formItemLayout">
        <a-input
          placeholder="请填写房间数量"
          v-decorator="['roomNum', { rules: [{ required: true, message: '请输入房间数量' }] }]"
        />
      </a-form-item>
      <a-form-item label="原始价格" v-bind="formItemLayout">
        <a-input
          placeholder="请填写原始价格"
          v-decorator="['price', { rules: [{ required: true, message: '请输入原始价格' }] }]"
        />
      </a-form-item>
      <a-form-item label="单间入住人数" v-bind="formItemLayout">
        <a-input
          placeholder="请填写单间入住人数"
          v-decorator="['validPeople', { rules: [{ required: true, message: '请输入单间入住人数' }] }]"
        />
      </a-form-item>
      <a-form-item label="房间服务" v-bind="formItemLayout">
        <a-select mode="tags" placeholder="添加房间服务" v-decorator="['serve', { rules: [{ required: false, message: '添加房间服务' }] }]">
          <a-select-option
            v-for="serve in allServes"
            :key="serve.createTime"
            :value="serve.name"
          >{{serve.name}}</a-select-option>
        </a-select>
      </a-form-item>
      
      <a-form-item :wrapper-col="{ span: 22 }" >
        <div class="uploadLmg">
          <addImg @uploadImg="uploadImg" :previewImg="previewImg"/>
        <a-input
          @change="preview"
          placeholder="上传房间图片或直接填入url"
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
import addImg from '../../../component/addImg'
export default {
  name: "addRoomModal",
  data() {
    return {
      newRoomType: false,
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
  computed: {
    ...mapGetters(["addRoomModalVisible", "hotelInfo", "roomTypes","allServes"])
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "addRoomModal" });
  },
  async mounted() {
    await this.getAllRoomType();
    await this.getAllServe();
  },
  methods: {
    ...mapMutations(["set_addRoomModalVisible", "set_addRoomParams"]),
    ...mapActions(["addRoom", "getAllRoomType","getAllServe"]),
    addNewType() {
      this.newRoomType = true;
    },
    cancel() {
      this.set_addRoomModalVisible(false);
      this.newRoomType = false;
      this.form.resetFields();
    },
    uploadImg(url){
      console.log('图片url：',url)
      this.form.setFieldsValue({'url':url})
      // this.$nextTick(() => {
      //       this.form.setFieldsValue({
      //         streamName: 'streamName',
      //       })});
    },
    preview(e){
      console.log(e)
      this.previewImg=e.target.value
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            roomType: this.newRoomType
              ? this.form.getFieldValue("roomTypeNew")
              : this.form.getFieldValue("roomType"),
            price: Number(this.form.getFieldValue("price")),
            total: Number(this.form.getFieldValue("roomNum")),
            validPeople: Number(this.form.getFieldValue("validPeople")),
            serve:this.form.getFieldValue("serve").join('|'),
            hotelId: this.hotelInfo.id,
            url:this.form.getFieldValue("url"),
          };
          this.set_addRoomParams(data);
          this.addRoom();
          this.form.resetFields();
          this.set_addRoomModalVisible(false);
        }
      });
    }
  }
};
</script>
<style lang="less" scoped>
.uploadLmg{
  display: flex;

  align-items: center;
}
</style>