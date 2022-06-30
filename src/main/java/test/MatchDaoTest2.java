package test;

import java.util.ArrayList;

import dao.MatchDAO;

public class MatchDaoTest2 {
	public static void main(String[] args) {
		MatchDAO dao = new MatchDAO();
		ArrayList<String> fakeResult = new ArrayList<String>();
		fakeResult.add("inshitsu");
		fakeResult.add("komorikiri");
		fakeResult.add("2");
		if(dao.sendResult(fakeResult)) {
	    	System.out.println("成功しました");
	    } else {
	    	System.out.println("失敗しました");
	    }
	}
}
