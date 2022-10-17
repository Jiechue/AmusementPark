<template>
  <div>
    <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;">
    <el-table-column prop="id" label="ID" width="180"/>
    <el-table-column prop="username" label="username"/>
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

  <el-dialog v-model="dialogFormVisible" title="用户信息">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="用户名" prop="username" label-width="formLabelWidth">
        <el-input v-model="state.form.username" autocomplete="off" placeholder="请输入用户名"/>
      </el-form-item>
      <el-form-item label="密码" prop="password" label-width="formLabelWidth">
        <el-input v-model="state.form.password" autocomplete="off" placeholder="请输入密码"/>
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
import { EditPen,Delete,Plus } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../request.js";
import {ElMessage} from "element-plus";
const { proxy } = getCurrentInstance()

const dialogFormVisible = ref(false)

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

const load = () => {
  request.get("/user/list").then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.tableDate = res.data
    }
  })
}
load()

const handleAdd = () => {
  dialogFormVisible.value= true
  state.form = {}
}

const handleEdit = (row) => {
  dialogFormVisible.value= true
  state.form = JSON.parse(JSON.stringify(row))
}

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

const deleteRow = (id) => {
  request.delete("/user/" + id).then(res => {
    if (res.code === '200' && res.data){
      ElMessage.success("删除成功")
    }else {
      ElMessage.success("删除失败")
    }
    load()
  })
}
</script>