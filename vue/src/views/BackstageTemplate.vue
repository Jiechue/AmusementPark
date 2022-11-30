<script setup>
import Header from '../components/BackstageHeader.vue'
import Left from '../components/BackstageLeft.vue'
</script>

<template>
  <Header/>
  <div style="display: flex">
    <div><Left/></div>
    <div style="width: 100%"><router-view /></div>
  </div>
</template>

