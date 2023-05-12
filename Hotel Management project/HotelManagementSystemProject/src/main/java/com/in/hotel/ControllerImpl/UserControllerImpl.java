package com.in.hotel.ControllerImpl;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.in.hotel.Constants.HotelConstants;
import com.in.hotel.Controller.UserController;
import com.in.hotel.Service.UserService;
import com.in.hotel.Utils.HotelUtils;
import com.in.hotel.wrapper.UserWrapper;
@RestController
public class UserControllerImpl implements UserController {
	
	
@Autowired
private UserService userService;
	
	
	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
			
			return userService.signUp(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {
			
			return userService.login(requestMap);
		}
		catch(Exception ex) {
			
		}
		
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<List<UserWrapper>> getAllUsers() {
		
		try {
			
			return userService.getAllUser();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<List<UserWrapper>>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		
		try {
			return userService.update(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> checkToken() {
		System.out.println("checkingToken");
		try {
			return userService.checkToken();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		
		try {
			return userService.changePassword(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}


	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			return userService.forgotPassword(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
