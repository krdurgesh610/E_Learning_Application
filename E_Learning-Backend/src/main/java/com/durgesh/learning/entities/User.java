package com.durgesh.learning.entities;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String userId;
	
	private String name;
	
	private String email;
	
	private String phoneNumber;
	
	private String password;
	
	private boolean about;
	
	private String active;
	
	private boolean emailVerified;
	
	private boolean phoneVerified;
	
	private Date createdAt;
	
	private String profilePath;
	
	private String recentOTP;

}
