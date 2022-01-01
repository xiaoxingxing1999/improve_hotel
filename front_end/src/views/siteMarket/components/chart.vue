<template>
  <div class="chart-container">
    
    <div id="myChart"></div>
    <a-select default-value="clientNum" style="width: 120px;margin-top:20px" @change="handleChange">
      <a-select-option v-for="(option,key) in showOptions" :key="key" :value="key">{{option}}</a-select-option>
    </a-select>
    
  </div>
</template>
<script>
import moment from "moment";
export default {
  data() {
    return {
      showOptions: {
        clientNum: "今日入住",
        orderDoneNum: "订单达成",
        orderBadNum: "订单撤销/异常",
        roomNumber: "在住总数",
        hotelNum: "酒店总数",
        vipnum: "会员在住"
      },
      selectedOpt: "clientNum"
    };
  },
  props: ["situation"],
  mounted() {
    this.drawLine();
  },
  computed: {
    times() {
      let res = this.situation.map(s => this.transDateFrom(s.today));
      return res;
    },
    clientNums() {
      return this.situation.map(s => s.clientNum);
    },
    orderDoneNums() {
      return this.situation.map(s => s.orderDoneNum);
    },
    orderBadNums() {
      return this.situation.map(s => s.orderBadNum);
    },
    roomNumbers() {
      return this.situation.map(s => s.roomNumber);
    },
    hotelNums() {
      return this.situation.map(s => s.hotelNum);
    },
    vipnums() {
      return this.situation.map(s => s.vipnum);
    },
    nums() {
      return {
        clientNum: this.clientNums,
        orderDoneNum: this.orderDoneNums,
        orderBadNum: this.orderBadNums,
        roomNumber: this.roomNumbers,
        hotelNum: this.hotelNums,
        vipnum: this.vipnums
      };
    }
  },
  methods: {
    transDateFrom(date) {
      return moment(date).format("YYYY-MM-D, HH:mm");
    },
    handleChange(val) {
      this.selectedOpt = val;
      this.drawLine();
      console.log('showData: ',this.nums[this.selectedOpt]);
    },
    drawLine() {
      this.chartLine = this.$echarts.init(document.getElementById("myChart"));

      // 指定图表的配置项和数据
      var option = {
        tooltip: {
          //设置tip提示
          trigger: "axis"
        },

        // legend: {
        //   //设置区分（哪条线属于什么）
        //   data: ["平均成绩", "学生成绩"]
        // },
        // color: ["#8AE09F", "#FA6F53"], //设置区分（每条线是什么颜色，和 legend 一一对应）
        xAxis: {
          //设置x轴
          type: "category",
          boundaryGap: false, //坐标轴两边不留白
          data: this.times,
          name: "日期", //X轴 name
          nameTextStyle: {
            //坐标轴名称的文字样式
            color: "rgb(48, 48, 51)",
            fontSize: 16,
            padding: [0, 0, 0, 20]
          },
          axisLine: {
            //坐标轴轴线相关设置。
            lineStyle: {
              color: "rgb(48, 48, 51)"
            }
          }
        },
        yAxis: {
          name: this.showOptions[this.selectedOpt],
          nameTextStyle: {
            color: "rgb(48, 48, 51)",
            fontSize: 16,
            padding: [0, 0, 10, 0]
          },
          axisLine: {
            lineStyle: {
              color: "rgb(48, 48, 51)"
            }
          },
          type: "value"
        },
        series: [
          {
            name: this.showOptions[this.selectedOpt],
            data: this.nums[this.selectedOpt],
            type: "line", // 类型为折线图
            lineStyle: {
              // 线条样式 => 必须使用normal属性
              normal: {
                color: "rgb(85, 92, 192)"
              }
            }
          }
        ]
      };

      // 使用刚指定的配置项和数据显示图表。
      this.chartLine.setOption(option);
    }
  }
};
</script>
<style lang="less" scoped>
#myChart {
  height: 300px;
  width: 80%;
  color: rgb(48, 48, 51);
}
.chart-container{
    width: 100%;
    display: flex;
}
</style>