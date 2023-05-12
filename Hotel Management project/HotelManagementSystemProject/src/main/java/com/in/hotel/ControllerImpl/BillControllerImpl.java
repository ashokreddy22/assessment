package com.in.hotel.ControllerImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.in.hotel.Constants.HotelConstants;
import com.in.hotel.Controller.BillController;
import com.in.hotel.Service.BillService;
import com.in.hotel.Utils.HotelUtils;
import com.in.hotel.model.Bill;
@RestController
public class BillControllerImpl implements BillController {

	
	@Autowired
	private BillService billService;
	
	@Override
	public ResponseEntity<String> generateReport(Map<String, Object> requestMap) {
		
		try {
			return billService.generateReport(requestMap);
		} catch (Exception e) {
e.printStackTrace();		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@Override
	public ResponseEntity<List<Bill>> getBills() {
		try {
			return billService.getBills();
		} catch (Exception e) {
e.printStackTrace();		}
		return null;
	}

	@Override
	public ResponseEntity<byte[]> getPdf(Map<String, Object> requestMap) {
		try {
			return billService.getPdf(requestMap);
		} catch (Exception e) {
e.printStackTrace();		}
		return null;
	}

	@Override
	public ResponseEntity<String> deleteBill(Integer id) {
		try {
			return billService.deleteBill(id);
		} catch (Exception e) {
e.printStackTrace();		}
		return HotelUtils.getResponseEntity(HotelConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
