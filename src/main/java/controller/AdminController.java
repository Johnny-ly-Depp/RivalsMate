package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.Account;
import model.AccountLogic;
import model.MatchResult;
import model.MatchResultLogic;

/**
 * Servlet implementation class MainController
 */
@WebServlet("/AdminController")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
       
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
  	
		request.setCharacterEncoding("UTF-8");
		String state = request.getParameter("state");
		String fowardString = "";
		
		//管理者アカウントでログインしていなければ、メインメニューに遷移する
		HttpSession session = request.getSession();
    	Account account = (Account) session.getAttribute("account");
    	if(session.getAttribute("account") == null || !(account.getUserId().equals("admin_player"))){	
    		fowardString = "/WEB-INF/jsp/main.jsp";
		
    	} else if(state == null) {
			fowardString = "/WEB-INF/jsp/adminLogin.jsp";
		} else {
			session.removeAttribute("admin");
			switch(state) {
			case "accountManagement":
					fowardString = "/WEB-INF/jsp/adminManagement_Accounts.jsp";
					break;
			case "matchManagement":
					fowardString = "/WEB-INF/jsp/adminManagement_Matches.jsp";
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
		request.setCharacterEncoding("UTF-8");
		AccountLogic accountLogic = new AccountLogic();
		MatchResultLogic matchLogic = new MatchResultLogic();
		switch(request.getParameter("state")) {
			case "login":
				procLogin(request, response);
				break;
			case "searchAccount":
				procSearchAccount(request, response, accountLogic);
				break;
			case "deleteAccount":
				procDeleteAccount(request, response, accountLogic);
				break;
			case "searchMatch":
				procSearchMatch(request, response, matchLogic);
				break;
			case "manageMatch":
				procManageMatch(request, response, matchLogic);
				break;
			}
	}

	private void procLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String fowarString = "";
		
		//管理画面ログイン用のパスワード
		if (request.getParameter("adminPass").equals("admin")) {
			fowarString = "/WEB-INF/jsp/adminManagement_Accounts.jsp";
		} else {
			request.setAttribute("message", "パスワードが違います。");
			fowarString = "/WEB-INF/jsp/adminLogin.jsp";
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(fowarString);
		dispatcher.forward( request, response);	  
	}
	
	private void procSearchAccount(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String search = request.getParameter("search");
		ArrayList<Account> result = new ArrayList<Account>();
		
		//テキストボックスに何も入力せず検索した場合、全てのアカウントを表示
		if(search.isEmpty()) {
			result = accountLogic.display();
		} else {
			ArrayList<Account> idSearchResult = accountLogic.matchId(search);
			ArrayList<Account> nameSearchResult = accountLogic.matchPlayerName(search);	
			ArrayList<Account> allResults = new ArrayList<>();
			allResults.addAll(idSearchResult);
			allResults.addAll(nameSearchResult);
			//「ID検索」「プレイヤー名検索」双方の結果から、重複を削除
			for (int i = 0; i < allResults.size(); i++) {
				Account item = allResults.get(i);
	            int j = 0;
	            for (; j < result.size(); j++) {
	                if (item.equals(result.get(j))) {
	                    break;
	                }
	            }
	            if (j == result.size()) {
	                result.add(item);
	            }
	        }
		}
		session.setAttribute("admin", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminManagement_Accounts.jsp");
		dispatcher.forward(request, response);	  
	}
	
	private void procDeleteAccount(HttpServletRequest request, HttpServletResponse response, AccountLogic accountLogic) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String[] deleteAccount = request.getParameterValues("deleteAccount");
		for(String account: deleteAccount) {
			if(accountLogic.delete(account) != null) {
				request.setAttribute("message", "アカウントの削除時に問題が発生しました");
			}
		}
		session.setAttribute("admin", accountLogic.display());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminManagement_Accounts.jsp");
		dispatcher.forward(request, response);	  
	}
	
	private void procSearchMatch(HttpServletRequest request, HttpServletResponse response, MatchResultLogic matchLogic) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String search = request.getParameter("search");
		ArrayList<MatchResult> result = new ArrayList<MatchResult>();
		
		//テキストボックスに何も入力せず検索した場合、全ての試合結果を表示
		if(search.isEmpty()) {
			result = matchLogic.display();
		} else {
			result = matchLogic.search(search);
		}
		session.setAttribute("admin", result);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminManagement_Matches.jsp");
		dispatcher.forward(request, response);	  
	}
	
	private void procManageMatch(HttpServletRequest request, HttpServletResponse response, MatchResultLogic matchLogic) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String[] matches = request.getParameterValues("matches");
		String execution = request.getParameter("execution");
		String message = "";
		
		for(String match: matches) {
			int matchId = Integer.parseInt(match);
			if(execution.equals("swap")) {
				if(matchLogic.swap(matchId) == false){
					message = "試合結果の変更時に問題が発生しました";
				}
			} else if (execution.equals("delete")) {
				if(matchLogic.delete(matchId) == false) {
					message = "試合の削除時に問題が発生しました";
				}
			}
			request.setAttribute("message", message);
		}
		session.setAttribute("admin", matchLogic.display());
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/adminManagement_Matches.jsp");
		dispatcher.forward(request, response);	  
	}
}
