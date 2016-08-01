<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./header.jsp"></jsp:include>
<script type="text/javascript" src="./js/page.js"></script>
<title>反馈</title>
<script type="text/javascript">
	$(function() {
		menuselect(4);
	});

	function showModel(fid) {
		$('#replyid').val(fid);
		$('#myModal').modal();
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
								<h4 class="modal-title" id="myModalLabel">回复</h4>
							</div>

							<form action="./feedbackreply" method="post">

								<div class="modal-body">
									回复内容：<input type="text" name="reply" /><br /> <input type="hidden" id="replyid" name="fid" value="" /> <br />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<input type="submit" class="btn btn-primary" value="回复" />
								</div>

							</form>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>








				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>编号</th>
							<th>发布人</th>
							<th>发布内容</th>
							<th>发布时间</th>
							<th>回复内容</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${feedback}" var="fb">
							<tr>
								<td>${fb.id}</td>
								<td>${fb.name}</td>
								<td>${fb.content}</td>
								<td><fmt:formatDate value="${ fb.create_time }" pattern="yyyy/MM/dd HH:mm" type="date" dateStyle="long" /></td>
								<td>${fb.reply }</td>
								<td><button class="btn btn-primary" type="button" onclick="showModel(${fb.id})">回复</button> 
								<a href="./feedbackdel?fid=${fb.id}"><button class="btn btn-primary" type="button">删除</button></a>
								<c:if test="${fb.status==0}">
								<a href="./feedbackshow?fid=${fb.id}"><button class="btn btn-primary" type="button">在前端显示</button></a>
								</c:if>
								<c:if test="${fb.status==1}">
								<a href="./feedbackshow?fid=${fb.id}"><button class="btn btn-primary" type="button">在前端隐藏</button></a>
								</c:if>
								</td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>


			<div class="span12">
				<c:if test="${ pageIndex != null && total > 1 }">
					<form id="pageForm" action="./feedbacklist">
						<input type="hidden" value="${ total }" id="pageCount" name="pageCount" /> <input type="hidden" value="${ pageIndex }" id="pageIndex" name="pageIndex" />
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

