<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./header.jsp"></jsp:include>

<script type="text/javascript" src="./js/page.js"></script>

<script type="text/javascript" src="./../fancy/jquery.fancybox.js?v=2.1.5"></script>
<link rel="stylesheet" type="text/css" href="../fancy/jquery.fancybox.css?v=2.1.5" media="screen" />
<title>相册</title>
<script type="text/javascript">
	$(function() {
		menuselect(1);
		
	})
	
	function opengallery(id){
		$('.fancybox-'+id).fancybox();
		$.fancybox.open($('.fancybox-'+id));
	}
</script>
</head>
<body>


	<div class="container-fluid">

		<jsp:include page="./nav.jsp"></jsp:include>

		<div class="row-fluid">
			<div class="span12">






				<!-- 模态框（Modal） -->
				<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header">
								<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
								<h4 class="modal-title" id="myModalLabel">上传新图片</h4>
							</div>

							<form action="./uploadimg" method="post" enctype="multipart/form-data">

								<div class="modal-body">
									主题名：<input type="text" name="theme" /><br /> 摄影者：<input type="text" name="name" /><br /> 图&nbsp;&nbsp;&nbsp;&nbsp;片：<input type="file" name="imgfile" /><br />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<input type="submit" class="btn btn-primary" value="添加" />
								</div>

							</form>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>





				<button class="btn btn-primary" type="button" data-toggle="modal" data-target="#myModal">添加</button>
				<div style="height: 20px; width: 100%;"></div>
				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>主题</th>
							<th>上传用户</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${themelist}" var="theme">
							<tr>
								<td>${theme.theme}</td>
								<td>${theme.name}</td>
								<td>
								<c:set value="${theme.imgs }" var="imgs"/>

								<button class="btn btn-primary" type="button" onclick="opengallery(${theme.id});">查看图片</button>
								<div style="display: none;">
								<c:forEach items="${imgs }" var="imgurl">
								<a class="fancybox-${theme.id}" href="${path}${imgurl}" data-fancybox-group="gallery">-----</a>
								</c:forEach>
								</div>
								<a href="./deltheme?theme=${theme.theme}"><button class="btn btn-primary" type="button">删除主题</button></a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			
			
						<div class="span12">
				<c:if test="${ pageIndex != null && total > 1 }">
					<form id="pageForm" action="./imgthemelist">
						<input type="hidden" value="${ total }" id="pageCount" name="pageCount" /> 
						<input type="hidden" value="${ pageIndex }" id="pageIndex" name="pageIndex" />
					</form>
					<ul id="pagination" class="pagination">

					</ul>
					<script type="text/javascript">
						setPage(document.getElementById("pagination"),
								parseInt($("#pageCount").val()), parseInt($(
										"#pageIndex").val()));
					</script>
				</c:if>
			</div>
			
			
		</div>
	</div>
</body>
</html>
