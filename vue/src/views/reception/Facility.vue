<template>
  <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
    <div class="facility-head">
      <div style="font-size: 20px">{{ state.facilityDate.name }}</div>
      <div><hr></div>
      <div style="display: flex;  ">
        <div><img src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png"></div>
        <div class="facility-content">
          <div class="facility-text">{{ state.facilityDate.description }}</div>
          <div style="margin-top: 10px">
            <div style="font-weight: bold;display: inline-block">开放时间:</div>
            <div style="display: inline-block;margin-left: 10px">{{ state.facilityDate.opentime }}</div>
          </div>
          <div style="margin-top: 10px">
            <div style="font-weight: bold;display: inline-block">身高要求:</div>
            <div style="display: inline-block;margin-left: 10px">{{ state.facilityDate.height }}</div>
          </div>
          <div style="margin-top: 10px">
            <div style="font-weight: bold;display: inline-block">适合年龄:</div>
            <div style="display: inline-block;margin-left: 10px">{{ state.facilityDate.age }}</div>
          </div>
          <div class="facility-up" @click="likeFacility(state.facilityDate)">
            <svg width="36" height="36" viewBox="0 0 36 36" xmlns="http://www.w3.org/2000/svg" class="icon">
              <path fill-rule="evenodd" clip-rule="evenodd" d="M9.77234 30.8573V11.7471H7.54573C5.50932 11.7471 3.85742 13.3931 3.85742 15.425V27.1794C3.85742 29.2112 5.50932 30.8573 7.54573 30.8573H9.77234ZM11.9902 30.8573V11.7054C14.9897 10.627 16.6942 7.8853 17.1055 3.33591C17.2666 1.55463 18.9633 0.814421 20.5803 1.59505C22.1847 2.36964 23.243 4.32583 23.243 6.93947C23.243 8.50265 23.0478 10.1054 22.6582 11.7471H29.7324C31.7739 11.7471 33.4289 13.402 33.4289 15.4435C33.4289 15.7416 33.3928 16.0386 33.3215 16.328L30.9883 25.7957C30.2558 28.7683 27.5894 30.8573 24.528 30.8573H11.9911H11.9902Z"/>
            </svg>
            <span>{{ state.facilityDate.liketotal }}</span>
          </div>
        </div>
      </div>
    </div>
    <div style="background-color: #faf5ec;border-radius: 20px;box-shadow: 2px 2px 3px 0 rgb(0 0 0 / 10%);">
      <div style="padding: 15px 15px 15px 15px">
        <div>留言板</div>
        <div><hr></div>
        <div>发表评论</div>
        <div class="comment">
          <div class="comment-avatar">
            <el-avatar :size="50" :src="state.user.avatar" />
          </div>
          <div class="comment-text"><el-input
              v-model="state.messageForm.content"
              :rows="2"
              type="textarea"
              placeholder="请输入内容"
              style="width: 500px"
          /></div>
          <div class="btn">
            <el-button type="primary" @click="commit" style="height: 50px">
              发布
            </el-button>
          </div>
        </div>
        <div><hr></div>
      </div>

      <div v-for="(item) in state.messageDate">
        <div class="message-avatar"><el-avatar :size="50" :src="item.user.avatar" /></div>
        <div class="message-container">
          <div class="message-username">{{ item.user.username }}</div>
          <div class="message-content">{{ item.content }}</div>
          <div class="info">
            <span class="date">{{ item.createtime }}</span>
            <span class="reply-like" @click="like(item)" ref="likeDOM">
            <i class="like">
              <svg v-if="!item.like" style="width: 100%;height: 100%" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="200" height="200">
                <path d="M594.176 151.168a34.048 34.048 0 0 0-29.184 10.816c-11.264 13.184-15.872 24.064-21.504 40.064l-1.92 5.632c-5.632 16.128-12.8 36.864-27.648 63.232-25.408 44.928-50.304 74.432-86.208 97.024-23.04 14.528-43.648 26.368-65.024 32.576v419.648a4569.408 4569.408 0 0 0 339.072-4.672c38.72-2.048 72-21.12 88.96-52.032 21.504-39.36 47.168-95.744 63.552-163.008a782.72 782.72 0 0 0 22.528-163.008c0.448-16.832-13.44-32.256-35.328-32.256h-197.312a32 32 0 0 1-28.608-46.336l0.192-0.32 0.64-1.344 2.56-5.504c2.112-4.8 5.12-11.776 8.32-20.16 6.592-17.088 13.568-39.04 16.768-60.416 4.992-33.344 3.776-60.16-9.344-84.992-14.08-26.688-30.016-33.728-40.512-34.944zM691.84 341.12h149.568c52.736 0 100.864 40.192 99.328 98.048a845.888 845.888 0 0 1-24.32 176.384 742.336 742.336 0 0 1-69.632 178.56c-29.184 53.44-84.48 82.304-141.76 85.248-55.68 2.88-138.304 5.952-235.712 5.952-96 0-183.552-3.008-244.672-5.76-66.432-3.136-123.392-51.392-131.008-119.872a1380.672 1380.672 0 0 1-0.768-296.704c7.68-72.768 70.4-121.792 140.032-121.792h97.728c13.76 0 28.16-5.504 62.976-27.456 24.064-15.104 42.432-35.2 64.512-74.24 11.904-21.184 17.408-36.928 22.912-52.8l2.048-5.888c6.656-18.88 14.4-38.4 33.28-60.416a97.984 97.984 0 0 1 85.12-32.768c35.264 4.096 67.776 26.88 89.792 68.608 22.208 42.176 21.888 84.864 16 124.352a342.464 342.464 0 0 1-15.424 60.544z m-393.216 477.248V405.184H232.96c-40.448 0-72.448 27.712-76.352 64.512a1318.912 1318.912 0 0 0 0.64 282.88c3.904 34.752 32.96 61.248 70.4 62.976 20.8 0.96 44.8 1.92 71.04 2.816z" fill="#9499a0"/>
              </svg>
              <svg v-if="item.like" style="width: 100%;height: 100%" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="200" height="200">
                <path d="M860.032 341.12h-182.08c7.488-17.408 14.72-38.528 18.048-60.544 5.952-39.872 4.992-87.36-18.368-129.088-21.76-38.848-50.304-60.928-83.52-61.376-30.72-0.384-53.888 18.176-65.728 33.408a199.296 199.296 0 0 0-32.064 59.264l-1.92 5.184c-5.44 14.976-10.88 29.952-23.04 51.456-19.712 34.816-48.832 56.128-77.696 74.368a391.936 391.936 0 0 1-30.976 17.92v552.448a4621.952 4621.952 0 0 0 351.872-5.312c51.264-2.752 100.672-28.544 127.488-76.032 24.32-43.136 55.168-108.16 74.368-187.264 20.416-84.16 24.64-152.704 24.576-195.968-0.128-46.336-38.72-78.4-80.96-78.4z m-561.344 541.312V341.12H215.808c-59.712 0-113.408 42.048-120.896 104.32a1376 1376 0 0 0 0.64 330.368c7.36 58.688 56.128 100.032 113.024 102.848 25.024 1.28 55.552 2.56 90.112 3.712z" fill="#00aeec"></path>
              </svg>
            </i>
            <span>{{ item.liketotal }}</span>
            </span>
              <span class="reply-dislike" @click="dislike(item)">
              <i class="dislike">
                <svg v-if="!item.dislike" style="width: 100%;height: 100%" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="200" height="200">
                  <path d="M594.112 872.768a34.048 34.048 0 0 1-29.12-10.816c-11.264-13.248-15.872-24.064-21.504-40.064l-1.92-5.632c-5.632-16.128-12.8-36.864-27.712-63.232-25.344-44.928-50.24-74.432-86.144-97.024-23.104-14.528-43.648-26.432-65.024-32.64V203.84a4570.24 4570.24 0 0 1 339.072 4.672c38.656 2.048 72 21.12 88.896 52.032 21.504 39.36 47.232 95.744 63.552 163.008 16.448 67.52 21.568 123.776 22.592 163.008 0.448 16.832-13.44 32.256-35.392 32.256h-197.248a32 32 0 0 0-28.608 46.336l0.128 0.32 0.64 1.28 2.56 5.568c2.176 4.8 5.12 11.776 8.384 20.16 6.528 17.088 13.568 39.04 16.768 60.416 4.928 33.344 3.712 60.16-9.344 84.992-14.08 26.688-30.016 33.728-40.576 34.944z m97.728-190.016h149.568c52.8 0 100.864-40.128 99.392-97.92a846.336 846.336 0 0 0-24.32-176.448 742.016 742.016 0 0 0-69.632-178.56c-29.248-53.44-84.48-82.304-141.824-85.248-55.68-2.88-138.24-5.952-235.712-5.952-96 0-183.488 3.008-244.672 5.76-66.368 3.136-123.328 51.392-130.944 119.872a1380.608 1380.608 0 0 0-0.768 296.704c7.68 72.768 70.4 121.792 140.032 121.792h97.728c13.76 0 28.16 5.504 62.976 27.392 24.064 15.168 42.432 35.264 64.448 74.368 11.968 21.12 17.472 36.864 22.976 52.736l2.048 5.888c6.656 18.88 14.336 38.4 33.216 60.416 19.456 22.72 51.456 36.736 85.184 32.768 35.2-4.096 67.776-26.88 89.792-68.672 22.208-42.112 21.888-84.8 16-124.288a343.04 343.04 0 0 0-15.488-60.608zM298.688 205.568v413.184H232.96c-40.512 0-72.448-27.712-76.352-64.512a1318.912 1318.912 0 0 1 0.64-282.88c3.904-34.816 32.896-61.248 70.4-62.976 20.8-0.96 44.736-1.92 71.04-2.816z" fill="#9499a0"/>
                </svg>
                <svg v-if="item.dislike" style="width: 100%;height: 100%" viewBox="0 0 1024 1024" xmlns="http://www.w3.org/2000/svg" width="200" height="200">
                  <path d="M860.032 693.632h-182.08c7.488 17.408 14.72 38.592 18.048 60.608 5.952 39.872 4.992 87.296-18.368 129.088-21.76 38.848-50.304 60.928-83.52 61.312-30.72 0.384-53.888-18.176-65.728-33.408a199.296 199.296 0 0 1-32.064-59.2l-1.92-5.248c-5.44-14.912-10.88-29.888-23.04-51.456-19.712-34.816-48.832-56.128-77.696-74.304a391.68 391.68 0 0 0-30.976-17.984V150.592a4617.408 4617.408 0 0 1 351.872 5.312c51.264 2.752 100.672 28.608 127.488 76.096 24.32 43.136 55.168 108.16 74.368 187.264 20.416 84.096 24.64 152.64 24.576 195.904-0.128 46.336-38.72 78.464-80.96 78.464zM298.624 152.32v541.248H215.808c-59.712 0-113.408-42.048-120.896-104.32a1376 1376 0 0 1 0.64-330.432c7.36-58.624 56.128-100.032 113.024-102.848 25.024-1.216 55.552-2.56 90.112-3.648z" fill="#00aeec"></path>
                </svg>
              </i>
              <span></span>
            </span>
            <template v-if="state.user.token">
              <div v-if="item.user.id === state.user.id">
                <el-popconfirm title="确定删除该评论吗？">
                  <template #reference>
                    <el-button type="info" text>删除</el-button>
                  </template>
                </el-popconfirm>
              </div>
              {{item.userid}}
            </template>
          </div>
        </div>
        <div class="message-line"></div>
      </div>

      <div style="margin-left: 35%;margin-top: 10px">
        <el-pagination
            background
            v-model:currentPage="currentPage"
            style="margin: auto"
            :small="small"
            :disabled="disabled"
            :background="background"
            layout="total, prev, pager, next"
            :total=total
            @current-change="handleCurrentChange"
        />
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
  facilityDate:[],
  form:{},
  facilityId: useRoute().params.id,
  messageForm:{}
})

const currentPage = ref(1)
const pageSize = ref(15)
const total = ref(0)
const load = () => {
  request.get("/facility/" + state.facilityId,{
    params: {
    }
  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.facilityDate = res.data
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
load()
loadMessage()

const like = (row) => {
  request.post("/action/like",row).then(res =>{
    console.log(res)
    loadMessage()
  })
}
const likeFacility = (row) => {
  request.post("/action/likeFacility",row).then(res =>{
    console.log(res)
    load()
  })
}
const dislike = (row) => {
  request.post("/action/hate",row).then(res =>{
    console.log(res)
    loadMessage()
  })
}
console.log(useRoute().params.id)
const handleCurrentChange = (val) => {
  currentPage.value = val
  load()
}
const commit = () => {
  state.messageForm.userid = state.user.id
  state.messageForm.facilityid = state.facilityId
  request.post("/message/release",state.messageForm).then(res =>{
    if (res.code === '200'){
      console.log(res)
      ElMessage.success("发布成功")
      loadMessage()
    }
  })
}
</script>

<style scoped>
hr{
  border-bottom: 1px solid #E3E5E7;
}
.facility-head{
  box-shadow: 2px 2px 3px 0 rgb(0 0 0 / 10%);
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
  margin-top: 20px;
  margin-bottom: 20px;
  width: 700px;
  display: flex;
  justify-content: space-between

}
.comment .comment-avatar{
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
  width: 100%;
}
.facility-content{
  margin-left: 50px;
  margin-right: 50px;
  margin-top: 20px;
  margin-bottom: 50px;
}
.facility-up{
  margin-top: 10px;
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
  width: 16px;
  height: 16px;
}
.dislike{
  width: 16px;
  height: 16px;
}
.reply-like{
  display: flex;
  align-items: center;
  margin-right: 19px;
  cursor: pointer;
}
.reply-dislike{
  display: flex;
  align-items: center;
  margin-right: 19px;
  cursor: pointer;
}
.info{
  display: flex;
  align-items: center;
  position: relative;
  margin-top: 2px;
  font-size: 13px;
  color: #9499A0;
}
.date{
  margin-right: 16px;
  color: #9499a0;
}
.message-avatar{
  left: 12px;
  position: absolute;
}
.message-container{
  padding: 22px 0 0 80px;
}
.message-username{
  margin-bottom: 15px;
  font-weight: 500;
}
.message-content{
  margin-bottom: 10px;
}
.message-line{
  margin-left: 80px;
  border-bottom: 1px solid #E3E5E7;
  margin-top: 14px;
}
</style>