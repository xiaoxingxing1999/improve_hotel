<template>
  <div class="replyComment">
<!--      查看、回复订单评价-->
<!--      <a-table :columns="columns" :dataSource="hotelComments" :pagination="pagination">-->
<!--          {{hotelComments}}-->
<!--      </a-table>-->
      <comments
        :commentList="hotelComments"
      />
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import hotelInfo from "./hotelInfo";
import comments from "../../component/comments";

export default {
  data() {
    return {

    };
  },
  components:{
      comments
  },
  computed: {
      ...mapGetters(["hotelComments","hotelInfo"]),
  },
  async mounted() {
    await this.getHotelInfo();
    await this.getHotelComments({
        hotelId:this.hotelInfo.id,
        page:0,
    })
  },
  methods: {
      ...mapActions(["getHotelComments","getHotelInfo"]),
        a(){
          console.log(this.hotelComments)
        }
  }
};
</script>
<style scoped lang="less">
.replyComment{
  padding: 30px 60px;
}
</style>