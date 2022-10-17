<template>
  <div>
    登录
    {{msg}}
    <el-button @click="changeMsg('登录成功！')">登录</el-button>
  </div>
  <div style="width: 400px">
    <div><h1 style="text-align: center">登 录</h1></div>
    <el-form :model="user" :rules="rules" ref="ruleFormRef" label-width="120px" size="large">
      <el-form-item label="用户名" prop="username">
        <el-input v-model="user.username" :prefix-icon="User"/>
      </el-form-item>
      <el-form-item label="密码" prop="password">
        <el-input v-model="user.password" :prefix-icon="Lock" show-password/>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" style="width: 100%" @click="Login">登 录</el-button>
      </el-form-item>
    </el-form>
  </div>
</template>

<script setup>
import {ref, reactive, getCurrentInstance} from 'vue';
import{ User,Lock } from '@element-plus/icons-vue'
import {ElMessage,ElNotification} from "element-plus";
import request from "../request.js";
import router from "../router/index.js";
const {proxy} = getCurrentInstance()


const msg = ref('登陆啊')
const changeMsg = (change) =>{
  msg.value=change  //通过这种方式来赋值  变量名change不能跟全局变量msg名字一样
}

const user = reactive({})

const rules = reactive({
  username:[
    {required: true, message: '请输入用户名', trigger: 'blur'}
  ],
  password: [
    {required: true, message:'请输入密码', trigger: 'blur'}
  ],
})
const Login = () =>{
  proxy.$refs.ruleFormRef.validate((valid)=>{

    if (valid){
      //Promise
      //往后台发请求 http://localhost:9090 /user/login
      // 后台数据格式{"code": "200","msg":"","data"：null}
      request.post('/user/login',user).then(res => {
        console.log(res)
        if (res.code === '200'){ //请求成功
          ElMessage({
            type: "success",
            message: '请求成功',
          })
          router.push("/")
        }else {
          ElMessage({
            type: "error",
            message: res.msg,
          })
        }
      })
    }else {
      ElNotification({
        type: "error",
        message: '用户名或密码错误',
      })
    }
  })
}
</script>