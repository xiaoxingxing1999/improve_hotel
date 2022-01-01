<!-- todo:重复密码，头像,酒店id -->
<template>

  <a-modal
    :visible="addManagerModalVisible"
    title="添加酒店工作人员"
    cancelText="取消"
    okText="确定"
    @cancel="cancel"
    @ok="handleSubmit"
  >
    <a-Form :form="form">
      <a-form-item v-bind="formItemLayout" label="用户邮箱">
        <a-input
          v-decorator="[
                        'email',
                        { rules: [{required: true, message: '请输入用户邮箱', }] }
                    ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="密码">
        <a-input
          v-decorator="[
                        'password',
                        { rules: [{required: true, message: '请输入密码', }] }
                    ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="重复密码">
        <a-input
          v-decorator="[
                        'passwordAgain',
                        { rules: [{required: true, message: '请重复输入密码', }] }
                    ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="姓名">
        <a-input
          v-decorator="[
                        'userName',
                        { rules: [{required: true, message: '请输入用户姓名', }] }
                    ]"
        />
      </a-form-item>
      <a-form-item v-bind="formItemLayout" label="手机号">
        <a-input
          v-decorator="[
                        'phoneNumber',
                        { rules: [{required: true, message: '请输入用户手机号', }] }
                    ]"
        />
      </a-form-item>
    </a-Form>
  </a-modal>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "addManagerModal",
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
    ...mapGetters(["addManagerModalVisible", "managerList"])
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "addManagerModal" });
  },
  mounted() {},
  methods: {
    ...mapMutations(["set_addManagerModalVisible", "set_addManagerParams"]),
    ...mapActions(["getManagerList", "addManager"]),
    cancel() {
      this.set_addManagerModalVisible(false);
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFieldsAndScroll((err, values) => {
        if (!err) {
          const data = {
            email: this.form.getFieldValue("email"),
            password: this.form.getFieldValue("password"),
            userName:this.form.getFieldValue("userName"),
            phoneNumber:this.form.getFieldValue("phoneNumber"),
          };
          this.set_addManagerParams(data);
          this.addManager();
        }
      });
    }
  }
};
</script>