package control;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import dao.PartDao;
import dao.PartDaoImpl;
import dao.VehicleDao;
import dao.VehicleDaoImpl;
import entities.Part;
import entities.User;
import entities.Vehicle;
import service.GrowlViewBean;
import util.SessionUtil;

@ManagedBean(name = "PartBean")
@RequestScoped
/*
 * 
 */
public class PartBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String license;
	private String replaceDate;

	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	private User currentUser;

	private Part newPart;
	private PartDao partDao;

	private Vehicle vehicle;
	private VehicleDao vehicleDao;
	private List<Vehicle> registeredVehicle;

	private GrowlViewBean message;

	public PartBean() {

		this.message = new GrowlViewBean();

		this.vehicle = new Vehicle();

		this.vehicleDao = new VehicleDaoImpl();
		this.registeredVehicle = new ArrayList<Vehicle>();

		this.newPart = new Part();
		this.partDao = new PartDaoImpl();
		
		this.currentUser = new User();

		Object obj = SessionUtil.getParam("logged");
		currentUser = (User) obj;
		
		this.registeredVehicle = vehicleDao.findByOwner(currentUser);
		
	}


	public void registerPart() {

		try {
			newPart.setReplacedDate(sdf.parse(replaceDate));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.vehicle = this.vehicleDao.findAvehicle(license);
		this.newPart.setVehicle(this.vehicle);
		this.partDao.insert(newPart);
		this.newPart = new Part();
		license = null;
		replaceDate = null;
		message.setSuccessMessage("Cadastro realizado com sucesso!");
		message.saveMessage(true);
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}

	public String getReplaceDate() {
		return replaceDate;
	}

	public void setReplaceDate(String replaceDate) {
		this.replaceDate = replaceDate;
	}

	public Part getNewPart() {
		return newPart;
	}

	public void setNewPart(Part newPart) {
		this.newPart = newPart;
	}

	public List<Vehicle> getRegisteredVehicle() {
		return registeredVehicle;
	}
}
