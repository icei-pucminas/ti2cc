package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Player;

public class PlayerDAO extends DAO {
	
	public PlayerDAO() {
		super();
		conectar();
	}

	public void finalize() {
		close();
	}
	
	
	public boolean insert(Player player) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "INSERT INTO player (codigo, login, senha, sexo, time) "
				       + "VALUES ("+player.getCodigo()+ ", '" + player.getLogin() + "', '"  
				       + player.getSenha() + "', '" + player.getSexo() + "', '" + player.getTime() + "');";
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}

	
	public Player get(int codigo) {
		Player player = null;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM produto WHERE id=" + codigo;
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	
	        if(rs.next()){            
	        	player = new Player(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0), rs.getString("time"));
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return player;
	}
	
	
	public List<Player> get() {
		return get("");
	}

	
	public List<Player> getOrderByCodigo() {
		return get("codigo");		
	}
	
	
	public List<Player> getOrderByLogin() {
		return get("login");		
	}
	
	
	public List<Player> getOrderBySexo() {
		return get("sexo");		
	}

	public List<Player> getOrderByTime() {
		return get("time");		
	}
	
	
	private List<Player> get(String orderBy) {	
	
		List<Player> players = new ArrayList<Player>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM player" + ((orderBy.trim().length() == 0) ? "" : (" ORDER BY " + orderBy));
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Player u = new Player(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0), rs.getString("time"));
	            players.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return players;
	}


	public List<Player> getSexoMasculino() {
		List<Player> players = new ArrayList<Player>();
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM player WHERE player.sexo LIKE 'M'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);	           
	        while(rs.next()) {	            	
	        	Player u = new Player(rs.getInt("codigo"), rs.getString("login"), rs.getString("senha"), rs.getString("sexo").charAt(0), rs.getString("time"));
	            players.add(u);
	        }
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return players;
	}
	
	
	public boolean update(Player player) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "UPDATE palyer SET login = '" + player.getLogin() + "', senha = '"  
				       + player.getSenha() + "', sexo = '" + player.getSexo() + "', time = '" + player.getTime() + "' WHERE codigo = " + player.getCodigo();
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	public boolean delete(int codigo) {
		boolean status = false;
		try {  
			Statement st = conexao.createStatement();
			String sql = "DELETE FROM player WHERE codigo = " + codigo;
			System.out.println(sql);
			st.executeUpdate(sql);
			st.close();
			status = true;
		} catch (SQLException u) {  
			throw new RuntimeException(u);
		}
		return status;
	}
	
	
	public boolean autenticar(String login, String senha) {
		boolean resp = false;
		
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM player WHERE login LIKE '" + login + "' AND senha LIKE '" + senha  + "'";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			resp = rs.next();
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return resp;
	}	

	public int BDLen(){
		int len = 0;
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT COUNT(*) FROM player";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			rs.next();
			len = rs.getInt(1);
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		return len;
	}

	public void printBD(){
		try {
			Statement st = conexao.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			String sql = "SELECT * FROM player";
			System.out.println(sql);
			ResultSet rs = st.executeQuery(sql);
			while(rs.next()){
				System.out.println(rs.getInt("codigo") + " - " + rs.getString("login") + " - " + rs.getString("senha") + " - " + rs.getString("sexo") + " - " + rs.getString("time"));
			}
	        st.close();
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}
}
