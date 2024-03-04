package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.model.productService;

public interface productServiceRepository extends JpaRepository<productService, Integer>{

	productService findByServiceCode(String code);

}
