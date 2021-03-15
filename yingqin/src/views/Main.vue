<template>
  <div>
    <el-container>
      <el-header>
        <div class="title">
          <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b"
          >
            <el-menu-item index="1">管理员审核</el-menu-item>
            <el-submenu index="2">
              <template slot="title">个人中心</template>
              <el-menu-item index="2-1">我的资料</el-menu-item>
              <el-menu-item index="2-2">修改密码</el-menu-item>
              <el-menu-item index="2-3">退出登录</el-menu-item>
              <el-menu-item index="2-5">注销用户</el-menu-item>
              <el-submenu index="2-4">
                <template slot="title">我的申请进度</template>
                <el-menu-item index="2-4-1">正在进行中</el-menu-item>
                <el-menu-item index="2-4-2">所有项目</el-menu-item>
                <el-menu-item index="2-4-3">我可以申请的项目</el-menu-item>
              </el-submenu>
            </el-submenu>
            <el-menu-item index="3">消息中心</el-menu-item>
            <el-menu-item index="4">管理员选项</el-menu-item>
            <el-menu-item index="10">{{$route.query.userName}}</el-menu-item>
          </el-menu>
        </div>
      </el-header>
      <el-divider content-position="left">政策服务搜索引擎</el-divider>
      <el-container>
        <el-aside width="200px">
          <el-row class="tac">
            <el-col :span="60">
              <el-menu
                default-active="2"
                background-color=""
                class="el-menu-vertical-demo"
                @open="handleOpen"
                @close="handleClose"
                unique-opened="false">
                <el-submenu index="1">
                  <template slot="title">
                    <i class="el-icon-location"></i>
                    <span>导航一</span>
                  </template>
                  <el-menu-item-group>
                    <template slot="title">分组一</template>
                    <el-menu-item index="1-1">选项1</el-menu-item>
                    <el-menu-item index="1-2">选项2</el-menu-item>
                  </el-menu-item-group>
                  <el-menu-item-group title="分组2">
                    <el-menu-item index="1-3">选项3</el-menu-item>
                  </el-menu-item-group>
                  <el-submenu index="1-4">
                    <template slot="title">选项4</template>
                    <el-menu-item index="1-4-1">选项1</el-menu-item>
                  </el-submenu>
                </el-submenu>
                <el-menu-item index="2">
                  <i class="el-icon-menu"></i>
                  <span slot="title">导航二</span>
                </el-menu-item>
                <el-menu-item index="3" disabled>
                  <i class="el-icon-document"></i>
                  <span slot="title">导航三</span>
                </el-menu-item>
                <el-menu-item index="4">
                  <i class="el-icon-setting"></i>
                  <span slot="title">导航四</span>
                </el-menu-item>
              </el-menu>
            </el-col>
          </el-row>
        </el-aside>
        <el-container>
          <el-header>
            <span>政策信息</span>
          </el-header>
          <el-main>
            <el-input
              placeholder="检索信息"
              v-model="input"
              clearable
              class="search">
            </el-input>
            <el-button type="primary" icon="el-icon-search" @click="search">搜索</el-button>
            <div>
              <el-table
                v-if="sec"
                v-loading="loading"
                :data="tableData"
                style="width: 100%"
                max-height="500"
              >
                <el-table-column
                  fixed
                  prop="policy.policyName"
                  label="政策名"
                  width="200"
                  show-overflow-tooltip="true"
                >
                </el-table-column>
                <el-table-column
                  prop="policy.policyTarget"
                  label="目标人群"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="policyRoute"
                  label="文件地址"
                  width="200"
                  show-overflow-tooltip="true">
                </el-table-column>
                <el-table-column
                  prop="policySimpleInfo"
                  label="简略信息"
                  width="220"
                  show-overflow-tooltip="true">
                </el-table-column>
                <el-table-column
                  prop="policy.policyCity"
                  label="城市"
                  width="200">
                </el-table-column>
                <el-table-column
                  fixed="right"
                  label="操作"
                  width="100">
                  <template slot-scope="scope">
                    <el-button @click="handleClick(scope.row)" type="text" size="small">查看</el-button>
                    <el-button @click="handleUse(scope.row)" type="text" size="small">申请</el-button>
                  </template>
                </el-table-column>
              </el-table>
              <el-table
                v-if="!sec"
                v-loading="loading"
                :data="tableData"
                style="width: 100%"
                max-height="500">
                <el-table-column
                  fixed
                  prop="userId"
                  label="用户Id"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="userName"
                  label="用户名"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="policyId"
                  label="政策编号"
                  width="200">
                </el-table-column>
                <el-table-column
                  prop="policyName"
                  label="政策名"
                  width="220">
                </el-table-column>
                <el-table-column
                  prop="policyState"
                  label="申请状态"
                  width="200">
                </el-table-column>
                <el-table-column
                  fixed="right"
                  label="操作"
                  width="100"
                  v-if="admin">
                  <template slot-scope="scope">
                    <el-button @click="updatePro(scope.row)" type="text" size="small">修改</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </div>
            <div class="block">
              <el-pagination
                @size-change="handleSizeChange"
                @current-change="handleCurrentChange"
                :current-page.sync="currentPage3"
                :page-size="100"
                layout="prev, pager, next, jumper"
                :total="1000">
              </el-pagination>
            </div>
          </el-main>
          <el-footer></el-footer>
        </el-container>
      </el-container>
    </el-container>
    <el-dialog title="全站检索" :visible.sync="dialogFormVisible">
      <el-input v-model="quanzhan" autocomplete="off" placeholder="检索内容"></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取 消</el-button>
        <el-button type="primary" @click="allSelect">确 定</el-button>
      </div>
    </el-dialog>


    <el-dialog title="注销用户" :visible.sync="zhuxiao">
      <el-input v-model="psw" autocomplete="off" placeholder="用户密码"></el-input>
      <div slot="footer" class="dialog-footer">
        <el-button @click="zhuxiao = false">取 消</el-button>
        <el-button type="primary" @click="exitUser">确 定</el-button>
      </div>
    </el-dialog>
  </div>

</template>

<script>
export default {
  name: "Main",
  beforeRouteEnter:(to,from,next)=>{
    next(vm=>{
      vm.getData()
    })
  },
  data() {
    return {
      dialogFormVisible: false,
      quanzhan:'',
      user:{
        userId:''
      },
      psw:'',
      zhuxiao:false,
      sec:true,
      currentPage3: 1,
      activeIndex: '1',
      activeIndex2: '1',
      tableData: [],
      input:'',
      loading:true,
      page:1,
      state:'',
      admin:false
    };
  },
  methods: {
    handleSelect(key, keyPath) {
      console.log(key);
      if(key === '1'){
        this.sec=false;
        this.state='进行中';
        this.admin=true
        this.getAll();
      }


      if(key==='2-1'){
        if(this.$route.query.userId === undefined){
          const h = this.$createElement;
          this.$notify({
            title: '登录提示',
            message: h('i', {style: 'color: teal'}, '您还没有登录')
          });
        }else {
          this.$router.push({path:'/personal',query:{userId:this.$route.query.userId}})
        }

      }
      else if(key==='2-2'){
        this.$router.push({path:'/forget'});
      }else if(key ==='2-3'){
        this.$router.push({path:'/'});
      }

      if(key === '2-4-1'){
        this.sec=false
        this.admin=false
        this.state='进行中'
        this.self()
      }
      if(key==='2-4-2'){
        this.sec=false
        this.admin=false
        this.state='所有'
        this.self()
      }

      if(key==='2-4-3'){
        this.sec=false
        this.admin=false
        this.state='可申请'
        this.getData()
      }

      if(key === '4'){
        this.dialogFormVisible= true
      }

      if(key === '2-5'){
        this.zhuxiao=true
      }


    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      this.page=val;
      if(this.input==='' && !this.admin){
        this.getData();
      }else if(!this.sec || this.admin) {
        this.getAll();
      } else {
        this.search();
      }

    },
    handleOpen(key, keyPath) {
      console.log(key, keyPath);
    },
    handleClose(key, keyPath) {
      console.log(key, keyPath);
    },
    getData(){
      this.sec=true;
      this.loading=true;
        this.$axios({
          url: 'http://localhost:8083/policy/findByLogin',
          method: 'Get',
          params: {
            userId:this.$route.query.userId,
            page:this.page,
            size:5
          }
        }).then((response)=>{
          this.tableData='';
          this.tableData=response.data.data;
          this.loading=false;
        }).catch(error=>{
          console.log("获取失败");
        })
    },
    search(){
      this.sec=true;
      this.loading=true;
      this.$axios({
        url:"http://localhost:8083/policy/findByName",
        method: 'get',
        params: {
          target:this.input,
          name:this.input,
          page:this.page,
          size:5
        }
      }).then((response)=>{
        console.log(response.data);
        this.tableData='';
        this.tableData=response.data.data;
        this.loading=false;
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    self(){
      this.sec=false;
      this.loading=true;
      this.$axios({
        url:"http://localhost:8082/self/myProgress",
        method: 'get',
        params: {
          userId:this.$route.query.userId,
          state:this.state,
          page: this.page
        }
      }).then((response)=>{
        console.log(response.data);
        this.tableData='';
        this.tableData=response.data.data;
        this.loading=false;
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    allSelect(){

      this.$axios({
        url:"http://localhost:8088/Admin/add",
        params:{
          name:this.quanzhan
        }
      }).then((response)=>{
        if(response.data.code==='200'){
          this.dialogFormVisible=false;
          this.$message("检索成功");
        }else {
          this.$message("检索失败");
        }
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    exitUser(){
      this.$axios({
        url: 'http://localhost:8081/login/deleteInfo',
        method: 'delete',
        params: {
          userId:this.$route.query.userId,
          password:this.psw
        }
      }).then((response)=>{
        console.log(response.data)
        this.zhuxiao=false
        if(response.data.code==='200'){
          this.$message("删除成功")
          this.$router.push({path:"/"})
        }
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    updatePro(row){
      this.$axios({
        url: 'http://localhost:8088/Admin/updateState',
        method: 'get',
        params: {
          userId:row.userId,
          policyId:row.policyId,
          state:'进行中'
        }
      }).then((response)=>{
        console.log(response.data)
        if(response.data.code==='200'){
          this.$message("成功")
        }
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    getAll(){
      this.sec=false;
      this.loading=true;
      this.$axios({
        url:"http://localhost:8088/Admin/findAll",
        method: 'get',
        params: {
          page: this.page
        }
      }).then((response)=>{
        console.log(response.data);
        this.tableData='';
        this.tableData=response.data.data;
        this.loading=false;
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    handleClick(row) {
      console.log(row)
      window.open(row.policyRoute)
    },
    handleUse(row) {
      console.log(row.policy.policyId)
      this.$axios({
        url:"http://localhost:8082/self/applyProgress",
        params:{
          userId:this.$route.query.userId,
          policyId:row.policy.policyId
        }
      }).then((response)=>{
        console.log(response.data)
        if(response.data.code==='200'){
          this.$message("申请成功");
        }else {
          this.$message("申请失败");
        }
      }).catch(error=>{
        console.log("获取失败");
      })
      // window.open("http://ty.sxzwfw.gov.cn/icity/govservice/project?i=1&type=gr")
    }
  }
}
</script>

<style>
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-aside {

  color: #333;
  text-align: center;
  line-height: 200px;
}

.el-main {
  background-color: #E9EEF3;
  color: #333;
  text-align: center;
  line-height: 160px;
}

body > .el-container {
  margin-bottom: 40px;
}

.el-container:nth-child(5) .el-aside,
.el-container:nth-child(6) .el-aside {
  line-height: 260px;
}

.el-container:nth-child(7) .el-aside {
  line-height: 320px;
}
.title{
  width: 100%;
  height: 60px;
}
.search{
  width: 300px;
  padding-right: 50px;
}

</style>
