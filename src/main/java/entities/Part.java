package entities;

import java.io.Serializable;
import java.util.Date;

public class Part implements Serializable{
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String name;
	
	private Double price;
	
	private Date replacedDate;
	
	private Vehicle vehicle;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Date getReplacedDate() {
		return replacedDate;
	}

	public void setReplacedDate(Date replacedDate) {
		this.replacedDate = replacedDate;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
}
