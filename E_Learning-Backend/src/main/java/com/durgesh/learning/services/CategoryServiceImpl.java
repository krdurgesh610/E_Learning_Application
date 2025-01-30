package com.durgesh.learning.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.durgesh.learning.dtos.CategoryDto;
import com.durgesh.learning.dtos.CourseDto;
import com.durgesh.learning.dtos.CustomPageResponse;
import com.durgesh.learning.entities.Category;
import com.durgesh.learning.entities.Course;
import com.durgesh.learning.exceptions.ResourceNotFoundException;
import com.durgesh.learning.repositories.CategoryRepo;
import com.durgesh.learning.repositories.CourseRepo;

@Service
public class CategoryServiceImpl implements CategoryService {

	private CategoryRepo categoryRepo;

	private CourseRepo courseRepo;

	private ModelMapper modelMapper;

	public CategoryServiceImpl(CategoryRepo categoryRepo, CourseRepo courseRepo, ModelMapper modelMapper) {
		super();
		this.categoryRepo = categoryRepo;
		this.courseRepo = courseRepo;
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

	@Override
	@Transactional
	public void addCourseToCategory(String catId, String courseId) {
		// get Category
		Category category = categoryRepo.findById(catId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found !!"));
		// get Course
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found !!"));

		// Course k under category list add ho gyi
		// category k under jo course ushme course add ho gyi
		category.addCourses(course);

		categoryRepo.save(category);

		System.out.println("Category relationship updated !!");
	}

	@Override
	@Transactional
	public List<CourseDto> getCoursesOfCategory(String categoryId) {
		Category category = categoryRepo.findById(categoryId)
				.orElseThrow(() -> new ResourceNotFoundException("Category Not Found !!"));
		List<Course> courses = category.getCourses();
		return courses.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
	}

}
