<template>
<div>欢迎登陆{{user.username}}</div>
</template>

<script setup>
import Cookies from "js-cookie"

const user = Cookies.get('admin') ? JSON.parse(Cookies.get('admin')) : {}
</script>

<style scoped>

</style>