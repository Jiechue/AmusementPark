<template>
  <div>欢迎登陆{{user.username}}</div>
  <div>
    <div>
      <div>园区介绍</div>
      <div><el-input
          v-model="textarea"
          :rows="2"
          type="textarea"
          placeholder="Please input"
      /></div>
    </div>
  </div>
</template>

<script setup>
import Cookies from "js-cookie"

const user = Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
</script>

<style scoped>

</style>