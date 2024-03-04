package com.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.dto.ResponseImageObject;
import com.model.serviceImage;
import com.service.serviceImageService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class serviceImageController {
	
	private static final Logger logger  = LoggerFactory.getLogger(serviceImageController.class);

	@Autowired
	private serviceImageService serviceImageService;

	@PostMapping("/add/serviceImage")
	public ResponseEntity<?> addOurProductImage(@RequestParam("file") MultipartFile file,
			@RequestParam("productcode") String code) {

//		System.out.print(code);
//		System.out.print(file.getOriginalFilename());

		try {
			
			serviceImage serviceImageData = this.serviceImageService.addServiceImage(file, code);
			if (serviceImageData == null) {
				throw new NullPointerException("error_NoSuchData");
			}
			logger.info("Image inserted Successfully !");
			return new ResponseEntity<serviceImage>(serviceImageData, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return new ResponseEntity<String>("Bad Getway ..! check your Details!" + e, HttpStatus.BAD_GATEWAY);
		}
	}
	
	

	@PostMapping("/get/serviceImage")
	public ResponseEntity<?> getSingleProductServiceAllData(@RequestParam("productcode") String code) {
		try {

			List<ResponseImageObject> serviceImageData = this.serviceImageService.getSingleProductimageAll(code);
//			if (serviceImageData.size() == 0) {
//				throw new NullPointerException("error_NoSuchData");
//			}
			logger.info("fetch especifics image successfully !");
			return new ResponseEntity<List<ResponseImageObject>>(serviceImageData, HttpStatus.OK);

		} catch (Exception e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return new ResponseEntity<String>("opps..! Now we are not able to give Data!" + e, HttpStatus.BAD_GATEWAY);
		}
	}

//	@GetMapping("/get/serviceimageall")
//	public ResponseEntity<?> getAllProductServiceData(){
//		try {
//			
//			List<serviceImage> serviceImageData = this.serviceImageService.getAllServiceImage();
//			if(serviceImageData.size()==0) {
//				throw new NullPointerException("error_unknown");
//			}
//			
//			return new ResponseEntity<List<serviceImage>>(serviceImageData,HttpStatus.OK);
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//			return new ResponseEntity<String>("Bad request"+e,HttpStatus.BAD_GATEWAY);
//		}
//	}

//	

//	@Autowired
//	private serviceImageRepository repo;
//	
//	@GetMapping("/ss")
//	public String Fake(@RequestParam("productcode") String code,@RequestParam("id") int id) {
//		System.out.print(this.repo.findByServiceCodeFile(code).size());
//		System.out.print(code);
//		return code;
//	}

}
