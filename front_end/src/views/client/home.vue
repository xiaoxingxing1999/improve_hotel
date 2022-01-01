<template>
  <div class="layout">
    <Header></Header>
    <transition name="fade-transform" mode="out-in">
        
      <div class="back">
        <router-view class="content" />
      </div>
    </transition>
  </div>
</template>
<script>
import Header from "./components/header";
export default {
  name: "layout",
  components: {
    Header
  }
};
</script>
<style scoped lang="less">
.layout {
  height: 100%;
}
.back {
  background-color: rgb(223, 236, 236);
  padding: 100px 0px 50px 0px;
  height: 100%;
  overflow: auto;
}
.content {
  width: 90%;
  margin: 0px auto;
  border-radius: 10px;
  background-color: rgb(255, 255, 255);
  box-shadow: 0px 0px 20px 2px rgba(145, 145, 144, 0.5);
}
.back::-webkit-scrollbar {
  /*滚动条整体样式*/
  width: 8px; /*高宽分别对应横竖滚动条的尺寸*/
  height: 1px;
}
.back::-webkit-scrollbar-thumb {
  /*滚动条里面小方块*/
  border-radius: 10px;
  background-color: rgb(160, 213, 235);
  background-image: -webkit-linear-gradient(
    45deg,
    rgba(255, 255, 255, 0.2) 25%,
    transparent 25%,
    transparent 50%,
    rgba(255, 255, 255, 0.2) 50%,
    rgba(255, 255, 255, 0.2) 75%,
    transparent 75%,
    transparent
  );
}
.back::-webkit-scrollbar-track {
  /*滚动条里面轨道*/
  box-shadow: inset 0 0 5px rgba(0, 0, 0, 0.2);
  background: #ededed;
  border-radius: 10px;
}
</style>