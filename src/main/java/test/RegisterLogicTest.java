package test;

import dao.AccountDAO;
import model.Account;
import model.AccountLogic;
import model.Login;

public class RegisterLogicTest {

	public static void main(String[] args) {
		Login login = new Login("newplayerrr", "1234");
		AccountDAO dao = new AccountDAO();
		AccountLogic bo = new AccountLogic();
	    Account result = bo.register(login);
	    if(result != null) {
	    	System.out.println("成功しました");
	    } else {
	    	System.out.println("失敗しました");
	    }
	}

}
