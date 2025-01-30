package com.durgesh.learning.services;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.durgesh.learning.dtos.CourseDto;
import com.durgesh.learning.entities.Course;
import com.durgesh.learning.exceptions.ResourceNotFoundException;
import com.durgesh.learning.repositories.CourseRepo;

@Service
public class CourseServiceImpl implements CourseService {

	private CourseRepo courseRepo;

	private ModelMapper modelMapper;

	public CourseServiceImpl(CourseRepo courseRepo, ModelMapper modelMapper) {
		super();
		this.courseRepo = courseRepo;
		this.modelMapper = modelMapper;
	}

	@Override
	public CourseDto create(CourseDto courseDto) {
		// Create course Id
		String couId = UUID.randomUUID().toString();
		courseDto.setId(couId);
		courseDto.setCreatedDate(new Date());

		// Convert dto to Entity
		Course course = modelMapper.map(courseDto, Course.class);
		Course saveCourse = courseRepo.save(course);
		return modelMapper.map(saveCourse, CourseDto.class);

	}

//	@Override
//	public List<CourseDto> getAll() {
//		List<Course> list = courseRepo.findAll();
//		List<CourseDto> courseDtos = list.stream().map(course -> modelMapper.map(course, CourseDto.class)).toList();
//		return courseDtos;
//	}

	@Override
	public CourseDto get(String courseId) {
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Fount !!"));
		return modelMapper.map(course, CourseDto.class);
	}

	@Override
	public void delete(String courseId) {
		courseRepo.deleteById(courseId);
	}

	@Override
	public CourseDto update(CourseDto dto, String courseId) {
		Course course = courseRepo.findById(courseId)
				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found !!"));

		modelMapper.map(dto, course);
		Course updateCourse = courseRepo.save(course);
		return modelMapper.map(updateCourse, CourseDto.class);

// Ye me bnaya tha sir ka trika alag h uper !!

//		course.setBanner(dto.getBanner());
//		course.setDiscount(dto.getDiscount());
//		course.setLongDesc(dto.getLongDesc());
//		course.setShortDesc(dto.getShortDesc());
//		course.setPrice(dto.getPrice());
//		course.setTitle(dto.getTitle());
//		Course savedCourse = courseRepo.save(course);
//		return modelMapper.map(savedCourse, CourseDto.class);
	}

	@Override
	public List<CourseDto> searchCourse(String Keyword) {
		List<Course> courses = courseRepo.findByTitleContainingIgnoreCaseOrShortDescContainingIgnoreCase(Keyword,
				Keyword);
		return courses.stream().map(course -> modelMapper.map(course, CourseDto.class)).collect(Collectors.toList());
	}

// getAllCourse me Custom Page SpringBoot me predefined h esiliye custom page use nhi kiye , mn ho to kr skte h 

	@Override
	public Page<CourseDto> getAllCourse(Pageable pageable) {
		Page<Course> courses = courseRepo.findAll(pageable);
		List<CourseDto> dtos = courses.getContent().stream().map(course -> modelMapper.map(course, CourseDto.class))
				.collect(Collectors.toList());
		return new PageImpl<>(dtos, pageable, courses.getTotalElements());
	}

//	@Override
//	public CourseDto create(CourseDto courseDto) {
//
//		Course saveCourse = courseRepo.save(dtoToEntity(courseDto));
//
//		return entityToDto(saveCourse);
//	}
//
//	@Override
//	public List<CourseDto> getAll() {
//		List<Course> courses = courseRepo.findAll();
//
//		List<CourseDto> courseDtoList = courses.stream().map(course -> entityToDto(course)).toList();
//		return courseDtoList;
//	}
//
//	@Override
//	public CourseDto update(CourseDto dto, String courseId) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public void delete(String courseId) {
//		Course course = courseRepo.findById(courseId)
//				.orElseThrow(() -> new ResourceNotFoundException("Course Not Found !!"));
//		courseRepo.delete(course);
//	}
//
//	@Override
//	public List<CourseDto> searchByTitle(String titleKeyword) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	public CourseDto entityToDto(Course course) {
////		CourseDto courseDto = new CourseDto();
////		courseDto.setId(course.getId());
////		courseDto.setTitle(course.getTitle());
////		courseDto.setBanner(course.getBanner());
////		courseDto.setCreatedDate(course.getCreatedDate());
////		courseDto.setDiscount(course.getDiscount());				
////		courseDto.setLongDesc(course.getLongDesc());
////		courseDto.setPrice(course.getPrice());
////		courseDto.setShortDesc(course.getShortDesc());
//
////		courseDto.setVideos(course.getVideos());
////		courseDto.setCategoryList(course.getCategoryList());
//
////	...... ye uper sb set krne se bachne k liye modelmapper ka use kiye h jo ek line me sb set kr diya .....
//		CourseDto courseDto = modelMapper.map(course, CourseDto.class);
//
//		return courseDto;
//	}
//
//	public Course dtoToEntity(CourseDto dto) {
////		Course course = new Course();
////
////		course.setId(dto.getId());
////		course.setBanner(dto.getBanner());
////		course.setCreatedDate(dto.getCreatedDate());
////		course.setDiscount(dto.getDiscount());
////		course.setLongDesc(dto.getLongDesc());
////		course.setPrice(dto.getPrice());
////		course.setShortDesc(dto.getShortDesc());
////		course.setTitle(dto.getTitle());
//
////	...... ye uper sb set krne se bachne k liye modelmapper ka use kiye h jo ek line me sb set kr diya .....
//		Course course = modelMapper.map(dto, Course.class);
//
//		return course;
//	}

}
