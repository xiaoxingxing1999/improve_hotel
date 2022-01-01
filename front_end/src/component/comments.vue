<template>
  <div class="comments-container">
    <div v-for="(commentInfo, index) in commentList" :key="index">
      <a-comment>
        <template slot="actions">
          <!-- 回复 -->
          <span key="comment-reply">
            <a-tooltip title="回复" @click="getCommentReply(commentInfo.id)">
              <a-icon type="message" />
              <span style="padding-left: 8px;cursor: auto">{{ commentInfo.repliesNum }}</span>
            </a-tooltip>
          </span>
        </template>
        <a slot="author" style="color:black">{{commentInfo.userName}}</a>
        <a-avatar slot="avatar" :src="commentInfo.userUrl" alt="Han Solo" />
        <p slot="content" class="content">
          <!-- <a-divider style="margin:5px 0px"></a-divider> -->
          <span>{{commentInfo.content}}</span>
        </p>
        <a-tooltip slot="datetime" :title="moment().format('YYYY-MM-DD HH:mm:ss')">
          <span style="font-weight:400">{{ moment(commentInfo.createTime).fromNow() }}</span>
          <span style="margin-bottom:10px;margin-left:20px;color:black">
            评分：
            <a-rate :default-value="commentInfo.rate" disabled />
          </span>
        </a-tooltip>
      </a-comment>
      <replies
        v-if="replyOfCommentId==commentInfo.id"
        :replies="commentReplies"
        :rid="-1"
        :cid="commentInfo.id"
        @changeReplyPage="changeReplyPage"
      />
    </div>
    <a-pagination :visible="commentList>1" v-model="currentPage" @change="changePage" show-less-items />
  </div>
</template>
<script>
import moment from "moment";
import replies from "./replies";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  data() {
    return {
      replyOfCommentId: -1,
      likes: 0,
      dislikes: 0,
      action: null,
      moment,
      currentPage:0,
    };
  },
  components: {
    replies
  },
  props: ["commentList"],
  computed: {
    ...mapGetters(["userInfo", "commentReplies"])
  },
  methods: {
    ...mapActions(["getCommentReplies"]),
    changePage(page) {
      this.currentPage = page;
      this.$emit('changePage',this.currentPage)
    },
    changeReplyPage(page){
      this.getCommentReplies({
          commentId: this.replyOfCommentId,
          page
        });
    },
    getCommentReply(id) {
      if (this.replyOfCommentId == id) {
        this.replyOfCommentId = -1;
      } else {
        this.getCommentReplies({
          commentId: id,
          page: this.currentPage
        });
        this.replyOfCommentId = id;
      }
    }
  }
};
</script>
<style lang="less" scoped>
.comments-container{
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
<style lang="less">
.ant-comment-content-author > a,
.ant-comment-content-author > span {
  font-weight: 600 !important;
  font-size: 16px !important;
  line-height: 18px;
}
.ant-comment-avatar img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
</style>