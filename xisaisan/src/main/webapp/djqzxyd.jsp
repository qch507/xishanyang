<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>西塞山旅游度假区_度假区在线预订</title>
<link href="css.css" rel="stylesheet" type="text/css" />

<script type="text/javascript">
function prev(){
	var pindex = parseInt(document.getElementById("pageIndex").value);
	var type = document.getElementById("type").value;
	if(pindex>1){
		window.location.href = "./productlist?type="+type+"&pageIndex="+(pindex-1);
	}
}

function next(){
	var pindex = parseInt(document.getElementById("pageIndex").value);
	var pcount = parseInt(document.getElementById("pageCount").value);
	var type = document.getElementById("type").value;
	if(pindex<pcount){
		window.location.href = "./productlist?type="+type+"&pageIndex="+(pindex+1);
	}
	
}

function start(){
	var type = document.getElementById("type").value;
	window.location.href = "./productlist?type="+type+"&pageIndex=1";
}

function end(){
	var type = document.getElementById("type").value;
	var pcount = parseInt(document.getElementById("pageCount").value);
	window.location.href = "./productlist?type="+type+"&pageIndex="+pcount;
}
</script>
</head>

<body>
<div class="head_bj">
  <div class="poem_zb"><img src="img/poem.png" /></div>
  <div class="ty1100 mt120">
    <div class="float_l"><img src="img/logo.png" width="200" height="100" /></div>
    <div class="menu">
      <ul>
        <li><a href="./main">网站首页</a></li>
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
      <div><img src="img/t5.png" width="310" height="77" /></div>
      <div class="djqzxyd">
       <ul>
       <c:forEach items="${plist}" var="product">
         <li>
           <div class="photo"><img src="${path }${product.pic }" width="272" height="162" /></div>
           <div class="name01">${product.name}</div>
           <div class="txt">${product.intro}</div>
           <div class="botton"><a href="./porder?pid=${product.id }">在线预订</a></div>
         </li>
</c:forEach>
       </ul>
   </div>
   
   <form action="./newslist">
   <input type="hidden" id="pageIndex" name="pageIndex" value="${pageIndex}" />
   </form>
   
   <input type="hidden" id="pageCount" name="pageCount" value="${pageCount}" />
   <input type="hidden" id="type" name="type" value="${type}" />
   <div id="page">【<a onclick="start();">首页</a>】【<a onclick="prev();">上一页</a>】【<a onclick="next();">下一页</a>】【<a onclick="end();">尾页</a>】　页次：${pageIndex}/${pageCount}页　共${total}条记录 　9条记录/页</div>
    
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
