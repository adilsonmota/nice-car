package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.User;
import entities.Vehicle;
import util.JpaUtil;

public class VehicleDaoImpl implements VehicleDao {

	public void insert(Vehicle vehicle) {
		
		String sql = "INSERT INTO TB_VEHICLE (LICENSE, BRAND, MODELNAME, NICKNAME) VALUES (?, ?, ?, ?)";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, vehicle.getLicense());
			ps.setString(2, vehicle.getBrand());
			ps.setString(3, vehicle.getModelName());
			ps.setString(4, vehicle.getNickname());
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Vehicle vehicle) {
		
		String sql = "UPDATE TB_VEHICLE SET BRAND = ?, MODELNAME = ?, NICKNAME = ? WHERE LICENSE = ?";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, vehicle.getBrand());
			ps.setString(2, vehicle.getModelName());
			ps.setString(3, vehicle.getNickname());
			ps.setString(4, vehicle.getLicense());
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(String license) {
		
		String sql = "DELETE FROM TB_VEHICLE WHERE LICENSE = ?";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, license);
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Vehicle findAvehicle(String license) {
		
		String sql = "SELECT V.LICENSE, V.BRAND, V.MODELNAME, V.NICKNAME, V.CPF_USER FROM TB_VEHICLE V WHERE LICENSE = ?";
		
		Vehicle vehicle = null;
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, license);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				vehicle = new Vehicle();
				
				vehicle.setLicense(rs.getString("LICENSE"));
				vehicle.setBrand(rs.getString("BRAND"));
				vehicle.setModelName(rs.getString("MODELNAME"));
				vehicle.setModelName(rs.getString("NICKNAME"));
				vehicle.setOwner((User) rs.getObject("CPF_USER"));	//verificar o êxito
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return vehicle;
	}

	public List<Vehicle> findAll() {
		
		String sql = "SELECT V.LICENSE, V.BRAND, V.MODELNAME, V.NICKNAME, V.CPF_USER FROM TB_VEHICLE V";
		
		List<Vehicle> listVehicles = new ArrayList<Vehicle>();
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Vehicle vehicle = new Vehicle();
				
				vehicle.setLicense(rs.getString("LICENSE"));
				vehicle.setBrand(rs.getString("BRAND"));
				vehicle.setModelName(rs.getString("MODELNAME"));
				vehicle.setModelName(rs.getString("NICKNAME"));
				vehicle.setOwner((User) rs.getObject("CPF_USER"));	//verificar o êxito
				
				listVehicles.add(vehicle);
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listVehicles;
	}

}
