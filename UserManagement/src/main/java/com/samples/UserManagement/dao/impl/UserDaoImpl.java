package com.samples.UserManagement.dao.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;

import com.samples.UserManagement.Entity.UserData;
import com.samples.UserManagement.dao.UserDao;



@Component
public class UserDaoImpl implements UserDao{

	@Autowired
	private HibernateTemplate hibernateTemplate;

	@Override
	@Transactional
	public int create(UserData user) {
		return (int) hibernateTemplate.save(user);
	}

	@Override
	@Transactional
	public UserData read(int id) {
		return hibernateTemplate.get(UserData.class,id);
	}

	@Override
	@Transactional
	public void update1(UserData user) {
		 hibernateTemplate.update(user);
	}
}
