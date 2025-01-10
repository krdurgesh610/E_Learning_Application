package com.durgesh.learning.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VideoDto {

	private String id;

	private String title;

	private String desc;

	private String filePath;

	private String contentType;


}
