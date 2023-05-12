package com.in.hotel.ControllerImpl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.in.hotel.Controller.DashBoardController;
import com.in.hotel.Service.DashBoardService;
@RestController
public class DashBoardControllerImpl implements DashBoardController {
@Autowired
private DashBoardService dashBoardService;
	@Override
	public ResponseEntity<Map<String, Object>> getCount() {
		return dashBoardService.getCount();
	}

}
