<template>
  <div class="form">
    <div>
      <el-container class="el-container">
        <el-header class="el-header">
          <el-menu
            :default-active="activeIndex2"
            class="el-menu-demo"
            mode="horizontal"
            @select="handleSelect"
            background-color="#545c64"
            text-color="#fff"
            active-text-color="#ffd04b">
            <el-menu-item index="1">政策查询</el-menu-item>
            <el-submenu index="2">
              <template slot="title">我的工作台</template>
              <el-menu-item index="2-1">我的申请进度查询</el-menu-item>
            </el-submenu>
            <el-menu-item index="3">消息中心</el-menu-item>
            <el-menu-item index="4">添加我的信息</el-menu-item>
          </el-menu>
        </el-header>
        <el-divider content-position="right">政策服务搜索引擎</el-divider>
        <el-main>
          <el-container>
            <el-aside width="350px">
            </el-aside>
            <el-container>
              <div class="card" v-if="!update">
                <el-main class="card">
                  <el-card class="box-card" body-style="{ padding: '5px';line-height:'30px'}">
                    <div slot="header" class="clearfix">
                      <span>个人信息</span>
                      <el-button style="float: right; padding: 3px 0" type="text">修改</el-button>
                    </div>
                    <div class="text item">
                      <el-row :gutter="20">
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <el-tag type="success">账号</el-tag>
                          </div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                          <span>{{userinfo.userId}}</span>
                          </div>
                        </el-col>
                      </el-row>


                      <el-row :gutter="20">
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <el-tag type="success">姓名</el-tag>
                          </div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <span>{{userinfo.userName}}</span>
                          </div>
                        </el-col>
                      </el-row>


                      <el-row :gutter="20">
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <el-tag type="success">常住城市</el-tag>
                          </div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <span>{{userinfo.userLocate}}</span>
                          </div>
                        </el-col>
                      </el-row>


                      <el-row :gutter="20">
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <el-tag type="success">毕业大学</el-tag>
                          </div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <span>{{ userinfo.userUniversity }}</span>
                          </div>
                        </el-col>
                      </el-row>


                      <el-row :gutter="20">
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <el-tag type="success">教育等级</el-tag>
                          </div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8"><div class="grid-content bg-purple"></div></el-col>
                        <el-col :span="8">
                          <div class="grid-content bg-purple">
                            <span>{{userinfo.userEducation}}</span>
                          </div>
                        </el-col>
                      </el-row>
                    </div>
                  </el-card>
                </el-main>
              </div>
              <div class="card" v-if="update">
                <el-main>
                  <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="身份证号" prop="ident">
                      <el-input v-model="ruleForm.ident"></el-input>
                    </el-form-item>
                    <el-form-item label="住址" prop="ident">
                      <el-input v-model="ruleForm.locate"></el-input>
                    </el-form-item>
                    <el-form-item label="我的学历" prop="region">
                      <el-select v-model="ruleForm.region" placeholder="请选择学历等级">
                        <el-option label="专科" value="zhuanke"></el-option>
                        <el-option label="本科" value="benke"></el-option>
                        <el-option label="研究生" value="yanjiusheng"></el-option>
                        <el-option label="博士" value="boshi"></el-option>
                      </el-select>
                    </el-form-item>
                    <el-form-item label="我的大学" prop="region">
                      <el-input v-model="ruleForm.university" placeholder="我的大学"></el-input>
                    </el-form-item>
                    <el-form-item label="身份证照片">
                      <el-upload
                        v-model="ruleForm.url"
                        name="pic"
                        action="http://localhost:8082/upload"
                        list-type="picture-card"
                        :on-progress="upload"
                        :on-remove="handleRemove">
                        <i class="el-icon-plus"></i>
                      </el-upload>
                    </el-form-item>
                    <el-form-item>
                      <el-button type="primary" @click="submitForm('ruleForm')">立即创建</el-button>
                      <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                  </el-form>
                </el-main>
              </div>
            </el-container>
          </el-container>

        </el-main>
        <el-divider content-position="left">政策服务搜索引擎</el-divider>
        <el-footer>
        </el-footer>
      </el-container>
    </div>
  </div>
</template>

<script>
export default {
  name: "Personal",
  data() {
    return {
      userinfo:{
        userId:'',
        userName:'',
        userLocate:'',
        userEducation:'',
        userUniversity:''
      },
      loading:false,
      activeIndex2: '1',
      update:false,
      ruleForm: {
        pic:{},
        ident: '',
        region: '',
        url:'',
        university:'',
        locate: '',
        date2: '',
        delivery: false,
        type: [],
        resource: '',
        desc: ''
      },
      dialogImageUrl: '',
      dialogVisible: false,
      rules: {
        ident: [
          {required: true, message: '请输入身份证号', trigger: 'blur'},
          {min: 18, max: 18, message: '长度为18位', trigger: 'blur'}
        ],
        region: [
          {required: true, message: '请输入', trigger: 'blur'}
        ]
      }
    }
  },
  beforeRouteEnter:(to,from,next)=>{
    next(vm=>{
      vm.getData()
    })
  },
  methods:{
    getData(){
      this.$axios({
        url:"http://localhost:8082/showInfo",
        method:'get',
        params:{
          userId: this.$route.query.userId
        }
      }).then((response)=>{
        console.log(response.data);
        if(response.data.code==='200'){
          let data=response.data.data;
          this.userinfo=data.userInfo;
          this.userinfo.userId=data.user.userId;
          this.userinfo.userName=data.user.userName;
        }else {
          alert("暂时没有信息呢")
        }

      }).catch(error=>{
        console.log("获取失败");
      })
    },
    handleSelect(key, keyPath) {
      if(key === '4'){
        if(this.userinfo.userEducation!=null){
          this.$message("无须完善信息")
        }else {
          this.update=true;
        }

      }
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            url:"http://localhost:8082/upload",
            params:{
              file:this.ruleForm.pic,
              userNum:this.$route.query.userId,
              userIdent:this.ruleForm.ident,
              userEducation:this.ruleForm.region,
              userLocate:this.ruleForm.locate,
              userUniversity:this.ruleForm.university
            },
            method:"post",
          }).then((response)=>{
            console.log(response.data);
          }).catch(error=>{
            console.log("cuowu")
          })
          console.log("url"+this.dialogImageUrl)
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    handleRemove(file, fileList) {
      console.log(file);
    },
    upload(event, file, fileList) {
      this.ruleForm.url=file.name;
      this.ruleForm.pic=file;
      this.dialogVisible = true;
    }
  }
}
</script>

<style scoped>
.form{
  width: 100%;
  height: 100%;
  position: absolute;
  top: 0%;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-container{

}
.el-main {
  background-image: url("../assets/bg.png");
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
.text {
  font-size: 14px;
  line-height: 60px;
}

.item {
  margin-bottom: 18px;
}
.clearfix{
  line-height: 60px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both
}

.box-card {
  width: 650px;
}
.card{
  background-color: black;
}
.demo-ruleForm{
  width: 650px;
}
</style>
