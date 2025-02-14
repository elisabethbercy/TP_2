package com.example.tp2.users;

import java.util.ArrayList;
import java.util.List;

import com.example.tp2.commandes.Commandes;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;


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

	private String nom;
	private String prenom;
	private String motdepasse;

	@OneToMany(mappedBy = "users", cascade = CascadeType.PERSIST)
	private List<Commandes> commandes = new ArrayList<>();
	
	public List<Commandes> getCommandes() {
		return commandes;
	}

	public void setCommandes(List<Commandes> commandes) {
		this.commandes = commandes;
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

	public void addCommande(Commandes commande) {
        commandes.add(commande);
        commande.setUsers(this); //  bidirectional 
    }
}
