package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.Account;
import model.Login;

public class AccountDAO {
	
	public final String RDB_DRIVE = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/rivalsofaether";
	private final String JDBC_USER = "ytak";
	private final String JDBC_PASS = "af3g42sl";
	
	public Account login(Login login) {
		
		Account account = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM user WHERE USER_ID = ? AND PASS = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getUserId());
			pstmt.setString(2, login.getPass());
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("USER_ID");
				String player = rs.getString("PLAYER");
				int character = rs.getInt("CHARACTER");
				int characterSub  = rs.getInt("CHARACTER_SUB");
			
				account = new Account(userId, player, character, characterSub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return account;
	}
	
	public Account add(Login login) {
		
		Account account = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "INSERT INTO user (USER_ID, PASS) VALUES ( ? , ? )";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getUserId());
			pstmt.setString(2, login.getPass());
			
			if(pstmt.executeUpdate() != 0) {
				account = new Account(login.getUserId());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return account;
	}
	 
	public Account edit(Account account) {
		
		Account edditedAccount = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "UPDATE user SET `PLAYER` = ? , `CHARACTER` = ? , `CHARACTER_SUB` = ? WHERE `USER_ID` = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, account.getPlayerName());
			pstmt.setInt(2, account.getCharacter());
			pstmt.setInt(3, account.getCharacterSub());
			pstmt.setString(4, account.getUserId());
			
			if(pstmt.executeUpdate() != 0) {
				edditedAccount = new Account(account.getUserId(), account.getPlayerName(), account.getCharacter(), account.getCharacterSub());
			}
	} catch (Exception e) {
		e.printStackTrace();
	}
	return edditedAccount;
	}
	
	public String editPass(Login login) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			String sql = "UPDATE `rivalsofaether`.`user` SET `PASS` = ? WHERE (`USER_ID` = ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, login.getPass());
			pstmt.setString(2, login.getUserId());
			
			if(pstmt.executeUpdate() == 0) {
				return "passEditFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public ArrayList<Account> display() {
		
		ArrayList<Account> accounts = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM user";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString("USER_ID");
				String player = rs.getString("PLAYER");
				int character = rs.getInt("CHARACTER");
				int characterSub  = rs.getInt("CHARACTER_SUB");
			
				accounts.add(new Account(userId, player, character, characterSub));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return accounts;
	}
	
	public Account search(String playerId) {
			
		Account account = null;
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM user WHERE USER_ID = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, playerId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				String userId = rs.getString("USER_ID");
				String player = rs.getString("PLAYER");
				int character = rs.getInt("CHARACTER");
				int characterSub  = rs.getInt("CHARACTER_SUB");
			
				account = new Account(userId, player, character, characterSub);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return account;
	}
	
	public ArrayList<Account> matchId(String playerId) {
			
			ArrayList<Account> accounts = new ArrayList<>();
			
			try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
				Class.forName(RDB_DRIVE);
				
				String sql = "SELECT * FROM user WHERE `USER_ID` LIKE ? ";
				PreparedStatement pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + playerId + "%");
				ResultSet rs = pstmt.executeQuery();
				
				while(rs.next()) {
					String userId = rs.getString("USER_ID");
					String player = rs.getString("PLAYER");
					int character = rs.getInt("CHARACTER");
					int characterSub  = rs.getInt("CHARACTER_SUB");
				
					accounts.add(new Account(userId, player, character, characterSub));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
			return accounts;
		}
		
	public ArrayList<Account> matchPlayerName(String playerName) {
		
		ArrayList<Account> accounts = new ArrayList<>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM user WHERE `PLAYER` LIKE ? ";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + playerName + "%");
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				String userId = rs.getString("USER_ID");
				String player = rs.getString("PLAYER");
				int character = rs.getInt("CHARACTER");
				int characterSub  = rs.getInt("CHARACTER_SUB");
			
				accounts.add(new Account(userId, player, character, characterSub));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return accounts;
	}
	
	public String delete(String userId) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			String sql = "DELETE FROM `rivalsofaether`.`user` WHERE `USER_ID` = ?;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			
			if(pstmt.executeUpdate() == 0) {
				return "deleteFail";
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
