<template>
  <div style="text-align: center;margin: auto">
    <div class="block text-center">
      <el-carousel height="150px">
        <el-carousel-item v-for="item in 4" :key="item">
          <h3 class="small justify-center" text="2xl">{{ item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
    <div style="width: 1024px;margin-left: auto;margin-right: auto;position: relative;">
      <div style="background-color: white;border-radius: 20px;">
        <div>全新奇幻巡游 开启冬日童话</div>
        <div>迪士尼冬日奇幻冰雪节</div>
        <div>当冬日的第一片雪花落在上海迪士尼度假区时，它绽放出了魔法。
          2022年11月28日至2023年2月19日，
          上海迪士尼度假区将迎来“迪士尼冬日奇幻冰雪节”。
          一起庆祝冬天的到来吧！</div>
        <div>乐园体验</div>
        <ul>
          <li class="list-li">
            <div class="list-li-container">
              <a href="http://localhost:8080">
                <div style="width: 90%;height: 250px;margin: auto">
                  <img width="100%" height="100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
                </div>
                <div style="margin: 10px;display: flex;justify-content: space-between;text-align: left">
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
          <li style="width: 48%;display: inline-block">
            <div class="list-li-container">
              <a href="http://localhost:8080">
                <div style="width: 90%;height: 250px;margin: auto">
                  <img width="100%" height="100%" src="https://secure.cdn2.wdpromedia.cn/resize/mwImage/2/434/244/90/wdpromedia.disney.go.com/media/wdpro-shdr-assets/prod/zh-cn-cn/system/images/shdr-ent-disney-winter-magic-cavalcade-hero-20221206.png">
                </div>
                <div style="margin: 10px;">
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