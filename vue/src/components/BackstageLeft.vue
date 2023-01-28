<template>
  <div>
    <el-menu :default-active="$route.path" :default-openeds="['/backstage']" router class="el-menu-demo" style="width: 150px">
      <el-menu-item index="/backstage/">
        <el-icon><Eleme/></el-icon>
        <span>首页</span>
      </el-menu-item>
      <el-sub-menu index="/backstage/">
        <template #title>
          关于页面
        </template>
        <el-menu-item index="user">用户管理</el-menu-item>
        <el-menu-item index="category">设施分类管理</el-menu-item>
        <el-menu-item index="facilities">设施管理</el-menu-item>
        <el-menu-item index="message">留言管理</el-menu-item>
        <el-menu-item index="admin">管理员管理</el-menu-item>
      </el-sub-menu>
    </el-menu>
  </div>
</template>

<script setup>
import { Eleme } from '@element-plus/icons-vue'
// export default {
//   name: "BackgroundLeft"
// }
</script>

<style scoped>

</style>