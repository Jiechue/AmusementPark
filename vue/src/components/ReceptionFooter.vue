<template>
  <div style="margin-top: 40px;background-color: white">
    <div style="width: 1024px;margin-left: auto;margin-right: auto">
      <div class="footer-container">
        <div class="footer-list">
          <div>乐园与演艺</div>
          <ul class="list-ul">
            <li><a href="">长隆欢乐世界</a></li>
            <li><a href="">长隆水上乐园</a></li>
            <li><a href="">长隆野生动物世界</a></li>
            <li><a href="">长隆飞鸟乐园</a></li>
            <li><a href="">长隆国际大马戏</a></li>
            <li><a href="">全部门票</a></li>
            <li><a href="">广州长隆年卡</a></li>
          </ul>
        </div>
        <div class="footer-list">
          <div>酒店住宿</div>
          <ul class="list-ul">
            <li><a href="">长隆酒店</a></li>
            <li><a href="">长隆熊猫酒店</a></li>
            <li><a href="">长隆香江酒店</a></li>
          </ul>
        </div>
        <div class="footer-list">
          <div>出行助手</div>
          <ul class="list-ul">
            <li><a href="">行前准备</a></li>
            <li><a href="">酒店穿梭巴服务</a></li>
            <li><a href="">酒店入住时间与行李寄存</a></li>
            <li><a href="">行程推荐</a></li>
            <li><a href="">长隆国际大马戏</a></li>
            <li><a href="">交通指南</a></li>
            <li><a href="">广州度假区地图</a></li>
          </ul>
        </div>
        <div class="footer-list">
          <div>服务支持</div>
          <ul class="list-ul">
            <li><a href="">客服中心</a></li>
            <li><a href="">问题反馈</a></li>
            <li><a href="">暴雨台风预案</a></li>
            <li><a href="">景区游客承载量</a></li>
            <li><a href="">常见问题</a></li>
            <li><a href="">APP下载</a></li>
            <li><a href="">壁纸下载</a></li>
            <li><a href="">地图下载</a></li>
          </ul>
        </div>
        <div class="footer-list">
          <div>动植物研创院</div>
          <ul class="list-ul">
            <li><a href="">长隆动植物研创院</a></li>
          </ul>
        </div>
        <div class="footer-list">
          <div>网上预订</div>
          <ul class="list-ul">
            <li><a href="">立即购买</a></li>
          </ul>
        </div>
        <div style="margin-left: 50px">
          <div>
            <div style="width: 80px"><img src="../assets/329f580aa74d88f1.jpg" width="100%"></div>
            <p>游乐园APP</p>
          </div>
          <div>
            <div style="width: 80px"><img src="../assets/travel.4a488f6.png" width="100%"></div>
            <p>关注游乐园</p>
          </div>
        </div>
      </div>
      <div class="contact-us">
        <a href="">客服热线：400-883-0083</a>
        <div>（人工服务时间：乐园、演艺、卡类咨询：9:00-18:00，酒店预定咨询：9:00-20:30，自助语音服务时间：全天24小时）</div>
      </div>
      <div class="first-head">
        <div><a href="">长隆集团</a></div>
        <div><a href="">广州长隆度假区</a></div>
        <div><a href="">珠海长隆度假区</a></div>
        <div><a href="">人才招聘</a></div>
      </div>
      <div class="second-head">
        <a href="">网站地图</a>
        <a href="">经营资质</a>
        <a href="">可信任网站</a>
        <a href="">隐私政策</a>
      </div>
    </div>
    <div style="background: #fab516;text-align: center;padding-top: 12px;padding-bottom: 12px"><a href="" style="color:white;">粤ICP备11002274号 ©XX集团有限公司 版权所有</a></div>
  </div>
</template>

<script>
export default {
  name: "ReceptionFooter"
}
</script>

<style scoped>
a{
  text-decoration: none;
}
.footer-container{
  display: flex;
  width: 1024px;
  margin-top: 30px;
  padding-left: 20px;
  padding-top: 20px;
}
.list-ul{
  list-style: none;
  padding-left: 0;
  padding-right: 10px;
  width: 142px;
}
.list-ul li a{
  color: #a7a9ac;
  line-height: 26px;
  font-size: 14px;
  font-weight: 400;
  transition: 0.3s;
}
.list-ul li a:hover{
  color: #fab516;
}
.contact-us{
  margin-top: 20px;
  text-align: center;
}
.contact-us a{
  font-size: 16px;
  font-weight: bolder;
  line-height: 16px;
  color: #fff;
  padding: 12px 48px;
  overflow: hidden;
  transition: 0.3s;
  border-radius: 20px!important;
  background-color: #fab516;
}
.contact-us a:hover{
  padding: 8px 48px;
}
.contact-us div{
  margin-top: 15px;
  font-size: 10px;
  color: #a7a9ac;
}
.first-head{
  margin-top: 20px;
  text-align: center;
}
.first-head div{
  display: inline-block;
  margin: 0 15px;
}
.first-head div a{
  font-size: 18px;
  font-weight: bold;
  transition: 0.4s;
  color: black;
}
.first-head div a:hover{
  color: #fab516;
}
.second-head{
  margin-top: 20px;
  text-align: center;
  margin-bottom: 20px;
}
.second-head a{
  padding-right: 20px;
  color: #a7a9ac;
  transition: 0.4s;
}
.second-head a:hover{
  color: #fab516;
}
</style>