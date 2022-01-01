<template>
  <!-- 显示房间服务，空房情况 -->

    
  <div>
    <span style="font-size:16px">空房情况</span>
    <a-calendar :validRange="validRange">
    <div slot="dateCellRender" slot-scope="value" class="events">
      <div v-if="getRoomRemain(value)">
        <a-badge v-if="getRoomRemain(value)==totalNum" status="success" text="完全空置" />
        <a-badge v-else-if="getRoomRemain(value)>2" status="processing" text="少量入住" />
        <a-badge v-else-if="getRoomRemain(value)>0" status="warning" text="接近满员" />
        <a-badge v-else status="error" text="没有空房" />
        <span style="font-size:25px">{{getRoomRemain(value)}} </span>
        <span class="line">/</span>
        <span class="total" >{{totalNum}}</span>
      </div>
      <div v-else style="font-size:16px">
        
      </div>
      
    </div>
  </a-calendar>
  </div>

</template>
<script>
const moment = require("moment");
export default {
  data() {
    return {
      curNum1:1,
      validRange:[moment(),moment().add(90, 'days')]
    };
  },
  props: {
    curNum: {
      type: String,
      default: "1"
    },
    totalNum:{
      type: Number,
      default: 1
    }
  },
  computed: {
    now: () => moment(),
    numArray:function(){
        return this.curNum.split('|')
    }
        
    
  },
  methods: {
    getRoomRemain(value) {
      let diff=value.diff(this.now,'days');
        if(diff<0 || diff>90){
            return '';
        }else{
            return this.numArray[diff];
        }
    },
    
  }
};
</script>
<style scoped>

.hide{
  display: none;
}
.events {
  list-style: none;
  margin: 0;
  padding: 0;
  font-size: 20px;
}
.events .ant-badge-status {
  overflow: hidden;
  white-space: nowrap;
  width: 100%;
  text-overflow: ellipsis;
  font-size: 12px;
}
.notes-month {
  text-align: center;
  font-size: 28px;
}
.notes-month section {
  font-size: 28px;
}
.total{
    font-size: 15px;
    margin-top: -15px;
    display: block;
    margin-left: 40px;
}
.line{
  display: block;
    margin-top: -20px;
    margin-left: 30px;
}
</style>
<style lang="less" >

.ant-fullcalendar-disabled-cell:not(.ant-fullcalendar-today) .ant-fullcalendar-date{
  background: rgba(180, 180, 180, 0.267);
}
</style>