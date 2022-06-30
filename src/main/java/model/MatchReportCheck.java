package model;

import java.util.ArrayList;

public class MatchReportCheck {
	ArrayList<String> reports = new ArrayList<>();
	boolean reportsSent = false;
	boolean reportAgain = false;
	
	//排他処理
	public synchronized String execute(String report){
		
		//双方の勝敗報告が被った場合
		//当メソッドに先にアクセスしたプレイヤーが、62行目で既に後処理を行っている
		if(reportAgain) {
			reportAgain = false;
			return "reportAgain";
		}
		
		//勝敗結果が正しく送信された場合
		//当メソッドに先にアクセスしたプレイヤーが、55行目で既に後処理を行っている
		if(reportsSent) {
			reportsSent = false;
			return null;
		}
		
		// List に自分のIDと勝敗結果を登録
		if(!(this.reports.contains(report)) && (this.reports.size() < 2)) {
			this.reports.add(report);
		}
		
		// List に両プレイヤーの報告が揃った場合
		if(this.reports.contains(report) && this.reports.size() == 2) {
			String p1id = this.reports.get(0).substring(0, this.reports.get(0).length() - 4);
			String p2id = this.reports.get(1).substring(0, this.reports.get(1).length() - 4);
			String p1report = this.reports.get(0).substring(this.reports.get(0).length() - 3); 
			String p2report = this.reports.get(1).substring(this.reports.get(1).length() - 3);
			
			//勝敗結果が正しく送信された場合
			if(!(p1report.equals(p2report))) {
				ArrayList<String> sendingResult = new ArrayList<>();
				String winnerPlayer = "";
					if(this.reports.get(0).contains("WIN")) {
						winnerPlayer = "1";
					} else {
						winnerPlayer = "2";
					}
				sendingResult.add(p1id);
				sendingResult.add(p2id);
				sendingResult.add(winnerPlayer);
				MatchResultLogic bo = new MatchResultLogic();
				bo.sendResult(sendingResult);
				reports.clear();
				reportsSent = true;
				return null;
			} else {
				//双方の勝敗報告が被った場合
				// List をリセットして、報告をやり直させる
				//真偽値を変更することによって、送信の失敗を記録する
				reports.clear();
				reportAgain = true;
				return "reportAgain";
			}
		}
		return "waitingForOpponent";
	}
}
