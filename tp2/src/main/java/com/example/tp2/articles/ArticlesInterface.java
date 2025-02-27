package com.example.tp2.articles;

import java.util.List;

import com.example.tp2.commandes.Commandes;

public interface ArticlesInterface {

    Articles newArticle(String nomArticle, String qteArticle, String prixArticle,  Commandes commandes);
    
    List<Articles> findByCommandes(Commandes commandes);

    List<Articles> findByIdCommande(Commandes commandes);

    List<Articles> getArticlesByIdCommandes(Commandes commandes);

    List<Articles> getArticlesByCommandes(Commandes commandes);

    public List<Articles>findAll();

   

    
    
}
