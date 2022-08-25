package com.samples.UserManagement.service.Impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.samples.UserManagement.Entity.UserData;
import com.samples.UserManagement.dao.UserDao;
import com.samples.UserManagement.service.UserService;

@Component("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userdao;

	@Override
	@Transactional
	public int save(UserData user) {
		return userdao.create(user);
	}

	@Override
	@Transactional
	public UserData readdata(int id) {
		return userdao.read(id);
	}

	@Override
	@Transactional
	public void updateData(UserData user) {
		userdao.update1(user);
	}

	
}
