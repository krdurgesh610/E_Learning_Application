package com.durgesh.learning.dtos;

import java.util.Date;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {

	private String id;

	@NotEmpty(message = "Title is required !!")
	@Size(min = 3, max = 50, message = "title must be between 3 to 50 charecters !!")
	private String title;

	@NotEmpty(message = "Description required !!")
	private String desc;

	private Date addedDate;

}
