
package com.example.tp2.articles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.example.tp2.commandes.Commandes;
import com.example.tp2.commandes.CommandesInterface;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/articles")

public class ArticlesController {

    @Autowired 
    private ArticlesInterface art_service;
    @Autowired 
    private CommandesInterface com_service;
    @Autowired 
    private ArticlesRepository art_repo;

    @PostMapping("/newarticle")
    public RedirectView newarticle(
        @RequestParam String nom_article,
        @RequestParam String qte_article,
        @RequestParam String prix_article,
        HttpSession session
    ){
        String user_email = (String) session.getAttribute("user_email");
        System.out.println(user_email+"  check if user_email is in session");

        String nomCommande = (String) session.getAttribute("nomCommande");
        System.out.println(nomCommande+"  check if nomCommande is in session");

        if (user_email == null) {
            // Redirect to home if no user 
            return new RedirectView("redirect:/store/home");
        }

        if(nomCommande == null){
            // Redirect to home if no user
            return new RedirectView("redirect:/commandes/commandes");
        }

        Commandes commandes = com_service.findByNomCommande(nomCommande).get(0);

        art_service.newArticle(nom_article, qte_article, prix_article, commandes);

        System.out.println(" ===========> User email saved in new article from Controller newarticle "+ user_email);

        return new RedirectView("/articles/articles");
    }




}