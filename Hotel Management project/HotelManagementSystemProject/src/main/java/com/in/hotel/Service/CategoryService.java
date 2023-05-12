package com.in.hotel.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.in.hotel.model.Category;

public interface CategoryService {

	ResponseEntity<String> addnewCategory(Map<String, String> requestMap);

	ResponseEntity<List<Category>> getAllCategory(String filterValue);

	ResponseEntity<String> updateCategory(Map<String, String> requestMap);

}
