package com.springmvc.service;

import java.util.List;

import com.springmvc.entity.User;

public interface UserService {

	User findById(Integer id);
	
	List<User> findAll();

	void saveOrUpdate(User user);
	
	void delete(int id);
	
}