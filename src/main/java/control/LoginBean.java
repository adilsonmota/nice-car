package control;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;
import service.GrowlViewBean;

@ManagedBean(name="LoginBean")
@SessionScoped
public class LoginBean {
	
	private String usrLogin;
	private String pswLogin;
	
	private List<User> registeredUsers;
	private User user;
	
	private UserDao userDao;
	
	private GrowlViewBean message;
		
	public LoginBean() {
		this.registeredUsers = new ArrayList<User>();
		this.user = new User();
		this.userDao = new UserDaoImpl();
		this.message = new GrowlViewBean();
	}
	
	
	public String login() {
		
		boolean msg = false;

		this.registeredUsers = this.userDao.findAll();
		
		for (User listUsers : registeredUsers) {
			if (	(listUsers.getEmail().equals(usrLogin) && listUsers.getPassword().equals(pswLogin))	) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public List<User> getRegisteredUsers() {
		return registeredUsers;
	}
	
}
