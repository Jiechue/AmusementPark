<template>
  <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
    <div style="text-align: center;margin-top: 10px">
      <el-cascader
          :props="{value: 'name', label: 'name'}"
          v-model="categories"
          :options="state.categories"
      ></el-cascader>
      <el-input style="width: 260px; margin-left: 10px" v-model="name" placeholder="请输入要查询的设施名称" clearable></el-input>
      <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
    </div>
    <div style="background-color: white;border-radius: 20px;box-shadow: 2px 2px 3px 2px rgb(0 0 0 / 10%);">
      <ul style="padding-top: 10px">
        <li class="list-li">
          <div class="list-li-container">
            <a @click="$router.push('/reception/facility/11')">
              <div style="width: 90%;height: 250px;margin: auto">
                <img style="width: 100%;height: 100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
              </div>
              <div style="margin: 10px;display: flex;justify-content: space-between">
                <div class="list-li-text">
                  <h3>迪士尼奇幻冬日巡游</h3>
                  <div style="display: flex;">
                    <div>全新的迪士尼奇幻冬日巡游来啦！迪士尼朋友们将带你沉浸于经典冬日童话中！</div>
                    <div style="width: 280px;margin-left: 20px;vertical-align: top">
                      <div style="font-weight: bold">开放时间</div>
                      <div style="font-size: 10px">上午 8:30至晚上 9:30</div>
                      <div style="font-size: 10px">游客身高：</div>
                    </div>
                  </div>
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
                  <h3></h3>
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
  categories:[]
})

const categories = ref('')
const name = ref('')
const load = () => {
  request.get("/facility/",{
    params: {
      name: name.value,
      categories: categories.value
    }
  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.list = res.data
      console.log(state.list)
    }
  })
}
const categoryTree = () => {
  request.get('/facilityCategory/tree').then(res => {
    state.categories = res.data
  })
}
load()
categoryTree()

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
  /*background-color: gray;*/
  padding: 20px;
  transition: all 0.3s;
}
.list-li-container a:hover img{
  transform:scale(105%,105%);
  overflow: hidden;
  transition: 0.3s;
}
.list-li-container a{
  cursor: pointer;
}
.list-li-container a:hover .list-li-icon::before{
  border-color: transparent transparent transparent #1994d7;
}
.list-li-container a:hover .list-li-text{
  color: #1994d7;
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
  border-color: transparent transparent transparent white;
  position: relative;
}
.list-li-icon::before{
  content: '';
  position: absolute;
  top: 40px;
  left: 0;
  border: 16px solid;
  border-color: transparent transparent transparent rgba(161,175,192,0.3);
}
.list-li-icon::after{
  content: '';
  position: absolute;
  top: 46px;
  left: -2px;
  border: 10px solid;
  border-color: transparent transparent transparent white;
}
h3{
  font-weight: bold;
  font-size: 18px;
  margin-bottom: 7px;
  display: inline-block;
}
</style>