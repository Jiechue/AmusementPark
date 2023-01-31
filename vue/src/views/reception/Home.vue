<template>
  <div style="margin: auto;min-width: 1024px;">
    <div class="block text-center">
      <el-carousel height="500px">
        <el-carousel-item v-for="item in state.test" :key="item">
<!--          <h3 class="small justify-center" text="2xl"><img src="{{state.test.url}}"></h3>-->
          <img :src="item" style="width: 100%;height: 100%">
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
      <div style="background-color: white;border-radius: 20px;padding-left: 20px;padding-right: 20px;box-shadow: 2px 2px 3px 2px rgb(0 0 0 / 10%);">
        <div style="margin-left: 20px;margin-right: 20px;margin-top: 10px">
          <h1>迪士隆欢乐世界</h1>
          <div style="display: flex;justify-content: space-between">
            <div style="width: 60%;font-size: 18px">长隆欢乐世界位于广州长隆旅游度假区的中心位置，是具备国际先进游乐设施和管理水平的超大型主题游乐园。长隆欢乐世界引进有全球领先的游乐设施70余套，其中包括有“全球过山车之王”之称的垂直过山车、创吉尼斯世界纪录的十环过山车、荣获行业设计金奖的摩托过山车、东半球唯一的U型滑板、超级大摆锤、亚洲先进四维影院、原创时空探险项目《星际决战》、奇幻马戏主题欢乐小镇、万人超级演艺广场、欢乐世界大巡游，集世界精彩游乐设施与大型演艺于一体。长隆欢乐世界曾经创下单日接待游客超过10万人的主题公园记录，被誉为“中国新一代游乐园的典范之作”。！</div>
            <div style="width: 28%;height: 120px;background-color: #ebf6fc;border-radius: 12px;padding: 20px">
              <div>开放或演出时间</div>
              <div>星期五</div>
              <div>上午 8:30 至 晚上 9:30</div>
            </div>
          </div>
          <div style="font-size: 20px;margin-top: 20px">乐园体验</div>
        </div>
        <div><hr></div>
        <ul>
          <li class="list-li">
            <div class="list-li-container">
              <a href="http://localhost:8080">
                <div style="width: 90%;height: 250px;margin: auto">
                  <img width="100%" height="100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
                </div>
                <div style="margin: 10px;display: flex;justify-content: space-between;text-align: left">
                  <div class="list-li-text">
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
          <li style="width: 48%;display: inline-block">
            <div class="list-li-container">
              <a href="http://localhost:8080">
                <div style="width: 90%;height: 250px;margin: auto">
                  <img width="100%" height="100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
                </div>
                <div class="list-li-text">
                  <div>迪士尼奇幻冬日巡游</div>
                  <div>全新的迪士尼奇幻冬日巡游来啦！迪士尼朋友们将带你沉浸于经典冬日童话中！</div>
                  <div>*效果请以实际运营情况为准</div>
                </div>
              </a>
            </div>
          </li>
          <li style="width: 48%;display: inline-block">
            <img src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
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
                <a class="text-btn-a">在线购票</a>
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
                    src="https://fuss10.elemecdn.com/a/3f/3302e58f9a181d2509f3dc0fa68b0jpeg.jpeg"
                    :zoom-rate="1.2"
                    :preview-src-list="state.test"
                    :initial-index="2"
                    fit="cover"
                />
              </div>
              <div class="text-btn">
                <a class="text-btn-a">在线购票</a>
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
      "https://img0.baidu.com/it/u=1705694933,4002952892&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1674061200&t=a5c4072f26119d5c590541d62bec8127"
  ],
  test:[
    "https://img0.baidu.com/it/u=1705694933,4002952892&fm=253&app=138&size=w931&n=0&f=JPEG&fmt=auto?sec=1674061200&t=a5c4072f26119d5c590541d62bec8127",
    "https://img2.baidu.com/it/u=1395980100,2999837177&fm=253&app=120&size=w931&n=0&f=JPEG&fmt=auto?sec=1674061200&t=8805a0cb4ac251db52c1b4cfbe42f1ed",
  ]
})
const loadFacilities = () => {
  request.get("/facility/home",{

  }).then(res =>{
    if (res.code === '200'){
      console.log(res)
      state.facilityDate = res.data.list
    }
  })
}
loadFacilities()
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
  background-color: white;
  padding: 20px;
}
.list-li-container a:hover img{
  transform:scale(105%,105%);
  overflow: hidden;
  transition: 0.3s;
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
  top: 0;
  left: 0;
  border: 16px solid;
  border-color: transparent transparent transparent rgba(161,175,192,0.3);
}
.list-li-icon::after{
  content: '';
  position: absolute;
  top: 6px;
  left: -2px;
  border: 10px solid;
  border-color: transparent transparent transparent white;
}
.list-li-text{
  margin: 10px;
}
.cont-bottom{
  top: 26px;
  position: relative;
  width: 100%;
  padding-bottom: 50px;
  background-color: #fff;
}
</style>