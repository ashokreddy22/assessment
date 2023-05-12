package com.in.hotel.Service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.in.hotel.model.Bill;

public interface BillService {

	ResponseEntity<String> generateReport(Map<String, Object> requestMap);

	ResponseEntity<List<Bill>> getBills();

	ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap);

	ResponseEntity<String> deleteBill(Integer id);

}
