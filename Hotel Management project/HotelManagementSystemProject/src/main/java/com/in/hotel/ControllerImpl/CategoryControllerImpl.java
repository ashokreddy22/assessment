package com.in.hotel.ControllerImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.in.hotel.Constants.HotelConstants;
import com.in.hotel.Controller.CategoryController;
import com.in.hotel.Service.CategoryService;
import com.in.hotel.Utils.HotelUtils;
import com.in.hotel.model.Category;
@RestController
public class CategoryControllerImpl implements CategoryController{

	@Autowired
	private CategoryService categoryService;
	
	@Override
	public ResponseEntity<String> addNewCategory(Map<String, String> requestMap) {
		
		try {
			return categoryService.addnewCategory(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Category>> getAllCategory(String filterValue) {
		try {
			return categoryService.getAllCategory(filterValue);
		}catch(Exception ex) {
			ex.printStackTrace();
		}return new ResponseEntity<>(new ArrayList<>(),HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateCategory(Map<String, String> requestMap) {
		try {
			return categoryService.updateCategory(requestMap);
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
