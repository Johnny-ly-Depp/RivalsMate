<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "constant.Constants, model.Account, model.AccountLogic"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
<title>対戦中</title>
</head>
<body>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
    location.hash = "#prepage";
    $(window).on("hashchange", function(){
        location.hash = "#prepage2";
    });
});
</script>

	<header>
		<img class="page-header" src="images/bg-header-thunderstorm.png">
		<img class="logo" src="images/logo.png">
	</header>

<div class="profileName_wrapper">
	<h1 class="profileName">${opponent.playerName}<br>　VS</h1>
	
	
	<%-- 使用キャラクターの画像表示 --%>
	
<%
	Account opponent = (Account) session.getAttribute("opponent");
	String userId = opponent.getUserId();
	AccountLogic bo = new AccountLogic();
	Account account = bo.search(userId);

String main = Constants.allCharactersOfficialOrder[account.getCharacter()];
	String sub = Constants.allCharactersOfficialOrder[account.getCharacterSub()];
	out.print("<img class=\"profileMainCharacter\" src=\"images/profiles/" + main + ".png\">");
	out.print("<img class=\"profileSubCharacter\" src=\"images/profiles/" + sub + ".png\">");
%>	

</div>	
<%
	String message = (String) request.getAttribute("message");
	if(message != null){
%>
	<p><font color="red"><%= message %></font></p>
<%	}	%>	

	<main class = "resultButton">
		<div class="form_wrapper">
			<form action="/RivalsOfAether/MainController" method="post">
				<label for= "win" >
					<img class="resultSendButton" src="images/button-red.png">
					<input id="win" type="radio" name="report" value="-WIN" >
					<div class="resultButtonLetter">WIN</div>
				</label>
				<label for= "lose" >
					<img class="resultSendButton" src="images/button-blue.png">	
					<input id="lose" type="radio" name="report" value="-LOS" >
					<div class="resultButtonLetter">LOSE</div>
				</label>
				<input type="hidden" name="state" value="sendResult">
		</div>	
				<br>
				<input type="submit" value="結果を送信" class="button button_middle">
			</form>
	</main>	
</body>
</html>