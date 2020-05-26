package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import entities.Vehicle;
import util.JpaUtil;

public class UserDaoImpl implements UserDao, Serializable {
	private static final long serialVersionUID = 1L;

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
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(User user) {
		
		String sql = "UPDATE TB_USER SET NAME = ?, PASSWORD = ? WHERE CPF = ?";
		
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

	public User findAuser(String email) {
		
		String sql = "SELECT CPF, NAME, EMAIL, PASSWORD, LICENSE, BRAND, MODELNAME, NICKNAME FROM TB_USER U "
					+ "LEFT JOIN TB_VEHICLE V ON (U.CPF = V.CPF_USER) WHERE EMAIL = ?";
		
		Connection conn;
		
		User obj = new User();
		Vehicle vehicle = new Vehicle();
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, email);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				obj.setCpf(rs.getString("CPF"));
				obj.setName(rs.getString("NAME"));
				obj.setEmail(rs.getString("EMAIL"));
				obj.setPassword(rs.getString("PASSWORD"));
				
				vehicle.setLicense(rs.getString("LICENSE"));
				vehicle.setBrand(rs.getString("BRAND"));
				vehicle.setModelName(rs.getString("MODELNAME"));
				vehicle.setNickname(rs.getString("NICKNAME"));
				
				obj.addVehicle(vehicle);
				vehicle = new Vehicle();
			}
			
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public List<User> findAll() {
		
		String sql = "SELECT U.CPF, U.NAME, U.EMAIL, U.PASSWORD FROM TB_USER U";
		
		List<User> listUsers = new ArrayList<User>();
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				User user = new User();
				
				user.setCpf(rs.getString("CPF"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));
				
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
