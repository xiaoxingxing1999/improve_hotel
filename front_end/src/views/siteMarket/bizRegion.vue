<template>
  <div class="addBizregion">
      <div class="input-Container">
          
          <div style="width: 300px; text-align: center;">
              商圈名称：
              <input class="a" type="text" style="border-radius:8px;height:100%" v-model="bizRegion_Name" placeholder="  请输入商圈名称">
          </div>
          <div style="width: 300px; text-align: center; ">
              <a-button type="primary" @click="addBizRegion_Button">
                  <a-icon type="plus" />添加商圈 
              </a-button>
          </div>
      </div>
      <div>
          <a-table :columns="columns" :dataSource="bizRegions" :pagination="pagination">
              {{bizRegions}}
          </a-table>

      </div>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import moment from 'moment'
import { message } from "ant-design-vue";
const columns = [
    {
        title: "商圈ID",
        dataIndex: "id",
    },
    {
        title: "商圈名称",
        dataIndex: "name"
    },
    {
        title: "创建时间",
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
    name:'bizRegion',
  data() {
    return {
        bizRegion_Name:"",
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
      ...mapGetters(["bizRegions"]),
  },
  async mounted() {
        await this.getBizRegions()
  },
  methods: {
      ...mapActions([
         "addBizRegion",
          "getBizRegions"
      ]),
      async addBizRegion_Button() {
          console.log(this.bizRegion_Name)
          if(!this.bizRegion_Name){
              message.error('商圈名不能为空')
              return
          }
          await this.addBizRegion({
              name:this.bizRegion_Name
          })
          this.reset_data()
      },
      reset_data(){
          this.bizRegion_Name=""
      },
  }
};
</script>
<style scoped lang="less">
.input-Container{
    display: flex;
    justify-content: center;
    margin: 30px 0px;
}
</style>