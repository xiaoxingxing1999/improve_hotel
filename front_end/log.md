### 5.28
- 重构router&views，使用home.vue里的layout组件+<router-view>（注册在home的子路由里）构成页面
### 5.29
- Promise的catch一定要有，不然会出奇怪的问题（跳出函数体）。
- vuex的state在页面刷新后会重置，暂时用mounted时getUserInfo解决，（也可以用**localStorage**）
- menu高亮跟不上路由：设置计算属性selected-key（根据路由改变）