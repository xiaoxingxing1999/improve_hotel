<template>
  <div class="hotelList">
    <!-- 走马灯 -->
    <a-carousel class="carousel" :autoplay="true">
      <div class="carousel-item" v-for="(img, index) in imgUrls" :key="index">
        <img :src="img" alt style="height=400px" />
      </div>
    </a-carousel>
    <!-- 搜索框 -->
    <div class="search-bar">
      <a-range-picker v-model="searchInfo.timeRange" />
      <a-input-search placeholder="输入目的地" enter-button @search="onSearch" v-model="searchInfo.name" />
    </div>
    <!-- 高级搜索 -->
    <div class="search-info">
      <a-row type="flex" justify="space-between" align="middle">
        <a-col :span="7">
          <span style="margin-right: 10px">房间类型 </span>
          <a-select
            :span="7"
            mode="multiple"
            placeholder="选择房间类型"
            :value="searchInfo.roomTypes"
            @change="onChangeRoomTypes"
          >
            <a-select-option v-for="item in filteredRoomTypes" :key="item" :value="item">{{ item }}</a-select-option>
          </a-select>
        </a-col>

        <a-col :span="7">
          <span style="margin-right: 10px">酒店服务</span>
          <a-select
            mode="multiple"
            placeholder="选择酒店服务"
            :value="searchInfo.serves"
            @change="onChangeServes"
          >
            <a-select-option v-for="item in filteredServes" :key="item" :value="item">{{ item }}</a-select-option>
          </a-select>
        </a-col>

        <a-col :span="7">
          <span style="margin-right: 10px">酒店星级</span>
          <a-select
            mode="multiple"
            placeholder="选择酒店星级"
            :value="searchInfo.stars"
            @change="onChangeStars"
          >
            <a-select-option v-for="item in filteredStars" :key="item" :value="item">{{ item }}</a-select-option>
          </a-select>
        </a-col>
      </a-row>

      <a-divider></a-divider>

      <a-row type="flex" justify="start" align="middle">
        <!-- 价格区间 -->
        <a-col :span="11"  class="select-bar">
          <span style="width:100px">价格区间</span>
          <a-slider range :min="1" :max="10000" v-model="searchInfo.priceRange" />
          <a-input v-model="searchInfo.priceRange[0]" :min="1" :max="10000" />
          <span style="margin:0px 5px">~</span>
          <a-input v-model="searchInfo.priceRange[1]" :min="1" :max="10000" />
          
        </a-col>
        <!-- 最低评分 -->
        <a-col :span="10" :offset="3" class="select-bar">
          <span>最低评分</span>
          <a-slider :min="0" :max="5" v-model="searchInfo.lowestRate" :step="0.01"/>
          <a-input-number v-model="searchInfo.lowestRate" :min="0" :max="5" />
          
        </a-col>
      </a-row>
    </div>
    <a-divider></a-divider>
    <!-- 搜索结果 -->
    <div style="align-self:flex-start;margin-left:20px;margin-bottom:30px">搜索结果：共找到 {{hotelList.length}} 家酒店</div>
    <a-spin class="search-result" :spinning="hotelListLoading">
      <div class="card-wrapper">
        <HotelCard
          :hotel="item"
          v-for="item in hotelList"
          :key="item.index"
          @click.native="jumpToDetails(item.id)"
        ></HotelCard>
        <div
          v-for="item in emptyBox"
          :key="item.name"
          class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3"
        ></div>
      </div>
      <a-pagination
        showQuickJumper
        :total="hotelList.totalElements"
        :defaultCurrent="1"
        @change="pageChange"
      ></a-pagination>
    </a-spin>
  </div>
</template>
<script>
import HotelCard from "./components/hotelCard";
import { mapGetters, mapActions, mapMutations } from "vuex";
import store from "@/store";
const moment = require("moment");
const STAR = ["One", "Two", "Three", "Four", "Five"];
export default {
  name: "home",
  components: {
    HotelCard
  },
  data() {
    return {
      emptyBox: [{ name: "box1" }, { name: "box2" }, { name: "box3" }],

      imgUrls: [
        "https://s1.ax1x.com/2020/05/28/tZrqRe.png",
        "https://s1.ax1x.com/2020/06/30/N4l0mD.png"
      ],
      searchInfo: {
        name: "",
        serves: [],
        roomTypes: [],
        priceRange: [0, 10000],
        lowestRate: 0,
        stars: [],
        timeRange: [moment(), moment().add(30, "days")]
      }
    };
  },
  async mounted() {
    await this.getHotelList();
    await this.getAllRoomType();
    await this.getAllServe();
  },
  computed: {
    ...mapGetters(["hotelList", "hotelListLoading"]),
    roomTypes: {
      get: function() {
        return this.$store.getters.roomTypes.map(e => e.name);
      },
      set: function(newValue) {
        console.log("set searchInfo.roomTypes", newValue);
        this.searchInfo.roomTypes = newValue;
      }
    },
    allServes: {
      get: function() {
        return this.$store.getters.allServes.map(e => e.name);
      },
      set: function(newValue) {
        this.searchInfo.serves = newValue;
      }
    },
    filteredServes() {
      return this.allServes.filter(o => !this.searchInfo.serves.includes(o));
    },
    filteredRoomTypes() {
      return this.roomTypes.filter(o => !this.searchInfo.roomTypes.includes(o));
    },
    filteredStars() {
      return STAR.filter(o => !this.searchInfo.stars.includes(o));
    }
  },
  methods: {
    ...mapMutations(["set_hotelListParams", "set_hotelListLoading"]),
    ...mapActions([
      "getHotelList",
      "getAllRoomType",
      "getAllServe",
      "searchHotel"
    ]),

    //搜索目的地酒店，待定，暂时获取全部
    async onSearch(value) {
      this.set_hotelListLoading(true);
      await this.searchHotel(this.searchInfo);
    },

    pageChange(page, pageSize) {
      const data = {
        pageNo: page - 1
      };
      this.set_hotelListParams(data);
      this.set_hotelListLoading(true);
      this.getHotelList();
    },
    jumpToDetails(id) {
      //编程式导航，向 history 栈添加一个新的记录，当用户点击浏览器后退按钮时，则回到之前的 URL
      this.$router.push({ name: "hotelDetail", params: { hotelId: id } });
    },
    onChangeRoomTypes(selectedItems) {
      this.searchInfo.roomTypes = selectedItems;
    },
    onChangeServes(selectedItems) {
      this.searchInfo.serves = selectedItems;
    },
    onChangeStars(selectedItems) {
      this.searchInfo.stars = selectedItems;
    }
  }
};
</script>
<style scoped lang="less">
.hotelList {
  text-align: center;
  padding: 20px;
  display: flex;
  flex-direction: column;
  .carousel {
    width: 95%;
    margin: 20px auto;
    height: 400px;
    .carousel-item {
      height: 300px;
    }
  }
  .search-bar {
    margin: -170px auto 100px auto;
    width: 50%;
    display: flex;
  }
  .search-info {
    .ant-select{
      width: 75%;
      margin-right: 3%;
    }
    .select-bar{
      display: flex;
      justify-content: space-around;
      align-items: center;
      .ant-slider{
        width: 50%;
        margin-right:5% ;
      }
      .ant-input{
        width: 20%;
      }
    }
  }
  .search-result {
    display: flex;
    flex-direction: column;

    .ant-spin-container {
      display: flex;
      flex-direction: column;
      .emptyBox {
        height: 0;
        margin: 10px 10px;
      }
      .card-wrapper {
        display: flex;
        justify-content: flex-start;
        flex-wrap: wrap;
        flex-grow: 3;
      }
      .card-wrapper .card-item {
        margin: 30px;
        position: relative;
        height: 188px;
      }
    }

    .ant-pagination {
      align-self: flex-end;
      width: 300px;
      float: right;
    }
  }
}
</style>