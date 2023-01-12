<template>
  <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
    <div class="facility-head">
      <div>迪士尼奇幻冬日巡游</div>
      <div><hr></div>
      <div style="display: flex;justify-content: space-between">
        <div><img src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png"></div>
        <div class="facility-content">
          <div class="facility-text">全新的迪士尼奇幻冬日巡游来啦！迪士尼朋友们将带你沉浸于经典冬日童话中！</div>
          <div class="facility-up">
            <svg width="36" height="36" viewBox="0 0 36 36" xmlns="http://www.w3.org/2000/svg" class="icon">
              <path fill-rule="evenodd" clip-rule="evenodd" d="M9.77234 30.8573V11.7471H7.54573C5.50932 11.7471 3.85742 13.3931 3.85742 15.425V27.1794C3.85742 29.2112 5.50932 30.8573 7.54573 30.8573H9.77234ZM11.9902 30.8573V11.7054C14.9897 10.627 16.6942 7.8853 17.1055 3.33591C17.2666 1.55463 18.9633 0.814421 20.5803 1.59505C22.1847 2.36964 23.243 4.32583 23.243 6.93947C23.243 8.50265 23.0478 10.1054 22.6582 11.7471H29.7324C31.7739 11.7471 33.4289 13.402 33.4289 15.4435C33.4289 15.7416 33.3928 16.0386 33.3215 16.328L30.9883 25.7957C30.2558 28.7683 27.5894 30.8573 24.528 30.8573H11.9911H11.9902Z"/>
            </svg>
            <span>1111</span>
          </div>
        </div>
      </div>
    </div>
    <div>
      <div>留言板</div>
      <div><hr></div>
      <div>评论</div>
      <div class="comment">
        <div class="avatar">
          <el-avatar :size="50" :src="circleUrl" />
        </div>
        <div class="text"><el-input
            v-model="text"
            :rows="3"
            type="textarea"
            placeholder="Please input"
        /></div>
        <div class="btn">
          <el-button>
            发布
          </el-button>
        </div>
      </div>
    </div>
    <div><hr></div>
    <div>
      <div><el-avatar :size="50" :src="circleUrl" /></div>
      <div class="container">
        <div><span>名称</span></div>
        <div>内容</div>
        <div>
          <span>日期</span>
          <span><i class="like"><svg><path d=""/></svg></i></span>
          <span><i class="dislike"><svg><path d=""/></svg></i></span>
        </div>
      </div>
    </div>
  </div>
</template>
<script setup>
import { EditPen,Delete,Plus,Search } from '@element-plus/icons-vue'
import {reactive,ref,getCurrentInstance} from "vue";
import request from "../../request.js";
import {ElMessage} from "element-plus";
import Cookies from "js-cookie";
import router from "../../router/index";
//获取参数router
import { useRoute } from 'vue-router'

const { proxy } = getCurrentInstance()


const state = reactive({
  user: Cookies.get('user') ? JSON.parse(Cookies.get('user')) : {},
  messageDate:[],
  form:{},
  facilityId: useRoute().params.id
})

const currentPage = ref(1)
const pageSize = ref(15)
const total = ref(0)
const load = () => {
  request.get("/facility/" + state.facilityId,{
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
    }
  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.messageDate = res.data.list
      total.value = res.data.total
    }
  })
}
const loadMessage = () => {
  request.get("/message/page/" + state.facilityId,{
    params: {
      currentPage: currentPage.value,
      pageSize: pageSize.value,
    }
  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.messageDate = res.data.list
      total.value = res.data.total
    }
  })
}
console.log(useRoute().params.id)
</script>

<style scoped>
hr{
  border-bottom: 1px solid #E3E5E7;
}
.facility-head{
  background-color: white;
  border-radius: 20px;
  padding: 10px;
  margin-top: 20px;
  margin-bottom: 20px;
}
img{
  border-radius: 20px;
}
.comment{
  display: flex;
  justify-content: space-between

}
.comment .avatar{
  display: flex;
  justify-content: center;
  align-items: center;
  width: 80px;
}
.comment .btn{
  display: flex;
  justify-content: center;
  align-items: center;
  position: relative;
  flex-basis: 70px;
  margin-left: 10px;
  border-radius: 4px;
  cursor: pointer;
}
.facility-content{
  margin-left: 50px;
  margin-right: 50px;
  margin-top: 20px;
  margin-bottom: 50px;
}
.facility-up{
  width: 92px;
  margin-right: 8px;
  cursor: pointer;
  transition: all 0.3s;
  white-space: nowrap;
  position: relative;
  display: inline-flex;
  align-items: center;
  color: #61666D;
  fill: #61666D;
  position: relative;
  bottom: 0;
}
.facility-content .facility-up:hover{
  color: #00AEEC;
  fill: #00AEEC;
}
.like{

}
</style>