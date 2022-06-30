<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "constant.Constants, model.Account, model.MatchResult,model.MatchResultLogic,model.AccountLogic,java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
<head>
<meta charset="UTF-8">
<%
	String userId = (String) session.getAttribute("userId");
	AccountLogic bo = new AccountLogic();
	Account account = bo.search(userId);
%>

<title><%=account.getPlayerName()%>のプロフィール</title>
</head>
<body>
	<header>
		<img class="page-header" src="images/header-no-characters.png">
		<a href="/RivalsOfAether/MainController"><img class="logo" src="images/logo.png" alt="ホーム画面に戻る"></a>
	</header>
<%	String message = (String) request.getAttribute("message");
	if(message != null){ %>
		<p><font color="red"><%= message %></font></p>
<%	} %>
	<div class="profileName_wrapper">
	<h1 class="profileName"><%=account.getPlayerName()%></h1>
	
	
	<%-- 使用キャラクターの画像表示 --%>
<%
String main = Constants.allCharactersOfficialOrder[account.getCharacter()];
	String sub = Constants.allCharactersOfficialOrder[account.getCharacterSub()];
	out.print("<img class=\"profileMainCharacter\" src=\"images/profiles/" + main + ".png\">");
	out.print("<img class=\"profileSubCharacter\" src=\"images/profiles/" + sub + ".png\">");
%>	

<%
	MatchResultLogic matchLogic = new MatchResultLogic();
	int[] resultNums = matchLogic.getMatchNums(account.getUserId());
	double winRate = Math.floor(((double)resultNums[1] / (double)resultNums[0]) * 100);
	
		if(resultNums[0] == 0){
			winRate = 0;
		}
%>
		<div class="record">
			<p><%= resultNums[0] %>戦　<%= resultNums[1] %>勝　<%= resultNums[2] %>敗　勝率　<%= (int)winRate %>%</p>
		</div>
	</div>
	<main>
		
		<h2>対戦結果</h2>
		<br>
		<%--　対戦一覧　--%>
	<%
		AccountLogic accountLogic = new AccountLogic();
		ArrayList<MatchResult> matchList = matchLogic.search(account.getUserId());
		
		for(int i = 0; i < matchList.size(); i++){
			if(i == 10){
				break;
			}
			
			//ポート数を取得
			int portNum = 1;
			int opponentPortNum = 2;
			if(matchList.get(i).getPlayer2().equals(account.getUserId())){
				portNum = 2;
				opponentPortNum = 1;
			}
			
			//試合の勝敗取得
			//相手に勝ったなら赤、負けたなら青
			String buttonColor = null;
			if(matchList.get(i).getWinner() == portNum){
				buttonColor = "red";
			} else {
				buttonColor = "blue";
			}	
			
			String playerId = null;
				switch(opponentPortNum){
					case 1:
						playerId =	matchList.get(i).getPlayer1();
						break;
					case 2:
						playerId =	matchList.get(i).getPlayer2();
				}
			String playerName = null;
			String aHref = "";
			
			out.println("<div class=\"match\">");
			

			//退会済みのプレイヤーは、IDを「プレイヤー名」としてそのまま使用  
			playerName = playerId;
			
			//登録中のプレイヤーであれば、「プレイヤー名」と「プロフィール画面への href 」を取得
			if(accountLogic.search(playerId) != null){
				playerName = accountLogic.search(playerId).getPlayerName();
				aHref = "<a href=\"/RivalsOfAether/MainController?state=profile&userId=" + playerId + "\">" ;
			}
			
			out.println("<div class=\"playerButton\">");
			out.print(aHref + "<img src=\"images/button-" + buttonColor + ".png\"><div class=\"matchListPlayerName\">" + playerName + "</div><br>");
			if(!(aHref.isEmpty())){
				out.print("</a>");
				
				//使用キャラクターカットイン画像出力
				out.print("<div class=\"cutin\">");
				for(int k = 0; k < 2; k++){
					int characterNum = 0;
						switch(k){
							case 0:
								characterNum = accountLogic.search(playerId).getCharacter();
								break;
							case 1:
								characterNum = accountLogic.search(playerId).getCharacterSub();
						}
					accountLogic.search(playerId).getCharacter();
					String character = Constants.allCharactersOfficialOrder[characterNum];
					out.print("<img src=\"images/cutin/" + character + ".png\">");
				}
				out.print("</div>");	
			}
			
			out.print("</div>");
			
		}	
			out.print("</div>"); %>
		<br>
	</main>
	<footer>
		<a href="/RivalsOfAether/MainController">戻る</a>
	<%	Account loggedInAccount = (Account) session.getAttribute("account");
		if((loggedInAccount != null) && (loggedInAccount.getUserId().equals(userId))){ %>
	<a href="/RivalsOfAether/MainController?state=profileEdit">プロフィールを編集する</a>
	<% } %>
	</footer>
	
</body>
</html>