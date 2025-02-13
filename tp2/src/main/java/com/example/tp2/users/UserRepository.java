package com.example.tp2.users;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends CrudRepository<Users, String>{
		
		Optional<Users>findById(String email);
		Optional<Users>findByEmail(String email);
		
		Optional<Users>findByMotdepasse(String motdepasse);
		//public List<Users> findByUserEmail(@Param("userEmail") String userEmail);
	
		
	}

