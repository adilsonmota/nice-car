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

	private Vehicle newVehicle;
	private List<Vehicle> registeredVehicles;
	private VehicleDao vehicleDao;
	
	private User currentUser;

	private GrowlViewBean message;

	public VehicleBean() {
		this.message = new GrowlViewBean();

		this.registeredVehicles = new ArrayList<Vehicle>();
		this.newVehicle = new Vehicle();
		this.vehicleDao = new VehicleDaoImpl();
		
		this.currentUser = new User();
	}
	

	public String registerVehicle() {

		boolean msg = true;
		
		Object obj = SessionUtil.getParam("logged");

		currentUser  = (User) obj;
		
		System.out.println(currentUser.getEmail());
		
		this.registeredVehicles = this.vehicleDao.findAll();

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


	public Vehicle getNewVehicle() {
		return newVehicle;
	}


	public void setNewVehicle(Vehicle newVehicle) {
		this.newVehicle = newVehicle;
	}


}
