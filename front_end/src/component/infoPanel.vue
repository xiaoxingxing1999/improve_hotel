<!-- 查看充值记录，用tab -->
<template>
  <div class="info-wrapper">
    <a-form
      :form="form"
      class="info-from"
      :label-col="{ span: 4 }"
      :wrapper-col="{ span: 15, offset: 1  }"
    >
      <a-form-item label="用户名">
        <a-input
        class="modifyName"
          placeholder="请填写用户名"
          v-decorator="['userName', { rules: [{ required: false, message: '请输入用户名' },{ validator: this.handleName }] }]"
          v-if="modifyInfo"
        />
        <span v-else class="userName">{{ userInfo.userName }}</span>
      </a-form-item>
      <a-form-item label="用户ID" v-if="userInfo.userType==='Client'">
        <!-- 信用充值要用到 -->
        <span>{{ userInfo.userId }}</span>
      </a-form-item>
      <a-form-item label="邮箱">
        <span>{{ userInfo.email }}</span>
      </a-form-item>

      <a-form-item label="手机号">
        <a-input
          placeholder="请填写手机号"
          v-decorator="['phoneNumber', { rules: [{ required: false, message: '请输入手机号' }, { validator: this.handlePhone }] }]"
          v-if="modifyInfo"
        />
        <span v-else>{{ userInfo.phoneNumber}}</span>
      </a-form-item>
      <a-form-item label="信用值" v-if="userInfo.userType==='Client'">
        <span>{{ userInfo.credit }}</span>
      </a-form-item>
      <a-form-item label="密码" v-if="modifyPwd">
        <a-input
          placeholder="请输入新密码"
          v-decorator="['password', { rules: [{ required: true, message: '请输入新密码' }, { validator: this.handlePassword }] }]"
        />
      </a-form-item>
      <a-form-item label="重复密码" v-if="modifyPwd">
        <a-input
          placeholder="请重复新密码"
          v-decorator="['passwordRepeat', { rules: [{ required: true, message: '请输入新密码' }, { validator: this.handlePasswordCheck }] }]"
        />
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modifyPwd">
        <a-button type="primary" @click="saveModifyPwd">保存</a-button>
        <a-button type="default" style="margin-left: 30px" @click="cancelModifyPwd">取消</a-button>
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-else-if="modifyInfo">
        <a-button type="primary" @click="saveModifyInfo" class="confirmModify">保存</a-button>
        <a-button type="default" style="margin-left: 30px" @click="cancelModifyInfo">取消</a-button>
      </a-form-item>
      <a-form-item :wrapper-col="{ span: 15, offset: 1 }" v-else>
        <a-button type="primary" @click="onModifyInfo" class="onModifyInfo">修改信息</a-button>
      <!-- <a-divider type="vertical"></a-divider> -->
        <a-button type="primary" @click="onModifyPwd" style="margin-left:30px">修改密码</a-button>
      </a-form-item>
    
    </a-form>
    <div class="avatarDiv">
      <uploadImg />
    </div>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import uploadImg from './uploadImg'
import { message } from 'ant-design-vue'
export default {
  name: "infoPanel",
  data() {
    return {
      modifyInfo: false,
      modifyPwd: false,
      form: this.$form.createForm(this, { name: "coordinated" })
    };
  },
  components: {uploadImg},
  computed: {
    ...mapGetters(["userId", "userInfo"])
  },
  async mounted() {
    await this.getUserInfo();
  },
  methods: {
    ...mapActions([
      "getUserInfo",
      "updateUserInfo",
      "changePwd",
      "login"
    ]),
    saveModifyInfo() {
      this.form.validateFields((err, values) => {
        if (!err) {
          const data = {
            userName: this.form.getFieldValue("userName"),
            phoneNumber: this.form.getFieldValue("phoneNumber"),
          };
          this.updateUserInfo(data).then(() => {
              console.log('用户信息修改成功')
              message.success('用户信息修改成功')
              this.getUserInfo()
            this.modifyInfo = false;
          }).catch(err => {
                console.log('用户信息修改失败')
                console.log(err)
                message.error('用户信息修改失败');
        })
        }
      });
    },
    saveModifyPwd() {
      this.form.validateFields((err, values) => {
        if (!err) {
          const data = {
            newPassword: this.form.getFieldValue("password")
          };
          this.changePwd(data).then(() => {
              console.log('密码修改成功')
              message.success('密码修改成功')
              this.login({
                  email:this.userInfo.email,
                  password:this.form.getFieldValue("password")
              })
            this.modifyPwd = false;
          }).catch(err => {
                console.log('修改密码失败')
                console.log(err)
                message.error('修改密码失败');
        })
        }
      
    })
    },
    onModifyInfo() {
        this.modifyInfo = true;
        this.$nextTick(() => {
            this.form.setFieldsValue({
              userName: this.userInfo.userName,
          phoneNumber: this.userInfo.phoneNumber
            })});
    },
    onModifyPwd() {
      this.modifyPwd = true;
    },
    cancelModifyInfo() {
      this.modifyInfo = false;
    },
    cancelModifyPwd() {
      this.modifyPwd = false;
    },
    handlePassword(rule, value, callback) {
      if (value.length < 6) {
        callback(new Error("密码长度至少6位"));
      }
      if (value.length > 20) {
        callback(new Error("密码长度至多20位"));
      }
      if(!/^[0-9a-zA-Z]*$/.test(value)){
        callback(new Error("密码只能由数字字母构成"));
      }
      callback();
    },
    handlePasswordCheck(rule, value, callback) {
      const password = this.form.getFieldValue("password");
      console.log(password);
      if (value === undefined) {
        callback(new Error("请输入密码"));
      }
      if (value && password && value.trim() !== password.trim()) {
        callback(new Error("两次密码不一致"));
      }
      callback();
    },
    handleName(rule, value, callback) {
      if (value.length <=2) {
        callback(new Error("姓名长度至少为2"));
      }
      callback();
    },
    handlePhone(rule, value, callback) {
      if (value.length!=11) {
        callback(new Error("手机号码应为11位"));
      }
      callback();
    },
  }
};
</script>
<style scoped lang="less">
.info-wrapper {
  display: flex;
  justify-content: space-between;
  width: 50%;
  .info-from {
    flex-grow: 1;
  }
}
</style>
<style lang="less">
.info-wrapper {
  .ant-tabs-bar {
    padding-left: 30px;
  }
}
</style>
<style lang="less">
</style>