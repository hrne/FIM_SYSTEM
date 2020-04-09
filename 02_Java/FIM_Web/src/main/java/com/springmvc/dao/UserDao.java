package com.springmvc.dao;

import java.util.List;

import com.springmvc.entity.User;

public interface UserDao {

	User findById(Integer id);

	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(Integer id);

}