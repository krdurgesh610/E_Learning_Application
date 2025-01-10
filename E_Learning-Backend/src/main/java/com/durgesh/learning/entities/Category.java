package com.durgesh.learning.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "categories")
public class Category {
	
	@Id
	private String id;
	
	private String title;
	
	@Column(name = "description")
	private String desc;
	
	private Date addedDate;
	
	@ManyToMany(mappedBy = "categoryList")
	private List<Course> courses = new ArrayList<>();

}
