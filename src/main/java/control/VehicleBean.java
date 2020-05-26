package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.VehicleDao;
import dao.VehicleDaoImpl;
import entities.User;
import entities.Vehicle;
import service.GrowlViewBean;
import util.SessionUtil;

@ManagedBean(name = "VehicleBean")
@SessionScoped
public class VehicleBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String keyword;
	private String license;
	
	private Vehicle newVehicle;
	private List<Vehicle> registeredVehicles;
	List<Vehicle> myVehicles;
	private VehicleDao vehicleDao;
	
	private User currentUser;

	private GrowlViewBean message;

	public VehicleBean() {
		this.message = new GrowlViewBean();

		this.registeredVehicles = new ArrayList<Vehicle>();
		this.myVehicles = new ArrayList<Vehicle>();
		this.newVehicle = new Vehicle();
		this.vehicleDao = new VehicleDaoImpl();
		this.currentUser = new User();

		Object obj = SessionUtil.getParam("logged");

		currentUser  = (User) obj;
		
		this.registeredVehicles = this.vehicleDao.findAll();
		
	}
	

	public String registerVehicle() {

		boolean msg = true;

		for (Vehicle listVehicles : registeredVehicles) {
			if (listVehicles.getLicense().equals(newVehicle.getLicense())) {
				msg = false;
			}
		}

		if (msg) {
			
			this.newVehicle.setOwner(currentUser);
			
			this.vehicleDao.insert(newVehicle);
			this.newVehicle = new Vehicle();
			message.setSuccessMessage("Cadastro realizado com sucesso!");
			message.saveMessage(true);
			return "register_parts.xhtml";
		} else {
			message.setErrorMessage("Esse veículo já está cadastrado!");
			message.saveMessage(false);
		}
		return null;
	}


	public void search() {
		this.myVehicles = this.vehicleDao.findByOwner(currentUser,keyword);
	}
	
	
	
	public Vehicle getNewVehicle() {
		return newVehicle;
	}
	public void setNewVehicle(Vehicle newVehicle) {
		this.newVehicle = newVehicle;
	}


	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public List<Vehicle> getMyVehicles() {
		return myVehicles;
	}


	public String getLicense() {
		return license;
	}


	public void setLicense(String license) {
		this.license = license;
	}
	
	
	
}
