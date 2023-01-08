<template>
  <div style="text-align: center;margin: auto">
    <div class="block text-center">
      <el-carousel height="150px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 class="small justify-center" text="2xl">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
      <div style="text-align: center">
        <el-input style="width: 260px; margin-left: 10px" v-model="name" placeholder="请输入设施分类名称" clearable></el-input>
        <el-button style="margin-left: 10px" type="primary" @click="load"><el-icon style="margin-right: 3px"><Search/></el-icon>查询</el-button>
        <el-button type="primary" @click="handleAdd"><el-icon style="margin-right: 3px"><Plus/></el-icon>新增</el-button>
      </div>
    </div>
    <div style="text-align: center;width: 80%;margin-left: auto;margin-right: auto;position: relative">
      <div>
        <div style="display: flex;justify-content: space-between;">
          <div title="门票价格" class="item">
            <div class="title-h2"><h2>门票价格</h2></div>
            <div class="cont">
              <div class="cont-content">
                <div class="ticket-category">
                  <div class="ticket-left">
                    <div class="ticket-left-word">
                      ￥<span>250</span>
                    </div>
                  </div>
                  <div class="ticket-right">
                    <div class="ticket-right-word">
                      标准票
                    </div>
                  </div>
                </div>
                <div class="ticket-category">
                  <div class="ticket-left">
                    <div class="ticket-left-word">
                      ￥<span>250</span>
                    </div>
                  </div>
                  <div class="ticket-right">
                    <div class="ticket-right-word">
                      标准票
                    </div>
                  </div>
                </div>
                <div class="ticket-category">
                  <div class="ticket-left">
                    <div class="ticket-left-word">
                      ￥<span>250</span>
                    </div>
                  </div>
                  <div class="ticket-right">
                    <div class="ticket-right-word">
                      标准票
                    </div>
                  </div>
                </div>
                <div class="ticket-category">
                  <div class="ticket-left">
                    <div class="ticket-left-word">
                      ￥<span>250</span>
                    </div>
                  </div>
                  <div class="ticket-right">
                    <div class="ticket-right-word">
                      标准票
                    </div>
                  </div>
                </div>
              </div>
              <div class="text-btn">
                <a class="text-btn-a">在线购票</a>
              </div>
              <img style="width: 100%;top: 2px" src="https://cdn.chimelong.com/upload/adfbfa03eea725de/c14aebcf3782b6fe.png">
            </div>
          </div>
          <div title="开放时间" class="item">
            <div class="title-h2"><h2>开放时间</h2></div>
            <div class="cont">
              <div class="cont-content">
                <img class="timer" src="https://cdn.chimelong.com/assets/images/timer.b2a3b0e.png">
                <div class="time-list">
                  <div>
                    <div><h3>营业时间</h3></div>
                    <div>常规营业时间：10:00-18:00</div>
                    <div>2022年12月31日至2023年1月2日：10:00-21:00
                      2023年1月22日至2023年1月28日：10:00-21:00
                      （闭园前2小时停止售票，验票入园时间与营业时间同步；入园时间如有变动，请以园区
                      公告为准。）</div>
                  </div>
                  <br style="background: #cecece;height: 1px;width: 100%">
                  <div>
                    <div><h3>今日暂停开放设施</h3></div>
                    <div>森林神庙、蹦跳车、欢乐老爷车、
                      激浪旋艇、迷你飞行器、疯狂巴士、
                      奇妙车队、火箭过山车、滑翔飞翼、
                      欢乐摩天轮、音乐船、快乐干线</div>
                  </div>
                </div>
              </div>
              <div class="text-btn">
                <a class="text-btn-a"></a>
              </div>
              <img style="width: 100%;top: 2px" src="https://cdn.chimelong.com/upload/adfbfa03eea725de/c14aebcf3782b6fe.png">
            </div>
          </div>
          <div title="乐园地图" class="item">
            <div class="title-h2"><h2>开放时间</h2></div>
            <div class="cont">
              <div class="cont-content">

              </div>
              <div class="text-btn">
                <a class="text-btn-a">在线购票</a>
              </div>
              <img style="width: 100%;top: 2px" src="https://cdn.chimelong.com/upload/adfbfa03eea725de/c14aebcf3782b6fe.png">
            </div>
          </div>
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
const state = reactive({
  tableDate:[],
  categories:[],
  form:{},
  date:{},
})
// const load = () => {
//   request.get("/facility/page",{
//
//   }).then(res =>{
//     if (res.code === '200'){
//       console.log(res)
//       state.tableDate = res.data.list
//       total.value = res.data.total
//     }
//   })
// }
</script>

<style scoped>

.el-carousel__item h3 {
  color: #475669;
  opacity: 0.75;
  line-height: 150px;
  margin: 0;
  text-align: center;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n + 1) {
  background-color: #d3dce6;
}
.ticket-left{
  background-color: #fab516;
  position: relative;
  width: 26%;
  height: 100%;
  float: left;
}
.ticket-left::after{
  position: absolute;
  top: 50%;
  left: -10px;
  transform: translateY(-50%);
  content: "";
  display: block;
  width: 20px;
  height: 20px;
  border-radius: 50%;
  background: #faf5ec;
}
.ticket-right{
  position: relative;
  width: 74%;
  height: 70px;
  float: right;
}
.item{
  padding-left: 5px;
  padding-right: 5px;
  width: 33.33333333%;
}
.ticket-category{
  overflow: hidden;
  border-radius: 10px;
  height: 70px;
  background: #fff;
  text-align: center;
  margin-bottom: 15px;
  display: block;
}
.cont{
  width: 100%;
  height: 100%;
  position: relative;
  border-radius: 20px;
  box-shadow: 2px 2px 3px 0 rgb(0 0 0 / 10%);
  background: #faf5ec;
  overflow: hidden;
}
.cont-content{
  width: 100%;
  height: 450px;
  padding: 50px 35px 0;
}
.title-h2{
  text-align: center;
  color: #4c4c4c;
  margin: 0 auto 30px;
  font-weight: 400;
}
.ticket-right-word{
  padding: 0 20px 0 10px;
  position: relative;
  top: 50%;
  transform: translateY(-50%);
}
.ticket-left-word{
  padding-left: 10px;
  position: relative;
  top: 50%;
  transform: translateY(-50%);
  color: #fff;
}
.timer{
  margin: 0 auto 15px;
  width: 65px;
}
.text-btn{
  text-align: center;
}
.text-btn a{
  margin: 0 auto;
  padding: 12px 48px;
  border: 1px solid #333;
  border-radius: 20px;
  overflow: hidden;
  transition: 0.3s;
  font-size: 14px;
  font-weight: bold;
}
.text-btn a:hover{
  background-color: #fab516;
  padding: 10px 40px;
  color: white;
  border: 1px solid #fab516;
}
</style>