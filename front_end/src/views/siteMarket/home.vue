<!-- sider高亮有问题，logo不收缩，个人头像。。 -->
<template>
  <a-layout id="components-layout-demo-side" style="min-height: 100vh">
    <a-layout-sider v-model="collapsed" collapsible :width="150">

      <a-menu theme="dark" :selected-keys="current" mode="inline" style="margin-top:50px">
        <a-menu-item key="1" @click="jumpTowebSituation">
          <a-icon type="line-chart" />
          <span>数据中心</span>
        </a-menu-item>
        <a-menu-item key="2" @click="jumpToAddCredit">
          <a-icon type="money-collect" />
          <span>信用充值</span>
        </a-menu-item>
        <a-menu-item key="3" @click="jumpToBizRegion">
          <a-icon type="environment" />
          <span>商圈管理</span>
        </a-menu-item>
        <a-menu-item key="4" @click="jumpToCoupon">
          <a-icon type="account-book" />
          <span>优惠券</span>
        </a-menu-item>
        <a-menu-item key="5" @click="jumpToUserInfo">
          <a-icon type="user" />
          <span>个人信息</span>
        </a-menu-item>
      </a-menu>
    </a-layout-sider>
    <a-layout>
      <a-layout-header class="header">
        <div class="label">
          <img src="@/assets/favicon.svg" class="logo" alt="logo" />
          <span class="title">HRMS 酒店预订管理系统</span>
        </div>
        <div class="logout">
          <a-dropdown placement="bottomCenter">
            <div class="user">
              <a-avatar :src="userInfo.url"></a-avatar>
              <span style="font-size: 14px">{{ userInfo.userName }}</span>
              <a-icon style="margin-left: 3px; font-size: 16px" type="down"></a-icon>
            </div>
            <a-menu slot="overlay">
              <a-menu-item @click="quit()"  class="outIcon">
                <a-icon type="poweroff"></a-icon>退出登录
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </div>
      </a-layout-header>
      <a-layout-content style="margin: 0 16px">
        <!-- 内容部分 -->
        <router-view></router-view>
      </a-layout-content>
      <a-layout-footer style="text-align: center">©2020 Created by Geecoder</a-layout-footer>
    </a-layout>
  </a-layout>
</template>
<script>
import router from "@/router";
import { mapGetters, mapMutations, mapActions } from "vuex";
export default {
  data() {
    return {
      collapsed: false
    };
  },
  computed: {
    ...mapGetters(["userId","userInfo"]),
    current: function() {
      let res = ["1"];
      if (this.$route.path === "/siteMarket/addCredit") {
        res = ["2"];
      } else if (this.$route.path === "/siteMarket/bizRegion") {
        res = ["3"];
      } else if (this.$route.path === "/siteMarket/coupon") {
        res = ["4"];
      } else if (this.$route.path === "/siteMarket/info") {
        res = ["5"];
      } 
      return res;
    }
  },
  mounted() {
    this.getUserInfo();  
  },
  methods: {
        ...mapActions([
            'getUserInfo',
        ]),
    jumpTowebSituation() {
      router.push("/siteMarket/webSituation");
    },
    jumpToBizRegion() {
      router.push("/siteMarket/bizRegion");
    },
    jumpToUserInfo() {
      router.push("/siteMarket/info")
    },
    jumpToCoupon() {
      router.push("/siteMarket/coupon")
    },
    jumpToAddCredit(){
        router.push("/siteMarket/addCredit")
    },
    async quit() {
      await this.$store.dispatch("logout");
      router.push(`/login?redirect=${this.$route.fullPath}`);
    },
  }
};
</script>

<style scoped lang="less">
// .ant-layout-sider-trigger{
//     width:150px;
// }
.ant-layout-content {
    height: 580px;
    overflow: auto;
}
.header {
  background: #fff;
  padding: 0;
  display: flex;
  justify-content: space-between;
  align-items: center;
  .logout {
    margin-right: 40px;
    .user {
      cursor: pointer;
      display: flex;
      align-items: center;
      span {
        margin-left: 5px;
      }
    }
  }
}

.label {
  height: 44px;
  line-height: 44px;
  vertical-align: middle;
  min-width: 400px;
  display: flex;
  align-items: center;
  .logo {
    height: 44px;
    vertical-align: top;
    margin: 0px 16px;
    border-style: none;
    cursor: pointer;
  }
  .title {
    font-size: 30px;
    color: rgba(29, 29, 29, 0.85);
    font-family: Avenir, "Helvetica Neue", Arial, Helvetica, sans-serif;
    font-weight: 600;
    position: relative;
    top: 2px;
  }
}
.ant-layout-sider {
  // background: rgba(29, 29, 29, 0.384);
  box-shadow: 0px 2px 20px 2px rgba(145, 145, 144, 0.5);
}
</style>
