package com.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.JwtResponse;
import com.dto.LoginRequest;
import com.dto.signUpRequest;
import com.dto.signUpResponse;
import com.helper.JwtHelper;
import com.model.userModel;
import com.service.userService;

@RestController
@RequestMapping("/auth")
public class userController {
	
	
	private static final Logger logger = LoggerFactory.getLogger(userController.class);
	
	@Autowired
	private JwtHelper jwtHelper;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private userService userservice;

	@PostMapping("register/user")
	public ResponseEntity<?> addUser(@RequestBody signUpRequest data) {
		userModel userData = null;
		userModel newdata = new userModel();
		newdata.setEmail(data.getEmail());
		newdata.setName(data.getName());
		newdata.setPassword(data.getPassword());
		signUpResponse responseData = new signUpResponse();
		try {
			userData = this.userservice.addUserData(newdata);
			
			if (userData == null) {
				throw new NullPointerException("error_NoData");
			}
			
			responseData.setName(userData.getName());
			responseData.setStatus("successfully ! inserted your Data.!");
			logger.info("Our User Register Successfully !");
			return new ResponseEntity<signUpResponse>(responseData, HttpStatus.OK);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return new ResponseEntity<String>("Opps !! User are not Inserted. Please Try Again" + e, HttpStatus.BAD_GATEWAY);
		}
	}
	
	

	@PostMapping("login/user")
	public ResponseEntity<?> LoginUserData(@RequestBody LoginRequest data) {

		Authentication authentication = authenticationManager
				.authenticate(new UsernamePasswordAuthenticationToken(data.getEmail(), data.getPassword()));
		
		if(authentication.isAuthenticated()) {
			userModel userData = (userModel) this.userservice.loadUserByUsername(data.getEmail());
			String token = this.jwtHelper.generateToken(userData);
			logger.info("User Login Successfully !");
			return new ResponseEntity<JwtResponse>(new JwtResponse(token,data.getEmail()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Opps ..! Please check your Credentials!",HttpStatus.BAD_GATEWAY);
		}
		

	}

//	@GetMapping("/show")
//	public List<userModel> data() {
//		return this.userservice.login();
//	}

}
