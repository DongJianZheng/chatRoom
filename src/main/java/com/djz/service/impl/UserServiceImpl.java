package com.djz.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.djz.dao.UserRepository;
import com.djz.pojo.User;
import com.djz.service.UserService;

 
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	@Override
	public User login(String username, String password) {
		return userRepository.getByUsernameAndPassword(username, password);
	}

	@Override
	public User getByUsername(User user) {
		return userRepository.getByUsername(user.getUsername());
	}

	@Override
	public void saveOrUpdate(User user) {
		userRepository.saveAndFlush(user);
	}

	@Override
	public User getById(long id) {
		return userRepository.getById(id);
	}

}
