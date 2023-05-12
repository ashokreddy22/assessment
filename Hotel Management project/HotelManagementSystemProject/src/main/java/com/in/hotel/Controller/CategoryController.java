package com.in.hotel.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.in.hotel.model.Category;

@RequestMapping(path="/category")
public interface CategoryController {
	
	
	@PostMapping(path="/addNewCategory")
	ResponseEntity<String> addNewCategory(@RequestBody(required=true)Map<String,String>requestMap);

	
	@GetMapping(path="/getCategory")
	ResponseEntity<List<Category>> getAllCategory(@RequestParam(required=false)String filterValue);
	
	
	@PostMapping(path="/updateCategory")
	ResponseEntity<String> updateCategory(@RequestBody(required=true)Map<String,String>requestMap);
}
