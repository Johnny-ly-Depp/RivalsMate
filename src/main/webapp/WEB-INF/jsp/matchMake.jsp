<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="refresh" content="5">
<link rel="stylesheet" type="text/css" href="css/otherPages.css">
<title>対戦募集中</title>
</head>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript">
$(function(){
    location.hash = "#prepage";
    $(window).on("hashchange", function(){
        location.hash = "#prepage2";
    });
});
</script>
<body>
	<header>
		<img class="page-header" src="images/bg-header-back-night.png">
		<a href="/RivalsOfAether/MainController"><img class="logo" src="images/logo.png" alt="ホーム画面に戻る"></a>
	</header>
	<div class="matchMake">
	<h1>対戦相手を探しています…</h1>
		<br>
		<form action="/RivalsOfAether/MainController" method="post">
		<input type="hidden" name="state" value="cancelMatchMaking">
		<input type="submit" value="募集を止める" class="button">
	</div>
</body>
</html>