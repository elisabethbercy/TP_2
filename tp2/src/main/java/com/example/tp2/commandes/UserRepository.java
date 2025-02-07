package com.example.tp2.commandes;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, Long>{
		
		Optional<Users>findByEmail(String email);
		Optional<Users>findByMotdepasse(String motdepasse);
	
		
	}

