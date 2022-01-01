<template>
  <!-- 上传完毕后删除原图片，更新用户数据 -->
  <div class="uploadImg-container">
    <img v-if="userInfo.url" :src="userInfo.url" class="avatar-img" />
    <img v-else src="../assets/default-avater.jpg" class="avatar-img" />
    <div class="line"></div>
    <a-upload
      name="multipartFile"
      list-type="picture-card"
      class="avatar-uploader"
      :show-upload-list="false"
      :action="uploadTargetUrl"
      :data="{token}"
      :before-upload="beforeUpload"
      @change="handleChange"
    >
      <div>
        <a-icon :type="loading ? 'loading' : 'upload'" />
      </div>
    </a-upload>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import { message } from "ant-design-vue";
import { getToken } from "../utils/auth";
export default {
  data() {
    return {
      loading: false
    };
  },
  computed: {
    ...mapGetters(["userInfo"]),
    token() {
      return getToken();
    },
    uploadTargetUrl() {
      let res =
        (process.env.NODE_ENV === "production" ? "" : "http://localhost:8080") +
        "/api/oss/upload";
      console.log("uploadTargetUrl: ", res);
      return res;
    }
  },
  methods: {
    ...mapActions([
      "updateUserInfo",
      "deleteFile",
      "uploadFile",
      "getUserInfo"
    ]),
    // uploadImg(option) {
    //   var that=this
    //   const formData = new FormData();
    //   console.log(option);
    //   let reader = new FileReader(); //新建一个FileReader对象
    //   reader.readAsDataURL(option.file); //将读取的文件转换成base64格式
    //   reader.onload = function(e) {
    //     console.log(e.target.result); //转换后的文件数据存储在e.target.result中
    //     formData.append("multipartFile", e.target.result);
    //   console.log(formData.get("multipartFile"));
    //    that.uploadFile(formData);
    //   };

    // },
    handleChange(info) {
      if (info.file.status === "uploading") {
        this.loading = true;
        return;
      }
      if (info.file.status === "done") {
        console.log(info.file.response);
        this.loading = false;
        let delUrl = this.userInfo.url;
        this.updateUserInfo({ url: info.file.response.data })
          .then(() => {
            console.log("用户信息修改成功");
            message.success("用户信息修改成功");
            this.getUserInfo();
            this.modifyInfo = false;
          })
          .catch(err => {
            console.log("用户信息修改失败");
            console.log(err);
            message.error("用户信息修改失败");
          });
        this.deleteFile({ url: delUrl });
      }
    },
    beforeUpload(file) {
      const isJpgOrPng =
        file.type === "image/jpeg" || file.type === "image/png";
      if (!isJpgOrPng) {
        this.$message.error("You can only upload JPG file!");
      }
      const isLt10M = file.size / 1024 / 1024 < 10;
      if (!isLt10M) {
        this.$message.error("Image must smaller than 10MB!");
      }
      return isJpgOrPng && isLt10M;
    }
  }
};
</script>
<style lang="less">
.uploadImg-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  .line{
    height: 60px;
    width: 0.1px;
    border-right: rgb(106, 106, 116) dashed 2px;
  }
  .avatar-img {
    width: 128px;
    height: 128px;
    border-radius: 50%;
  }
  .ant-upload.ant-upload-select-picture-card{
    margin: 0px;
  }
  .avatar-uploader > .ant-upload {
    width: 32px;
    height: 32px;
    border-radius: 50%;
  }
  .ant-upload-select-picture-card i {
    font-size: 32px;
    color: #999;
  }

  .ant-upload-select-picture-card .ant-upload-text {
    margin-top: 8px;
    color: #666;
  }
}
</style>
