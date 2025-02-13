package com.example.tp2.commandes;



import java.util.Optional;

import com.example.tp2.users.Users;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

@Entity
public class Commandes {
    @Id
    @GeneratedValue
    private Long id;
    private String nom_commande;

    // joining users email column to Commandes usr_email column
    @JoinColumn(name = "usr_email", referencedColumnName = "email",nullable = true)
    private Optional<Users>usr_email;


    
    public Commandes(String nom_commande, Optional<Users> usr_email) {
        this.nom_commande = nom_commande;
        this.usr_email = usr_email;
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

    public Users getUsr_email() {
        return usr_email;
    }

    public void setUsr_email(Users usr_email) {
        this.usr_email = usr_email;
    }

    
    

    

}
