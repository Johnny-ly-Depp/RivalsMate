package test;

import model.MatchResultLogic;

public class MatchDeleteTest {
	public static void main(String[] args) {
		MatchResultLogic bo = new MatchResultLogic();
	    if(bo.delete(4)) {
	    	System.out.println("成功しました");
	    } else {
	    	System.out.println("失敗しました");
	    }
	}
}
