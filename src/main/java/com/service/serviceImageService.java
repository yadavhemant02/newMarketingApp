package com.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.dto.ResponseImageObject;
import com.model.productService;
import com.model.serviceImage;
import com.repository.productServiceRepository;
import com.repository.serviceImageRepository;

@Service
public class serviceImageService {

	@Autowired
	private serviceImageRepository serviceImageRepo;

	@Autowired
	private productServiceService productServiceService;

	// this meathod check productService are present or not ....
	public boolean checkServiceArePresent(String code) {
		productService productData = this.productServiceService.getProductServiceByCode(code);
		if (productData == null) {
			return false;
		}
		return true;
	}

	public serviceImage addServiceImage(MultipartFile file, String code) throws IOException {
		// create object on serviceImage ...
		serviceImage serviceImageData = new serviceImage();

		// get orginal file name and set orginal file name
		String originalFilename = file.getOriginalFilename();
		serviceImageData.setFileName(originalFilename);

		// find file type and set
		String filetypeData = null;
		if (originalFilename != null && originalFilename.contains(".")) {
			String[] ss = originalFilename.split("\\.");
			filetypeData = ss[1];
			serviceImageData.setFileType(filetypeData);
		} else {
			throw new NullPointerException("error_unknown");
		}

		// get byte Array and set
		try {
			byte[] byteData = file.getBytes();
			serviceImageData.setImage(byteData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Set All Values in object....
		if (checkServiceArePresent(code) == true) {
			serviceImageData.setServiceCodeFile(code);
		} else {
			throw new NullPointerException("error_unknown");
		}

		// set Date in object
//		Date date = new Date();
//		java.sql.Date datetime = new java.sql.Date(date.getTime());

		LocalDateTime currentDateTime = LocalDateTime.now();
		serviceImageData.setImageDate(currentDateTime);

		serviceImage data = this.serviceImageRepo.save(serviceImageData);

		return data;

	}

	public List<serviceImage> getAllServiceImage() {
		List<serviceImage> serviceImageData = this.serviceImageRepo.findAll();
		return serviceImageData;
	}

	public List<ResponseImageObject> getSingleProductimageAll(String productCode) {

		// this.serviceImageRepo.findByServiceCodeFile(productCode);
		List<serviceImage> serviceImageData= null;
		try {
			serviceImageData = getAllServiceImage();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			
		}

//        serviceImageData.stream().forEach(image->{
//        	byte[] bytedata = image.getImage();
//        	String Encoded = byteImageToBase64(bytedata);
//        	image.setEncodeImage(Encoded);
//        	image.setImage(null);
//        });

		List<ResponseImageObject> ResponseList = new ArrayList<>();

		for (serviceImage image : serviceImageData) {
			ResponseImageObject newResponseImage = new ResponseImageObject();
			newResponseImage.setId(image.getId());
			newResponseImage.setFileName(image.getFileName());
			String Encode = byteImageToBase64(image.getImage());
			newResponseImage.setEncodeImage(Encode);
			newResponseImage.setImageDate(image.getImageDate());
			newResponseImage.setFileType(image.getFileType());
			newResponseImage.setServiceCodeFile(image.getServiceCodeFile());
			ResponseList.add(newResponseImage);
		}
		
		List<ResponseImageObject> data  = new ArrayList<>();

		 data = ResponseList.stream()
				.filter(image -> image.getServiceCodeFile().equals(productCode)).collect(Collectors.toList());
        
		return data;
	}

	// for checking Base64
	// ...................

	public static String byteImageToBase64(byte[] imageBytes) {
		// Encode the byte array to Base64
		String base64String = Base64.getEncoder().encodeToString(imageBytes);
		return base64String;
	}

//	 public static byte[] base64ToByteImage(String base64String) {
//	        // Decode the Base64 string to byte array
//	        byte[] imageBytes = Base64.getDecoder().decode(base64String);
//	        return imageBytes;
//	    }

	// ......................

}
