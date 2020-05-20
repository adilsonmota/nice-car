package entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="TB_PART")
public class Part implements Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ID", nullable = false)
	@GeneratedValue(generator = "SEQ_PART")
	@SequenceGenerator(name="SEQ_PART", sequenceName = "SEQ_PART", allocationSize = 1)
	private Integer id;
	
	@Column(name="NAME", nullable = false)
	private String name;
	
	@Column(name="PRICE")
	private Double price;
	
	@Column(name="REPLACEDDATE")
	private Date replacedDate;
	
	@ManyToOne
	@JoinColumn(name = "LICENSE_VEHICLE")
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
