<template>
  <div>
    <el-container>
        <el-header>
            <el-header>
              <span>{{title}}</span>
              <el-link type="danger" v-if="!isOk"><router-link to="/register">还没注册？</router-link></el-link>
              <el-link type="danger" v-if="isOk"><router-link to="/forget">忘记密码？</router-link></el-link>
            </el-header>
        </el-header>
      <el-divider content-position="center">政策服务搜索引擎</el-divider>
      <el-main>
        <div class="login-box" v-loading="loading">
          <div class="demo-image">
            <div class="block" v-for="fit in fits" :key="fit">
              <el-image
                style="width: 100px; height: 100px"
                :src="url"
                :fit="fit"></el-image>
            </div>
          </div>
          <div style="margin: 20px;"></div>
          <el-form :label-position="labelPosition" label-width="80px" :model="formLabelAlign">
            <el-form-item label="用户名">
              <el-input v-model="user.userId" clearable placeholder="用户名"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input placeholder="请输入密码" v-model="user.password" show-password></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="success" v-on:click="login" class="login-button">
                登陆
              </el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-main>
    </el-container>
  </div>

</template>

<script>
export default {
  data() {
    return {
      title:'请先登录哦',
      user:{
        userId:'',
        password: ''
      },
      isOk:false,
      input: '',
      fits: ['contain'],
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      loading:false
    }
  },
  methods:{
    login() {
      this.loading=true
      this.$axios({
        url: 'http://localhost:8081/login/userLogin',
        method: 'post',
        params: {
          userId:this.user.userId,
          password: this.user.password
        },
        data: {

        },
      }).then((response)=>{
        console.log(response.data);
        let code=response.data.code
        if(code==='200'){
          this.$router.push({path:"/main",query:{userId:this.user.userId,userName:response.data.data.userName}});
        }else if(code==='401') {
          this.title='检查您的用户名和密码'
          this.isOk=true;
          this.loading=false;
        }else {

        }
      }).catch(error=>{
        this.title='没联网呢'
        this.loading=false;
      })
    },
    open4() {
      this.$message.error('错了哦，这是一条错误消息');
    }
  }


}
</script>


<style lang="scss" scoped>
.login-box{
  border: 1px solid #DCDFE6;
  width: 350px;
  margin:40px auto;
  padding:35px 35px 15px 35px;
  border-radius: 5px;
  -webkit-border-radius: 5px;
  -moz-border-radius: 5px;
  box-shadow:0 0 25px #909399;
}

.login-title{
  text-align:center;
  margin:0 auto 40px auto;
  color:#303133;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-main {
  background-image: url("../assets/bg.png");
  color: #333;
  text-align: center;
  line-height: 160px;
}
.el-container {
  margin-bottom: 40px;
}
.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
}
.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;
}
.block {
  padding-bottom: 50px;
  height: 60px;
}
.login-button{
  position: absolute;
  left: 27%;
}
</style>
