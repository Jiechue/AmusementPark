<template>
  <div style="height: 60px;line-height: 60px; background-color: white;display: flex">
    <div>
      <img src="src/assets/AmusementPark.svg" alt="" style="width: 40px;position: relative;top: 10px;left: 20px">
      <span style="margin-left: 25px;font-size: 24px">游乐园管理系统</span>
    </div>
    <div style="flex: 1;text-align: right;padding-right: 20px;padding-top: 22px">
      <el-dropdown size="large" style="height: 100%;">
        <span style="cursor: pointer">{{ user.username }}<el-icon><ArrowDown /></el-icon></span>
        <template #dropdown>
          <el-dropdown-menu>
            <el-dropdown-item @click="Logout">退出</el-dropdown-item>
            <el-dropdown-item @click="UpdatePassword(user.id)">修改密码</el-dropdown-item>
          </el-dropdown-menu>
        </template>
      </el-dropdown>
    </div>
  </div>

  <el-dialog v-model="dialogFormVisible" title="修改密码">
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
        <el-button @click="dialogFormVisible = false">取消</el-button>
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
import {ElMessage} from "element-plus";
import {getCurrentInstance, reactive, ref} from "vue";
const { proxy } = getCurrentInstance()
const dialogFormVisible = ref(false)
const user = Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
const Logout = () =>{
  if (Cookies.get('admin')){
    Cookies.remove('admin')
  }
  router.push('/login')
}
const UpdatePassword = (row) => {
  dialogFormVisible.value= true
  state.form.id = row
}
const state = reactive({
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
const SavePassword = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (state.form.id){//如果id存在则为编辑
      request.put("/admin/updatePassword",state.form).then(res => {
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
          dialogFormVisible.value = false;
        }else {
          console.log(res)
          ElMessage.error(res.msg)
        }
      })
    }
  })
}
</script>

<style scoped>

</style>