package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_USER")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CPF", nullable = false)
	private String cpf;
	
	@Column(name="EMAIL", nullable = false)
	private String email;
	
	@Column(name="PASSWORD", nullable = false)
	private String password;
	
	@Column(name="NAME")
	private String name;

	@OneToMany(mappedBy="owner", cascade = CascadeType.ALL)
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
/*
	public void setVehicles(List<Vehicle> vehicles) {
		this.vehicles = vehicles;
	}
*/	
}
