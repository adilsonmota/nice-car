package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private String cpf;
	
	private String email;
	
	private String password;
	
	private String name;

	private List<Vehicle> vehicles = new ArrayList<Vehicle>();

	
	public void addVehicle(Vehicle vehicle) {
		vehicles.add(vehicle);
	}
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Vehicle> getVehicles() {
		return vehicles;
	}
}
