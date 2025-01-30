package com.durgesh.learning.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.learning.config.AppConstants;
import com.durgesh.learning.dtos.CategoryDto;
import com.durgesh.learning.dtos.CourseDto;
import com.durgesh.learning.dtos.CustomMessage;
import com.durgesh.learning.dtos.CustomPageResponse;
import com.durgesh.learning.services.CategoryService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

	private CategoryService categoryService;

	public CategoryController(CategoryService categoryService) {
		super();
		this.categoryService = categoryService;
	}

	@PostMapping
	public ResponseEntity<CategoryDto> create(@Valid @RequestBody CategoryDto categoryDto) {
		CategoryDto createdDto = categoryService.insert(categoryDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@GetMapping
	public CustomPageResponse<CategoryDto> getAll(
			@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int pageNumber,
			@RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int pageSize,
			@RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.DEFAULT_SORT_BY) String sortBy,
			@RequestParam(value = "sortByAcc", required = false, defaultValue = AppConstants.DEFAULT_SORT_DIRECTION) String sortByAcc) {
//		List<CategoryDto> all = categoryService.getAll(pageNumber, pageSize);
		return categoryService.getAll(pageNumber, pageSize, sortBy, sortByAcc);
	}

	@GetMapping("/{categoryId}")
	public CategoryDto getOne(@PathVariable String categoryId) {
		return categoryService.get(categoryId);
	}

	@DeleteMapping("/{categoryId}")
	public ResponseEntity<CustomMessage> delete(@PathVariable String categoryId) {
		categoryService.delete(categoryId);
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage("Category Deleted !!");
		customMessage.setSuccess(true);
		return ResponseEntity.status(HttpStatus.OK).body(customMessage);
	}

	@PutMapping("/{categoryId}")
	public CategoryDto update(@PathVariable String categoryId, @RequestBody CategoryDto categoryDto) {
		CategoryDto update = categoryService.update(categoryDto, categoryId);
		return update;
	}

	@PostMapping("/{categoryId}/courses/{courseId}")
	public ResponseEntity<CustomMessage> addCourseToCategory(@PathVariable String categoryId,
			@PathVariable String courseId) {

		categoryService.addCourseToCategory(categoryId, courseId);
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage("Category Updated !!");
		customMessage.setSuccess(true);
		return ResponseEntity.status(HttpStatus.OK).body(customMessage);
	}

	@GetMapping("/{categoryId}/courses")
	public ResponseEntity<List<CourseDto>> getCoursesOfCategory(@PathVariable String categoryId) {
		List<CourseDto> coursesOfCategory = categoryService.getCoursesOfCategory(categoryId);
		return ResponseEntity.status(HttpStatus.OK).body(coursesOfCategory);
	}

}
