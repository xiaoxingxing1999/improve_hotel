<template>
  <div class="webSituation-container">
    <!-- 1 -->
    <a-row :gutter="16" justify="space-between">
      <a-col :span="8">
        <a-card>
          <a-statistic title="今日入住" :value="webSituationCur.roomNumber"></a-statistic>
        </a-card>
      </a-col>
      <a-col :span="8">
        <a-card>
          <a-statistic title="在住总数" :value="webSituationCur.clientNum">
            <template #suffix>
              <div v-if="webSituationCur.clientChange>0" class="more">
                <span style="margin-right:10px">{{webSituationCur.clientChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="webSituationCur.clientChange<0" class="less">
                <span style="margin-right:10px">{{webSituationCur.clientChange}}</span>
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
          <a-statistic title="会员在住" :value="webSituationCur.vipnum">
            <template #suffix>
              <div v-if="webSituationCur.vipchange>0" class="more">
                <span style="margin-right:10px">{{webSituationCur.vipchange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="webSituationCur.vipchange<0" class="less">
                <span style="margin-right:10px">{{webSituationCur.vipchange}}</span>
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
          <a-statistic title="订单达成" :value="webSituationCur.orderDoneNum">
            <template #suffix>
              <div v-if="webSituationCur.orderDoneChange>0" class="more">
                <span style="margin-right:10px">{{webSituationCur.orderDoneChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="webSituationCur.orderDoneChange<0" class="less">
                <span style="margin-right:10px">{{webSituationCur.orderDoneChange}}</span>
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
          <a-statistic title="订单撤销/异常" :value="webSituationCur.orderBadNum">
            <template #suffix>
              <div v-if="webSituationCur.orderBadChange>0" class="more">
                <span style="margin-right:10px">{{webSituationCur.orderBadChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="webSituationCur.orderBadChange<0" class="less">
                <span style="margin-right:10px">{{webSituationCur.orderBadChange}}</span>
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
          <a-statistic title="酒店总数" :value="webSituationCur.hotelNum">
            <template #suffix>
              <div v-if="webSituationCur.hotelChange>0" class="more">
                <span style="margin-right:10px">{{webSituationCur.hotelChange}}</span>
                <a-icon type="arrow-up" />
              </div>
              <div v-else-if="webSituationCur.hotelChange<0" class="less">
                <span style="margin-right:10px">{{webSituationCur.hotelChange}}</span>
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
    <!-- 3 -->
    <a-row :gutter="16" style="margin-top:60px">
      <a-col :span="12" class="rate">
        <div class="rate-title">会员比率</div>
        <a-progress
          type="circle"
          :percent="100"
          :success-percent="vipRate"
          :format="progressFunc"
          strokeColor="rgb(197, 196, 196)"
        />
      </a-col>
      <a-col :span="12" class="rate">
        <div class="rate-title">订单完成</div>
        <a-progress
          type="circle"
          :percent="100"
          :success-percent="orderDoneRate"
          :format="progressFunc"
          strokeColor="rgb(197, 196, 196)"
        />
      </a-col>
    </a-row>
    <div class="tendency">
      
      <div class="tendency-title">趋势分析</div>
      <chart :situation="webSituations"/>
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
    ...mapGetters(["webSituations", "webSituationCur"]),
    vipRate() {
      let total = this.webSituationCur.clientNum + this.webSituationCur.vipnum;
      if (total == 0) {
        return 100;
      }
      return Number(((this.webSituationCur.vipnum / total) * 100).toFixed(2));
    },
    orderDoneRate() {
      let total =
        this.webSituationCur.orderDoneNum + this.webSituationCur.orderBadNum;
      if (total == 0) {
        return 100;
      }
      return Number(
        ((this.webSituationCur.orderDoneNum / total) * 100).toFixed(2)
      );
    }
  },
  mounted() {
    this.getWebSituation();
  },
  methods: {
    ...mapActions(["getWebSituation"]),
    progressFunc(percent, successPercent) {
      return `${successPercent}%`;
    }
  }
};
</script>
<style scoped lang="less">
.rate {
  display: flex;
  align-items: center;
  justify-content: center;
  .rate-title {
    font-size: 20px;
    margin-right: 20px;
  }
}
.ant-card {
  height: 110px;
  max-width: 350px;
}
.webSituation-container {
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

.tendency {
  width: 100%;
  .tendency-title {
    font-size: 20px;
  }
}
</style>
<style lang="less">
.webSituation-container {
  .ant-statistic > .ant-statistic-content {
    margin-left: 90px;
  }
  .ant-progress-circle {
    path:nth-child(2) {
      stroke: rgb(64, 129, 172) !important;
    }
  }
}
.ant-statistic-content-suffix {
  margin-left: 20px;
  font-size: 16px;
}
</style>