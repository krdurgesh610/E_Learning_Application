package com.durgesh.learning.services;

import java.util.List;

import com.durgesh.learning.dtos.CourseDto;

public interface CourseService {

	CourseDto create(CourseDto courseDto);

	List<CourseDto> getAll();

	CourseDto update(CourseDto dto, String courseId);

	void delete(String courseId);

	List<CourseDto> searchByTitle(String titleKeyword);

}
