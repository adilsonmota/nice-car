package control;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;
import service.GrowlViewBean;
import util.SessionUtil;

@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private String usrLogin;
	private String pswLogin;
	
	private List<User> registeredUsers;
	private UserDao userDao;

	private GrowlViewBean message;

	public LoginBean() {
		this.message = new GrowlViewBean();

		this.registeredUsers = new ArrayList<User>();
		this.userDao = new UserDaoImpl();
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
			
			User current = new User();
			current = userDao.findAuser(usrLogin);
			
			SessionUtil.setParam("logged", current);
			
			return "register_vehicle.xhtml";
		} else {
			message.setErrorMessage("Falha no Login! Verifique seu usuário e senha!");
			message.saveMessage(false);
		}
		usrLogin = null;
		pswLogin = null;
		
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
}
