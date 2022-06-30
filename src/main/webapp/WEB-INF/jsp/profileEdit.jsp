<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import = "model.Account, constant.Constants" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>プロフィール編集</title>
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
			<%	}
			Account account = (Account)session.getAttribute("account");
			int mainCharacter = account.getCharacter();
			int subCharacter = account.getCharacterSub(); %>
			<div class="form">
				<form action="/RivalsOfAether/MainController" method="post">
					<dl>
						<dt><label>プレイヤー名</label></dt>
						 <dd><input type="text" name="playerName" value= ${account.playerName} ></dd>
						<p>パスワードを変更する場合、<br>以下のテキストボックスを記入してください。</p>
						<dt><label>現在のパスワード</label></dt>
						 <dd><input type="password" name="oldPass" pattern="^[a-zA-Z0-9]+$"></dd>
						<dt><label>新しいパスワード</label></dt>
						 <dd><input type="password" name="newPass" pattern="^[a-zA-Z0-9]+$"></dd>
						<dt><label>新しいパスワードの<br>確認</label></dt>
						 <dd><input type="password" name="newPassCheck" pattern="^[a-zA-Z0-9]+$"></dd>
					</dl>
					<br>
					<dd><input type="hidden" name="userId" value= ${account.userId} ></dd>
					<dd><input type="hidden" name="state" value="edit"></dd>
				
			</div>
			
			<div class="characterSelect_wrapper">
			<%	for(int i = 0; i < 2; i++){ 
				String mainORsubJPN = "";
				String mainORsubENG = "";
				String mainORsubCharacterString = "";
					switch(i){
						case 0:
							mainORsubJPN = "メイン";
							mainORsubENG = "main";
							mainORsubCharacterString = Constants.allCharactersOfficialOrder[mainCharacter];
							break;
						case 1:
							mainORsubJPN = "サブ";
							mainORsubENG = "sub";
							mainORsubCharacterString = Constants.allCharactersOfficialOrder[subCharacter];
					}
				out.print("<p>" + mainORsubJPN + "キャラクター</p>");
					//キャラクター選択画面
					for(String character: Constants.characterSelectScreenOrder){ 
						//2段目がオリンピアから始まるため <br> を挿入
						if(character.equals("olympia")){
							out.print("<br>");
						}
						
						//ラジオボタン
						//現在の使用キャラクターはチェック済みにしておく
						String checked = " ";
						if(character.equals(mainORsubCharacterString)){
							checked = "checked=\"checked\"";
						}
						out.print("<label class=\"character\">");
						out.print( "<input id=\"" + character + mainORsubENG + "\" type=\"radio\" name=\"" + mainORsubENG + "Character\" value=\"" + character + "\" " + checked + ">");
						out.print("<label for=\"" + character + mainORsubENG + "\">");
						
						//画像出力
						//「オリ」と「ショベルナイト」は画像サイズが他と異なるので、特例とする
						String oriOrSk = " ";
						if(character.equals("oriandsein") || character.equals("shovelknight")){
							oriOrSk = " id=\"oriANDsk\" ";
						}
						out.print("<img" + oriOrSk + "class=\"characterSelect\" src=\"images/characterSelect/" + character + ".png\">");
						out.print("</label></label>");
					}
				}%>
				
					<div class="button_middle">
					<br><br>
						<input class="button" type="submit" value="登録">
				</form>
					</div>
							
			</div>
		<br>
		<br>

	<footer>
		<a href="/RivalsOfAether/MainController?state=profile&userId=${account.userId}">戻る</a>
		<a href="/RivalsOfAether/MainController?state=profileDelete">アカウントを削除</a>
	</footer>

</body>
</html>