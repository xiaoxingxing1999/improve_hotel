<template>
  <div><h1>用户帮助：</h1>
  <a-tabs tab-position="left" size="large">
    <a-tab-pane tab="预定酒店" key="1">
      <h2>步骤1：筛选目标酒店</h2> <br>
      <img :src="p1" width="600">
      <br>
      <a-divider></a-divider>
      <h2>步骤2：填写订单</h2><br>
      <img :src="p2" width="600">
      <br>
      <a-divider></a-divider>
      <h2>步骤3：检查核对</h2>
      <br>
      <img :src="p4" width="600">
      <br>
    </a-tab-pane>
    <a-tab-pane tab="处理订单" key="2">
      <h2>撤销和查看</h2> <br>
      <img :src="p3" width="600">
    </a-tab-pane>

  </a-tabs>
  </div>
</template>

<script>
export default {
  name: "help",
  data() {
    return {
      p1:require("../../assets/p1.png"),
      p2:require("../../assets/p2.png"),
      p3:require("../../assets/p3.png"),
      p4:require("../../assets/p4.png")
    }
  }
}
</script>

<style scoped>

</style>