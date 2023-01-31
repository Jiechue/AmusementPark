<template>
  <div>
    <el-input style="width: 260px; margin-left: 10px" v-model="username" placeholder="请输入用户名" clearable></el-input>

    <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
    <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;">
    <el-table-column prop="id" label="ID" width="180"/>
    <el-table-column prop="username" label="用户名"/>
    <el-table-column prop="password" label="密码"/>
    <el-table-column prop="power" label="权限"/>
    <el-table-column width="200px" label="操作">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleEdit(scope.row)"><el-icon><EditPen/></el-icon>编辑</el-button>
        <el-popconfirm title="确定删除吗？" @confirm="deleteRow(scope.row.id)" confirm-button-text="确定" cancel-button-text="取消">
          <template #reference>
            <el-button link type="danger" size="small"><el-icon><Delete/></el-icon>删除</el-button>
          </template>
        </el-popconfirm>
      </template>
    </el-table-column>
  </el-table>

  <div style="margin: 10px 0">
    <el-pagination
        background
        v-model:currentPage="currentPage"
        v-model:page-size="pageSize"
        :page-sizes="[100, 200, 300, 400]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

  <el-dialog v-model="dialogFormVisible" title="用户信息">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="用户名" prop="username" label-width="formLabelWidth">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" label-width="formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码"/>
      </el-form-item>
      <el-form-item label="权限" prop="power" label-width="formLabelWidth">
        <el-select v-model="state.form.power" placeholder="Select">
          <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value"
              :disabled="item.disabled"
          />
        </el-select>
      </el-form-item>

    </el-form>
    <template #footer>
      <span class="dialog-footer">
        <el-button @click="dialogFormVisible = false">取消</el-button>
        <el-button type="primary" @click="Save">保存</el-button>
      </span>
    </template>
  </el-dialog>
</template>

<script setup>
import { EditPen,Delete,Plus,Search } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../../request.js";
import {ElMessage} from "element-plus";
import Cookies from "js-cookie";
const { proxy } = getCurrentInstance()

const dialogFormVisible = ref(false)

const checkEmail = (rule, value, callback) => {
  if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
    return callback(new Error('邮箱格式错误'))
  }
  callback()
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

const currentPage = ref(1)
const pageSize = ref(100)
const total = ref(0)
const username = ref('')

const load = () => {
  request.get("/admin/page",{
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      username: username.value,
    }
  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.tableDate = res.data.list
      total.value = res.data.total
    }
  })
}
load()
const handleSizeChange = (val) => {
  pageSize.value = val;
  load()
}
const handleCurrentChange = (val) => {
  currentPage.value = val
  load()
}

const handleAdd = () => {
  dialogFormVisible.value= true
  state.form = {}
}

const handleEdit = (row) => {
  dialogFormVisible.value= true
  state.form = JSON.parse(JSON.stringify(row))
}
const handleAvatarSuccess = (res) => {
  if (res.code === '200'){
    state.form.avatar = res.data
  }
}

const Save = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (valid){
      if (state.form.id){//如果id存在则为编辑
        request.put("/admin",state.form).then(res => {
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
        request.post("/admin",state.form).then(res => {
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
    }else {

    }
  })
}

const deleteRow = (id) => {
  request.delete("/admin/" + id).then(res => {
    if (res.code === '200' && res.data){
      ElMessage.success("删除成功")
    }else {
      ElMessage.success("删除失败")
    }
    load()
  })
}

const options = [
  {
    value: 0,
    label: '超级管理员',
  },
  {
    value: 1,
    label: '管理员',
  }]
</script>

<style scoped>
.avatar-uploader .avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
<style>
.avatar-uploader .el-upload {
  border: 1px dashed var(--el-border-color);
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
  transition: var(--el-transition-duration-fast);
}

.avatar-uploader .el-upload:hover {
  border-color: var(--el-color-primary);
}

.el-icon.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  text-align: center;
}
</style>