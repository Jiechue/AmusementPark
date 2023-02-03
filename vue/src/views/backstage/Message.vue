<template>
  <div>
    <el-input style="width: 260px; margin-left: 10px" v-model="name" placeholder="请输入要查询的内容" clearable></el-input>

    <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>

  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;">

    <el-table-column prop="id" label="ID" width="180"/>
    <el-table-column prop="userid" label="用户ID"/>
    <el-table-column prop="facilityid" label="设施ID"/>
    <el-table-column prop="liketotal" label="总点赞数"/>
    <el-table-column prop="disliketotal" label="总不喜欢数"/>
    <el-table-column prop="createtime" label="创建时间"/>
    <el-table-column prop="content" label="内容"/>

    <el-table-column width="200px" label="操作">
      <template #default="scope">
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


</template>

<script setup>
import { EditPen,Delete,Plus,Search } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../../request.js";
import {ElMessage} from "element-plus";
import Cookies from "js-cookie";
const { proxy } = getCurrentInstance()

const state = reactive({
  admin: Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {},
  tableDate:[],
  categories:[],
  form:{},
  date:{},

})
const currentPage = ref(1)
const pageSize = ref(100)
const total = ref(0)
const username = ref('')

const load = () => {
  request.get("/message/page",{
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
const deleteRow = (id) => {
  request.delete("/message/" + id).then(res => {
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

</style>