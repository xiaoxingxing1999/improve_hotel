<template>
  <div class="container">
    <div class="hotelInfo">
      <div class="hotelInfo-img">
        <img v-if="currentHotelInfo.url" alt="example" :src="currentHotelInfo.url" referrerpolicy="no-referrer" />
        <img v-else alt="example" src="@/assets/cover.jpeg" referrerpolicy="no-referrer" />
      </div>
      <div class="hotelInfo-text">
        <a-row type="flex" justify="start" align="middle">
          <a-col
            :span="16"
            style="font-size:30px;font-weight:600;letter-spacing:5px"
          >{{currentHotelInfo.hotelName}}</a-col>

          <a-col :span="6" :offset="2">
            <div :class="[currentHotelInfo.ordered?'green':'blue','stamp']">
              <span>{{currentHotelInfo.ordered?"到此一游":"全新体验"}}</span>
            </div>
          </a-col>
        </a-row>
        <a-row type="flex" justify="start" align="middle">
          <a-col :span="24" style="font-size:16px;">{{currentHotelInfo.hotelDescription}}</a-col>
        </a-row>
        <a-row type="flex" justify="start" align="middle">
          <a-col :span="12">
            <span class="label">星级</span>
            <span class="text">
              <a-rate :value="getHotelStar" disabled />
            </span>
          </a-col>
          <a-col :span="2">
            <span class="label" style="display:block">评分</span>
          </a-col>
          <a-col :span="10">
              
              <div style="display:flex;color:black">
                <div style="font-size:26px;font-weight:600;">{{currentHotelInfo.rate}}</div>
                <div style="font-size:24px;font-weight:600;margin:7px 0 0 5px">/</div>
                <div style="font-size:22px;font-weight:400;margin:10px 0 0 5px">5</div>
              </div>
          </a-col>
        </a-row>
        <a-row type="flex" justify="start" align="middle">
          
          <a-col :span="12">
            <span class="label">商圈</span>
            <span class="text">{{currentHotelInfo.bizRegion}}</span>
          </a-col>
          <a-col :span="2">
            <span class="label">地址</span>
          </a-col>
          <a-col :span="8">
            
            <span class="text">{{currentHotelInfo.address}}</span>
          </a-col>
          <a-col :span="2">
            <a-icon
              type="environment"
              theme="twoTone"
              style="display:block;position:relative;font-size:20px"
            />
          </a-col>
        </a-row>
        <a-row type="flex" justify="start" align="middle">
          <a-col :span="12">
            <span class="label">服务</span>
            <span
              class="text"
            >{{currentHotelInfo.hotelServe?currentHotelInfo.hotelServe.split('|').join(' '):'暂无'}}</span>
          </a-col>
          <a-col :span="3">
            <span class="label">联系经理</span>
          </a-col>
          <a-col :span="9">
            
            <span class="text">{{currentHotelInfo.phoneNum}}</span>
          </a-col>
        </a-row>
      </div>
    </div>
    <a-divider></a-divider>
    <div class="title">房间</div>
    <RoomList :rooms="currentHotelInfo.room"></RoomList>
    <a-divider></a-divider>
    <!-- 热门评论，查看全部酒店评论 -->
    <div class="title">评论</div>
    <comments :commentList="hotelComments" style="margin-left:50px" />
  </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import RoomList from "./components/roomList";
import comments from "../../component/comments";
export default {
  name: "hotelDetail",
  components: {
    RoomList,
    comments
  },
  data() {
    return {};
  },
  computed: {
    // 使用对象展开运算符将 getter （这里只有一个currentHotelInfo）混入 computed 对象中
    ...mapGetters(["currentHotelInfo", "hotelComments",'userId']),
    getHotelStar: function() {
      let starStr = this.currentHotelInfo.hotelStar;
      if (starStr == "Five") {
        return 5;
      } else if (starStr == "Four") {
        return 4;
      } else if (starStr == "Three") {
        return 3;
      }
      return 0;
    }
  },
  async mounted() {
    this.set_currentHotelId(Number(this.$route.params.hotelId));
    await this.getUserInfo();
    await this.getHotelById({
      hotelId:Number(this.$route.params.hotelId),
      userId:this.userId
    });
    
    this.getHotelComments({
      hotelId: this.currentHotelInfo.id,
      page: 0
    });
  },
  beforeRouteUpdate(to, from, next) {
    this.set_currentHotelId(Number(to.params.hotelId));
    this.getHotelById();
    next();
  },
  methods: {
    ...mapMutations(["set_currentHotelId"]),
    ...mapActions(["getHotelById", "getHotelComments",'getUserInfo'])
  }
};
</script>
<style scoped lang="less">
.ant-row-flex {
  margin-top: 20px;
}
.title {
  font-size: 25px;
  font-weight: 500;
  margin: 10px 0px;
}
.container {
  display: flex;
  flex-direction: column;
  padding: 20px;
  .hotelInfo {
    display: flex;
    justify-content: space-between;
    .hotelInfo-img {
      width: 30%;
      height: 300px;

      img {
        object-fit: cover;
        height: 100%;
        width: 100%;
        border-radius: 10px;
      }
    }
    .hotelInfo-text {
      width: 65%;
      .stamp {
        margin-top: 20px;
        transform: rotate(30deg);
        font-size: 25px;
        font-weight: 500;

        padding: 10px;

        border-radius: 25px;
        text-align: center;
      }
      .green {
        color: rgba(112, 175, 69, 0.822);
        border: solid 4px rgba(112, 175, 69, 0.822);
      }
      .blue {
        color: rgba(18, 84, 206, 0.822);
        border: solid 4px rgba(18, 84, 206, 0.822);
      }
      .label {
        font-size: 16px;
        font-weight: 600;
        margin-right: 15px;
      }
      .text {
        font-size: 16px;
      }
    }
  }
}
</style>