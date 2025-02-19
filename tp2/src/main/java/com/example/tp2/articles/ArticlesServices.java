package com.example.tp2.articles;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tp2.commandes.Commandes;

@Service
public class ArticlesServices implements ArticlesInterface {

    @Autowired
    private ArticlesRepository a_repo;

    @Override
    public Articles newArticle(String nom_article, String qte_article, String prix_article, Commandes commandes) {
       Articles articles = new Articles();
        articles.setNom_article(nom_article);
        articles.setQte_article(qte_article);
        articles.setPrix_article(prix_article);
        articles.setCommandes(commandes);
        return a_repo.save(articles);
    }

    @Override
    public List<Articles> getArticlesByCommandes(Commandes commandes) {
    
        return a_repo.findByCommandes(commandes);
    }

       
    @Override
    public List<Articles> findAll() {

        return a_repo.findAll();
    }

    @Override
    public List<Articles> findByCommandes(Commandes commandes) {
        return a_repo.findByNomCommande(commandes.getNomCommande());
    }




    
}
