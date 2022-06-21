package com.supinfo.project.dao;

import java.util.List;

import com.supinfo.project.entity.User;


public interface UserDao {
	
	
	User addUser(User User);
	
	
	User findUserById(Long id);
	
	
	List<User> getAllUsers();
	User findUserByName(String name);
	User findUserByNamePassword(String name,String password);
	
}
