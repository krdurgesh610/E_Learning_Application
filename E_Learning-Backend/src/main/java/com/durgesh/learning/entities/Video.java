package com.durgesh.learning.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {

	@Id
	private String id;

	private String title;

	@Column(name = "description", length = 1000)
	private String desc;

	private String filePath;

	private String contentType;
	
	@ManyToOne
	private Course course;

}
