package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entities.Part;
import entities.User;
import entities.Vehicle;
import util.JpaUtil;

public class VehicleDaoImpl implements VehicleDao {

	public void insert(Vehicle vehicle) {

		String sql = "INSERT INTO TB_VEHICLE (LICENSE, BRAND, MODELNAME, NICKNAME, CPF_USER) VALUES (?, ?, ?, ?, ?)";

		Connection conn;

		try {
			conn = JpaUtil.getConexao();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, vehicle.getLicense());
			ps.setString(2, vehicle.getBrand());
			ps.setString(3, vehicle.getModelName());
			ps.setString(4, vehicle.getNickname());
			ps.setString(5, vehicle.getOwner().getCpf());

			ps.execute();
			ps.close();
		} catch (SQLException e) {
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
		} catch (SQLException e) {
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
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public Vehicle findAvehicle(String license) {

		String sql = "SELECT BRAND, MODELNAME, LICENSE, NICKNAME, P.ID, P.NAME, PRICE, REPLACEDDATE, "
				+ "U.CPF, U.NAME, U.EMAIL, U.PASSWORD FROM TB_VEHICLE V LEFT JOIN TB_PART P "
				+ "ON (V.LICENSE = P.LICENSE_VEHICLE) RIGHT JOIN TB_USER U ON (U.CPF = V.CPF_USER) "
				+ "WHERE V.LICENSE= ?";

		Vehicle obj = new Vehicle();
		User user = new User();
		Part part = new Part();

		Connection conn;
		try {
			conn = JpaUtil.getConexao();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, license);

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {

				obj.setLicense(rs.getString("LICENSE"));
				obj.setBrand(rs.getString("BRAND"));
				obj.setModelName(rs.getString("MODELNAME"));
				obj.setNickname(rs.getString("NICKNAME"));

				part.setId(rs.getInt("ID"));
				part.setName(rs.getString("NAME"));
				part.setPrice(rs.getDouble("PRICE"));
				part.setReplacedDate(rs.getDate("REPLACEDDATE"));

				user.setCpf(rs.getString("CPF"));
				user.setName(rs.getString("NAME"));
				user.setEmail(rs.getString("EMAIL"));
				user.setPassword(rs.getString("PASSWORD"));

				obj.addPart(part);
				obj.setOwner(user);
				part = new Part();
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return obj;
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
//				vehicle.setOwner((User) rs.getObject("CPF_USER"));	verificar o êxito

				listVehicles.add(vehicle);
			}
			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listVehicles;
	}

	@Override
	public List<Vehicle> findByOwner(User owner) {

		String sql = "SELECT LICENSE, BRAND, MODELNAME, NICKNAME FROM TB_USER U "
				+ "LEFT JOIN TB_VEHICLE V ON (U.CPF = V.CPF_USER) WHERE EMAIL = ?";

		List<Vehicle> listVehicles = new ArrayList<Vehicle>();

		Connection conn;

		try {
			conn = JpaUtil.getConexao();

			PreparedStatement ps = conn.prepareStatement(sql);

			ps.setString(1, owner.getEmail());

			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				Vehicle vehicle = new Vehicle();
				
				vehicle.setLicense(rs.getString("LICENSE"));
				vehicle.setBrand(rs.getString("BRAND"));
				vehicle.setModelName(rs.getString("MODELNAME"));
				vehicle.setNickname(rs.getString("NICKNAME"));

				listVehicles.add(vehicle);
			}

			ps.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return listVehicles;
	}

}
