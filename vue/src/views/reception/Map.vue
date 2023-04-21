<template>
<div>
  <div style="text-align: center">
    <span>选择地图:</span>
    <el-select v-model="img" class="m-2" placeholder="Select">
      <el-option
          v-for="item in options"
          :key="item.value"
          :label="item.label"
          :value="item.value"
      />
    </el-select>
  </div>
  <img :src="imgUrl">
</div>
</template>

<script setup>
import {reactive,ref,getCurrentInstance} from "vue";
const imgUrl = ref('')
const options = [
  {
    value: 'Option1',
    label: 'Option1',
  },
  {
    value: 'Option2',
    label: 'Option2',
  },
  {
    value: 'Option3',
    label: 'Option3',
  },
  {
    value: 'Option4',
    label: 'Option4',
  },
  {
    value: 'Option5',
    label: 'Option5',
  },
]
</script>

<style scoped>

</style>