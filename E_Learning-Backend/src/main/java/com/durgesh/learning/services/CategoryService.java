package com.durgesh.learning.services;

import com.durgesh.learning.dtos.CategoryDto;
import com.durgesh.learning.dtos.CustomPageResponse;

public interface CategoryService {

	CategoryDto insert(CategoryDto categoryDto);

	CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortByAcc);

	CategoryDto get(String categoryId);

	void delete(String categoryId);

	CategoryDto update(CategoryDto categoryDto, String categoryId);

}
