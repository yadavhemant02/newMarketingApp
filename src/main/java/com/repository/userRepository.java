package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

//import com.model.customerUser;
import com.model.userModel;


public interface userRepository extends JpaRepository<userModel, Integer>{

	UserDetails findByEmail(String email);
	

}
