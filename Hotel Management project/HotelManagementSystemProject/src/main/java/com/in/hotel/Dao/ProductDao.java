package com.in.hotel.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import com.in.hotel.model.Product;
import com.in.hotel.wrapper.ProductWrapper;
@Repository
public interface ProductDao extends JpaRepository<Product,Integer>{

	List<ProductWrapper> getallProducts();
@Transactional
@Modifying
	Integer updateProductStatus(@Param("status")String status,@Param("id") int id);
List<ProductWrapper> getProductByCategory(@Param("categoryId")Integer id);
List<ProductWrapper> getProductById(@Param("productId")Integer id);

}
