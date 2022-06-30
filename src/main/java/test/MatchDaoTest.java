package test;

import java.util.ArrayList;

import dao.MatchDAO;
import model.MatchResult;
import model.MatchResultLogic;

public class MatchDaoTest {
	public static void main(String[] args) {
		MatchDAO dao = new MatchDAO();
		MatchResultLogic bo = new MatchResultLogic();
	    ArrayList<MatchResult> result = bo.display();
	    if(result.size() == 0) {
	    	System.out.println("失敗しました");
	    } else {
	    	System.out.println(result.size());
	    	System.out.println("成功しました");
	    }
	}
}
