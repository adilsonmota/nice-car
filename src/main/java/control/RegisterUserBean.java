package control;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import dao.UserDao;
import dao.UserDaoImpl;
import entities.User;
import service.GrowlViewBean;

@ManagedBean(name = "RegisterUserBean")
@SessionScoped
public class RegisterUserBean {
	
	private List<User> registeredUsers;
	private User newUser;

	private UserDao userDao;
	
	private GrowlViewBean message;
	
	public RegisterUserBean() {
		this.registeredUsers = new ArrayList<User>();
		this.newUser = new User();
		this.userDao = new UserDaoImpl();
		this.message = new GrowlViewBean();
	}
	
	public String registerUser() {
		
		boolean msg = true;

		this.registeredUsers = this.userDao.findAll();

		for (User listUsers : registeredUsers) {
			if ((listUsers.getEmail().equals(this.newUser.getEmail())) 	|| 
				(listUsers.getCpf().equals(this.newUser.getCpf()))) {
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
	
	public User getNewUser() {
		return newUser;
	}

	public void setNewUser(User newUser) {
		this.newUser = newUser;
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
