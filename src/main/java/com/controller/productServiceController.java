package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.productService;
import com.service.productServiceService;

@RestController
@RequestMapping("/auth")
public class productServiceController {
	
	private static final Logger logger = LoggerFactory.getLogger(productServiceController.class);

	@Autowired
	private productServiceService productserviceService;

	@PostMapping("/add/productService")
	public ResponseEntity<?> addDataOfProductService(@RequestBody productService data) {
		try {
			productService productData = this.productserviceService.addProductService(data);
			if (productData == null) {
				throw new NullPointerException("error_NoSuchData Inserted !");
			} else {
				logger.info("new Services add Successfully !");
				return new ResponseEntity<productService>(productData, HttpStatus.OK);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>("Bad Request" + e, HttpStatus.BAD_GATEWAY);
		}
	}
	

	@GetMapping("/get/productService")
	public ResponseEntity<?> getAllProductServiceData() {
		try {
			List<productService> data = this.productserviceService.getAllProductService();
//			if (data.size() == 0) {
//				throw new NullPointerException(" sorry ! No such service are abailable !");
//			} else {
//				logger.info("fech all services successfully !");
//				return new ResponseEntity<List<productService>>(data, HttpStatus.OK);
//			}
			return new ResponseEntity<List<productService>>(data, HttpStatus.OK);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>("Bad Request" + e, HttpStatus.BAD_GATEWAY);
		}
	}

}
