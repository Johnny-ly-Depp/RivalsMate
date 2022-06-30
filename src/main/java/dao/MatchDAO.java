package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import model.MatchResult;


public class MatchDAO {
	
	public final String RDB_DRIVE = "com.mysql.cj.jdbc.Driver";
	private final String JDBC_URL = "jdbc:mysql://localhost:3306/rivalsofaether";
	private final String JDBC_USER = "ytak";
	private final String JDBC_PASS = "af3g42sl";
	
	public ArrayList<MatchResult> display() {
		
		ArrayList<MatchResult> matchList = new ArrayList<MatchResult>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM rivalsofaether.match;";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int matchId = rs.getInt("MATCH_ID");
				String player1 = rs.getString("PLAYER1");
				String player2 = rs.getString("PLAYER2");
				int winner = rs.getInt("WINNER");
				
				matchList.add(new MatchResult(matchId, player1, player2, winner));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return matchList;
	}	
	
	public ArrayList<MatchResult> search(String userId) {

		ArrayList<MatchResult> matchList = new ArrayList<MatchResult>();
		
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM rivalsofaether.match WHERE `PLAYER1` = ? or `PLAYER2` = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			pstmt.setString(2, userId);
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				int matchId = rs.getInt("MATCH_ID");
				String player1 = rs.getString("PLAYER1");
				String player2 = rs.getString("PLAYER2");
				int winner = rs.getInt("WINNER");
				
				matchList.add(new MatchResult(matchId, player1, player2, winner));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return matchList;
	}	
	
	
	public boolean sendResult(ArrayList<String> report) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "INSERT INTO `rivalsofaether`.`match`  (`PLAYER1`, `PLAYER2`, `WINNER`) VALUES ( ? , ? , ?)";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, report.get(0));
			pstmt.setString(2, report.get(1));
			pstmt.setInt(3, Integer.parseInt(report.get(2)));
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean swapResult(int matchId) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "SELECT * FROM rivalsofaether.match WHERE `MATCH_ID` = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, matchId);
			ResultSet rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int winner = rs.getInt("WINNER");
				if(winner == 1) {
					winner = 2;
				} else {
					winner = 1;
				}
				sql = "UPDATE `rivalsofaether`.`match` SET `WINNER` = ? WHERE (`MATCH_ID` = ?);";
				PreparedStatement pstmt2 = conn.prepareStatement(sql);
				pstmt2.setInt(1, winner);
				pstmt2.setInt(2, matchId);
				if(pstmt2.executeUpdate() != 0) {
					return true;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean delete(int matchId) {
		try(Connection conn = DriverManager.getConnection(JDBC_URL , JDBC_USER, JDBC_PASS)){
			Class.forName(RDB_DRIVE);
			
			String sql = "DELETE FROM `rivalsofaether`.`match` WHERE (`MATCH_ID` = ?);";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, matchId);
			
			if(pstmt.executeUpdate() != 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
