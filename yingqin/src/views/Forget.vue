<template>
  <div>
    <div class="backg">
      <img src="../assets/bg.png" width="100%" height="100%"/>
    </div>
    <div class="form">
      <div v-if="!send">
          <el-container>
            <el-header class="el-header">重置您的密码</el-header>
            <el-main class="el-main">
              <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                <el-form-item>
                    <el-input v-model="ruleForm.id" placeholder="账号"></el-input>
                </el-form-item>
                <el-form-item>
                  <div style="margin-top: 15px;">
                    <el-input placeholder="电子邮箱" v-model="ruleForm.email" class="input-with-select">
                      <el-select v-model="select" slot="prepend" placeholder="请输入">
                        <el-option label="电子邮箱" value="1"></el-option>
                      </el-select>
                      <el-button slot="append" type="success" @click="sendEmail" v-bind:disabled="isSend">发送验证码</el-button>
                    </el-input>
                  </div>
                </el-form-item>
              </el-form>
            </el-main>
            <el-footer class="el-footer">Footer</el-footer>
          </el-container>
      </div>
      <div v-if="send">
        <el-container>
          <el-header>
            <span>重置您的密码</span>
          </el-header>
          <el-main>
            <el-form :model="ruleForm" status-icon :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
              <el-form-item label="验证码" prop="code">
                <el-input type="clearable" v-model="ruleForm.code" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="新的密码" prop="pass">
                <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item label="确认密码" prop="checkPass">
                <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
                <el-button @click="resetForm('ruleForm')">重置</el-button>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: "Forget",
  data(){
    var checkId = (rule, value, callback) => {
      if (value==='') {
        this.isSend=true;
        return callback(new Error('账号不能为空'));
      }
      callback();
    };
    var checkEmail = (rule, value, callback) => {
      if (value==='') {
        return callback(new Error('邮箱不能为空'));
      }
      callback();
    };
    var checkCode = (rule, value, callback) => {
      if (value==='') {
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
    return{
      ruleForm: {
        id: '',
        email: '',
        pass: '',
        code:'',
        checkPass: ''
      },
      isSend:false,
      send:false,
      input2: '',
      input3: '',
      select: '',
      rules: {
        id: [
          { validator: checkId, trigger: 'blur' }
        ],
        email: [
          { validator: checkEmail, trigger: 'blur'}
        ],
        code: [
          { validator: checkCode, trigger: 'blur'}
        ],
        pass: [
          { validator: validatePass, trigger: 'blur' }
        ],
        checkPass: [
          { validator: validatePass2, trigger: 'blur' }
        ]
      }
    }
  },
  methods:{
    sendEmail() {
      if(this.ruleForm.id==null){
        this.isSend=true;
      }
      this.isSend=false;
      this.$axios({
        url: 'http://localhost:8081/login/sendCode',
        method: 'GET',
        params: {
          userEmail:this.ruleForm.email
        }
      }).then((response)=>{
        if(response.data){
          this.$message("发送成功")
          this.send=true;

        }
        console.log(this.send)
      }).catch(error=>{
        console.log("获取失败");
      })
    },
    submitForm(formName) {
      this.$refs[formName].validate((valid) => {
        if (valid) {
          this.$axios({
            url: 'http://localhost:8081/login/updatePassword',
            method: 'POST',
            params: {
              userId:this.ruleForm.id,
              password:this.ruleForm.pass,
              matchCode:this.ruleForm.code
            }
          }).then((response)=>{
            if(response.data.code=='200'){
              this.$router.push({path:'/'})
            }else {
              console.log(response.data)
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
    }
  }


}
</script>

<style scoped>
.el-select .el-input {
  width: 130px;
}
.input-with-select .el-input-group__prepend {
  background-color: #fff;
}
.form{
  width: 700px;
  height: 100%;
  top: 20%;
  position: absolute;
  left: 30%;
  z-index:1;
  font-family: "Helvetica Neue",Helvetica,"PingFang SC","Hiragino Sans GB","Microsoft YaHei","微软雅黑",Arial,sans-serif;
}
.backg{
  width:100%;
  height:100%;
  z-index:-1;
  position: absolute;
  top: 0%;
}
.el-header, .el-footer {
  opacity: 0.2;
  color: #333;
  text-align: center;
  line-height: 60px;
}

.el-main {
  background-color: #E9EEF3;
  opacity: 0.85;
  color: #333;
  text-align: center;
  line-height: 80px;
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
</style>
