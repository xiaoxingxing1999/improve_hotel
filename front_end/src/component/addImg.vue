<template>
  <!-- 上传完毕后删除原图片，更新用户数据 -->
  <div>  
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
    <img v-if="previewImg" :src="previewImg" class="avatar-img" alt="预览图片"/>
    <img v-else-if="url" :src="url" class="avatar-img" alt="上传图片"/>
    <div v-else class="avatar-img" style="text-align:center;display: table-cell;vertical-align:middle">上传图片<a-icon :type="loading ? 'loading' : 'upload'" /> </div>

    </a-upload>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import { message } from 'ant-design-vue'
import { getToken } from '../utils/auth'
export default {
  data() {
    return {
      loading: false,
      url:''
    };
  },
  computed: {
    ...mapGetters([]),
    token(){
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
  props:{
    previewImg:{
      type:String,
      required:false,
    }
  },
  methods: {
    ...mapActions([ "uploadFile"]),
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
        let url=info.file.response.data
        this.url=url
        this.$emit("uploadImg",url)
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
<style lang="less" scoped>
.avatar-img {
  width: 128px;
  height: 128px;
  border-radius: 15px;
  border: rgb(39, 38, 37) 1px dashed;
}
/* .avatar-uploader{
    width: 32px;
  height: 32px;
  display: block;
} */
.avatar-uploader > .ant-upload {

  width: 133px;
  height: 133px;
  border-radius: 15px;
}
.ant-upload-select-picture-card i {
  font-size: 32px;
  color: #999;
}

.ant-upload-select-picture-card .ant-upload-text {
  margin-top: 8px;
  color: #666;
}
</style>
