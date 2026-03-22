package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Users;

//“UsersRepository = bridge between your code and database”
public interface UsersRepository extends JpaRepository<Users, Long>{
	
	boolean existsByUsername(String username);
}
