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
	private String brand;
	private String modelName;
	private String nickname;
	
	private Vehicle selectedVehicle;
	private Vehicle vehicle;
	private List<Vehicle> registeredVehicles;
	private List<Vehicle> myVehicles;
	private VehicleDao vehicleDao;
	
	private User currentUser;

	private GrowlViewBean message;

	public VehicleBean() {
		this.message = new GrowlViewBean();
		
		this.currentUser = new User();
		
		this.selectedVehicle = new Vehicle();
		this.vehicle = new Vehicle();
		this.registeredVehicles = new ArrayList<Vehicle>();
		this.myVehicles = new ArrayList<Vehicle>();
		this.vehicleDao = new VehicleDaoImpl();
		

		Object obj = SessionUtil.getParam("logged");

		currentUser  = (User) obj;
		
		this.registeredVehicles = this.vehicleDao.findAll();
	}
	

	public String registerVehicle() {

		boolean msg = true;

		for (Vehicle listVehicles : registeredVehicles) {
			if (listVehicles.getLicense().equals(vehicle.getLicense())) {
				msg = false;
			}
		}

		if (msg) {
			
			this.vehicle.setOwner(currentUser);
			
			this.vehicleDao.insert(vehicle);
			this.vehicle = new Vehicle();
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
	
	public void selectVehicle() {
/*		
*	não obtive extito em carregar diretamente os dados do objeto selecionado no dataTable		
*	e mostrar nos inputText da tela search_vehicle.xhtml	
*/		
		this.brand = selectedVehicle.getBrand();
		this.modelName = selectedVehicle.getModelName();
		this.license = selectedVehicle.getLicense();
		this.nickname = selectedVehicle.getNickname();
	}

	public void updateVehicle() {
		if (this.license != null) {
	
			this.vehicle.setBrand(this.brand);
			this.vehicle.setModelName(this.modelName);
			this.vehicle.setLicense(this.license);
			this.vehicle.setNickname(this.nickname);
			
			vehicleDao.update(vehicle);
			message.setSuccessMessage("Alterado com sucesso!");
			message.saveMessage(true);
			
			this.brand = null;
			this.modelName = null;
			this.license = null;
			this.nickname = null;
		
		} else {
			message.setErrorMessage("Ops! Carregue os dados primeiro");
			message.saveMessage(false);
		}
	}

	public void deleteVehicle() {
		if (this.license != null) {
			
			vehicleDao.remove(this.license);
			message.setSuccessMessage("Veículo e todas as peças excluído do sistema!");
			message.saveMessage(true);
			
			this.brand = null;
			this.modelName = null;
			this.license = null;
			this.nickname = null;
		
		} else {
			message.setErrorMessage("Ops! Carregue os dados primeiro");
			message.saveMessage(false);
		}
	}

	public String getKeyword() {
		return keyword;
	}


	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getLicense() {
		return license;
	}


	public void setLicense(String license) {
		this.license = license;
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


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public Vehicle getSelectedVehicle() {
		return selectedVehicle;
	}


	public void setSelectedVehicle(Vehicle selectedVehicle) {
		this.selectedVehicle = selectedVehicle;
	}


	public Vehicle getVehicle() {
		return vehicle;
	}


	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}


	public List<Vehicle> getRegisteredVehicles() {
		return registeredVehicles;
	}


	public List<Vehicle> getMyVehicles() {
		return myVehicles;
	}
}
