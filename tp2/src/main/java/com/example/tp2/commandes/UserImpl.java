package com.example.tp2.commandes;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserImpl implements UserItf {
	@Autowired
	private UserRepository repo;
	
	public void create(String nom, String prenom, String email, String motdepasse) {
		var user = new Users(nom,prenom, email, motdepasse);
		repo.save(user);
	}
	
	public void login(String email, String motdepasse) {
		
		Optional<Users> user_mail = repo.findByEmail(email);
		Optional<Users> user_motdepasse = repo.findByEmail(motdepasse);
		
		
		if(user_mail.isPresent()) {
			Users auteur_to_update = auteur_id.get();
			auteur_to_update.setNom(nom);
			auteur_to_update.setPrenom(prenom);
			repo.save(auteur_to_update);
			
		}
		
	}
	
	

	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}
	
	
}
