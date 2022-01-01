<template>

  <div class="">
<!--      充值、查看充值记录-->
<!--    <div style="width: 100%; text-align: right; margin:20px 0">-->
<!--        <input class="" type="text" v-model="Uid" placeholder="请输入客户id">-->
<!--        <input class="" type="text" v-model="credit" placeholder="请输入充值信用值">-->

<!--        <a-button type="primary" @click="addCredit_Button">-->
<!--        <a-icon type="plus" />信用充值-->
<!--      </a-button>-->
<!--    </div>-->
<!--    <div>-->
<!--     充值记录-->
<!--    </div>-->
      <div>
          <div style="width: 100%; text-align: center; margin:5px 0">
              <a-button type="primary" @click="addCredit_Button">
                  <a-icon type="plus" />信用充值
              </a-button>
          </div>
          <div style="width: 100%; text-align: center; margin:10px 0">
              客户ID：
              <input class="" type="text" style="border-radius:8px" v-model="Uid" placeholder="请输入客户id">
              <a-divider type="vertical"></a-divider>
              充值额度：
              <input class="" type="text" style="border-radius:8px" v-model="credit" placeholder="请输入充值信用值">
          </div>

      </div>
      <div>
          <a-table :columns="columns" :dataSource="dataList" :pagination="pagination">
            {{bills}}
          </a-table>

      </div>
  </div>

</template>



<script>
import { mapGetters, mapMutations,mapActions } from "vuex";
import moment from 'moment'
const columns = [
    {
        title: "订单号",
        dataIndex: "id",
    },
    {
        title: "客户ID",
        dataIndex: "uid"
    },
    {
        title: "充值信用值",
        dataIndex: "credit_t"
    },
    {
        title: "充值时间",
        dataIndex: "createTime",
        customRender: (text, row, index) => {
            return moment(text).format("YYYY-MM-D  HH:mm")
        },
        // sorter: {
        //     compare: (a, b) => b.id - a.id,
        //     multiple: 1,
        // },
    },
];

export default {
    name:'addCredit',
  data() {
    return {
        creditFormVisual:false,
        Uid:'',
        credit:'',
        columns,
        pagination: {
            pageSize: 5,//每页中显示10条数据
            showSizeChanger: true,
            pageSizeOptions: ["5","6", "7", "8"],//每页中显示的数据
        },
    };
  },
  components: {

  },
  computed: {
      ...mapGetters(["bills"]),
      dataList: function () {
          //表格展示的数据来源
          let res = [];
          let bills = this.bills;
          bills.forEach(e => {
              res.push({
                  //简略展示部分
                  id: e.id,
                  uid: e.uid,
                  mid: e.mid,
                  credit_t: e.credit,
                  createTime: e.createTime,
              });
          });
          return res
      },
  },
  async mounted() {
    await this.getAllBills();
  },
  methods: {
    ...mapActions([
      "getAllBills",
        "addCredit"
    ]),
    ...mapMutations([

    ]),

    async addCredit_Button() {
      // this.creditFormVisual=true
        console.log(this.Uid)
        console.log(this.credit)
        await this.addCredit({
            uid:this.Uid,
            credit:this.credit
        })
        this.reset_data()
    },
    reset_data(){
        this.Uid=''
        this.credit=''
    },
  }

};
</script>


<style scoped lang="less">
</style>