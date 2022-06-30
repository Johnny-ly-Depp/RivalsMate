<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.MatchResult, java.util.ArrayList"	%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/otherPages.css">

<title>試合管理</title>
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
				<input type="hidden" name="state" value="searchMatch">
				<br>
				<input type="submit" value="プレイヤーIDから試合を検索" class="button">
			</form>
			<br>
			<%	ArrayList<MatchResult> result = (ArrayList<MatchResult>) session.getAttribute("admin");	%>
			<table border="1">
			<tr>
				<th>1P</th><th>2P</th><th>
			</tr>
		<% 	if(result != null){	%>
			<form action="/RivalsOfAether/AdminController" method="post">
		<% 		for(MatchResult match: result){	%>
					<tr>
						<td><%= match.getPlayer1() %></td><td><%= match.getPlayer2() %></td><td><input type="checkbox" name="matches" value="<%= match.getMatchId() %>"></td>
			<% 	}	%>
					</tr>	
			</table>
			<br>
			<select name= "execution">
			<option value="">選択してください</option>
			<option value="swap">勝敗を変更</option>
			<option value="delete">試合を削除</option>
			</select>
			<input type="hidden" name="state" value="manageMatch">
			<input type="submit" value="実行" class="button">
			</form>
		<%	} else { %>
			</table>
		<% } %>	
		</div>
	<br>
	</main>
	<footer>
		<a href="/RivalsOfAether/MainController">メイン画面に戻る</a>
		<a href="/RivalsOfAether/AdminController?state=accountManagement">アカウント管理</a>
	</footer>
</body>
</html>