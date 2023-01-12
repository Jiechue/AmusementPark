<template>
  <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
    <div style="text-align: center">
      <el-input style="width: 260px; margin-left: 10px" v-model="name" placeholder="请输入设施分类名称" clearable></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
      <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
    </div>
    <div style="background-color: white;border-radius: 20px;">
      <ul style="padding-top: 10px">
        <li class="list-li">
          <div class="list-li-container">
            <a @click="$router.push('/reception/facility/11')">
              <div style="width: 90%;height: 250px;margin: auto">
                <img style="width: 100%;height: 100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
              </div>
              <div style="margin: 10px;display: flex;justify-content: space-between">
                <div>
                  <div>迪士尼奇幻冬日巡游</div>
                  <div>全新的迪士尼奇幻冬日巡游来啦！迪士尼朋友们将带你沉浸于经典冬日童话中！</div>
                  <div>*效果请以实际运营情况为准</div>
                </div>
                <div class="list-li-icon"></div>
              </div>
            </a>
          </div>
        </li>
        <li class="list-li">
          <div class="list-li-container">
            <a href="http://localhost:8080">
              <div style="width: 90%;height: 250px;margin: auto">
                <img width="100%" height="100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
              </div>
              <div style="margin: 10px;display: flex;justify-content: space-between">
                <div>
                  <div>迪士尼奇幻冬日巡游</div>
                  <div>全新的迪士尼奇幻冬日巡游来啦！迪士尼朋友们将带你沉浸于经典冬日童话中！</div>
                  <div>*效果请以实际运营情况为准</div>
                </div>
                <div class="list-li-icon"></div>
              </div>
            </a>
          </div>
        </li>
      </ul>
    </div>
  </div>
</template>

<script setup>
import { EditPen,Delete,Plus,Search } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../../request.js";
import {ElMessage} from "element-plus";
import Cookies from "js-cookie";

const state = reactive({
  user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
  list:[],
  form:{},
})

const load = () => {
  request.get("/facility/list",{
    params: {

    }
  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.list = res.data
    }
  })
}
</script>

<style scoped>
.list-li{
  width: 48%;
  display: inline-block;
  /*height: 374px;*/
  margin-bottom: 10px;
  margin-right: 10px;
}

.list-li-container{
  border-radius: 20px;
  background-color: gray;
  padding: 20px;
}
.list-li-container a:hover img{
  transform:scale(105%,105%);
  overflow: hidden;
  transition: 0.3s;
}
.list-li-container a:hover .list-li-icon::before{
  border-color: transparent transparent transparent yellow;
}
.list-li-container img{
  border-radius: 20px;
}
a{
  text-decoration: none;
  color: black;
}
.list-li-icon{
  width: 0;
  height: 0;
  top: 0;
  left: 0;
  border: 20px solid;
  vertical-align: middle;
  border-color: transparent transparent transparent gray;
  position: relative;
}
.list-li-icon::before{
  content: '';
  position: absolute;
  top: 0;
  left: 0;
  border: 18px solid;
  border-color: transparent transparent transparent red;
}
.list-li-icon::after{
  content: '';
  position: absolute;
  top: 6px;
  left: 0;
  border: 12px solid;
  border-color: transparent transparent transparent gray;
}
</style>