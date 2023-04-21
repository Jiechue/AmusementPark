<template>
  <div style="margin: auto;min-width: 1024px;">
    <div class="block text-center">
      <el-carousel height="500px" style="width: 1424px;margin-left: auto;margin-right: auto">
        <el-carousel-item v-for="item in state.url" :key="item">
<!--          <h3 class="small justify-center" text="2xl"><img src="{{state.test.url}}"></h3>-->
          <img :src="item" style="width: 100%;height: 100%">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
      <div style="background-color: white;border-radius: 20px;padding-left: 20px;padding-right: 20px;box-shadow: 2px 2px 3px 2px rgb(0 0 0 / 10%);">
        <div style="margin-left: 20px;margin-right: 20px;margin-top: 10px">
          <h1>{{ state.form.title }}</h1>
          <div><hr></div>
          <div style="display: flex;justify-content: space-between">
            <div style="width: 60%;font-size: 18px">{{state.form.description}}</div>
<!--            <div style="width: 28%;height: 120px;background-color: #ebf6fc;border-radius: 12px;padding: 20px">-->
<!--              <div>开放或演出时间</div>-->
<!--              <div>星期五</div>-->
<!--              <div>上午 8:30 至 晚上 9:30</div>-->
<!--            </div>-->
          </div>
          <div style="font-size: 20px;margin-top: 20px">精选设施</div>
        </div>
        <div><hr></div>
        <ul>
          <li class="list-li" v-for="(item) in state.facilityDate">
            <div class="list-li-container">
              <a @click="$router.push('/reception/facility/'+item.id)">
                <div style="width: 90%;height: 250px;margin: auto">
                  <img style="width: 100%;height: 100%" :src="item.cover">
                </div>
                <div style="margin: 10px;display: flex;justify-content: space-between">
                  <div class="list-li-text">
                    <h3>{{ item.name }}</h3>
                    <div style="display: flex;justify-content: space-between;width: 400px">
                      <div>{{ item.description }}</div>
                      <div style="margin-left: 20px;vertical-align: top;">
                        <div style="font-weight: bold">开放时间</div>
                        <div style="font-size: 10px">{{ item.opentime }}</div>
                        <div style="font-size: 10px">游客身高：{{item.height}}</div>
                        <div style="font-size: 10px">适合年龄：{{item.age}}</div>
                      </div>
                    </div>
                    <div style="display:flex;justify-content: space-between">
                      <div>好评数：{{item.liketotal}}</div>
                      <div v-if="item.enable" style="font-weight: bold;color: green">可以游玩</div>
                      <div v-if="!item.enable" style="font-weight: bold;color: red">暂未开放</div>
                    </div>
                  </div>
                  <div class="list-li-icon"></div>
                </div>
              </a>
            </div>
          </li>
        </ul>
      </div>
      <div style="text-align: center">
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
                <a class="text-btn-a" href="#">在线购票</a>
              </div>
              <img style="width: 100%;top: 30px;position: relative;" src="https://cdn.chimelong.com/upload/adfbfa03eea725de/c14aebcf3782b6fe.png">
              <div class="cont-bottom"></div>
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
                    <div style="float: left" v-html="state.form.opentime"></div>
                  </div>
                  <br style="background: #cecece;height: 1px;width: 100%">
                </div>
              </div>
              <img style="width: 100%;top: 50px;position: relative;" src="https://cdn.chimelong.com/upload/adfbfa03eea725de/c14aebcf3782b6fe.png">
              <div class="cont-bottom" style="top: 46px"></div>
            </div>
          </div>
          <div title="乐园地图" class="item">
            <div class="title-h2"><h2>乐园地图</h2></div>
            <div class="cont">
              <div class="cont-content">
                <el-image
                    style="width: 250px; height: 300px"
                    src="https://cdn.chimelong.com/upload/f920f6a5f4dfed92/371697ed00ac7811.png"
                    :zoom-rate="1.2"
                    :preview-src-list="srcList"
                    :initial-index="2"
                    fit="cover"
                />
              </div>
              <div class="text-btn">
                <a class="text-btn-a" href="map">查看地图</a>
              </div>
              <img style="width: 100%;top: 30px;position: relative;" src="https://cdn.chimelong.com/upload/adfbfa03eea725de/c14aebcf3782b6fe.png">
              <div class="cont-bottom"></div>
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
  facilityDate:[],
  categories:[],
  form:{},
  date:{},
  url: [

  ]
})
const srcList = [
  'https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg',
  'https://fuss10.elemecdn.com/1/34/19aa98b1fcb2781c4fba33d850549jpeg.jpeg',
  'https://fuss10.elemecdn.com/0/6f/e35ff375812e6b0020b6b4e8f9583jpeg.jpeg',
  'https://fuss10.elemecdn.com/9/bb/e27858e973f5d7d3904835f46abbdjpeg.jpeg',
  'https://fuss10.elemecdn.com/d/e6/c4d93a3805b3ce3f323f7974e6f78jpeg.jpeg',
  'https://fuss10.elemecdn.com/3/28/bbf893f792f03a54408b3b7a7ebf0jpeg.jpeg',
  'https://fuss10.elemecdn.com/2/11/6535bcfb26e4c79b48ddde44f4b6fjpeg.jpeg',
]
const load = () => {
  request.get("/home/load",{

  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.form = res.data
    }
  })
}
const loadImages = () => {
  request.get("/home/load-image",{

  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.url = res.data
    }
  })
}
const loadFacilities = () => {
  request.get("/facility/home",{

  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.facilityDate = res.data
    }
  })
}
load()
loadImages()
loadFacilities()
const test = () => {
  console.log(state.form)
  console.log()
  console.log(state.facilityDate)
}
</script>

<style scoped>
:root{
  --list-color: grey;
}
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
  height: 600px;
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
.cont-bottom{
  top: 26px;
  position: relative;
  width: 100%;
  padding-bottom: 50px;
  background-color: #fff;
}
</style>