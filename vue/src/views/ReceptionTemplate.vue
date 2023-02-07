<script setup>
import Header from '../components/ReceptionHeader.vue'
import Footer from '../components/ReceptionFooter.vue'

</script>
<template>
  <div>
    <Header/>
  </div>
  <div>
    <router-view />
  </div>
  <div>
    <Footer/>
  </div>
</template>


<style scoped>

</style>
