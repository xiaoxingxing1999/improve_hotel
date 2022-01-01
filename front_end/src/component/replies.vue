<template>
  <div class="container">
    <div v-for="(replyInfo, index) in replies" :key="index">
      <a-comment>
        <template slot="actions">
          <!-- <span key="comment-like"> -->
          <!-- 点赞 -->
          <!-- <a-tooltip title="点赞">
              <a-icon
                type="like"
                :theme="action === 'liked' ? 'filled' : 'outlined'"
                @click="like"
              />
            </a-tooltip>
            <span style="padding-left: 8px;cursor: auto">{{ likes }}</span>
          </span>
          <a-divider type="vertical" v-if="rid==-1"></a-divider>-->
          <!-- 回复 -->
          <span key="comment-reply" v-if="rid==-1">
            <a-tooltip title="回复" @click="getSubReply(replyInfo.id)">
              <a-icon type="message" />
              <span style="padding-left: 8px;cursor: auto">{{ replyInfo.repliesNum }}</span>
            </a-tooltip>
          </span>
        </template>
        <a slot="author" style="color:black">{{replyInfo.username}}</a>
        <a-avatar slot="avatar" :src="replyInfo.url" alt="Han Solo" />
        <p slot="content" class="content">
          <span>{{replyInfo.content}}</span>
        </p>
        <a-tooltip slot="datetime" :title="moment().format('YYYY-MM-DD HH:mm:ss')">
          <span style="font-weight:400">{{ moment(replyInfo.createTime).fromNow() }}</span>
        </a-tooltip>
      </a-comment>
      <!-- 递归使用组件 -->
      <replies
        v-if="replyOfReplyId==replyInfo.id"
        :replies="subReplies"
        :rid="replyOfReplyId"
        :cid="cid"
      />
    </div>
    <!--  -->
    <p class="pStyle" v-if="replyOfReplyId==-1">回复</p>
    <a-comment v-if="replyOfReplyId==-1">
      <a-avatar slot="avatar" :src="userInfo.url" alt="Han Solo" />
      <div slot="content">
        <a-form-item>
          <a-textarea :rows="4" :value="replyText" @change="handleChange" />
        </a-form-item>
        <a-form-item>
          <a-button
            html-type="submit"
            :loading="submitting"
            type="primary"
            @click="handleSubmitReply"
          >回复</a-button>
        </a-form-item>
      </div>
    </a-comment>
  </div>
</template>
<script>
import moment from "moment";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  name: "replies",
  data() {
    return {
      replyOfReplyId: -1,
      likes: 0,
      dislikes: 0,
      action: null,
      moment,
      replyText: "",
      submitting: false,
      currentReplyPage:0,
      currentSubReplyPage:0,
    };
  },
  components: {},
  props: ["replies", "rid", "cid"],
  computed: {
    ...mapGetters(["userInfo", "subReplies"])
  },
  methods: {
    ...mapActions(["getReplyReplies", "replyComment", "getCommentReplies"]),
    changeReplyPage(page) {
      this.currentReplyPage = page;
      this.$emit('changeReplyPage',this.currentReplyPage)
    },
    changeSubReplyPage(page) {
      this.currentSubReplyPage = page;
      this.$emit('changeSubReplyPage',this.currentSubReplyPage)
    },
    async handleSubmitReply() {
      if (!this.replyText) {
        return;
      }

      if (this.rid == -1) {
        console.log("提交一级回复");
        this.submitting = true;
        await this.replyComment({
          cid: this.cid,
          rid: this.rid,
          content: this.replyText
        });
        this.getCommentReplies({
          commentId: this.cid,
          page: 0
        });
      } else {
        console.log("提交二级回复");
        this.submitting = true;
        await this.replyComment({
          cid: this.cid,
          rid: this.rid,
          content: this.replyText
        });
        this.getReplyReplies({
          replyId: this.rid,
          page: 0
        });
      }

      this.submitting = false;
      this.replyText = "";
    },
    handleChange(e) {
      this.replyText = e.target.value;
    },
    getSubReply(id) {
      if (this.replyOfReplyId == id) {
        this.replyOfReplyId = -1;
      } else {
        this.getReplyReplies({
          replyId: id,
          page: 0
        });
        this.replyOfReplyId = id;
      }
    }
  }
};
</script>
<style lang="less" scoped>
.container {
  margin-left: 40px;
}
.ant-rate {
  font-size: 16px;
}
.content {
  display: flex;
  flex-direction: column;
}
.ant-avatar {
  width: 50px;
  height: 50px;
}
</style>