package com.repository;

import java.util.Collection;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.dto.ResponseImageObject;
import com.model.serviceImage;

public interface serviceImageRepository extends  JpaRepository<serviceImage, Integer>{

	//@Query(value = "SELECT u FROM serviceImage u WHERE u.serviceCodeFile = 'RCMjhzG2BY'")
	Collection<serviceImage> findByServiceCodeFile(String productCode);

	//List<ResponseImageObject> findByFileType(String code);

}
