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
    <div class="list-upload">
      <div class="title">首页图片</div>
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
const fileFormList = ref([])
const load = () => {
  request.get("/home/load").then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.form = res.data
    }
  })
}
load()
const Save = () => {
  console.log(fileFormList.value)
  request.post("/home",fileFormList.value).then(res => {
    if (res.code === '200'){
      fileFormList.value = null
      fileList.value.forEach(value => {
        console.log(value)
        value = null;
        console.log(value)
      })
      console.log(fileList.value)
    }else {
      console.log(res)
      ElMessage.error(res.msg)
    }
  })
  request.put("/home",state.form).then(res => {
    if (res.code === '200'){
      ElMessage.success("保存成功")
      load()
    }else {
      console.log(res)
      ElMessage.error(res.msg)
    }
  })
}
const handleAvatarSuccess = (res) => {
  fileFormList.value.push(fileList.value[fileList.value.length-1].response.data)
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
.list-upload{
  margin-top: 40px;
  margin-left: 20px;
}
</style>