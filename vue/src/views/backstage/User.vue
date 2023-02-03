<template>
  <div>
    <el-input style="width: 260px; margin-left: 10px" v-model="username" placeholder="请输入用户名" clearable></el-input>
    <el-input style="width: 260px; margin-left: 10px" v-model="phone" placeholder="请输入电话号码" clearable></el-input>
    <el-input style="width: 260px; margin-left: 10px" v-model="email" placeholder="请输入邮箱地址" clearable></el-input>
    <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
    <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;">
    <el-table-column prop="id" label="ID" width="180"/>
    <el-table-column prop="username" label="用户名"/>
    <el-table-column prop="realName" label="真实姓名"/>
    <el-table-column prop="sex" label="性别"/>
    <el-table-column prop="age" label="年龄"/>
    <el-table-column prop="phone" label="电话"/>
    <el-table-column prop="email" label="邮箱"/>
    <el-table-column prop="address" label="地址"/>
    <el-table-column prop="avatar" label="头像">
      <template v-slot="scope">
        <el-image :src="scope.row.avatar" :preview-src-list="[scope.row.avatar]"></el-image>
      </template>
    </el-table-column>
    <el-table-column width="200px" label="操作">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="handleReset(scope.row)"><el-icon><EditPen/></el-icon>重置密码</el-button>
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
            :action="'http://localhost:9090/api/user/file/upload?token=' + state.admin.token"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="state.form.avatar" :src="state.form.avatar" class="avatar" />
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
  <el-dialog v-model="dialogUpdateFormVisible" title="用户信息">
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
            :action="'http://localhost:9090/api/user/file/upload?token=' + state.admin.token"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="state.form.avatar" :src="state.form.avatar" class="avatar" />
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
</template>

<script setup>
import { EditPen,Delete,Plus,Search } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../../request.js";
import {ElMessage} from "element-plus";
import Cookies from "js-cookie";
const { proxy } = getCurrentInstance()

const dialogFormVisible = ref(false)
const dialogUpdateFormVisible = ref(false)

const checkEmail = (rule, value, callback) => {
  if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
    return callback(new Error('邮箱格式错误'))
  }
  callback()
}
const state = reactive({
  admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
  tableDate:[],
  form:{},
  rules :{
    username:[
      {required: true, message: '请输入用户名', trigger: 'blur'}
    ],
    email: [
      {validator: checkEmail,target: 'blur'}
    ],
    password: [
      {required: true, message:'请输入密码', trigger: 'blur'},
      {min: 5 , max: 16 , message: '最少输入五位最多输入十六位', trigger: 'blur'}
    ],
  }
})
const url = ref('http://localhost:9090/api/user/file/upload?token=' + state.admin.token)
const currentPage = ref(1)
const pageSize = ref(100)
const total = ref(0)
const username = ref('')
const phone = ref('')
const email = ref('')
const load = () => {
  request.get("/user/page",{
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
      username: username.value,
      phone: phone.value,
      email: email.value
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
  dialogUpdateFormVisible.value= true
  state.form = JSON.parse(JSON.stringify(row))
}
const handleAvatarSuccess = (res) => {
  state.form.avatar = {avatar: ''}
  console.log(res.data)
  if (res.code === '200'){
    state.form.avatar = res.data
  }
}
const handleReset = (row) => {
  state.form = JSON.parse(JSON.stringify(row))
  console.log(state.form)
  Reset()
}
const Reset = () => {
  if (state.form.id){
    request.put("/user/reset",state.form).then(res => {
      console.log(res)
      if (res.code === '200'){
        ElMessage.success("重置成功密码为:" + res.data)
        dialogFormVisible.value = false;
        load()
      }else {
        ElMessage.error(res.msg)
      }
    })
  }
}
const Save = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (valid){
      if (state.form.id){//如果id存在则为编辑
        request.put("/user/adminUpdate",state.form).then(res => {
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
        request.post("/user/adminAdd",state.form).then(res => {
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