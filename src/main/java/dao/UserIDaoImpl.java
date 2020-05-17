package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import util.JpaUtil;

public class UserIDaoImpl implements UserDao {

	public void insert(User user) {
		
		String sql = "INSERT INTO TB_USER (EMAIL, PASSWORD, NAME, CPF) VALUES (?, ?, ?, ?)";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getEmail());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getName());
			ps.setString(4, user.getCpf());
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(User user) {
		
		String sql = "UPDATE TB_USER SET NAME = ?, SENHA = ? WHERE CPF = ?";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, user.getName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getCpf());
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(String cpf) {
		
		String sql = "DELETE FROM TB_USER WHERE CPF = ?";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, cpf);
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public User findAuser(String cpf) {
		
		String sql = "SELECT U.CPF, U.NAME, U.EMAIL, U.SENHA FROM TB_USER U WHERE CPF = ?";
		
		User user = null;
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, cpf);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				user = new User();
				
				user.setCpf(rs.getString("CPF"));
				user.setName(rs.getString("NAME"));
				user.setPassword(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	public List<User> findAll() {
		
		String sql = "SELECT U.CPF, U.NAME, U.SENHA FROM TB_USER U";
		
		List<User> listUsers = new ArrayList<User>();
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				User user = new User();
				
				user.setCpf(rs.getString("CPF"));
				user.setEmail(rs.getString("EMAIL"));
				user.setName(rs.getString("SENHA"));
				
				listUsers.add(user);
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listUsers;
	}
}
