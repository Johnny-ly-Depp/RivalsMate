package test;

import model.Account;
import model.AccountLogic;
import model.Login;

public class LoginLogicTest {
  public static void main(String[] args) {
    testExecute1(); // ログイン成功のテスト
    testExecute2(); // ログイン失敗のテスト
  }

  public static void testExecute1() {
    Login login = new Login("admin_playeraaaaaaaaaa", "1234");
    AccountLogic bo = new AccountLogic();
    Account result = bo.login(login);
    if (result != null) {
      System.out.println("testExcecute1:成功しました");
    } else {
      System.out.println("testExcecute1:失敗しました");
    }
  }

  public static void testExecute2() {
    Login login = new Login("minato", "12345");
    AccountLogic bo = new AccountLogic();
    Account result = bo.login(login);
    if (result == null) {
      System.out.println("testExcecute2:成功しました");
    } else {
      System.out.println("testExcecute2:失敗しました");
    }
  }
}