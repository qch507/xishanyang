<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./header.jsp"></jsp:include>
<script charset="utf-8" src="./editor/kindeditor-all-min.js"></script>
<script charset="utf-8" src="./editor/lang/zh-CN.js"></script>
<title>反馈</title>
<script type="text/javascript">
	$(function() {
		menuselect(0);
	});

//	KindEditor.ready(function(K) {
//		window.editor = K.create('#editor_id');
//	});
	
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('#editor_id', {
			resizeType : 1,
			allowPreviewEmoticons : false,
			allowImageUpload : true,
			uploadJson : './uploadimgjson',
			items : [ 'fontname', 'fontsize', '|', 'forecolor', 'hilitecolor',
					'bold', 'italic', 'underline', 'removeformat', '|',
					'justifyleft', 'justifycenter', 'justifyright',
					'insertorderedlist', 'insertunorderedlist', '|',
					'emoticons', 'image', 'link' ]
		});
	});
</script>
</head>
<body>









	<div class="container-fluid">

		<jsp:include page="./nav.jsp"></jsp:include>

		<div class="row-fluid">
			<form action="./editnews" method="post">
				<input type="hidden" name="id" value="${news.id }" />
				<div class="span12">
					标题：<br /> <input type="text" name="title" style="width: 700px;" value="${news.title }" />
				</div>

				<div class="span12" style="margin-top: 20px;">
					类型 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<select name="type"><option value="0">普通新闻</option>
						<option value="1">首页公告</option></select>
				</div>

				<div class="span12" style="margin-top: 20px;">
					时间 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;(例：2016-08-01 13:00)<br /> <input type="text" name="publishtime" style="width: 700px;" 
					value="<fmt:formatDate value="${news.create_time }" pattern="yyyy-MM-dd HH:mm" type="date" dateStyle="long" />" />
				</div>

				<div class="span12" style="margin-top: 20px; margin-bottom: 20px;">
					<textarea id="editor_id" name="content" style="width: 700px; height: 300px;">${news.content }</textarea>

				</div>

				<div class="span12">
					<input class="btn btn-primary" type="submit" value="提交" />

				</div>
			</form>
		</div>
	</div>








</body>
</html>

