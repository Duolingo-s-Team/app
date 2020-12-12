package com.duolingo.client.rmi.interfaces;

import java.util.List;

import com.duolingo.client.rmi.models.User;

public interface IUser {

	public List<User> getAllUsers();
	
	public User getUserById(long user_id);
	
	public User getUserByName(String username);
	
	public boolean deleteUser(User user);
	
	public User insertUser(User user);
	
	public User updateUser(User user);
}
