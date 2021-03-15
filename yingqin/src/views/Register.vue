<template>
  <div>
    <div class="backg">
      <img src="../assets/bg.png" width="100%" height="100%" model="fit"/>
    </div>
    <el-container class="el-container">
      <div class="form">
        <el-container class="el-container">
<!--          <el-divider content-position="right">小杨的课设</el-divider>-->
          <el-main class="el-main">
            <div class="demo-image">
              <div class="block" v-for="fit in fits" :key="fit">
                <el-image
                  style="width: 100px; height: 100px"
                  :src="url"
                  :fit="fit"></el-image>
              </div>
            </div>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="账号" prop="id">
                <el-input type="clearable" v-model="ruleForm.id" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="用户名" prop="name">
                <el-input type="clearable" v-model="ruleForm.name" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="邮箱" prop="email">
                <el-input type="clearable" v-model="ruleForm.email" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="验证码" prop="code">
                <el-input type="clearable" v-model="ruleForm.code" autocomplete="off"></el-input>
                <div>
                  <el-button type="primary" class="code" @click="sendCode">验证码</el-button>
                </div>

              </el-form-item>
              <el-form-item label="密码" prop="pass">
                <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <el-input type="clearable" v-model="ruleForm.sex" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="年龄">
                <el-input type="clearable" v-model="ruleForm.age" autocomplete="off"></el-input>
              </el-form-item>

              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
      </div>
    </el-container>

  </div>

</template>

<script>
export default {
  name: "Register",
  data() {
    var checkId = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('账号不能为空'));
      }
      callback();
    };
    var checkName = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('用户名不能为空'));
      }
      callback();
    };
    var checkEmail = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('邮箱不能为空'));
      }
      callback();
    };
    var checkCode = (rule, value, callback) => {
      if (!value) {
        return callback(new Error('验证码不能为空'));
      }
      callback();
    };
    var validatePass = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请输入密码'));
      } else {
        if (this.ruleForm.checkPass !== '') {
          this.$refs.ruleForm.validateField('checkPass');
        }
        callback();
      }
    };
    var validatePass2 = (rule, value, callback) => {
      if (value === '') {
        callback(new Error('请再次输入密码'));
      } else if (value !== this.ruleForm.pass) {
        callback(new Error('两次输入密码不一致!'));
      } else {
        callback();
      }
    };
    return {
      fits: ['contain'],
      url: 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg',
      ruleForm: {
        id:'',
        name:'',
        email: '',
        code:'',
        pass: '',
        checkPass: '',
        sex:'',
        age:''
      },
      rules: {
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ],
        id: [
          { validator: checkId, trigger: 'blur'}
        ],
        name: [
          { validator: checkName,trigger: 'blur'}
        ],
        email: [
          { validator: checkEmail,trigger: 'blur'}
        ],
        code: [
          { validator: checkCode, trigger: 'blur'}
        ]
      }
    };
  },
  methods: {
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            url: 'http://localhost:8081/register/registUser',
            method: 'POST',
            params: {
              userId:this.ruleForm.id,
              password:this.ruleForm.pass,
              userName:this.ruleForm.name,
              userEmail:this.ruleForm.email,
              code:this.ruleForm.code,
              sex:this.ruleForm.sex,
              userAge:this.ruleForm.age
            }
          }).then((response)=>{
            console.log(response.data)
            if(response.data.code==='200'){
              this.$router.push({path:'/'})
            }
          }).catch(error=>{
            console.log("获取失败");
          })
        } else {
          console.log('error submit!!');
          return false;
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },
    sendCode() {
      this.$axios({
        url: 'http://localhost:8081/login/sendCode',
        method: 'GET',
        params: {
          userEmail:this.ruleForm.email
        }
      }).then((response)=>{
        this.$message("发送成功")
        console.log(true)
      }).catch(error=>{
        console.log("获取失败");
      })
    }
  },

  // beforeRouteEnter:(to,from,next)=>{
  //   // document.querySelector('body').setAttribute('style', 'background-image: url("../assets/bg.png")');
  //   next()
  // }
}
</script>

<style scoped>
.form{
  width: 700px;
  height: 100%;
  top: 0%;
  position: absolute;
  left: 30%;
  z-index:1;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
.el-header, .el-footer {
  background-color: #B3C0D1;
  color: #333;
  text-align: center;
  line-height: 60px;
}
.el-main {
  background-color: #eff2f5;
  opacity: 0.85;
  color: #333;
  text-align: center;
  line-height: 130px;
}
.el-container {
  margin-bottom: 20px;
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
.backg{
  width:100%;
  height:100%;
  z-index:-1;
  position: absolute;
  top: 0%;
}
.demo-image{
  height: 100px;
}
el-input{
  width: 60px;
}
.code{
  position: absolute;
  right: 0.2%;
  top: 4.8%;
}
</style>
