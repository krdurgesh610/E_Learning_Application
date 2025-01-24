package com.durgesh.learning.dtos;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomPageResponse<T> {

	private int pageSize;

	private int pageNumber;

	private long totalElements;
	
	private int totalPages;

	private boolean isLast;

	private List<T> content;

}
