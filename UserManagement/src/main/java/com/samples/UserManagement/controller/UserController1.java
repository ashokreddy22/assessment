package com.samples.UserManagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.samples.UserManagement.service.UserService;
import com.samples.UserManagement.Entity.UserData;

@Controller
public class UserController1 {
	@Autowired
	private UserService userService;

	@RequestMapping("/userInsert")
	public String ShowUserInsertPage() {
		return "userInsertData";
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public String registerUser(@ModelAttribute("user") UserData user, ModelMap model) {

		int id = userService.save(user);

		model.addAttribute("result", "User created with Id: " + id);

		return "userInsertData";

	}

	@RequestMapping("/userRetrieve")
	public String ShowUserretrievePage() {
		return "RetrieveData";
	}

	@RequestMapping(value = "/retrieveData", method = RequestMethod.POST)

	public String UserData(@ModelAttribute("user") UserData user, ModelMap model) {
		UserData userdata = userService.readdata(user.getId());
		System.out.println(userdata);
		if (userdata != null) {
			model.addAttribute("userdata", " User data of Id : " + userdata);
		} else {
			model.addAttribute("userdata", "Userdata of Id " + user.getId() + ":  Oops.no data found");
		}
		return "RetrieveData";
	}

	@RequestMapping("/userUpdate")
	public String ShowUpdateInsertPage() {
		return "UserUpdate";
	}

	@RequestMapping(value = "/userUpdatedata", method = RequestMethod.POST)
	public String UpdateUser(@ModelAttribute("user") UserData user, ModelMap model) {

		userService.updateData(user);

		model.addAttribute("result", " sussessfully User updated with Id: "+user.getId());

		return "UserUpdate";

	}
}