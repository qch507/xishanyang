<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>西塞山旅游度假区</title>
<link href="css.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="css/responsiveslides.css">
<link rel="stylesheet" href="css/style.css">
<script type="text/javascript" src="js/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="js/responsiveslides.min.js"></script>
<script type="text/javascript">
// You can also use "$(window).load(function() {"
$(function () {

  // Slideshow 4
  $("#slider4").responsiveSlides({
	auto: false,
	pager: false,
	nav: true,
	speed: 500,
	namespace: "callbacks",
	before: function () {
	  $('.events').append("<li>before event fired.</li>");
	},
	after: function () {
	  $('.events').append("<li>after event fired.</li>");
	}
  });

});
</script>
</head>

<body>
<div class="head_bj">
  <div class="poem_zb"><img src="img/poem.png" /></div>
  <div class="ty1100 mt120">
    <div class="float_l"><img src="img/logo.png" width="200" height="100" /></div>
    <div class="menu">
      <ul><li><a href="./main">网站首页</a></li>
        <li><a href="./newslist">度假区动态</a></li>
        <li><a href="djqdl.html">度假区导览</a></li>
        <li><a href="./themelist">度假区风光</a></li>
        <li><a href="djqxmbf.html" class="color01">霞幕北飞</a></li>
        <li><a href="djqzxyd.html">在线预订</a></li>
        <li><a href="#">妙在西塞山</a></li>
        <li><a href="./feedback">互动天地</a></li>
      </ul>
    </div>
  </div>
  <div class="ty1100 mt35">
    <div><img src="img/zbj_t.png" width="1100" height="29" /></div>
    <div class="zbj_c">
      <div class="float_l w645">
        <div class="ty_title01"><img src="img/t1.png" width="300" height="74" /></div>
        <div class="txt01" style="word-break:keep-all; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">
        <c:forEach items="${notice}" var="notice">
          <a href="./newsdetail?id=${notice.id}">[公告]${notice.title }</a><br />
          </c:forEach>
          </div>
          
        <div class="ty_title01"><img src="img/t2.png" width="300" height="74" /></div>
        <div class="mt25">吴兴西塞山旅游度假区位于浙江省湖州市吴兴区妙西镇西部，总占地面积约48.36平方公里，是一个集产业、休闲、观光、富民“四位一体”的省级平台，有四个明显特点：一是交通区位优。度假区处于上海、杭州、南京、安吉的黄金交叉点，已完全融入长三角都市二小时经济圈。二是生态环境好。山水、田园、村舍构筑起极佳的原生态环境，真正诠释了“绿水青山就是金山银山”的真谛。三是文化底蕴深。自古以来，妙西镇就有“僧儒道一体、诗禅茶一味”的美誉，禅茶文化...[<a href="#">查看详情</a>]</div>
        
        <div class="ty_title01"><img src="img/t4.png" width="300" height="74" /></div>
        <div class="jd_we">
          <div class="jd_bj"><a href="#"><img src="img/jd01.jpg" width="210" height="139" /></a></div>
          <div class="jd_bj"><a href="#"><img src="img/jd02.jpg" width="210" height="139" /></a></div>
          <div class="jd_bj"><a href="#"><img src="img/jd03.jpg" width="210" height="139" /></a></div>
        </div>
        <div class="jd_we">
          <div class="jd_bj"><a href="#"><img src="img/jd04.jpg" width="210" height="139" /></a></div>
          <div class="jd_bj"><a href="#"><img src="img/jd05.jpg" width="210" height="139" /></a></div>
          <div class="jd_bj"><a href="#"><img src="img/jd06.jpg" width="210" height="139" /></a></div>
        </div>
        <div class="ty_title01"><img src="img/t3.png" width="300" height="74" /></div>
        <div>
          <div class="fg_bj"><a href="#"><img src="img/fg01.jpg" width="300" height="200" /></a></div>
          <div class="fg_bj"><a href="#"><img src="img/fg02.jpg" width="300" height="200" /></a></div>
          <div class="fg_bj"><a href="#"><img src="img/fg03.jpg" width="300" height="200" /></a></div>
          <div class="fg_bj"><a href="#"><img src="img/fg04.jpg" width="300" height="200" /></a></div>
        </div>
      </div>
      <div class="float_r w288">
        <div class="ty_title02"><img src="img/u798-4.png" width="131" height="42" /></div>
        <div>
          <p style="text-align: center">
            <iframe class="video_iframe" style="z-index:1;" src="http://v.qq.com/iframe/player.html?vid=h0170fmb55t&amp;width=287&amp;height=180&amp;auto=0" allowfullscreen="" frameborder="0" height="180" width="287"></iframe>
          </p>
        </div>
        <div class="ty_title02"><img src="img/u934-4.png" width="131" height="42" /></div>
        <div><a href="djqzxyd.html"><img src="img/kstd_01.jpg" width="287" height="74" /></a></div>
        <div><a href="djqzxyd_chaxun.html"><img src="img/kstd_02.jpg" width="287" height="57" /></a></div>
        <div><a href="#"><img src="img/kstd_03.jpg" width="287" height="74" /></a></div>
        <div class="tianqi">
          <iframe allowtransparency="true" frameborder="0" width="290" height="96" scrolling="no" src="http://tianqi.2345.com/plugin/widget/index.htm?s=2&z=1&t=0&v=0&d=2&bd=0&k=000000&f=&q=1&e=1&a=1&c=54511&w=290&h=96&align=center"></iframe>
        </div>
        <div class="ty_title02"><img src="img/u1048-4.png" width="131" height="42" /></div>
        <div class="news_sy">
          <ul>
          <c:forEach items="${news}" var="news">
            <li style="word-break:keep-all; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;"><a href="./newsdetail?id=${news.id}">[动态]${news.title }</a></li>
           </c:forEach>
          </ul>
          <div class="txt01 txt_r"><a href="./newslist">更多>>></a></div>
        </div>
        <div class="ty_title02"><img src="img/u1057-4.png" width="131" height="42" /></div>
        <div class="bbs_sy">
          <ul>
          <c:forEach items="${feedback}" var="feedback">
            <li>·来自[${feedback.name }]的留言<br />
              <fmt:formatDate value="${ feedback.create_time }" pattern="yyyy/MM/dd HH:mm" type="date" dateStyle="long" /><br />
              <div style="width:250px; word-break:keep-all; white-space:nowrap; overflow:hidden; text-overflow:ellipsis;">“${feedback.content }”</div></li>
              </c:forEach>
          </ul>
          <div class="txt01 txt_r"><a href="./feedback">更多>>></a></div>
        </div>
        <div class="ty_title02"><img src="img/u3369-4.png" width="200" height="42" /></div>
        <div>
          <div id="wrapper">
            <div class="callbacks_container">
              <ul class="rslides" id="slider4">
                <li> <a href="#"><img src="img/ttc01.jpg" alt=""></a>
                  <p class="caption">四锦黄桃</p>
                </li>
                <li> <a href="#"><img src="img/ttc02.jpg" alt=""></a>
                  <p class="caption">妙硒米</p>
                </li>
                <li> <a href="#"><img src="img/ttc03.jpg" alt=""></a>
                  <p class="caption">三癸茶</p>
                </li>
              </ul>
            </div>
          </div>
        </div>
      </div>
    </div>
    <div><img src="img/zbj_b.png" width="1100" height="29" /></div>
  </div>
  <div class="b_bj">
    <div class="b_menu">
      <ul><li><a href="./main">网站首页</a></li>
        <li><a href="./newslist">度假区动态</a></li>
        <li><a href="djqdl.html">度假区导览</a></li>
        <li><a href="./themelist">度假区风光</a></li>
        <li><a href="djqxmbf.html" class="color01">霞幕北飞</a></li>
        <li><a href="djqzxyd.html">在线预订</a></li>
        <li><a href="#">妙在西塞山</a></li>
        <li><a href="./feedback">互动天地</a></li>
      </ul>
    </div>
    <div class="b_txt">游览电话:0572-2532004/3120655/3121622 | 传真:0572-3120671 | 投诉电话:0572-2530838<br />Copyright 2016 All Rights Reserved.湖州妙西旅游开发有限公司 版权所有<br />网站IPC：浙ICP备16027868号<br />吴兴西塞山旅游度假区办公室 主办<br />技术支持：湖州二十一城信息科技有限公司</div>
  </div>
</div>
</body>
</html>
