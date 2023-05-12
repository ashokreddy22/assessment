package com.in.hotel.ServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.in.hotel.Constants.HotelConstants;
import com.in.hotel.Dao.ProductDao;
import com.in.hotel.JWT.JwtFilter;
import com.in.hotel.Service.ProductService;
import com.in.hotel.Utils.HotelUtils;
import com.in.hotel.model.Category;
import com.in.hotel.model.Product;
import com.in.hotel.wrapper.ProductWrapper;

@Service

public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Autowired
	private JwtFilter jwtFilter;

	@Override
	public ResponseEntity<String> addProduct(Map<String, String> requestMap) {
		try {

			if (jwtFilter.isAdmin()) {
				if (validateProductMap(requestMap, false)) {
					productDao.save(getProductFromMap(requestMap, false));
					return HotelUtils.getResponseEntity("Product Added Successfully", HttpStatus.OK);
				}
				return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
			} else {
				return HotelUtils.getResponseEntity(HotelConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	private Product getProductFromMap(Map<String, String> requestMap, boolean isAdd) {
		Category category = new Category();
		category.setId(Integer.parseInt(requestMap.get("categoryId")));

		Product product = new Product();

		if (isAdd) {
			product.setId(Integer.parseInt(requestMap.get("id")));
		} else {
			product.setStatus("true");
		}
		product.setCategory(category);
		product.setName(requestMap.get("name"));
		product.setDescription(requestMap.get("description"));
		product.setPrice(Integer.parseInt(requestMap.get("price")));

		return product;
	}

	private boolean validateProductMap(Map<String, String> requestMap, boolean validateId) {
		if (requestMap.containsKey("name")) {
			if (requestMap.containsKey("id") && validateId) {
				return true;
			} else if (!validateId) {
				return true;
			}
		}
		return false;
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getAllProducts() {
		try {

			return new ResponseEntity<>(productDao.getallProducts(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateProduct(Map<String, String> requestMap) {

		try {
			if (jwtFilter.isAdmin()) {
				if (validateProductMap(requestMap, true)) {
					Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
					if (!optional.isEmpty()) {
						Product product = getProductFromMap(requestMap, true);
						product.setStatus(optional.get().getStatus());
						productDao.save(product);
						return HotelUtils.getResponseEntity("Product updated Successfully", HttpStatus.OK);
					}
					return HotelUtils.getResponseEntity("Product Id does not exist", HttpStatus.OK);
				} else {
					return HotelUtils.getResponseEntity(HotelConstants.INVALID_DATA, HttpStatus.BAD_REQUEST);
				}

			} else {
				return HotelUtils.getResponseEntity(HotelConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> deleteProduct(Integer id) {

		try {
			if (jwtFilter.isAdmin()) {
				Optional<Product> optional = productDao.findById(id);
				if (!optional.isEmpty()) {

					productDao.deleteById(id);
					return HotelUtils.getResponseEntity("Product deleted Successfully", HttpStatus.OK);
				}
				return HotelUtils.getResponseEntity("Product Id does not exist", HttpStatus.OK);

			} else {

				return HotelUtils.getResponseEntity(HotelConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<String> updateStatus(Map<String, String> requestMap) {

		try {
			if (jwtFilter.isAdmin()) {
				Optional<Product> optional = productDao.findById(Integer.parseInt(requestMap.get("id")));
				if (!optional.isEmpty()) {
					productDao.updateProductStatus(requestMap.get("status"), Integer.parseInt(requestMap.get("id")));
					return HotelUtils.getResponseEntity("Product status updated Successfully", HttpStatus.OK);
				}
				return HotelUtils.getResponseEntity("Product Id does not exist", HttpStatus.OK);

			} else {
				return HotelUtils.getResponseEntity(HotelConstants.UNAUTHORIZED_ACCESS, HttpStatus.UNAUTHORIZED);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<ProductWrapper>> getByCategory(Integer id) {

		try {
			return new ResponseEntity(productDao.getProductByCategory(id), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ArrayList<>(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<ProductWrapper> getProductById(Integer id) {
		try {
			return new ResponseEntity(productDao.getProductById(id), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return new ResponseEntity<>(new ProductWrapper(), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
