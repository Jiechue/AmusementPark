<template>
  <div>
    <el-button>新增</el-button>
  </div>
  <el-table :data="state.tableDate" stripe style="width: 100%;">
    <el-table-column prop="id" label="ID" width="180"/>
    <el-table-column prop="username" label="username"/>
    <el-table-column width="200px" label="操作">
      <template #default>
        <el-button link type="primary" size="small" @click=""><el-icon><EditPen/></el-icon>Edit</el-button>
        <el-button link type="danger" size="small" @click=""><el-icon><Delete/></el-icon>Delete</el-button>
      </template>
    </el-table-column>
  </el-table>
</template>

<script setup>
import { EditPen,Delete } from '@element-plus/icons-vue'
import {reactive} from "vue";
import request from "../request.js";

const state = reactive({
  tableDate:[]
})
request.get("/user/list").then(res =>{
  console.log(res)
  state.tableDate = res
})
</script>