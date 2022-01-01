<template>
  <div class="container">
    <!-- 1 -->
    <a-row :gutter="16" justify="space-between">
      <a-col :span="8">
        <a-card>
          <a-statistic title="今日入住" :value="hotelSituationCur.roomNumber"></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic title="在住总数" :value="hotelSituationCur.clientNum">
            <template #suffix>
              <div v-if="hotelSituationCur.clientChange>0" class="more">
                <span style="margin-right:10px">{{hotelSituationCur.clientChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="hotelSituationCur.clientChange<0" class="less">
                <span style="margin-right:10px">{{hotelSituationCur.clientChange}}</span>
                <a-icon type="arrow-down" />
              </div>
              <div v-else class="norm">
                <span style="font-size:30px">-</span>
              </div>
            </template>
          </a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic title="会员在住" :value="hotelSituationCur.vipnum">
            <template #suffix>
              <div v-if="hotelSituationCur.vipchange>0" class="more">
                <span style="margin-right:10px">{{hotelSituationCur.vipchange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="hotelSituationCur.vipchange<0" class="less">
                <span style="margin-right:10px">{{hotelSituationCur.vipchange}}</span>
                <a-icon type="arrow-down" />
              </div>
              <div v-else class="norm">
                <span style="font-size:30px">-</span>
              </div>
            </template>
          </a-statistic>
        </a-card>
      </a-col>
    </a-row>
    <!-- 2 -->
    <a-row :gutter="16">
      <a-col :span="8">
        <a-card>
          <a-statistic title="订单达成" :value="hotelSituationCur.orderDoneNum">
            <template #suffix>
              <div v-if="hotelSituationCur.orderDoneChange>0" class="more">
                <span style="margin-right:10px">{{hotelSituationCur.orderDoneChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="hotelSituationCur.orderDoneChange<0" class="less">
                <span style="margin-right:10px">{{hotelSituationCur.orderDoneChange}}</span>
                <a-icon type="arrow-down" />
              </div>
              <div v-else class="norm">
                <span style="font-size:30px">-</span>
              </div>
            </template>
          </a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic title="订单撤销/异常" :value="hotelSituationCur.orderBadNum">
            <template #suffix>
              <div v-if="hotelSituationCur.orderBadChange>0" class="more">
                <span style="margin-right:10px">{{hotelSituationCur.orderBadChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="hotelSituationCur.orderBadChange<0" class="less">
                <span style="margin-right:10px">{{hotelSituationCur.orderBadChange}}</span>
                <a-icon type="arrow-down" />
              </div>
              <div v-else class="norm">
                <span style="font-size:30px">-</span>
              </div>
            </template>
          </a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic title="房间总数" :value="hotelSituationCur.roomTotalNumber"></a-statistic>
        </a-card>
      </a-col>
    </a-row>
    <!-- 3 -->
    <a-row :gutter="16" style="margin-top:60px">
      <a-col :span="12" class="rate">
        <div class="rate-title">房间空置</div>
        <a-progress type="circle" :percent="roomVacancy" :format="percent => `${percent}%`" />
      </a-col>
      <a-col :span="12" class="rate">
        <div class="rate-title">订单完成</div>
        <a-progress type="circle" :percent="orderDoneRate" :format="percent => `${percent}%`" />
      </a-col>
    </a-row>
    <!-- 趋势分析 -->
    <a-divider/>
    <div class="tendency">
      
      <div class="tendency-title">趋势分析</div>
      <chart :situation="hotelSituations"/>
    </div>
  </div>
</template>

<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import chart from './components/chart'
export default {
  data() {
    return {};
  },
  components: {chart},
  computed: {
    ...mapGetters(["hotelSituationCur","rooms","hotelSituations"]),
    roomVacancy(){
      let total=0
      this.rooms.forEach(room=>{
        total+=room.validPeople*room.total
      })
      if(total==0){
        return 100
      }
      return Number((100-this.hotelSituationCur.clientNum/total*100).toFixed(2))
    },
    orderDoneRate(){
      let total=this.hotelSituationCur.orderDoneNum+this.hotelSituationCur.orderBadNum
      if(total==0){
        return 100
      }
      return Number((this.hotelSituationCur.orderDoneNum/total*100).toFixed(2))
    }
  },
  mounted() {
    this.getHotelSituation();
  },
  methods: {
    ...mapActions(["getHotelSituation"])
  }
};
</script>
<style scoped lang="less">
.rate{
  display: flex;
  align-items: center;
  justify-content: center;
  .rate-title{
    font-size: 20px;
    margin-right: 20px;
  }
}
.ant-card{
  height: 110px;
  max-width: 350px;
}
.container {
  padding: 30px;
  min-height: 100%;
  overflow: auto;
  .ant-row {
    margin: 20px 0;
  }
}

.more {
  color: rgb(119, 194, 76);
}
.less {
  color: rgb(187, 40, 20);
}
.norm {
}
.ant-statistic-content {
  margin-left: 70px;
}
.tendency{
  width: 100%;
  .tendency-title{
    font-size: 20px;
  }
}
</style>
<style lang="less">

.ant-statistic-content-suffix {
  margin-left: 20px;
  font-size: 16px;
}
</style>