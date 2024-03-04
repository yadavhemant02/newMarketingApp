package com.model;




import java.time.LocalDateTime;

//import java.util.Date;
import java.util.List;

import org.hibernate.type.descriptor.java.LocalDateTimeJavaType;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class productService {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Column(unique = true)
	private String serviceName;
	private String serviceAbout;
	@Column(unique = true)
	private String serviceCode;
	private LocalDateTime serviceDate;
	
	

}
