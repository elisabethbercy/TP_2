package com.example.tp2.users;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService implements UserInterface {
	
	@Autowired
	private UserRepository repo;
	
	public void create(String nom, String prenom, String email, String motdepasse) {
		
		var user = new Users(nom,prenom, email, motdepasse);
		repo.save(user);		
	}	

	@Override
	public Optional<Users>findById(String email) {
	
		return repo.findById(email);
		
	}




	@Override
	public boolean existById(String email) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Optional<Users> findByEmail(String email) {
		
		return repo.findByEmail(email);
		
	}
		
}
