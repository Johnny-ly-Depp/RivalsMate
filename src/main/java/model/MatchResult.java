package model;

public class MatchResult {
	private int matchId;
	private String player1;
	private String player2;
	private int winner;
	
	public MatchResult(int matchId, String player1, String player2, int winner) {
		super();
		this.matchId = matchId;
		this.player1 = player1;
		this.player2 = player2;
		this.winner = winner;
	}
	
	public int getMatchId() {
		return matchId;
	}
	public String getPlayer1() {
		return player1;
	}
	public String getPlayer2() {
		return player2;
	}
	public int getWinner() {
		return winner;
	}
}
