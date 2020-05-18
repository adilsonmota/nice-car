package entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="TB_VEHICLE")
public class Vehicle {

	@Id
	@Column(name="LICENSE_VEHICLE", nullable = false)
	private String license;
	
	@Column(name="BRAND", nullable = false)
	private String brand;
	
	@Column(name="MODELNAME", nullable = false)
	private String modelName;
	
	@ManyToOne
	@JoinColumn(name = "CPF_USER")
	private User owner;
	
	@OneToMany(mappedBy = "vehicle", cascade = CascadeType.ALL)
	private List<Part> parts;
	
	@Column(name="NICKNAME")
	private String nickname;
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public List<Part> getParts() {
		return parts;
	}

	public void setParts(List<Part> parts) {
		this.parts = parts;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	public Double totalPaid() {
		Double tot = 0.0;
		
		for (Part part : parts) {
			tot += part.getPrice();
		}
		
		return tot;
	}
	
}
