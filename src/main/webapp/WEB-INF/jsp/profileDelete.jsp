<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>アカウント削除</title>
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
</head>
	<header>
		<img class="page-header" src="images/header-no-characters.png">
		<a href="/RivalsOfAether/MainController"><img class="logo" src="images/logo.png" alt="ホーム画面に戻る"></a>
	</header>
<body>
<%	String message = (String) request.getAttribute("message");
	if(message != null){ %>
		<p><font color="red"><%= message %></font></p>
<%	} %>
	<div class="form">
		<p>アカウントを削除します。<br>
		よろしければパスワードを入力してください。</p>
		<form action="/RivalsOfAether/MainController" method="post">
			<input type="password" name="pass">
		
		<input type="hidden" name="state" value="delete">
		<input type="submit" value="削除" class="button">
		<br>
		</form>
	</div>	
	<footer>
		<a href="/RivalsOfAether/MainController">戻る</a>
		
	</footer>
</body>
</html>