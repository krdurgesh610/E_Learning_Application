package com.durgesh.learning.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.durgesh.learning.dtos.CategoryDto;
import com.durgesh.learning.dtos.CustomPageResponse;
import com.durgesh.learning.entities.Category;
import com.durgesh.learning.exceptions.ResourceNotFoundException;
import com.durgesh.learning.repositories.CategoryRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo;

	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepo categoryRepo, ModelMapper modelMapper) {
		super();
		this.categoryRepo = categoryRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CategoryDto insert(CategoryDto categoryDto) {

		// Create category Id
		String catId = UUID.randomUUID().toString();
		categoryDto.setId(catId);
		categoryDto.setAddedDate(new Date());

		// Convert dto to Entity
		Category category = modelMapper.map(categoryDto, Category.class);
		Category saveCategory = categoryRepo.save(category);
		return modelMapper.map(saveCategory, CategoryDto.class);
	}

	@Override
	public CustomPageResponse<CategoryDto> getAll(int pageNumber, int pageSize, String sortBy, String sortByAcc) {
		Sort sort = Sort.by(sortBy);
		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);
		Page<Category> categoryPage = categoryRepo.findAll(pageRequest);
		List<Category> all = categoryPage.getContent();
		List<CategoryDto> categoryDtoList = all.stream().map(category -> modelMapper.map(category, CategoryDto.class))
				.toList();
		CustomPageResponse<CategoryDto> customPageResponse = new CustomPageResponse<CategoryDto>();
		customPageResponse.setContent(categoryDtoList);
		customPageResponse.setLast(categoryPage.isLast());
		customPageResponse.setTotalElements(categoryPage.getTotalElements());
		customPageResponse.setTotalPages(categoryPage.getTotalPages());
		customPageResponse.setPageNumber(pageNumber);
		customPageResponse.setPageSize(categoryPage.getSize());
		return customPageResponse;
	}

	@Override
	public CategoryDto get(String categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found !!"));
		return modelMapper.map(category, CategoryDto.class);
	}

	@Override
	public void delete(String categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found !!"));
		categoryRepo.delete(category);
	}

	@Override
	public CategoryDto update(CategoryDto categoryDto, String categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found !!"));
		category.setTitle(categoryDto.getTitle());
		category.setDesc(categoryDto.getDesc());
		category.setAddedDate(new Date());
		Category savedCategory = categoryRepo.save(category);
		return modelMapper.map(savedCategory, CategoryDto.class);
	}

}
