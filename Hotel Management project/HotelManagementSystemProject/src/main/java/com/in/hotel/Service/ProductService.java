package com.in.hotel.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.in.hotel.wrapper.ProductWrapper;

public interface ProductService {

	ResponseEntity<String> addProduct(Map<String, String> requestMap);

	ResponseEntity<List<ProductWrapper>> getAllProducts();

	ResponseEntity<String> updateProduct(Map<String, String> requestMap);

	ResponseEntity<String> deleteProduct(Integer id);

	ResponseEntity<String> updateStatus(Map<String, String> requestMap);

	ResponseEntity<List<ProductWrapper>> getByCategory(Integer id);

	ResponseEntity<ProductWrapper> getProductById(Integer id);

}
