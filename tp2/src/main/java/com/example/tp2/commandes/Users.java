package com.example.tp2.commandes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity

public class Users {
	
	
	public Users() {
		
	}
	
	public Users(String nom, String prenom, String email, String motdepasse) {
		super();
	
		this.nom = nom;
		this.prenom = prenom;
		this.motdepasse = motdepasse;
		this.email = email;
	}
	
	
	@Id
	private String email;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getMotdepasse() {
		return motdepasse;
	}
	public void setMotdepasse(String motdepasse) {
		this.motdepasse = motdepasse;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	private String nom;
	private String prenom;
	private String motdepasse;
	
}
