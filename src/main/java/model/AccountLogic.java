package model;

import java.util.ArrayList;

import dao.AccountDAO;

public class AccountLogic {
	
	AccountDAO dao = new AccountDAO();

	public Account edit(Account account) {
		 return dao.edit(account);
	}
	 
	public String editPass(Login login) {
		 return dao.editPass(login);
	}
	 
	public Account login(Login login) {
		 return dao.login(login);
	}
	
	public ArrayList<Account> display() {
		 return dao.display();
	}
	 
	public Account search(String playerId) {
		 return dao.search(playerId);
	}
	
	public ArrayList<Account> matchId(String playerId) {
		 return dao.matchId(playerId);
	}
	
	public ArrayList<Account> matchPlayerName(String playerName) {
		 return dao.matchPlayerName(playerName);
	}
	 
	public Account register(Login login) {
		return dao.add(login);
	}
	
	public String delete(String userId) {
		 return dao.delete(userId);
	}
}