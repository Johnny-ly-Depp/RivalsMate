package test;

import model.AccountLogic;
import model.Login;

public class AccountDeleteTest {
  public static void main(String[] args) {
	  Login login = new Login("newaccount", "1234");
	  AccountLogic bo = new AccountLogic();
	  String result = bo.delete(login.getUserId());
	  
	  System.out.println(result);
	  if(result == null) {
		  System.out.println("成功しました");
	  } else {
		  System.out.println("失敗しました");
	  }
  }
}