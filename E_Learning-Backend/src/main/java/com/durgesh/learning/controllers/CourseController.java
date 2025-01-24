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
import org.springframework.web.bind.annotation.RestController;

import com.durgesh.learning.dtos.CourseDto;
import com.durgesh.learning.dtos.CustomMessage;
import com.durgesh.learning.services.CourseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/courses")
public class CourseController {

	private CourseService courseService;

	public CourseController(CourseService courseService) {
		super();
		this.courseService = courseService;
	}

	@PostMapping
	public ResponseEntity<CourseDto> create(@Valid @RequestBody CourseDto courseDto) {
		CourseDto createdDto = courseService.create(courseDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(createdDto);
	}

	@GetMapping
	public List<CourseDto> getAll() {
		return courseService.getAll();
	}

	@GetMapping("/{courseId}")
	public CourseDto getOne(@PathVariable String courseId) {
		return courseService.get(courseId);
	}

	@DeleteMapping("/{courseId}")
	public ResponseEntity<CustomMessage> delete(@PathVariable String courseId) {
		courseService.delete(courseId);
		CustomMessage customMessage = new CustomMessage();
		customMessage.setMessage("Course Deleted !!");
		customMessage.setSuccess(true);
		return ResponseEntity.status(HttpStatus.OK).body(customMessage);
	}

	@PutMapping("/{courseId}")
	public CourseDto update(@PathVariable String courseId, @RequestBody CourseDto courseDto) {
		CourseDto update = courseService.update(courseDto, courseId);
		return update;
	}

}
