package test;

import java.util.ArrayList;

import dao.AccountDAO;
import model.Account;
import model.AccountLogic;

public class SearchLogicTest {

	public static void main(String[] args) {
		String string = "ing";
		AccountDAO dao = new AccountDAO();
		AccountLogic bo = new AccountLogic();
	    ArrayList<Account> result = bo.matchId(string);
	    if(result == null) {
	    	System.out.println("nullでした");
	    } else {
	    	System.out.println("検索成功しました");
	    }
	}

}
