package com.example.tp2.commandes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Users {
	
	private long user_id;
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
	@GeneratedValue
	public long getUser_id() {
		return user_id;
	}
	public void setUser_id(long user_id) {
		this.user_id = user_id;
	}
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
	private String email;
}
