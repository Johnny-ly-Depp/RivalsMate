<%@page import="constant.Constants"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="model.Account, model.MatchResult,model.MatchResultLogic,model.AccountLogic,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rival Mates</title>
<link rel="stylesheet" type="text/css" href="css/main.css">
</head>
<body>
	<header>

		
			<% 	if(session.getAttribute("account") == null){	%>
					<a href="/RivalsOfAether/MainController?state=login"><img class="login" src="images/login.png" alt="ログイン"></a>
			<%	} else { %>
				<div class="buttons">
					<form action="/RivalsOfAether/MainController" method="POST">
						<input type="hidden" name="state" value="matchMaking">
						<input class="versus" type="image" src="images/versus.png" alt="対戦募集">
					</form>
					<a href="/RivalsOfAether/MainController?state=profile&userId=${account.userId}"><img class="mystats" src="images/mystats.png" alt="マイプロフィール"></a>
					<a href="/RivalsOfAether/MainController?state=logout"><img class="logout" src="images/logout.png" alt="ログアウト"></a>
				</div>
			<%	} %>
		
		<img class="page-header" src="images/header.png">
		<a href="/RivalsOfAether/MainController"><img class="logo" src="images/logo.png" alt="ホーム画面に戻る"></a>
	</header>
<%
String message = (String) request.getAttribute("message");
	if(message != null){
%>
	
		<p><font color="red"><%=message%></font></p>
		<br>
	<%	}	%>
	
	<main>

		<h3>対戦一覧</h3>

	<%	AccountLogic accountLogic = new AccountLogic();
		MatchResultLogic matchLogic = new MatchResultLogic();
		ArrayList<MatchResult> matchList = matchLogic.display();
		
		for(int i = 0; i < matchList.size(); i++){
			if(i == 10){
				break;
			}
			
			//試合の勝敗取得
			//勝者側のプレイヤーを赤色のボタンにする
			String[] buttonColor = null;
			if(matchList.get(i).getWinner() == 1){
				buttonColor = new String[]{"red", "blue"};
			} else {
				buttonColor = new String[]{"blue", "red"};
			}	
			
			String[] playerId = {matchList.get(i).getPlayer1(), matchList.get(i).getPlayer2()};
			String[] playerName = new String[2];
			String[] aHref = {"", ""};
			
			out.println("<div class=\"match\">");
			
			for(int j = 0; j < 2; j++){
				//退会済みのプレイヤーは、IDを「プレイヤー名」としてそのまま使用  
				playerName[j] = playerId[j];
				
				//登録中のプレイヤーであれば、「プレイヤー名」と「プロフィール画面への href 」を取得
				if(accountLogic.search(playerId[j]) != null){
					playerName[j] = accountLogic.search(playerId[j]).getPlayerName();
					aHref[j] = "<a href=\"/RivalsOfAether/MainController?state=profile&userId=" + playerId[j] + "\">" ;
				}
				
				out.println("<div class=\"playerButton\">");
				out.print(aHref[j] + "<img src=\"images/button-" + buttonColor[j] + ".png\"><div class=\"matchListPlayerName\">" + playerName[j] + "</div>");
				if(!(aHref[j].isEmpty())){
					out.print("</a>");
					
					//使用キャラクターカットイン画像出力
					out.print("<div class=\"cutin\">");
					for(int k = 0; k < 2; k++){
						int characterNum = 0;
							switch(k){
								case 0:
									characterNum = accountLogic.search(playerId[j]).getCharacter();
									break;
								case 1:
									characterNum = accountLogic.search(playerId[j]).getCharacterSub();
							}
						accountLogic.search(playerId[j]).getCharacter();
						String character = Constants.allCharactersOfficialOrder[characterNum];
						out.print("<img src=\"images/cutin/" + character + ".png\">");
					}
					out.print("</div>");	
				}
				
				out.print("</div>");
			}
			
			out.print("</div>");
		} %>
	</main>
	<br>

	<br>
	
	<%	if(session.getAttribute("account") != null){	
			Account account = (Account) session.getAttribute("account");
			if(account.getUserId().equals("admin_player")){	
				out.print("<footer><p></p><a href=\"/RivalsOfAether/AdminController\">管理者画面</a></footer>");
				}
		}	%>
</body>
</html>