package com.example.tp2.commandes;
import com.example.tp2.users.Users;
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

    // joining users email column to Commandes usrEmail column
    @ManyToOne
    @JoinColumn(name = "usrEmail", referencedColumnName = "email",nullable = true)
    private Users usrEmail;


    public Commandes(){

    }

    public Commandes(String nom_commande, Users usrEmail) {
        this.nom_commande = nom_commande;
        this.usrEmail = usrEmail;
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

    public Users getusrEmail() {
        return usrEmail;
    }

    public void setusrEmail(Users usrEmail) {
        this.usrEmail = usrEmail;
    }

    
    

    

}
