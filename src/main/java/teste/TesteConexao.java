package teste;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;
import util.JpaUtil;

public class TesteConexao {

	public static void main(String[] args) {

		
		/*
		 * String sql =
		 * "INSERT INTO TB_USER (EMAIL, PASSWORD, NAME, CPF) VALUES (?, ?, ?, ?)";
		 * 
		 * String sql = "SELECT U.CPF, U.NAME, U.EMAIL, U.PASSWORD FROM TB_USER U";
		 * 
		 * Connection conn;
		 * 
		 * try { conn = JpaUtil.getConexao();
		 * 
		 * PreparedStatement ps = conn.prepareStatement(sql);
		 * 
		 * ps.setString(1, "teste@teste.com"); ps.setString(2, "1234"); ps.setString(3,
		 * "teste"); ps.setString(4, "00000000000");
		 * 
		 * ResultSet rs = ps.executeQuery();
		 * 
		 * rs = ps.executeQuery();
		 * 
		 * while (rs.next()) { System.out.println(rs.getString("NAME")+ ", " +
		 * rs.getString("EMAIL")); }
		 * 
		 * } catch (SQLException e) { e.printStackTrace(); }
		 */

		UserDao userDao = new UserDaoImpl();

		List<User> listUsers = new ArrayList<User>();

		listUsers = userDao.findAll();


		User user = new User();
		user.setCpf("11111111111");
		user.setEmail("teste1@teste1.com");
		user.setName("teste1");
		user.setPassword("teste1");
		
		userDao.insert(user);
		
		for (User lstuser : listUsers) {
			System.out.println(lstuser.getCpf());
			System.out.println(lstuser.getEmail());
			System.out.println(lstuser.getName());
			System.out.println(lstuser.getPassword());
			System.out.println(lstuser.getVehicles());
		}
		
		
	}
}