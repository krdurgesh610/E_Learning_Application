package com.durgesh.learning.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.durgesh.learning.entities.User;

public interface UserRepo extends JpaRepository<User, String>{

}
