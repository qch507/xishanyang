<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./header.jsp"></jsp:include>
<script type="text/javascript" src="./js/page.js"></script>
<title>资讯</title>
<script type="text/javascript">
	$(function() {
		menuselect(0);
	});
</script>
</head>
<body>


	<div class="container-fluid">

		<jsp:include page="./nav.jsp"></jsp:include>

		<div class="row-fluid">
			<div class="span12">
				<a href="./newsdetail"><button class="btn btn-primary" type="button">添加</button></a>
				<div style="height: 20px; width: 100%;"></div>
				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>编号</th>
							<th>标题</th>
							<th>类型</th>
							<th>发布时间</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${newslist}" var="news">
							<tr>
								<td>${news.id}</td>
								<td>${news.title}</td>
								<td><c:if test="${news.type==0}">普通新闻</c:if>
								<c:if test="${news.type==1}">首页公告</c:if></td>
								<td><fmt:formatDate value="${ news.create_time }" pattern="yyyy/MM/dd HH:mm" type="date" dateStyle="long" /></td>
								<td><a href="./newsdetail?newsid=${news.id}"><button class="btn btn-primary" type="button">修改</button></a> <a href="./delnews?newsid=${news.id}"><button class="btn btn-primary" type="button">删除</button></td>
								</a>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>

			<div class="span12">
				<c:if test="${ pageIndex != null && total > 1 }">
					<form id="pageForm" action="./newslist">
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
