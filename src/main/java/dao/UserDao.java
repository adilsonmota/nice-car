package dao;

import java.util.List;

import entities.User;

public interface UserDao {

	public void insert(User user);
	public void update(User user);
	public void remove(String cpf);
	public User findAuser(String email);
	public List<User> findAll();
}
