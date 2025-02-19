package com.example.tp2.articles;

import java.util.List;

import com.example.tp2.commandes.Commandes;

public interface ArticlesInterface {

    Articles newArticle(String nom_article, String qte_article, String prix_article,  Commandes commandes);
    
    List<Articles> findByCommandes(Commandes commandes);

    List<Articles> getArticlesByCommandes(Commandes commandes);

    public List<Articles>findAll();

    
    
}
