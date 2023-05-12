package com.in.hotel.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.in.hotel.wrapper.ProductWrapper;

@RequestMapping(path = "/product")
public interface ProductController {

	@PostMapping(path = "/addProduct")
	ResponseEntity<String> addProduct(@RequestBody(required = true) Map<String, String> requestMap);

	@GetMapping(path = "/getAllProducts")
	ResponseEntity<List<ProductWrapper>> getAllProducts();

	@PostMapping(path = "/updateProduct")
	ResponseEntity<String> updateProduct(@RequestBody(required = true) Map<String, String> requestMap);

	@PostMapping(path = "/deleteProduct/{id}")
	ResponseEntity<String> deleteProduct(@PathVariable("id") Integer id);

	@PostMapping(path = "/updateStatus")
	ResponseEntity<String> updateStatus(@RequestBody(required = true) Map<String, String> requestMap);

	@GetMapping(path = "/getByCategory/{id}")
	ResponseEntity<List<ProductWrapper>> getByCategory(@PathVariable("id") Integer id);

	@GetMapping(path = "/getById/{id}")
	ResponseEntity<ProductWrapper> getProductById(@PathVariable("id") Integer id);
	
	

}
