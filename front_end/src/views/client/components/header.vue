<template>
  <div class="header">
    <div class="label">
      <img src="@/assets/favicon.svg" class="logo" alt="logo" @click="jumpToHome" />
      <span class="title">HRMS 酒店预订管理系统</span>
    </div>
    <a-menu :selected-keys="current" mode="horizontal" theme="light">
      <a-menu-item key="home" @click="jumpToHome" class="toHome">
        <a-icon type="home" />
首页
      </a-menu-item>
      <a-menu-item key="info" @click="jumpToUserInfo" class="toUserInfo">
        <a-icon type="user" />个人中心
      </a-menu-item>
      <a-menu-item key="order" @click="jumpToOrders" class="toOrders">
        <a-icon type="user" />
订单管理
      </a-menu-item>
      <a-menu-item key="help" @click="jumpToHelp" class="toHelp">
        <a-icon type="user" />
        用户帮助
      </a-menu-item>
    </a-menu>
    <div class="logout">
      <a-dropdown placement="bottomCenter">
        <div class="user">
          <a-avatar :src="userInfo.url"></a-avatar>
          <span style="font-size: 14px">{{ userInfo.userName }}</span>
          <a-icon style="margin-left: 3px; font-size: 16px" type="down"></a-icon>
        </div>
        <a-menu slot="overlay">
          
          <a-menu-item @click="quit()">
            <a-icon type="poweroff" class="outIcon"></a-icon>退出登录QAQ
          </a-menu-item>
        </a-menu>
      </a-dropdown>
    </div>
  </div>
</template>
<script>
import { mapGetters, mapActions, mapMutations } from "vuex";
import router from "@/router";
export default {
  name: "",
  data() {
    return {
      
    };
  },
  computed: {
    ...mapGetters(["userId", "userInfo"]),
    current:function(){
        let res=['home']
        if(this.$route.path==='/client/hotelList'){
            res=["home"]
        }else if(this.$route.path==='/client/info'){
            res=["info"]
        }else if(this.$route.path==='/client/orders'){
            res=["order"]
        }else if(this.$route.path==='/client/help'){
          res=["help"]
        }
        else if(/orderDetail/.test(this.$route.path)){
            res=["order"]
        }
        else if(/hotelDetail/.test(this.$route.path)){
            res=["home"]
        }
        return res;
    }
  },
  mounted() {
    this.getUserInfo();  
  },
  methods: {
    ...mapMutations([]),
    ...mapActions(["logout","getUserInfo"]),

    async quit() {
      await this.$store.dispatch("logout");
      router.push(`/login?redirect=${this.$route.fullPath}`);
    },
    jumpToUserInfo() {
      router.push("/client/info");
    },
    jumpToHome() {
      router.push("/client/hotelList");
    },
    jumpToOrders() {
      router.push("/client/orders");
    },
    jumpToHelp() {

      router.push("/client/help");
    }
  }
};
</script>
<style scoped lang="less">
.header {
  display: flex;
  line-height: 44px;
  height: 50px;
  width: 100%;
  padding: 5px;
  align-items: center;
  justify-content: space-between;
  min-width: 800px;
  border-bottom: 0.5px solid rgba(75, 70, 70, 0.3);
  box-shadow: 0px 2px 20px 2px rgba(145, 145, 144, 0.5);
  background-color: rgba(255, 255, 255, 1);
  z-index: 9999;
  position: absolute;
  .label {
    height: 44px;
    line-height: 44px;
    vertical-align: middle;
    min-width: 400px;

    .logo {
      height: 44px;
      vertical-align: top;
      margin-right: 16px;
      border-style: none;
      cursor: pointer;
    }

    .title {
      font-size: 33px;
      color: rgba(0, 0, 0, 0.85);
      font-family: Avenir, "Helvetica Neue", Arial, Helvetica, sans-serif;
      font-weight: 600;
      position: relative;
      top: 2px;
    }

    .search {
      width: 300px;
      margin-left: 30px;
    }
  }
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
</style>
<style lang="less">
.header {
  .ant-menu {
    background: none;
  }
}
</style>