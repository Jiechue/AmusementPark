<template>
  <div>
    <el-input style="width: 260px; margin-left: 10px" v-model="name" placeholder="请输入设施名称" clearable></el-input>

    <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
    <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;">
    <el-table-column prop="id" label="ID"/>
    <el-table-column prop="category" label="设施类别"/>
    <el-table-column prop="name" label="设施名称"/>
    <el-table-column prop="opentime" label="开放时间"/>
    <el-table-column prop="price" label="价格"/>
    <el-table-column prop="description" label="描述"/>
    <el-table-column prop="enable" label="是否开放">
      <template v-slot="scope">
        <el-switch
            v-model="scope.row.enable"
            @change="changeShow(scope.row)"
            inline-prompt
            active-text="开放"
            inactive-text="不开放"
            active-color="#13ce66"
            inactive-color="#ff4949"
        ></el-switch>
      </template>
    </el-table-column>
    <el-table-column prop="height" label="游客身高"/>
    <el-table-column prop="age" label="游客年龄"/>
    <el-table-column prop="show" label="是否在首页展示">
      <template v-slot="scope">
        <el-switch
            v-model="scope.row.show"
            @change="changeShow(scope.row)"
            inline-prompt
            active-text="展示"
            inactive-text="不展示"
            active-color="#13ce66"
            inactive-color="#ff4949"
        ></el-switch>
      </template>
    </el-table-column>
    <el-table-column prop="liketotal" label="点赞总数"/>
    <el-table-column prop="createtime" label="创建时间"/>
    <el-table-column prop="updatetime" label="更新时间"/>
    <el-table-column prop="cover" label="设施图片">
      <template v-slot="scope">
        <el-image :src="scope.row.cover" :preview-src-list="[scope.row.cover]"></el-image>
      </template>
    </el-table-column>
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

  <el-dialog v-model="dialogFormVisible" title="设施信息">
    <el-form :model="state.form" :rules="state.rules" ref="ruleFormRef" label-width="120px" style="width: 85%">
      <el-form-item label="设施类别" prop="category" label-width="formLabelWidth">
        <el-cascader
            :props="{value: 'name', label: 'name'}"
            v-model="state.form.categories"
            :options="state.categories"
        ></el-cascader>
      </el-form-item>
      <el-form-item label="设施名称" prop="name" label-width="formLabelWidth">
        <el-input v-model="state.form.name" autocomplete="off" placeholder="请输入设施名称"/>
      </el-form-item>
      <el-form-item label="开放时间" prop="opentime" label-width="formLabelWidth">
        <el-time-picker
            v-model="state.date.date"
            type="datetimerange"
            value-format="HH:mm:ss"
            is-range
            arrow-control
            range-separator="To"
            start-placeholder="Start time"
            end-placeholder="End time"
            @change="dateFormat"
        />
      </el-form-item>
      <el-form-item label="价格" prop="price" label-width="formLabelWidth">
        <el-input v-model="state.form.price" autocomplete="off" placeholder="请输入价格"/>
      </el-form-item>
      <el-form-item label="描述" prop="description" label-width="formLabelWidth">
        <el-input v-model="state.form.description" autocomplete="off" placeholder="请输入描述"/>
      </el-form-item>
<!--      <el-form-item label="是否可用" prop="enable" label-width="formLabelWidth">-->
<!--        <el-select v-model="state.form.enable" placeholder="Select">-->
<!--          <el-option-->
<!--              v-for="item in options"-->
<!--              :key="item.value"-->
<!--              :label="item.label"-->
<!--              :value="item.value"-->
<!--              :disabled="item.disabled"-->
<!--          />-->
<!--        </el-select>-->
<!--      </el-form-item>-->
      <el-form-item label="游客身高" prop="height" label-width="formLabelWidth">
        <el-input v-model="state.form.height" autocomplete="off" placeholder="请输入描述"/>
      </el-form-item>
      <el-form-item label="游客年龄" prop="age" label-width="formLabelWidth">
        <el-input v-model="state.form.age" autocomplete="off" placeholder="请输入描述"/>
      </el-form-item>
      <el-form-item label="设施图片" prop="avatar" label-width="formLabelWidth">
        <el-upload
            class="avatar-uploader"
            :action="'http://localhost:9090/api/facility/file/upload?token=' + state.admin.token"
            :show-file-list="false"
            :on-success="handleAvatarSuccess"
        >
          <img v-if="state.form.cover" :src="state.form.cover" class="avatar" />
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

const checkEmail = (rule, value, callback) => {
  if (!/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/.test(value)){
    return callback(new Error('邮箱格式错误'))
  }
  callback()
}
const state = reactive({
  admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
  tableDate:[],
  categories:[],
  form:{},
  date:{},
  rules :{
    name:[
      {required: true, message: '请输入设施名称', trigger: 'blur'}
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
const name = ref('')

const load = () => {
  request.get("/facility/page",{
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
  categoryTree()
}

const handleEdit = (row) => {
  dialogFormVisible.value= true
  state.form = JSON.parse(JSON.stringify(row))
  if (state.form.category){
    state.form.categories = state.form.category.split(' > ')
  }
  categoryTree()
}
const handleAvatarSuccess = (res) => {
  state.form.cover = {cover: ''}
  console.log(res.data)
  if (res.code === '200'){
    state.form.cover = res.data
  }
}

const Save = () => {
  proxy.$refs.ruleFormRef.validate((valid)=>{
    if (valid){
      if (state.form.id){//如果id存在则为编辑
        request.put("/facility",state.form).then(res => {
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
        request.post("/facility",state.form).then(res => {
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

const categoryTree = () => {
  request.get('/facilityCategory/tree').then(res => {
    state.categories = res.data
  })
}

const deleteRow = (id) => {
  request.delete("/facility/" + id).then(res => {
    if (res.code === '200' && res.data){
      ElMessage.success("删除成功")
    }else {
      ElMessage.success("删除失败")
    }
    load()
  })
}

const dateFormat = (picker) => {
  state.form.opentime = picker[0] + "-" +picker[1]
  //类型是type="daterange"
  //this.params.startTime=picker[0].toString
  // this.params.endTime=picker[1].toString
}
const changeShow = (row) => {
  request.put("/facility",row).then(res => {
    if (res.code === '200'){
      ElMessage.success("更改成功")
      load()
    }else {
      console.log(res)
      ElMessage.error(res.msg)
    }
  })
}
// const options = [
//   {
//     value: 0,
//     label: '可用',
//   },
//   {
//     value: 1,
//     label: '不可用',
//   }]
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