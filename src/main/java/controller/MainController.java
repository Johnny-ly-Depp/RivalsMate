package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import constant.Constants;
import model.Account;
import model.AccountLogic;
import model.Login;
import model.MatchMaking;
import model.MatchReportCheck;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    	HttpSession session = request.getSession();
		request.setCharacterEncoding("UTF-8");
		String state = request.getParameter("state");
		
		//【マッチメイキング中の場合】
		//マッチメイキングの際、自動更新により doGet() が呼び出される。
		//その場合、以下のセッションスコープを参照して再度マッチメイキング画面に遷移し直す。
		String matchMakingState = "";
			if(session.getAttribute("matchMakingState") != null) {
				matchMakingState = (String) session.getAttribute("matchMakingState");
			}
			
		//リクエストパラメーター "state" の値によって遷移先を決める
		String fowardString = "";
		if(state == null) {
			//マッチメイキング中の場合
			switch(matchMakingState) {
				case "matchMaking":
					AccountLogic accountLogic = new AccountLogic();
					procMatchMaking(request, response, accountLogic);
					break;
				case "reportWaiting":
					procSendResult(request, response); 
					break; 
				default:
					fowardString = "/WEB-INF/jsp/main.jsp";
			}
		} else {
			switch(state) {
				case "matchList":
					fowardString = "/WEB-INF/jsp/main.jsp";
					break;
				case "login":
					fowardString = "/WEB-INF/jsp/login.jsp";
					break;
				case "logout":
					session.invalidate();
					fowardString = "/WEB-INF/jsp/main.jsp";
					break;
				case "register":
					fowardString = "/WEB-INF/jsp/register.jsp";
					break;
				case "profile":
					String userId = request.getParameter("userId");
					session.setAttribute("userId", userId);
					fowardString = "/WEB-INF/jsp/profile.jsp";
					break;
				case "profileEdit":
					fowardString = "/WEB-INF/jsp/profileEdit.jsp";
					break;
				case "profileDelete":
					fowardString = "/WEB-INF/jsp/profileDelete.jsp";
					break;
				case "matchScreen":
					fowardString = "/WEB-INF/jsp/matchScreen.jsp";
					break;
			}
		}		
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
		dispatcher.forward(request, response);
	}

	/**
	 * @param session 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		
		//フォームに入力した値を変数に格納する
		request.setCharacterEncoding("UTF-8");
		String userId = request.getParameter("userId");
		String pass = request.getParameter("pass");
		String playerName = request.getParameter("playerName");
		AccountLogic accountLogic = new AccountLogic();
		
		//リクエストパラメーター "state" の値によって、実行するメソッドを決める
		switch(request.getParameter("state")) {
			case "login":
				procLogin(request, response, accountLogic, userId, pass);
				break;
			case "register":
				procRegister(request, response, accountLogic, userId, pass);
				break;
			case "edit":
				procEdit(request, response, accountLogic, userId, playerName);
				break;
			case "delete":
				procDelete(request, response,accountLogic, pass);
				break;
			case "matchMaking":
				procMatchMaking(request, response, accountLogic);
				break;
			case "cancelMatchMaking":
				procCancelMatchMaking(request, response);
				break;
			case "sendResult":
				procSendResult(request, response);
				break;	
		}
	}

	private void procLogin(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic, String userId, String pass) throws IOException, ServletException {
		Login login = new Login(userId, pass);
		Account account = accountLogic.login(login);
		
		String message = "";
		String fowarString = "";
		if (account != null) {
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			message = "ようこそ、" + account.getPlayerName() + "さん";
			fowarString = "/WEB-INF/jsp/main.jsp";
		} else {
			message = "ログインに失敗しました。";
			request.setAttribute("userId", userId);
			fowarString = "/WEB-INF/jsp/login.jsp";
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowarString);
		dispatcher.forward(request, response);
		  
	}
	
	private void procRegister(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic, String userId, String pass) throws IOException, ServletException {
		
		String message = "";
		String fowardString = "";
		Account account = null;
		
		//フォームが正しく入力されているか確認
		//されている場合、DBに登録する
		String passCheck = request.getParameter("passCheck");
		if(!(pass.isEmpty()) && (pass.equals(passCheck))) {
			Login newAccount = new Login(userId, pass);
			account = accountLogic.register(newAccount);
		}
		
		//登録成功
		if (account != null) {
			HttpSession session = request.getSession();
			session.setAttribute("account", account);
			fowardString = "/WEB-INF/jsp/profileEdit.jsp";
		//登録失敗
		} else {
			message = "登録に失敗しました。";
			if(!pass.equals(passCheck)) {
				message = "確認パスワードが一致しません。";
			}
			request.setAttribute("userId", userId);
			fowardString = "/WEB-INF/jsp/register.jsp";
		}
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
			dispatcher.forward(request, response);
	}
	
	private void procEdit(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic, String userId, String playerName) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String message = "";
		String fowardString = "";
		
		//パスワード編集機能
		String oldPass = request.getParameter("oldPass");
		String newPass = request.getParameter("newPass");
		String newPassCheck = request.getParameter("newPassCheck");
		
		//3つの欄が正しく入力されている場合
		if(!(oldPass.isEmpty()) && (!(newPass.isBlank()) && newPass.equals(newPassCheck))) {
			Login oldPassCheck = new Login(userId ,oldPass);
			
			//旧パスワードが正しい場合、新パスワードを登録する
			if(accountLogic.login(oldPassCheck) != null) {
				Login newPassLogin = new Login(userId, newPass);
				if(accountLogic.editPass(newPassLogin) == null){
					message = "パスワードを変更しました";
				
				//登録失敗	
				} else {
					message = "パスワードの編集に失敗しました。もう一度お試しください";
					fowardString = "/WEB-INF/jsp/profileEdit.jsp";
				}
			} else {
				message = "パスワードが間違っています。";
				fowardString = "/WEB-INF/jsp/profileEdit.jsp";
			}
		//欄が入力されているが、不備がある場合
		} else if(!(oldPass.isBlank()) || !(newPass.isBlank()) || !(newPassCheck.isBlank())) {
			message = "パスワードの編集に失敗しました。入力値が正しいかご確認ください";
			fowardString = "/WEB-INF/jsp/profileEdit.jsp";
		}
		
		//パスワード変更に失敗した場合、即座に遷移する
		//キャラクター選択の処理には進まない
		if(!(fowardString.isEmpty())) {
			request.setAttribute("message", message);
			RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
			dispatcher.forward(request, response);
		}
		
		//キャラクター選択機能
		int mainCharacter = Constants.convertCharacterNum(request.getParameter("mainCharacter"));
		int subCharacter = Constants.convertCharacterNum(request.getParameter("subCharacter"));
		Account account = null;
		
		if(!(playerName.isEmpty()) && (mainCharacter != subCharacter)) {
			Account edittedAccount = new Account(userId, playerName, mainCharacter, subCharacter);
			account = accountLogic.edit(edittedAccount);
		}
		
		//登録成功
		if (account != null) {
			session.setAttribute("account", account);
			session.setAttribute("userId", userId);
			fowardString = "/WEB-INF/jsp/profile.jsp";
		//登録失敗
		} else {
			message = "プロフィールの編集に失敗しました。";
			if(mainCharacter == subCharacter) {
				message = "メインキャラクターとサブキャラクターが重複しています。"; 
			} 
			fowardString = "/WEB-INF/jsp/profileEdit.jsp";
			
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
		dispatcher.forward(request, response);
	}	
	
	private void procDelete(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic, String pass) throws IOException, ServletException {
		String message = "";
		String fowardString = "";
		
		//セッションスコープから現在ログイン中のアカウントを取得する
		HttpSession session = request.getSession();
		Account loggingInAccount = (Account) session.getAttribute("account");
		
		//アカウント管理用の entity"Login" を生成
		Login deleteingAccount = new Login(loggingInAccount.getUserId(), pass);
		if(accountLogic.login(deleteingAccount) != null) {
			accountLogic.delete(deleteingAccount.getUserId());
			message ="アカウントを削除しました。";
			fowardString = "/WEB-INF/jsp/main.jsp";
			session.invalidate();
		} else {
			message ="アカウントの削除に失敗しました。";
			fowardString = "/WEB-INF/jsp/profileDelete.jsp";
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
		dispatcher.forward(request, response);
	}
	
	private void procMatchMaking(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic) throws IOException, ServletException {
		HttpSession session = request.getSession();
		ServletContext application = getServletContext();
		
		//マッチメイク用の entity が未生成であれば生成する
		MatchMaking matchMaking = null;
		if(application.getAttribute("matchMaking") == null) {
			matchMaking = new MatchMaking();
		} else {
			matchMaking = (MatchMaking) application.getAttribute("matchMaking");
		}
		
		//ログインしていない状態でこのページを開いてしまった場合、
		//メインメニューに遷移させる
		Account loggedInAccount = (Account) session.getAttribute("account");
		if(loggedInAccount == null) {
			session.invalidate();
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
			dispatcher.forward(request, response);
		}
		
		//マッチング処理
		String userId = loggedInAccount.getUserId();
		ArrayList<String> match = matchMaking.execute(userId);
		String fowardString = "";
		
		//対戦相手が見つかった場合
		if(match != null) {
			fowardString = "/WEB-INF/jsp/matchScreen.jsp";
			session.setAttribute("matchMakingState", "reportWaiting");
			//対戦相手の情報を取得し、セッションスコープに格納する
			String opponentId = match.get(0);
				if(opponentId.equals(userId)) {
					opponentId = match.get(1);
				}
			Account opponent = accountLogic.search(opponentId);
			session.setAttribute("opponent", opponent);
			
		//見つからない場合	
		} else {
			fowardString = "/WEB-INF/jsp/matchMake.jsp";
			session.setAttribute("matchMakingState", "matchMaking");
		}
		application.setAttribute("matchMaking", matchMaking);
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
		dispatcher.forward(request, response);
	}
	
	private void procCancelMatchMaking(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		session.removeAttribute("matchMakingState");
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/main.jsp");
		dispatcher.forward(request, response);
	}
	
	private void procSendResult(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		ServletContext application = getServletContext();
		HttpSession session = request.getSession();
		Account account = (Account) session.getAttribute("account");
		
		//自身の勝敗報告を取得
		String WINorLOSE = "";
		if(session.getAttribute("WINorLOSE") != null) {
			WINorLOSE = (String) session.getAttribute("WINorLOSE");
		} else {
			WINorLOSE = request.getParameter("report");
			session.setAttribute("WINorLOSE", WINorLOSE);
		}
		
		//試合結果報告用の entity が未生成であれば生成する
		MatchReportCheck matchReport = null;
		if(application.getAttribute("matchReport") == null) {
			matchReport = new MatchReportCheck();
		} else {
			matchReport = (MatchReportCheck) application.getAttribute("matchReport");
		}
		
		String fowardString = "";
		String message = "";
		//試合結果をDBに送信
		String reportResult = matchReport.execute(account.getUserId() + WINorLOSE);
		if(reportResult == null) {
			fowardString = "/WEB-INF/jsp/main.jsp";
			message = "対戦を終了しました";
			session.removeAttribute("WINorLOSE");
			session.removeAttribute("opponent");
			session.removeAttribute("matchMakingState");
		} else {
			//送信失敗した場合、 reportResult にエラーを示す文字列が格納される
			switch(reportResult) {
				case "waitingForOpponent":
					fowardString = "/WEB-INF/jsp/reportSent.jsp";
					application.setAttribute("matchReport", matchReport);
					break;
				case "reportAgain":
					fowardString = "/WEB-INF/jsp/matchScreen.jsp";
					message = "相手の結果報告との食い違いが生じています。報告が正しいか確認し、もう一度送信してください。";
					session.removeAttribute("WINorLOSE");
					break;
			}
		}
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowardString);
		dispatcher.forward(request, response);
	}
}
