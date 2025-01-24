package com.durgesh.learning.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private String id;

	@NotEmpty(message = "Title is required !!")
	private String title;

	@NotEmpty(message = "Sort Description are required !!")
	@Size(min = 5, max = 50, message = "Sort Description must be between 5 to 50 charecters")
	private String shortDesc;

	@NotEmpty(message = "Long Description are required !!")
	@Size(min = 5, max = 200, message = "Long Description must be between 5 to 200 charecter")
	private String longDesc;

	private long price;

	private boolean live = false;

	private double discount;

	private Date createdDate;

	private String banner;

	private List<VideoDto> videos = new ArrayList<>();

	private List<CategoryDto> categoryList = new ArrayList<>();

}
