package com.example.tp2.commandes;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, String>{
		
		Optional<Users>findById(String email);
		
		Optional<Users>findByMotdepasse(String motdepasse);

	
		
	}

