package com.durgesh.learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.learning.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, String>{

}
