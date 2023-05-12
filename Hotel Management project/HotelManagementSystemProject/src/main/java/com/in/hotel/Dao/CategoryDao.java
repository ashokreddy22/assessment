package com.in.hotel.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.in.hotel.model.Category;
@Repository
public interface CategoryDao extends JpaRepository<Category,Integer> {

	
List<Category>getAllCategory();
}
