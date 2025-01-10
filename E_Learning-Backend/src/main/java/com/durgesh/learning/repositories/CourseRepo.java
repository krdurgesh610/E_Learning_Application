package com.durgesh.learning.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.learning.entities.Course;

public interface CourseRepo extends JpaRepository<Course, String>{
	
	Optional<Course> findByTitle(String title);
	
	List<Course> findByLive(boolean live);
	
	//Search the course by title
//	Optional<Course> findByAllCourse(String title);
	
//	List<Course> findByCategoryId(String id);

}
