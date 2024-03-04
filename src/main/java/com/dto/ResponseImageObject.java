package com.dto;



import java.time.LocalDateTime;


import jakarta.persistence.Lob;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ResponseImageObject {
	
	
	private int id;
	private String serviceCodeFile;
	private String fileType;
	private String fileName;
	private String encodeImage;
	private LocalDateTime imageDate;

}
