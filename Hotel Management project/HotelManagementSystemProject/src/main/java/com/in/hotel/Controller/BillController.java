package com.in.hotel.Controller;

import java.util.List;
import java.util.Map;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.in.hotel.model.Bill;

@RequestMapping(path="/bill")
public interface BillController {

	
	@PostMapping(path="/generateReport")
	ResponseEntity<String> generateReport(@RequestBody(required=true)Map<String,Object>requestMap);
	
	@GetMapping(path="/getBills")
	ResponseEntity<List<Bill>>getBills();
	
	
	@PostMapping(path="/getPdf")
	ResponseEntity<byte[]>getPdf(@RequestBody Map<String,Object>requestMap);
	
	@PostMapping(path="/deleteBill/{id}")
	ResponseEntity<String>deleteBill(@PathVariable("id")Integer id);
}
