<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>新規登録</title>
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
</head>
<body>
	<header>
		<img class="page-header" src="images/header.png">
		<a href="/RivalsOfAether/MainController"><img class="logo" src="images/logo.png" alt="ホーム画面に戻る"></a>
	</header>
	
	<%	String message = (String) request.getAttribute("message");
		if(message != null){	%>
			<p><font color="red"><%= message %></font></p>
	<%	}	%>
	<div class="form">
	<form action="/RivalsOfAether/MainController" method="post">
		<dl>
			<dt><label>ユーザーID</label></dt>
	<%	if(message != null){
			String userId = (String) request.getAttribute("userId"); %>
			<dd><input type="text" name="userId"  pattern="^[a-zA-Z0-9]+$" value=<%= userId %>></dd>
	<%	} else {	%>
			<dd><input type="text" name="userId" pattern="^[a-zA-Z0-9]+$"></dd>
	<%	}	%>

			<dt><label>パスワード</label></dt>
			<dd><input type="password" name="pass" pattern="^[a-zA-Z0-9]+$"></dd>
			<dt><label>パスワードの再確認</label></dt>
			<dd><input type="password" name="passCheck" pattern="^[a-zA-Z0-9]+$"></dd>
			<dd><input type="hidden" name="state" value="register"></dd>
			<div  class="button_wrapper">
				<input class="button" type="submit" value="新規登録">
			</div>
		</dl>
		
		
	</form>
	</div>
	
	<footer>
		<a href="/RivalsOfAether/MainController?state=login">戻る</a>
	</footer>
	
</body>
</html>