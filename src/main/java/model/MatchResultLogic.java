package model;

import java.util.ArrayList;

import dao.MatchDAO;

public class MatchResultLogic {
	MatchDAO dao = new MatchDAO();
	
	public ArrayList<MatchResult> display(){
		return dao.display();
	}
	
	public ArrayList<MatchResult> search(String userId){
		return dao.search(userId);
	}
	
	public boolean sendResult(ArrayList<String> report) {
		return dao.sendResult(report);
	}
	
	//特定のプレイヤーの「試合数」「勝利数」「敗北数」を取得するメソッド
	public int[] getMatchNums(String userId) {
		int matchCount = 0;
		int winCount = 0;
		int loseCount = 0;
		
		ArrayList<MatchResult> matches = dao.search(userId);
		for(int i = 0; i < matches.size(); i++){
			String players[] = {matches.get(i).getPlayer1(), matches.get(i).getPlayer2()};
			if(players[matches.get(i).getWinner() - 1] .equals(userId)){
				winCount ++;
			}
		}
		matchCount = matches.size();
		loseCount = matchCount - winCount;
		int[] data = {matchCount, winCount, loseCount};
		
		return data;
	}
	
	public boolean swap(int matchId) {
		return dao.swapResult(matchId);
	}
	
	public boolean delete(int matchId) {
		return dao.delete(matchId);
	}
}
