package com.durgesh.learning.dtos;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CourseDto {

	private String id;

	private String title;

	private String shortDesc;

	private String longDesc;

	private long price;

	private boolean live = false;

	private double discount;

	private Date createdDate;

	private String banner;

	private List<VideoDto> videos = new ArrayList<>();

	private List<CategoryDto> categoryList = new ArrayList<>();

}
