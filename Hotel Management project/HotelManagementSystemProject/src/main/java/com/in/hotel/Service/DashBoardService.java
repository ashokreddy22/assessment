package com.in.hotel.Service;

import java.util.Map;

import org.springframework.http.ResponseEntity;

public interface DashBoardService {

	ResponseEntity<Map<String, Object>> getCount();

}
