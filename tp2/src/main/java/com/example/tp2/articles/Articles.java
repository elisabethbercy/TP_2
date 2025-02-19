package com.example.tp2.articles;

import com.example.tp2.commandes.Commandes;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Articles {

    @Id
    @GeneratedValue
    private long id;
    private String nom_article;
    private String qte_article;
    private String prix_article;

    

    // joining commande  nom_commande column to article table
    @ManyToOne
    @JoinColumn(name = "nom_commande")
    private Commandes commandes;    

    public Articles(){

    }

    public Articles(String nom_article, String qte_article, String prix_article, Commandes commandes) {
        this.nom_article = nom_article;
        this.qte_article = qte_article;
        this.prix_article = prix_article;
        this.commandes = commandes;
    }



    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom_article() {
        return nom_article;
    }

    public void setNom_article(String nom_article) {
        this.nom_article = nom_article;
    }

    public String getQte_article() {
        return qte_article;
    }

    public void setQte_article(String qte_article) {
        this.qte_article = qte_article;
    }

    public String getPrix_article() {
        return prix_article;
    }

    public void setPrix_article(String prix_article) {
        this.prix_article = prix_article;
    }

    public Commandes getCommandes() {
        return commandes;
    }

    public void setCommandes(Commandes commandes) {
        this.commandes = commandes;
    }

}
