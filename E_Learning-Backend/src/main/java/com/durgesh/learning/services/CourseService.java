package com.durgesh.learning.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.durgesh.learning.dtos.CourseDto;

public interface CourseService {

	CourseDto create(CourseDto courseDto);

//	List<CourseDto> getAll();

	CourseDto get(String courseId);

	CourseDto update(CourseDto dto, String courseId);

	void delete(String courseId);

	List<CourseDto> searchCourse(String Keyword);
	
	Page<CourseDto> getAllCourse(Pageable pageable);

}
