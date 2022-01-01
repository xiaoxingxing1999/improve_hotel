<template>
  <a-card hoverable class="hotelCard">
    <img alt="example" v-if="!hotel.url" src="@/assets/cover.jpeg" slot="cover" referrerpolicy="no-referrer" />
    <img alt="example" v-else :src="hotel.url" slot="cover" referrerpolicy="no-referrer" />

      <a-card-meta>
        <span slot="title" style="font-size:20px;font-weight: 700;letter-spacing:3px">{{hotel.hotelName}}</span>
        <template slot="description" >
          <div style="display:flex;align-items:center;justy-content:center">
            <span style="margin:5px 20px 0 0;font-size:16px;font-weight:600">星级</span>
          <a-rate style="font-size: 15px" :value="getHotelStar" disabled allowHalf />
          </div>
          <div style="display:flex;align-items:center;justy-content:center;margin-top:20px">
            <span style="margin:5px 20px 0 0;font-size:16px;font-weight:600">评分</span>
            <div style="display:flex;color:black">
              <div style="font-size:26px;font-weight:500;">{{hotel.rate}}</div>
              <div style="font-size:24px;font-weight:500;margin:7px 0 0 5px">/</div>
              <div style="font-size:22px;font-weight:400;margin:10px 0 0 5px">5</div>
            </div>
          </div>
        </template>
      </a-card-meta>

  </a-card>
</template>
<script>
export default {
  name: "",
  props: {
    hotel: {}
  },
  data() {
    return {};
  },
  computed:{
    getHotelStar: function() {
      let starStr = this.hotel.hotelStar;
      if (starStr == "Five") {
        return 5;
      } else if (starStr == "Four") {
        return 4;
      } else if (starStr == "Three") {
        return 3;
      }else if (starStr == "Two") {
        return 2;
      }else if (starStr == "One") {
        return 1;
      }
      return 0;
    }
  },
  methods:{
    
  }
};
</script>
<style scoped lang="less">
.hotelCard {
  margin: 10px 1%;
  width: 30%;
  max-height: 200px;
  img {
    border-radius: 10px;
    height: 100%;
    object-fit: cover;
  }
  
}

</style>

<style lang="less">
.hotelCard {
  display: flex;
  border-radius: 15px;
  padding:15px;
  .ant-card-cover {
    height: 100%;
    width: 40%;
  }
  .ant-card-body {
    height: 100%;
    width: 60%;
  }
}

.ant-card-hoverable:hover {
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.4);
}
</style>