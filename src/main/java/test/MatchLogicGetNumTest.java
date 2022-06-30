package test;

import model.MatchResultLogic;

public class MatchLogicGetNumTest {
	public static void main(String[] args) {
		MatchResultLogic bo = new MatchResultLogic();
		int[] result = bo.getMatchNums("komorikiri");
		System.out.println("試合数" + result[0] + "、勝利数" + result [1] + "、敗北数" + result [2]);
	}
}
