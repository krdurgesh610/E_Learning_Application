package com.durgesh.learning.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "courses")
public class Course {
	
	@Id
	private String id;
	
	private String title;
	
	private String shortDesc;
	
	private String longDesc;
	
	private long price;
	
	private boolean live = false;
	
	private double discount;
	
	private Date createdDate;
	
	private String banner;
	
	@OneToMany(mappedBy = "course")
	private List<Video> videos = new ArrayList<>();
	
	@ManyToMany
	private List<Category> categoryList = new ArrayList<>();

}
