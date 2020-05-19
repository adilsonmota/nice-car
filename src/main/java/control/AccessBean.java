package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.PartDao;
import dao.PartDaoImpl;
import dao.UserDao;
import dao.UserDaoImpl;
import dao.VehicleDao;
import dao.VehicleDaoImpl;
import entities.Part;
import entities.User;
import entities.Vehicle;
import service.GrowlViewBean;

@ManagedBean(name = "AccessBean")
@SessionScoped
public class AccessBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usrLogin;
	private String pswLogin;
	private String license;
	
	private User newUser;
	private List<User> registeredUsers;
	private UserDao userDao;

	private Vehicle newVehicle;
	private List<Vehicle> registeredVehicles;
	private VehicleDao vehicleDao;

	private Part newPart;
	private List<Part> registeredParts;
	private PartDao partDao;

	private GrowlViewBean message;

	public AccessBean() {
		this.message = new GrowlViewBean();

		this.registeredUsers = new ArrayList<User>();
		this.newUser = new User();
		this.userDao = new UserDaoImpl();

		this.registeredVehicles = new ArrayList<Vehicle>();
		this.newVehicle = new Vehicle();
//		this.newVehicle.setOwner(new User());
		this.vehicleDao = new VehicleDaoImpl();

		this.registeredUsers = new ArrayList<User>();
		this.newUser = new User();
		this.userDao = new UserDaoImpl();
		
		this.registeredParts = new ArrayList<Part>();
		this.newPart = new Part();
//		this.newPart.setVehicle(new Vehicle());
		this.partDao = new PartDaoImpl();

	}

	public String login() {

		boolean msg = false;

		this.registeredUsers = this.userDao.findAll();

		for (User listUsers : registeredUsers) {
			if ((listUsers.getEmail().equals(usrLogin) && listUsers.getPassword().equals(pswLogin))) {
				msg = true;
			}
		}
		if (msg) {
			message.setSuccessMessage("Sucesso no Login!");
			message.saveMessage(true);
			return "register_vehicle.xhtml";
		} else {
			message.setErrorMessage("Falha no Login! Verifique seu usuário e senha!");
			message.saveMessage(false);
		}
		return null;
	}
	
	public String registerPart() {

		this.partDao.insert(newPart);
	
		message.setSuccessMessage("Cadastro realizado com sucesso!");
		return "register_parts.xhtml";
	}

	public String registerVehicle() {

		boolean msg = true;

		this.registeredVehicles = this.vehicleDao.findAll();

		for (Vehicle listVehicles : registeredVehicles) {
			if (listVehicles.getLicense().equals(newVehicle.getLicense())) {
				msg = false;
			}
		}

		if (msg) {
			this.newVehicle.setOwner(getLogged());
			this.vehicleDao.insert(newVehicle);
			this.newPart.setVehicle(newVehicle);
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

	public User getLogged() {
		User current = new User();
		current = userDao.findAuser(usrLogin);
		return current;
	}

	public String registerUser() {

		boolean msg = true;

		this.registeredUsers = this.userDao.findAll();

		for (User listUsers : registeredUsers) {
			if ((listUsers.getEmail().equals(this.newUser.getEmail()))
					|| (listUsers.getCpf().equals(this.newUser.getCpf()))) {
				msg = false;
			}
		}

		if (msg) {
			this.userDao.insert(newUser);
			this.newUser = new User();
			message.setSuccessMessage("Cadastro realizado com sucesso!");
			message.saveMessage(true);
			return "login.xhtml";
		} else {
			message.setErrorMessage("CPF ou email já está em uso!");
			message.saveMessage(false);
		}
		return null;
	}

	public String getUsrLogin() {
		return usrLogin;
	}

	public void setUsrLogin(String usrLogin) {
		this.usrLogin = usrLogin;
	}

	public String getPswLogin() {
		return pswLogin;
	}

	public void setPswLogin(String pswLogin) {
		this.pswLogin = pswLogin;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(List<User> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public Vehicle getNewVehicle() {
		return newVehicle;
	}

	public void setNewVehicle(Vehicle newVehicle) {
		this.newVehicle = newVehicle;
	}

	public List<Vehicle> getRegisteredVehicles() {
		return registeredVehicles;
	}

	public void setRegisteredVehicles(List<Vehicle> registeredVehicles) {
		this.registeredVehicles = registeredVehicles;
	}

	public VehicleDao getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public Part getNewPart() {
		return newPart;
	}

	public void setNewPart(Part newPart) {
		this.newPart = newPart;
	}

	public List<Part> getRegisteredParts() {
		return registeredParts;
	}

	public void setRegisteredParts(List<Part> registeredParts) {
		this.registeredParts = registeredParts;
	}

	public PartDao getPartDao() {
		return partDao;
	}

	public void setPartDao(PartDao partDao) {
		this.partDao = partDao;
	}

	public GrowlViewBean getMessage() {
		return message;
	}

	public void setMessage(GrowlViewBean message) {
		this.message = message;
	}


}
