<template>
  <div style="height: 60px;line-height: 60px;display: flex;background-color: #545c64;justify-content: space-between">
    <div>
      <img src="src/assets/AmusementPark.svg" alt="" style="width: 40px;position: relative;top: 10px;left: 20px">
      <span style="margin-left: 25px;font-size: 24px;color: #fff">迪士隆欢乐世界</span>
    </div>
    <el-menu
        :default-active="$route.path"
        :default-openeds="['/reception/']"
        router
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        style="width: 50%;margin-left: 20px"
    >
      <el-menu-item index="/reception/">首页</el-menu-item>
      <el-menu-item index="/reception/facilities">乐园设施</el-menu-item>
      <el-menu-item index="/reception/map">乐园地图</el-menu-item>
      <el-menu-item index="/reception/ticket">乐园门票</el-menu-item>
      <el-sub-menu index="2">
        <template #title>须知及帮助</template>
        <el-menu-item index="2-1">帮助中心</el-menu-item>
        <el-menu-item index="2-2">游客服务</el-menu-item>
        <el-menu-item index="2-3">无障碍服务</el-menu-item>
        <el-menu-item index="2-4">联系我们</el-menu-item>
        <el-menu-item index="2-5">电子邮件</el-menu-item>
        <el-menu-item index="2-6">在线咨询</el-menu-item>
        <el-menu-item index="2-7">手机APP</el-menu-item>
        <el-sub-menu index="2-8">
          <template #title><span style="font-weight: bold">须知</span></template>
          <el-menu-item index="2-8-1">游客须知</el-menu-item>
          <el-menu-item index="2-8-2">酒店须知</el-menu-item>
          <el-menu-item index="2-8-3">公园须知</el-menu-item>
        </el-sub-menu>
        <hr style="color: #8c939d">
        <div style="color: white;width: 190px;font-size: 15px;padding-right: 20px">游览上海迪士尼度假区期间，如需咨询请致电预订服务中心：400-180-0000（中国内地）或31580000（其他国家/地区）。</div>
      </el-sub-menu>
<!--      <el-menu-item index="3" disabled>Info</el-menu-item>-->
<!--      <el-menu-item index="4">Orders</el-menu-item>-->
    </el-menu>
    <div style="flex: 1;text-align: right;padding-right: 20px;padding-top: 22px;width: 100px">
      <el-dropdown size="large" style="height: 100%;justify-content: space-between;width: 160px">
        <span style="cursor: pointer;color: #fff"><el-avatar :size="25" :src="state.user.avatar" style="position: absolute;right: 164px;top: -4px"/>{{ state.user.username }}<el-icon color="#fff"><ArrowDown /></el-icon></span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item v-if="state.user.token" @click="Logout">退出</el-dropdown-item>
            <el-dropdown-item v-if="!state.user.token" @click="showLogin">登陆</el-dropdown-item>
            <el-dropdown-item v-if="!state.user.token" @click="handleAdd">注册</el-dropdown-item>
            <el-dropdown-item v-if="state.user.token" @click="handleEdit(state.user)">个人信息</el-dropdown-item>
            <el-dropdown-item v-if="state.user.token" @click="showUpdatePassword(state.user.id)">修改密码</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

  <el-dialog v-model="dialogLoginFormVisible" title="登陆">
    <el-form :model="state.form" :rules="state.rules" ref="ruleLoginFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="用户名" prop="name" label-width="formLabelWidth">
        <el-input v-model="user.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码" prop="remark" label-width="formLabelWidth">
        <el-input v-model="user.password" autocomplete="off" placeholder="请输入密码"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogLoginFormVisible = false">取消</el-button>
        <el-button type="primary" @click="Login">登陆</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogFormPasswordVisible" title="修改密码">
    <el-form :model="state.form" :rules="state.rules" ref="rulePasswordFormRef" label-width="120px" style="width: 85%">
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

  <el-dialog v-model="dialogFormVisible" title="用户信息">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="用户名" prop="username" label-width="formLabelWidth">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="真实姓名" prop="realName" label-width="formLabelWidth">
        <el-input v-model="state.form.realName" autocomplete="off" placeholder="请输入真实姓名"/>
      </el-form-item>
      <el-form-item label="性别" prop="sex" label-width="formLabelWidth">
        <el-radio-group v-model="state.form.sex" class="ml-4">
          <el-radio label="男">男</el-radio>
          <el-radio label="女">女</el-radio>
        </el-radio-group>
      </el-form-item>
      <el-form-item label="年龄" prop="age" label-width="formLabelWidth">
        <el-input v-model="state.form.age" autocomplete="off" placeholder="请输入年龄"/>
      </el-form-item>
      <el-form-item label="电话号码" prop="phone" label-width="formLabelWidth">
        <el-input v-model="state.form.phone" autocomplete="off" placeholder="请输入电话号码"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email" label-width="formLabelWidth">
        <el-input v-model="state.form.email" autocomplete="off" placeholder="请输入邮箱"/>
      </el-form-item>
      <el-form-item label="地址" prop="address" label-width="formLabelWidth">
        <el-input type="textarea" v-model="state.form.address" autocomplete="off" placeholder="请输入地址"/>
      </el-form-item>
      <el-form-item label="头像" prop="avatar" label-width="formLabelWidth">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/user/file/userUpload?userToken=' + state.user.token"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="state.form.avatar" :src="state.form.avatar" class="avatar" style="width: 178px;height: 178px"/>
          <el-icon v-else class="avatar-uploader-icon"><Plus /></el-icon>
        </el-upload>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="Save">保存</el-button>
      </span>
    </template>
  </el-dialog>

  <el-dialog v-model="dialogFormRegister" title="注册">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="用户名" prop="username" label-width="formLabelWidth">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" label-width="formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item label="邮箱" prop="email" label-width="formLabelWidth">
        <el-input v-model="state.form.email" autocomplete="off" placeholder="请输入邮箱"/>
      </el-form-item>
    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormRegister = false">取消</el-button>
        <el-button type="primary" @click="Save">注册</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import{ ArrowDown,Plus } from '@element-plus/icons-vue'
import router from "../router/index.js";
import Cookies from "js-cookie"
import request from "../request.js";
import {ElMessage, ElNotification} from "element-plus";
import { Eleme } from '@element-plus/icons-vue'
import {getCurrentInstance, reactive, ref} from "vue";

const { proxy } = getCurrentInstance()

const dialogFormVisible = ref(false)
const dialogFormPasswordVisible = ref(false)
const dialogLoginFormVisible = ref(false)
const dialogFormRegister = ref(false)

const checkEmail = (rule, value, callback) => {
  if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
    return callback(new Error('邮箱格式错误'))
  }
  callback()
}

const user = reactive({})
const state = reactive({
  user : Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
  tableDate:[],
  form:{},
  rules :{
    username:[
      {required: true, message: '请输入用户名', trigger: 'blur'}
    ],
    email: [
      {required: true, validator: checkEmail,target: 'blur'}
    ],
    password: [
      {required: true, message:'请输入密码', trigger: 'blur'},
      {min: 5 , max: 16 , message: '最少输入五位最多输入十六位', trigger: 'blur'}
    ],
    oldPassword: [
      {required: true, message:'请输入密码', trigger: 'blur'},
      {min: 5 , max: 16 , message: '最少输入五位最多输入十六位', trigger: 'blur'}
    ],
    newPassword: [
      {required: true, message:'请输入密码', trigger: 'blur'},
      {min: 5 , max: 16 , message: '最少输入五位最多输入十六位', trigger: 'blur'}
    ],
  }
})
console.log(state.user)
const showLogin = () =>{
  dialogLoginFormVisible.value=true
}
const showUpdatePassword = (row) =>{
  dialogFormPasswordVisible.value=true
  state.form.id = row
}
const Login = () =>{
  proxy.$refs.ruleLoginFormRef.validate((valid)=>{
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
  proxy.$refs.rulePasswordFormRef.validate((valid)=>{
    if (valid){
      if (state.form.id){//如果id存在则为编辑
        request.put("/user/updatePassword",state.form).then(res => {
          if (res.code === '200'){
            ElMessage.success("保存成功")
            dialogFormVisible.value = false;
          }else {
            ElMessage.error(res.msg)
          }
        })
      }
    }
  })
}
const Logout = () =>{
  if (Cookies.get('user')){
    Cookies.remove('user')
  }
  location.reload();
}

const handleAdd = () => {
  dialogFormRegister.value= true
  state.form = {}
}

const handleEdit = (row) => {
  dialogFormVisible.value= true
  state.form = JSON.parse(JSON.stringify(row))
}

const Save = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (valid){
      if (state.form.id){//如果id存在则为编辑
        request.put("/user/userUpdate",state.form).then(res => {
          if (res.code === '200'){
            ElMessage.success("保存成功")
            dialogFormVisible.value = false;
            request.post("/user/findUserById",state.user).then(res => {
              if (res.code === '200'){
                if (res.data){
                  console.log(res.data)
                  Cookies.set('user',JSON.stringify(res.data))
                  console.log(Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {})
                  location.reload();
                }
              }else {
                ElMessage.error(res.msg)
              }
            })
          }else {
            ElMessage.error(res.msg)
          }
        })
      }else {
        console.log(state.form)
        request.post("/user/userAdd",state.form).then(res => {
          if (res.code === '200'){
            ElMessage.success("注册成功")
            dialogFormRegister.value = false;
          }else {
            console.log(res)
            ElMessage.error(res.msg)
          }
        })
      }
    }
  })
}
const handleAvatarSuccess = (res) => {
  state.form.avatar = {avatar: ''}
  console.log(res.data)
  if (res.code === '200'){
    state.form.avatar = res.data
  }
}
</script>

<style scoped>

</style>