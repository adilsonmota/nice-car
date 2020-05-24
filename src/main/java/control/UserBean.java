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

@ManagedBean(name = "UserBean")
@SessionScoped
public class UserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private User newUser;
	private List<User> registeredUsers;
	private UserDao userDao;

	private GrowlViewBean message;

	public UserBean() {
		this.message = new GrowlViewBean();

		this.registeredUsers = new ArrayList<User>();
		this.newUser = new User();
		this.userDao = new UserDaoImpl();
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
			this.newUser = new User();
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
}
