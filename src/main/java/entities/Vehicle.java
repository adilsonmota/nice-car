package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vehicle implements Serializable{
	private static final long serialVersionUID = 1L;

	private String license;
	
	private String brand;
	
	private String modelName;
	
	private User owner;
	
	private List<Part> parts = new ArrayList<Part>();
	
	private String nickname;
	
	
	public void addPart(Part part) {
		parts.add(part);
	}
	
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
