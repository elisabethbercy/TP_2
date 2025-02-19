package com.example.tp2.commandes;
import com.example.tp2.users.Users;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Commandes {
    
    @Id
    @GeneratedValue
    private Long id;

    private String nom_commande;

    // joining users email column to Commandes table
    @ManyToOne
    @JoinColumn(name = "email")
    private Users users;


    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }

    public Commandes(){

    }

    public Commandes(String nom_commande, Users users) {
        this.nom_commande = nom_commande;
        this.users =  users;
    }

   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom_commande() {
        return nom_commande;
    }

    public void setNom_commande(String nom_commande) {
        this.nom_commande = nom_commande;
    }
    

}
