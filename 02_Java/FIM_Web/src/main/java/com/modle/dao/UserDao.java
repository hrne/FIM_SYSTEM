package com.modle.dao;

import java.util.List;

import com.modle.entity.User;

public interface UserDao {

	User findById(Integer id);

	List<User> findAll();

	void save(User user);

	void update(User user);

	void delete(Integer id);

}