<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Account, java.util.ArrayList"	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
<title>ユーザー管理</title>
</head>
<body>
	<header>
		<img class="page-header" src="images/header-no-characters.png">
	</header>
	<main>
	<%	String message = (String) request.getAttribute("message");
	if(message != null){	%>
		<p><font color="red"><%=message%></font></p>
		<br>
	<%	}	%>
		<div class="form">
			<form action="/RivalsOfAether/AdminController" method="post">
				<input type="text" name="search">
				<input type="hidden" name="state" value="searchAccount">
				<input type="submit" value="ユーザー検索" class="button">
			</form>
			<br>
		
			<table border="1">
			<tr>
				<th>ユーザーID</th><th>プレイヤー名</th><th>
			</tr>
		<%	ArrayList<Account> result = (ArrayList<Account>) session.getAttribute("admin");	
		 	if(result != null){	%>
			<form action="/RivalsOfAether/AdminController" method="post">
		<% 		for(Account account: result){	%>
					<tr>
		<% 				if(!(account.getUserId().equals("admin_player"))){	%>
							<td><%= account.getUserId() %></td><td><%= account.getPlayerName() %></td><td><input type="checkbox" name="deleteAccount" value="<%= account.getUserId() %>"></td>
				<% 		}	%>
					</tr>	
		<% 		}	%>
			</table>
			<br>
			<input type="hidden" name="state" value="deleteAccount" >
			<input type="submit" value="削除" class="button button_middle">	
			</form>
		<% } else { %>
			</table>
		<% } %>	
		</div>
	</main>
	<br>
	

	<footer>
		<a href="/RivalsOfAether/MainController">メイン画面に戻る</a>
		<a href="/RivalsOfAether/AdminController?state=matchManagement">試合管理</a>
	</footer>
</body>
</html>