<template>
  <div class="coupon-item">
    <img
      class="coupon-image"
      v-if="coupon.type==0"
      src="../../../assets/coupon-targetMoney.png"
      alt
    />
    <img class="coupon-image" v-if="coupon.type==1" src="../../../assets/coupon-discount.png" alt />
    <div class="coupon-info">
      <div class="left">
        <div class="name">{{coupon.couponName}}</div>
        <!-- <div class="description"><p>{{coupon.description}}</p></div> -->
      </div>
      <div class="right">
        <div class="row">
          <div v-if="coupon.couponType!='ROOMS'" class="text">满</div>
          <div v-if="coupon.couponType!='ROOMS'" class="number">{{coupon.targetMoney}}</div>
          <div v-if="coupon.couponType!='ROOMS'" class="text">元</div>
          <div v-if="coupon.couponType=='ROOMS'" class="text">满</div>
          <div v-if="coupon.couponType=='ROOMS'" class="number">{{coupon.targetMoney}}</div>
          <div v-if="coupon.couponType=='ROOMS'" class="text">间</div>
        </div>
        <div class="row">
          <div class="text" v-if="coupon.type==0">减</div>
          <div class="number" v-if="coupon.type==0">{{coupon.discountMoney}}</div>
          <div class="text" v-if="coupon.type==1">尊享</div>
          <div class="number" v-if="coupon.type==1">{{coupon.discount*100}}</div>
          <div class="text" v-if="coupon.type==1">折</div>
        </div>
      </div>
    </div>
    <div class="no">No.{{padNumber(coupon.id,8)}}</div>
    <a-tooltip class="moreInfo">
      <template slot="title">{{coupon.description}}</template>
      <a-icon type="question-circle" />
    </a-tooltip>
  </div>
</template>
<script>
export default {
  data() {
    return {};
  },
  props: ["coupon"],
  computed: {},
  methods: {
    padNumber(num, fill) {
      let len = ("" + num).length;
      if (fill > len) {
        // 新建一个空数组，长度为所缺位数+1，利用join(0)，得到一个000...的字符串
        num = Array(fill - len + 1 || 0).join(0) + num;
      }
      return num;
    }
  }
};
</script>
<style lang="less" scoped>
.moreInfo {
  font-size: 16px;
  position: relative;
  left: -170px;
  top: -135px;
  display: block;
}
.no {
  margin-top: -25px;
  margin-left: 15px;
  font-weight: 600;
  font-size: 13px;
}
.coupon-item {
  height: 100%;
  min-width: 400px;
  max-width: 400px;
  padding: 10px 10px;
  border-radius: 10px;
}
.coupon-item:hover {
  background-color: rgb(220, 221, 221);
  opacity: 0.75;
  transition-duration: 0.3s;
}
.coupon-image {
  width: 100%;
  height: 150px;
  object-fit: fill;
  margin: 0px auto;
  border-radius: 10px;
  box-shadow: 0px 0px 10px 2px rgba(145, 145, 144, 0.5);
}
.coupon-info {
  width: 100%;
  height: 150px;
  margin-top: -150px;
  display: flex;
  justify-content: space-between;
  align-items: center;
  text-align: center;
  font-family: SimHei;
  .left {
    height: 100%;
    width: 65%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-end;
    padding-right: 10px;
    .name {
      font-size: 30px;
      font-weight: 600;
      margin-bottom: 1px;
      margin-right: 0px;
      margin-top: -15px;
      color: black;
    }
    .description {
      font-size: 18px;
      font-weight: 500;
      color: rgb(88, 87, 87);
      margin-right: 10px;

      p {
        overflow: hidden;
        width: 150px;
        text-overflow: ellipsis;
        white-space: nowrap;
        text-align: end;
      }
    }
  }
  .right {
    height: 100%;
    width: 35%;
    display: flex;
    flex-direction: column;
    justify-content: center;
    align-items: flex-start;
    padding-left: 20px;
    .text {
      font-size: 21px;
      font-weight: 500;
      color: black;
      margin-right: 5px;
      white-space: nowrap;
    }
    .number {
      font-size: 33px;
      font-weight: 500;
      color: rgb(209, 14, 14);
      margin-right: 5px;
    }
  }
}
.row {
  display: flex;
  align-items: center;
}
</style>