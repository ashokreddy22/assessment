package com.in.hotel.Dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.in.hotel.model.Bill;
@Repository
public interface BillDao extends JpaRepository<Bill,Integer>{

	List<Bill> getAllBills();

	List<Bill> getBillByUserName(@Param("userName")String currentUser);

}
