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
    public Articles newArticle(String nomArticle, String qteArticle, String prixArticle, Commandes commandes) {
       Articles articles = new Articles();
        articles.setNomArticle(nomArticle);
        articles.setQteArticle(qteArticle);
        articles.setPrixArticle(prixArticle);
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

    @Override
    public List<Articles> findByIdCommande(Commandes  commande) {
        return a_repo.findByIdCommande(commande.getId());
    }

    @Override
    public List<Articles> getArticlesByIdCommandes(Commandes commandes) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getArticlesByIdCommandes'");
    }

    // @Override
    // public List<Articles> getArticlesByIdCommandes(Commandes commandes) {
    //     return a_repo.findByIdCommande(commandes);
    // }



    
}
