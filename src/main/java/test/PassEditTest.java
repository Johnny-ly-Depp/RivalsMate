package test;

import model.AccountLogic;
import model.Login;

public class PassEditTest {
  public static void main(String[] args) {
	  Login login = new Login("hello", "4444");
	  AccountLogic bo = new AccountLogic();
	  String result = bo.editPass(login);
	  
	  System.out.println(result);
	  if(result == null) {
		  System.out.println("成功しました");
	  } else {
		  System.out.println("失敗しました");
	  }
  }
}