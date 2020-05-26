package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import entities.Part;
import entities.Vehicle;
import util.JpaUtil;

public class PartDaoImpl implements PartDao, Serializable {
	private static final long serialVersionUID = 1L;

	public void insert(Part part) {
		
		String sql = "INSERT INTO TB_PART (ID, NAME, PRICE, REPLACEDDATE, LICENSE_VEHICLE) VALUES (?, ?, ?, ?, ?)";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, selectId());
			ps.setString(2, part.getName());
			ps.setDouble(3, part.getPrice());
			ps.setDate(4, new Date(part.getReplacedDate().getTime()));
			ps.setString(5, part.getVehicle().getLicense());
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void update(Part part) {
		
		String sql = "UPDATE TB_PART SET NAME = ?, PRICE = ?, REPLACEDDATE = ?, LICENSE_VEHICLE = ? WHERE ID = ?";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, part.getName());
			ps.setDouble(2, part.getPrice());
			ps.setDate(3, (Date) part.getReplacedDate());
			ps.setString(4, part.getVehicle().getLicense());
			ps.setInt(5, part.getId());
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void remove(Integer id) {
		
		String sql = "DELETE FROM TB_PART WHERE ID = ?";
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ps.execute();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public Part findApart(Integer id) {
		
		String sql = "SELECT ID, NAME, PRICE, REPLACEDDATE, LICENSE_VEHICLE, BRAND,MODELNAME, NICKNAME FROM TB_PART P "
					+ "INNER JOIN TB_VEHICLE V ON (P.LICENSE_VEHICLE = V.LICENSE) WHERE ID = ?";
		
		Part obj = new Part();
		Vehicle vehicle = new Vehicle();
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				vehicle.setLicense(rs.getString("LICENSE"));
				vehicle.setBrand(rs.getString("BRAND"));
				vehicle.setModelName(rs.getString("MODELNAME"));
				vehicle.setNickname(rs.getString("NICKNAME"));
				
				obj.setId(rs.getInt("ID"));
				obj.setName(rs.getString("NAME"));
				obj.setPrice(rs.getDouble("PRICE"));
				obj.setReplacedDate(rs.getDate("REPLACEDDATE"));
				
				obj.setVehicle(vehicle);
				
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
	}

	public List<Part> findAll() {
		
		String sql = "SELECT P.ID, P.NAME, P.PRICE, P.REPLACEDDATE, P.LICENSE_VEHICLE FROM TB_PART P";
		
		List<Part> listParts = new ArrayList<Part>();
		
		Connection conn;
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				
				Part part = new Part();
				
				part.setId(rs.getInt("ID"));
				part.setName(rs.getString("NAME"));
				part.setPrice(rs.getDouble("PRICE"));
				part.setReplacedDate(rs.getDate("REPLACEDDATE"));
				part.setVehicle((Vehicle) rs.getObject("LICENSE_VEHICLE"));	//verificar êxito
				
				listParts.add(part);
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listParts;
	}
	
	private Integer selectId() {
		
		String sql = "select SEQ_PART.nextval from dual";
		
		Integer backId = null;
		
		Connection conn;
		
		try {
			conn = JpaUtil.getConexao();
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				backId = rs.getInt(1);
			}
			
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		return backId;
	}

}
