package com.in.hotel.JWT;

import java.util.ArrayList;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.in.hotel.Dao.UserDao;

@Service

public class CustomerUsersDetailsService implements UserDetailsService {
@Autowired
private UserDao userDao;

	private com.in.hotel.model.User userDetail;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		

		userDetail=userDao.findByEmailId(username);
	
		if(!Objects.isNull(userDetail)) {
			
			return  new User(userDetail.getEmail(),userDetail.getPassword(),new ArrayList<>());
		}
		else throw new UsernameNotFoundException("User not Found "+username);
	}

	
	public com.in.hotel.model.User getUserDetail(){
		
		
		return userDetail;
	}
}
