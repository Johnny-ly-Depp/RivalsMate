package model;

public class Account {
	private String userId;
	private String playerName;
	private int character;
	private int characterSub;

	public Account(String userId, String playerName, int character, int characterSub) {
		super();
		this.userId = userId;
		this.playerName = playerName;
		this.character = character;
		this.characterSub = characterSub;
		
		if(playerName == null) {
			this.playerName = userId;
		}
	}
	
	public Account(String userId) {
		super();
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}
	
	public String getPlayerName() {
		return playerName;
	}

	public int getCharacter() {
		return character;
	}

	public int getCharacterSub() {
		return characterSub;
	}
}
