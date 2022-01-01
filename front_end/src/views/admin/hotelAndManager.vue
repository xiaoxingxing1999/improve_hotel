<!-- todo：展开部分（展示酒店、工作人员详细信息），删除按钮 ，添加员工按钮（主表格里？），员工绑定酒店-->
<template>
  <div class="wrapper">
    
      
        <div style="width: 100%; text-align: right; margin:20px 0">

          <a-button type="primary" @click="addHotel">
            <a-icon type="plus" />添加酒店
          </a-button>
        </div>
        <!-- 主表格 -->
        <a-table :columns="columns" :dataSource="dataList" bordered :expandRowByClick="true">
          <!-- 按钮设置 -->
          <span slot="action" slot-scope="text, record">
            <a-button type="primary" @click="addManager(record)">修改酒店工作人员</a-button>
          </span>
          <!-- 展开部分 -->
          <div
            slot="expandedRowRender"
            slot-scope="record"
            style="margin: 0"
            align="center"       >
            <!-- {{record.hotelName}} -->
            <table  cellspacing="0" cellpadding="0">
            <tbody>
              <tr class="ant-descriptions-row">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon" >酒店名称</th>
                <td colspan="1" class="ant-descriptions-item-content toHotel">
                  <span>{{record.hotelName}}</span>                  
                </td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店电话</th>
                <td colspan="3" class="ant-descriptions-item-content">{{record.hotel.phoneNum}}</td>
              </tr>
              <tr class="ant-descriptions-row">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店星级</th>
                <td colspan="1" class="ant-descriptions-item-content">{{record.hotel.hotelStar}}</td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店评分</th>
                <td colspan="3" class="ant-descriptions-item-content">{{record.hotel.rate}}</td>
              </tr>
              
              <tr class="ant-descriptions-row">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店地区</th>
                <td colspan="1" class="ant-descriptions-item-content">{{record.hotel.bizRegion}}</td>
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店地址</th>
                <td colspan="3" class="ant-descriptions-item-content">{{record.hotel.address}}</td>
              </tr>
              <tr class="ant-descriptions-row">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店服务</th>
                <td colspan="5" class="ant-descriptions-item-content">
                  {{record.hotel.hotelServe}}
                </td>
              </tr>
              <tr class="ant-descriptions-row">
                <th class="ant-descriptions-item-label ant-descriptions-item-colon">酒店描述</th>
                <td colspan="5" class="ant-descriptions-item-content">
                  {{record.hotel.hotelDescription}}
                </td>
              </tr>
              
            </tbody>
            </table>
          </div>
        </a-table>
        
      
      <!-- 酒店信息修改 -->
      <!-- <a-tab-pane tab="酒店信息修改" key="2">

      </a-tab-pane> -->
    
    <AddManagerModal></AddManagerModal>
    <AddHotelModal :visible="hotelModalvisible" @closeHotelModal="closeHotelModal"></AddHotelModal>
    <p></p>
  </div>
</template>
<script>
import { mapGetters, mapMutations, mapActions } from "vuex";
import AddManagerModal from "./components/addManagerModal";
import AddHotelModal from "./components/addHotelModal";
const columns = [
  {
    title: "酒店ID",
    dataIndex: "hotelId"
  },
  {
    title: "酒店名称",
    dataIndex: "hotelName"
  },
  {
    title: "工作人员",
    dataIndex: "hotelManager"
  },
  {
    title: "操作",
    key: "action",
    scopedSlots: { customRender: "action" }
  }
  // {
  //     title: '用户邮箱',
  //     dataIndex: 'email',
  // },
  // {
  //     title: '用户名',
  //     dataIndex: 'userName',
  // },
  // {
  //     title: '用户密码',
  //     dataIndex: 'password',
  // },
  // {
  //     title: '用户手机号',
  //     dataIndex: 'phoneNumber',
  // },
  // {
  //     title: '信用值',
  //     dataIndex: 'credit',
  // },
];
export default {
  name: "manageHotel",
  data() {
    return {
      formLayout: "horizontal",
      pagination: {},
      columns,
      data: [],
      hotelModalvisible:false,
      form: this.$form.createForm(this, { name: "manageUser" })
    };
  },
  components: {
    AddHotelModal,
    AddManagerModal
  },
  computed: {
    ...mapGetters(["addManagerModalVisible", "managerList", "hotelList"]),
    dataList: function() {
      //表格展示的数据来源
      let res = [];
      let hotels = this.hotelList;
      hotels.forEach(e => {
        let manager = this.getManagerById(e.managerId);
        if (manager) {
          res.push({
            //简略展示部分
            hotelName: e.hotelName,
            hotelId: e.id,
            hotelManager: manager.userName,
            //展开展示部分
            hotel: e,
            manager
          });
        } else {
          res.push({
            hotelName: e.hotelName,
            hotelId: e.id,
            hotelManager: "暂无",
            hotel: e,
            manager
          });
        }
      });
      console.log("dataList");
      console.log(res);
      return res;
    }
  },
  mounted() {
    this.getManagerList();
    this.getHotelList();
  },
  methods: {
    ...mapActions(["getManagerList", "getHotelList"]),
    ...mapMutations(["set_addManagerModalVisible","set_addManagerParams"]),
    getManagerById(id) {
      let managers = this.managerList;
      let res;
      managers.forEach(e => {
        if (e.id === id) {
          res = e;
        }
      });
      console.log("getManagerById");
      console.log(res);
      if (res) {
        return res;
      } else {
        return null;
      }
    },
    addManager(record) {
        console.log(record)
        this.set_addManagerParams({hotelId:record.hotelId})
      this.set_addManagerModalVisible(true);
    },
    addHotel() {
      this.hotelModalvisible=true;
    },
    closeHotelModal(){
      this.hotelModalvisible=false;
    },
    onClick() {
      console.log("click");
    }
    // getHotelIdByName:(name)=>{
    //     const hotels=this.hotelList
    //     let res=hotels.filter(hotel => hotel.hotelName===name)
    //     console.log('getHotelIdByName : ')
    //     console.log(res)
    //     return res[0]
    // },
  }
};
</script>
<style scoped lang="less">
.wrapper {
  padding: 50px;
  .chart {
    display: flex;
    align-items: center;
    justify-content: space-between;
    margin-top: 20px;
  }
}
</style>
<style lang="less">
.wrapper {
  .ant-tabs-bar {
    padding-left: 30px;
  }
}
</style>
