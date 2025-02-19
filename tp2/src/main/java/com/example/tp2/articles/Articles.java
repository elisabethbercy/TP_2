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
    private long idArticle;
    private String nomArticle;
    private String qteArticle;
    private String prixArticle;

    

    // joining commande  nomCommande column to article table
    @ManyToOne
    @JoinColumn(name = "id")
    private Commandes commandes;    

    public Articles(){

    }

    public Articles(String nomArticle, String qteArticle, String prixArticle, Commandes commandes) {
        this.nomArticle = nomArticle;
        this.qteArticle = qteArticle;
        this.prixArticle = prixArticle;
        this.commandes = commandes;
    }



    public long getId() {
        return idArticle;
    }

    public void setId(long idArticle) {
        this.idArticle = idArticle;
    }

    
    public String getNomArticle() {
        return nomArticle;
    }

    public void setNomArticle(String nomArticle) {
        this.nomArticle = nomArticle;
    }

    public String getQteArticle() {
        return qteArticle;
    }

    public void setQteArticle(String qteArticle) {
        this.qteArticle = qteArticle;
    }

    public String getPrixArticle() {
        return prixArticle;
    }

    public void setPrixArticle(String prixArticle) {
        this.prixArticle = prixArticle;
    }
  

    public Commandes getCommandes() {
        return commandes;
    }

    public void setCommandes(Commandes commandes) {
        this.commandes = commandes;
    }

}
