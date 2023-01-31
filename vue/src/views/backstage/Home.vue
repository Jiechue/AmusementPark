<template>

  <div class="container">
    <div class="login-message">欢迎登陆-{{admin.username}}</div>
    <div class="list">
      <div class="title">园区标题</div>
      <div><el-input
          v-model="state.form.title"
          :rows="3"
          type="textarea"
          placeholder="园区标题"
      /></div>
    </div>
    <div class="list">
      <div class="title">园区介绍</div>
      <div ><el-input
          v-model="state.form.description"
          :rows="3"
          type="textarea"
          placeholder="园区介绍"
      /></div>
    </div>
    <div class="list">
      <div class="title">开放时间</div>
      <div ><el-input
          v-model="state.form.opentime"
          :rows="3"
          type="textarea"
          placeholder="营业时间"
      /></div>
    </div>
    <div class="list upload">
      <el-upload
          v-model:file-list="fileList"
          :action="'http://localhost:9090/api/user/file/upload?token=' + admin.token"
          list-type="picture-card"
          :on-success="handleAvatarSuccess"
          :on-remove="handleRemove"
      >
        <el-icon><Plus /></el-icon>
      </el-upload>
    </div>
    <div class="btn">
      <el-button type="primary" @click="Save">保存</el-button>
    </div>
  </div>
</template>

<script setup>
import { EditPen,Delete,Plus,Search } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../../request.js";
import {ElMessage} from "element-plus";
import Cookies from "js-cookie";
const { proxy } = getCurrentInstance()

const admin = Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
const state = reactive({
  form: {},
  avatar:[]
})
const fileList = ref([])

const Save = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (state.form.id){//如果id存在则为编辑
      request.put("/user",state.form).then(res => {
        if (res.code === '200'){
          ElMessage.success("保存成功")
          dialogFormVisible.value = false;
          load()
        }else {
          ElMessage.error(res.msg)
        }
      })
    }else {
      console.log(state.form)
      request.post("/user",state.form).then(res => {
        if (res.code === '200'){
          ElMessage.success("保存成功")
          dialogFormVisible.value = false;
          load()
        }else {
          console.log(res)
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
const handleAvatarSuccess = (res) => {
  console.log(res.data)
  if (res.code === '200'){
    fileList.value.push(res.data)
  }
}
const handleRemove = (file,files)=>{
  console.log(file,files)
  console.log(fileList.value)
}
</script>

<style scoped>
.container{
  margin-left: 30px;
  margin-top: 30px;
  width: 1100px;
  background-color: white;
  border-radius: 20px;
  padding: 20px;
  box-shadow: 2px 2px 3px 2px rgb(0 0 0 / 10%);
}
.list{
  width: 500px;
  margin-left: 20px;
  display: inline-block;

  margin-top: 30px;
}
.login-message{
  font-size: 20px;
  margin-left: 20px;
  font-weight: bold;
}
.btn{
  margin-top: 30px;
  margin-left: 50px;
  text-align: center;
}
.btn button{
  width: 100px;
  height: 50px;
}
.title{
  margin-bottom: 5px;
  font-size: 17px;
}
</style>