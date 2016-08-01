<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">

<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap.min.css">

<!-- 可选的Bootstrap主题文件（一般不用引入） -->
<link rel="stylesheet" href="//cdn.bootcss.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="//cdn.bootcss.com/jquery/1.11.3/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="//cdn.bootcss.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
<title>登录</title>
</head>
<style>
.modal {
	display: block;
}

.fade {
	opacity: 1;
}

.modal-header, .modal-body, .modal-footer {
	padding: 20px;
}

.modal-input-span {
	width: 100%;
	height: 36px;
	font-size: 20px;
}
</style>

<body style="background-color: #f9f9f9">
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="false">
		<div class="modal-dialog" style="margin: 300px auto; width: 400px;">
			<div class="modal-content" style="padding: 0px 51px 0px 45px;">
				<form action="./login" id="login_form" method="post">
					<div class="modal-header" style="text-align: center;">
						<h4 class="modal-title" style="font-size: 27px" id="myModalLabel">后台管理</h4>
					</div>
					<div class="modal-body">
						<div class="modal-input-span">用户名：</div>
						<div style="width: 100%; height: 40px;">
							<input type="text" id="user_name" name="user_name" class="form-control" style="min-width: 259px;" />
						</div>
						<div class="modal-input-span" style="margin-top: 10px;">密&emsp;码：</div>
						<div style="width: 100%; height: 40px;">
							<input type="password" id="password" name="password" class="form-control" style="min-width: 259px;" />
						</div>
					</div>
					<div class="modal-footer" style="text-align: center;">
						<input type="submit" id="login_btn" style="width: 100%;" class="btn btn-primary" value="登录">
					</div>
				</form>
			</div>
		</div>
	</div>
	<script>
		function login() {
			var user_name = $('#user_name').val();
			if (user_name == '') {
				alert('用户名不能为空！');
				return false;
			}
			var password = $('#password').val();
			if (password == '') {
				alert('密码不能为空！');
				return false;
			}
			$.ajax({
				type : 'post',
				url : 'login',
				data : $('#login_form').serialize(),
				success : function(data) {
					console.log(data);
					if (data != null) {
						if (data.code == 0) {
							window.location.href = 'userinfo';
						} else {
							alert(data.message);
						}
					}
				}
			});

		}

		function keyLogin() {
			if (event.keyCode == 13) //回车键的键值为13
				document.getElementById("login_btn").click(); //调用登录按钮的登录事件
		}
	</script>
</body>
</html>