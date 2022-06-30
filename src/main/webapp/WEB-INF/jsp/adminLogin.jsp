<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
<title>管理者ログイン</title>
</head>
<body>
	<header>
		<img class="page-header" src="images/header-no-characters.png">
		<a href="/RivalsOfAether/MainController"><img class="logo" src="images/logo.png" alt="ホーム画面に戻る"></a>
	</header>
<%
	String message = (String) request.getAttribute("message");
	if(message != null){
%>
	<p><font color="red"><%= message %></font></p>
<%
	}
%>
	<div class="form">
		<p>管理用パスワードを入力してください</p>
		<form action="/RivalsOfAether/AdminController" method="post">
		<input type="password" name="adminPass">
		<input type="hidden" name="state" value="login">
		<input type="submit"  class="button">
		</form>
	</div>
	<footer>
		<a href="/RivalsOfAether/MainController">戻る</a>
		
	</footer>
</body>
</html>