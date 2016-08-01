<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<jsp:include page="./header.jsp"></jsp:include>

<script type="text/javascript" src="./js/page.js"></script>

<title>产品</title>
<script type="text/javascript">
	$(function() {
		menuselect(2);

	});
	
	function showModel(pid) {
		$('#pid').val(pid);
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
								<h4 class="modal-title" id="myModalLabel">产品</h4>
							</div>

							<form action="./editproduct" method="post" enctype="multipart/form-data">
							<input type="hidden" id="pid" name="id" value="" /> <br />
								<div class="modal-body">
									名字：<input type="text" name="name"  style="width:370px;"/><br /><br />
									简介：<textarea rows="3" cols="50" name="intro" ></textarea><br /><br />
									类型：<select name="type"><option value="景点门票">景点门票</option>
									<option value="酒店住宿">酒店住宿</option>
									<option  value="土特产">土特产</option></select><br /> <br />
									主&nbsp;&nbsp;&nbsp;&nbsp;图：<input type="file" name="imgfile" /><br />
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
									<input type="submit" class="btn btn-primary" value="提交" />
								</div>

							</form>

						</div>
						<!-- /.modal-content -->
					</div>
					<!-- /.modal -->
				</div>





				<button class="btn btn-primary" type="button" onclick="showModel(0)">添加</button>
				
				<div style="height: 20px; width: 100%;"></div>
				<table class="table table-bordered table-hover table-condensed">
					<thead>
						<tr>
							<th>编号</th>
							<th>名字</th>
							<th>主图</th>
							<th>简介</th>
							<th>类型</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${plist}" var="product">
							<tr>
								<td>${product.id}</td>
								<td>${product.name}</td>
								<td><img src="  ${path }${product.pic}"  height="100px;"/></td>
								<td>${product.intro}</td>
								<td>${product.type}</td>
								<td><button class="btn btn-primary" type="button" onclick="showModel(${product.id})">修改</button>
								<a href="./delproduct?pid=${product.id}"><button class="btn btn-primary" type="button">删除</button></a></td>
							</tr>
						</c:forEach>

					</tbody>
				</table>
			</div>
			
			
									<div class="span12">
				<c:if test="${ pageIndex != null && total > 1 }">
					<form id="pageForm" action="./productlist">
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
