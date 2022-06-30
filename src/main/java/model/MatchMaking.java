package model;

import java.util.ArrayList;

public class MatchMaking {
	private ArrayList<String> players = new ArrayList<String>();
	private boolean p1ready = false;
	
	//排他制御
	public synchronized ArrayList<String> execute(String playerId) {
		// List に自分のIDを登録
		if(!(this.players.contains(playerId)) && (this.players.size() < 2)) {
			this.players.add(playerId);
		}
		
		// List に両プレイヤーのIDが揃った場合
		if(this.players.contains(playerId) && this.players.size() == 2) {
			//先に当メソッドにアクセスするプレイヤー
			//真偽値を変更することによって、 List を取得済みであると記録する
			if(this.p1ready == false) {
				this.p1ready = true;
				return this.players;
			} else {
			//追ってアクセスするプレイヤー
			//フィールドをリセットして、次のマッチメイクで使用できるようにする
				ArrayList<String> playersClone = (ArrayList<String>) this.players.clone();
				this.players.clear();
				this.p1ready = false;
				return playersClone; 
			}			
		}
		return null;
	}
}
