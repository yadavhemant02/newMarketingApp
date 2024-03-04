package com.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//import com.model.customerUser;
import com.model.userModel;
import com.repository.userRepository;

@Service
public class userService implements UserDetailsService{
	
	
	
	@Autowired
	private userRepository userRepo;
	
	public userModel addUserData(userModel data) {
		data.setPassword(passwordEncoder().encode(data.getPassword()));
		return this.userRepo.save(data);
	}
	
	public List<userModel> login() {
		return this.userRepo.findAll();
		
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserDetails userData = this.userRepo.findByEmail(email);
		return userData;
	}
	
	 @Bean
	 public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	 }

}
