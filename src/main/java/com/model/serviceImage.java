package com.model;







import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
public class serviceImage {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String serviceCodeFile;
	private String fileType;
	private String fileName;
	@Lob
	private byte[] image;
	private LocalDateTime imageDate;
	
	

}
