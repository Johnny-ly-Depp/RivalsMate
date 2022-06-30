package test;

import dao.AccountDAO;
import model.Account;
import model.AccountLogic;

public class EditLogicTest {

	public static void main(String[] args) {
		Account account = new Account("edittest", "更新テス", 1, 2);
		AccountDAO dao = new AccountDAO();
		AccountLogic bo = new AccountLogic();
	    Account result = bo.edit(account);
	    if(result == null) {
	    	System.out.println("アカウント情報の編集に失敗しました");
	    } else {
	    	System.out.println("成功しました");
	    }
	}

}
