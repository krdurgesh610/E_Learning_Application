package com.durgesh.learning.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.learning.entities.Course;
import com.durgesh.learning.entities.Video;

public interface VideoRepo extends JpaRepository<Video, String>{
	
	Optional<Video> findByTitle(String title);
	
	List<Video> findByCourse(Course course);
	
}
