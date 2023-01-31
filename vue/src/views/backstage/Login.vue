<template>
<!--  <div>-->
<!--    登录-->
<!--    {{msg}}-->
<!--    <el-button @click="changeMsg('登录成功！')">登录</el-button>-->
<!--  </div>-->
  <div style="width: 500px;height:400px;background-color:white;border-radius: 10px;
  margin: 150px auto; padding: 50px" >
    <div style="text-align: center;margin: 30px;font-size: 30px;font-weight: bold">游乐园管理系统后台登录</div>
    <el-form :model="user" :rules="rules" ref="ruleFormRef" size="large">
      <el-form-item prop="username">
        <el-input placeholder="请输入用户名" v-model="user.username" :prefix-icon="User"/>
      </el-form-item>
      <el-form-item prop="password">
        <el-input placeholder="请输入密码" v-model="user.password" :prefix-icon="Lock" show-password/>
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
import request from "../../request.js";
import router from "../../router/index.js";
import Cookies from "js-cookie";
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
      request.post('/admin/login',user).then(res => {
        console.log(res)
        if (res.code === '200'){ //请求成功
          ElMessage({
            type: "success",
            message: '请求成功',
          })
          if (res.data){
            Cookies.set('admin',JSON.stringify(res.data))
          }
          router.push('/backstage/')
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

<style>


</style>