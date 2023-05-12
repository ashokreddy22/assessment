package com.in.hotel.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.in.hotel.Constants.HotelConstants;
import com.in.hotel.Dao.UserDao;
import com.in.hotel.JWT.CustomerUsersDetailsService;
import com.in.hotel.JWT.JwtFilter;
import com.in.hotel.JWT.JwtUtil;
import com.in.hotel.Service.UserService;
import com.in.hotel.Utils.EmailUtils;
import com.in.hotel.Utils.HotelUtils;
import com.in.hotel.model.User;
import com.in.hotel.wrapper.UserWrapper;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private CustomerUsersDetailsService customerUsersDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private JwtFilter jwtFilter;
	@Autowired
	private EmailUtils emailUtils;

	@Override
	public ResponseEntity<String> signUp(Map<String, String> requestMap) {
		try {
//		log.info("Inside signup{}",requestMap);
			if (validateSignUpMap(requestMap)) {
				User user = userDao.findByEmailId(requestMap.get("email"));
				if (Objects.isNull(user)) {
					userDao.save(getUserFromMap(requestMap));
					return HotelUtils.getResponseEntity("Successfully Registred", HttpStatus.OK);
				} else {
					return HotelUtils.getResponseEntity("Email already Exists", HttpStatus.BAD_REQUEST);
				}
			} else {
				return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			}
		} catch (Exception ex) {
			ex.printStackTrace();

		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	private boolean validateSignUpMap(Map<String, String> requestMap) {
		if (requestMap.containsKey("name") && requestMap.containsKey("contactNumber") && requestMap.containsKey("email")
				&& requestMap.containsKey("password")) {
			return true;
		}
		return false;
	}

	private User getUserFromMap(Map<String, String> requestMap) {
		User user = new User();
		user.setName(requestMap.get("name"));
		user.setContactNumber(requestMap.get("contactNumber"));
		user.setEmail(requestMap.get("email"));
		user.setPassword(requestMap.get("password"));
		user.setStatus("false");
		user.setRole("user");
		return user;
	}

	@Override
	public ResponseEntity<String> login(Map<String, String> requestMap) {
		try {

			Authentication auth = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(requestMap.get("email"), requestMap.get("password")));

			if (auth.isAuthenticated()) {

				if (customerUsersDetailsService.getUserDetail().getStatus().equalsIgnoreCase("true")) {
					System.out.println("authenticated"+requestMap.get("email"));
					return new ResponseEntity<String>(
							"{\"token\":\""
									+ jwtUtil.generateToken(customerUsersDetailsService.getUserDetail().getEmail(),
											customerUsersDetailsService.getUserDetail().getRole())
									+ "\"}",
							HttpStatus.OK);
				} else {
					return new ResponseEntity<String>("{\"message\":\"" + "Wait for Admin Approval." + "\"}",
							HttpStatus.BAD_REQUEST);
				}

			}
		} catch (Exception ex) {
//			log.error("{}",ex);
		}

		return new ResponseEntity<String>("{\"message\":\"" + "Bad Credentials." + "\"}", HttpStatus.BAD_REQUEST);

	}

	@Override
	public ResponseEntity<List<UserWrapper>> getAllUser() {

		try {

			if (jwtFilter.isAdmin()) {

				return new ResponseEntity<>(userDao.getAllUser(), HttpStatus.OK);
			} else {
				return new ResponseEntity<>(new ArrayList<>(), HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> update(Map<String, String> requestMap) {
		try {
			if (jwtFilter.isAdmin()) {
				Optional<User> optional = userDao.findById(Integer.parseInt(requestMap.get("id")));
				if (!optional.isEmpty()) {

					userDao.updateStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
					sendMailToAllAdmin(requestMap.get("status"), optional.get().getEmail(), userDao.getAllAdmin());
					return HotelUtils.getResponseEntity("UserStatus updated Successfully", HttpStatus.OK);
				} else {
					HotelUtils.getResponseEntity("User id Doesn't Exist", HttpStatus.OK);
				}

			} else {
				return HotelUtils.getResponseEntity(HotelConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private void sendMailToAllAdmin(String status, String email, List<String> allAdmin) {
		allAdmin.remove(jwtFilter.getCurrentUser());
		if (status != null && status.equalsIgnoreCase("true")) {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Approved",
					"User:-" + email + "\n is approved by\nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
		} else {
			emailUtils.sendSimpleMessage(jwtFilter.getCurrentUser(), "Account Disabled",
					"User:-" + email + "\n is disabled by\nADMIN:-" + jwtFilter.getCurrentUser(), allAdmin);
		}

	}

	@Override
	public ResponseEntity<String> checkToken() {

		return HotelUtils.getResponseEntity("true", HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> changePassword(Map<String, String> requestMap) {
		try {
			User userObj = userDao.findByEmail(jwtFilter.getCurrentUser());
			if (!userObj.equals(null)) {
				if (userObj.getPassword().equals(requestMap.get("oldPassword"))) {

					userObj.setPassword(requestMap.get("newPassword"));
					userDao.save(userObj);
					return HotelUtils.getResponseEntity("Password Changed Successfully", HttpStatus.OK);
				}
				return HotelUtils.getResponseEntity("IncorrectPassword", HttpStatus.BAD_REQUEST);
			}
			return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> forgotPassword(Map<String, String> requestMap) {
		try {
			
			User user = userDao.findByEmail(requestMap.get("email"));
		
			if (!Objects.isNull(user) && !Strings.isNullOrEmpty(user.getEmail()))
				
				emailUtils.forgotMail(user.getEmail(), "Credentails by HotelManagement System", user.getPassword());
			return HotelUtils.getResponseEntity("check your mail for Credentials", HttpStatus.OK);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
