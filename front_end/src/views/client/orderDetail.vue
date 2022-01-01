<template>
  <div class="container">
    <div class="back" @click="back" >
        <a-icon type="arrow-left" style="margin-right:5px" ></a-icon>
        <span> 返回</span>
    </div>
    
    <!-- 订单描述 -->
    
    <orderInfo :orderShown="orderShown"/>
    <!-- 订单评价 -->
    <a-divider />
    <p class="pStyle">
        <span>评价</span>
        
    </p>
    <a-comment v-if="!hasComment">
      <div slot="author" style=""><span>评分</span><a-rate style="margin-left:10px" v-model="commentRate" /></div>
      
      <a-avatar slot="avatar" :src="userInfo.url" alt="Han Solo" />
      <div slot="content" style="margin-top:20px">
        <a-form-item>
          <a-textarea :rows="4" :value="commentText" @change="handleChange" />
        </a-form-item>
        <a-form-item>
          <a-button
            html-type="submit"
            :loading="submitting"
            type="primary"
            @click="handleSubmitComment"
          >回复</a-button>
        </a-form-item>
      </div>
    </a-comment>
    <orderComment v-else :commentList="[orderComment]"></orderComment>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import orderComment from "../../component/comments";
import orderInfo from "../../component/orderInfo";
import moment from 'moment'
export default {
  components: {
    orderComment,
    orderInfo
  },
  data() {
    return {
      submitting: false,
      commentText: "",
      commentRate: 5
      //   orderShown:{}
    };
  },
  async mounted() {
    await this.getUserOrders();
  },
  computed: {
    ...mapGetters([
      "userId",
      "userInfo",
      "userOrderList",
      "orderComment",
      "commentReplies",
    ]),
    orderShown() {
      let res = this.$store.getters.orderShown(
        Number(this.$route.params.orderId)
      );
      if (res) {
        this.getOrderComment({ orderId: res.id});
      }
      return res;
    },
    hasComment: function() {
      console.log(this.orderComment);
      return JSON.stringify(this.orderComment) != "{}";
    }
  },
  //   watch:{
  //       userOrderList:function(list){
  //           this.orderShown=this.$store.getters.orderShown(Number(this.$route.params.orderId))
  //       }
  //   },

  methods: {
    ...mapActions(["comment", "getUserOrders", "getOrderComment"]),

    // onClose() {
    //   this.$emit('closeDrawer')
    // },
    async handleSubmitComment() {
      if (!this.commentText) {
        return;
      }

      this.submitting = true;
      await this.comment({
        oid: this.orderShown.id,
        url: "",
        content: this.commentText,
        rate: this.commentRate
      });
      this.submitting = false;
      this.commentText='';
      this.getOrderComment({orderId:this.orderShown.id})
    },
    handleChange(e) {
      this.commentText = e.target.value;
    },
    back() {
      this.$router.replace("../../client/orders");
    },
    
  }
};
</script>
<style scoped lang="less">
.container {
  min-height: 120%;
  padding: 20px 40px;
}
.back{
    display: flex;
    align-items: center;
    margin-bottom: 20px;
    cursor:pointer;
    width: 50px;
}
.pStyle{
    font-size: 20px;
}

</style>