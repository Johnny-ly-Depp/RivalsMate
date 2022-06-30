<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ログイン</title>
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
				<dd><input type="text" name="userId" value=<%= userId %>></dd>
			<%	} else {	%>
				<dd><input type="text" name="userId"></dd>
			<%	}	%>
				<dt><label>パスワード</label></dt>
				<dd><input type="password" name="pass"></dd>
		
				<dd><input type="hidden" name="state" value="login"></dd>
		</dl>	
		  <div class="clear_float"></div>
		
			<div class="button_wrapper_login">
				<input class="button" type="submit" value="ログイン">
			</div>
	</form>
	</div>
	
	<footer>
		<a href="/RivalsOfAether/MainController">戻る</a>
		<a href="/RivalsOfAether/MainController?state=register">新規登録</a>
	</footer>
	
		
</body>
</html>