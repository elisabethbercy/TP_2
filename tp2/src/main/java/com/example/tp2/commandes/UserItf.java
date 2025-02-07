package com.example.tp2.commandes;

import java.util.Optional;

import org.springframework.stereotype.Service;


public interface UserItf {
	
	void create(String nom, String prenom,String email,  String modepasse);
	public Optional<Users>findById(String email);
	boolean existById(String email);

	
}
