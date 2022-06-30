package test;

import model.MatchResultLogic;

public class MatchLogicSwapResultTest {
	public static void main(String[] args) {
		MatchResultLogic bo = new MatchResultLogic();
		if(bo.swap(15)) {
			System.out.println("成功しました");
		} else {
			System.out.println("失敗しました");
		}
		
	}
}
