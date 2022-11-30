<template>
  <div>
    <el-input style="width: 260px; margin-left: 10px" v-model="name" placeholder="请输入设施分类名称" clearable></el-input>

    <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
    <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;" row-key="id" default-expand-all>
    <el-table-column prop="id" label="ID" width="180"/>
    <el-table-column prop="name" label="设施分类名称"/>
    <el-table-column prop="remark" label="备注"/>
    <el-table-column prop="createtime" label="创建时间"/>
    <el-table-column prop="updatetime" label="更新时间"/>
    <el-table-column width="250px" label="操作">
      <template #default="scope">
        <el-button link type="primary" size="small" v-if="!scope.row.pid" @click="handleAddSecondLevel(scope.row)"><el-icon><EditPen/></el-icon>添加二级分类</el-button>
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
        :page-sizes="[1, 200, 300, 400]"
        :small="small"
        :disabled="disabled"
        :background="background"
        layout="total, sizes, prev, pager, next, jumper"
        :total=total
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
    />
  </div>

  <el-dialog v-model="dialogFormVisible" title="设施分类信息">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="设施分类" prop="name" label-width="formLabelWidth">
        <el-input v-model="state.form.name" autocomplete="off" placeholder="请输入设施分类名称"/>
      </el-form-item>
      <el-form-item label="备注" prop="remark" label-width="formLabelWidth">
        <el-input v-model="state.form.remark" autocomplete="off" placeholder="请输入备注"/>
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
const secondLevelAdd = ref (false)

const checkEmail = (rule, value, callback) => {
  if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
    return callback(new Error('邮箱格式错误'))
  }
  callback()
}
const state = reactive({
  tableDate:[],
  form:{},
  pid: null,
  rules :{
    name:[
      {required: true, message: '请输入设施分类名', trigger: 'blur'}
    ],
    password: [
      {required: true, message:'请输入密码', trigger: 'blur'},
      {min: 5 , max: 16 , message: '最少输入五位最多输入十六位', trigger: 'blur'}
    ],
  }
})

const currentPage = ref(1)
const pageSize = ref(1)
const total = ref(0)
const name = ref('')

const load = () => {
  request.get("/facilityCategory/page",{
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      name: name.value,
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

const handleAddSecondLevel = (row) => {
  dialogFormVisible.value= true
  state.pid = row.id
}

const Save = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (state.form.id){//如果id存在则为编辑
      request.put("/facilityCategory",state.form).then(res => {
        if (res.code === '200'){
          ElMessage.success("保存成功")
          dialogFormVisible.value = false;
          load()
        }else {
          ElMessage.error(res.msg)
        }
      })
    }else if (state.pid){
      state.form.pid = state.pid
      console.log(state.form)
      request.post("/facilityCategory",state.form).then(res => {
        if (res.code === '200'){
          ElMessage.success("保存成功")
          dialogFormVisible.value = false;
          load()
        }else {
          console.log(res)
          ElMessage.error(res.msg)
        }
      })
    } else {
      console.log(state.form)
      request.post("/facilityCategory",state.form).then(res => {
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
  request.delete("/facilityCategory/" + id).then(res => {
    if (res.code === '200' && res.data){
      ElMessage.success("删除成功")
    }else {
      ElMessage.success("删除失败")
    }
    load()
  })
}
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