<template>
  <div style="height: 60px;line-height: 60px;display: flex;background-color: #545c64;justify-content: space-between">
    <div>
      <img src="src/assets/AmusementPark.svg" alt="" style="width: 40px;position: relative;top: 10px;left: 20px">
      <span style="margin-left: 25px;font-size: 24px;color: #fff">迪士隆欢乐世界</span>
    </div>
    <el-menu
        :default-active="$route.path"
        :default-openeds="['/reception']"
        router
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="width: 70%"
    >
      <el-menu-item index="/reception">首页</el-menu-item>
      <el-menu-item index="/reception/facilities">乐园设施</el-menu-item>
<!--      <el-menu-item index="3" disabled>Info</el-menu-item>-->
<!--      <el-menu-item index="4">Orders</el-menu-item>-->
    </el-menu>
    <div style="flex: 1;text-align: right;padding-right: 20px;padding-top: 22px;width: 100px">
      <el-dropdown size="large" style="height: 100%;justify-content: space-between">
        <span style="cursor: pointer;color: #fff"><el-avatar :size="25" :src="state.user.avatar" style="position: absolute;right: 60px;top: -4px"/>{{ state.user.username }}<el-icon color="#fff"><ArrowDown /></el-icon></span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item v-if="state.user.token" @click="Logout">退出</el-dropdown-item>
            <el-dropdown-item v-if="!state.user.token" @click="showLogin">登陆</el-dropdown-item>
            <el-dropdown-item v-if="!state.user.token" @click="">注册</el-dropdown-item>
            <el-dropdown-item v-if="state.user.token" @click="">个人信息</el-dropdown-item>
            <el-dropdown-item v-if="state.user.token" @click="showUpdatePassword(state.user.id)">修改密码</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

  <el-dialog v-model="dialogFormVisible" title="登陆">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="用户名" prop="name" label-width="formLabelWidth">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码" prop="remark" label-width="formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="Login">登陆</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogFormPasswordVisible" title="修改密码">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="旧密码" prop="oldPassowrd" label-width="formLabelWidth">
        <el-input v-model="state.form.oldPassword" autocomplete="off" placeholder="请输入旧密码"/>
      </el-form-item>
      <el-form-item label="新密码" prop="newPassword" label-width="formLabelWidth">
        <el-input v-model="state.form.newPassword" autocomplete="off" placeholder="请输入新密码"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormPasswordVisible = false">取消</el-button>
        <el-button type="primary" @click="SavePassword">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import{ ArrowDown } from '@element-plus/icons-vue'
import router from "../router/index.js";
import Cookies from "js-cookie"
import request from "../request.js";
import {ElMessage, ElNotification} from "element-plus";
import { Eleme } from '@element-plus/icons-vue'
import {getCurrentInstance, reactive, ref} from "vue";

const { proxy } = getCurrentInstance()
const dialogFormVisible = ref(false)
const dialogFormPasswordVisible = ref(false)

const state = reactive({
  user : Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
  tableDate:[],
  form:{},
  rules :{
    username:[
      {required: true, message: '请输入用户名', trigger: 'blur'}
    ],
    password: [
      {required: true, message:'请输入密码', trigger: 'blur'},
      {min: 5 , max: 16 , message: '最少输入五位最多输入十六位', trigger: 'blur'}
    ],
  }
})
console.log(state.user)
const showLogin = () =>{
  dialogFormVisible.value=true
}
const showUpdatePassword = (row) =>{
  dialogFormPasswordVisible.value=true
  state.form.id = row
}
const Login = () =>{
  proxy.$refs.ruleFormRef.validate((valid)=>{

    if (valid){
      //Promise
      //往后台发请求 http://localhost:9090 /user/login
      // 后台数据格式{"code": "200","msg":"","data"：null}
      request.post('/user/login',state.form).then(res => {
        console.log(res)
        if (res.code === '200'){ //请求成功
          ElMessage({
            type: "success",
            message: '请求成功',
          })
          if (res.data){
            Cookies.set('user',JSON.stringify(res.data))
          }
          location.reload();
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
const SavePassword = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (state.form.id){//如果id存在则为编辑
      request.put("/user/updatePassword",state.form).then(res => {
        if (res.code === '200'){
          ElMessage.success("保存成功")
          dialogFormVisible.value = false;
        }else {
          ElMessage.error(res.msg)
        }
      })
    }else {
      console.log(state.form)
      request.post("/admin",state.form).then(res => {
        if (res.code === '200'){
          ElMessage.success("保存成功")
          dialogFormPasswordVisible.value = false;
        }else {
          console.log(res)
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
const Logout = () =>{
  if (Cookies.get('user')){
    Cookies.remove('user')
  }
  location.reload();
}

</script>

<style scoped>

</style>