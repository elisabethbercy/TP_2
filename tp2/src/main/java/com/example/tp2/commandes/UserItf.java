package com.example.tp2.commandes;

import org.springframework.stereotype.Service;


public interface UserItf {
	
	void create(String nom, String prenom,String email,  String modepasse);
	void login(String email, String motdepasse);
	void init();
}
