
package com.example.tp2.articles;

import java.util.Map;
import java.util.Optional;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
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
        // @RequestParam Long id,
        @RequestParam String nomArticle,
        @RequestParam String qteArticle,
        @RequestParam String prixArticle,
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

        art_service.newArticle(nomArticle, qteArticle, prixArticle, commandes);

        System.out.println(" ===========> User email saved in new article from Controller newarticle "+ user_email);

        return new RedirectView("/articles/listarticles?id="+ commandes.getId());
    }


    @GetMapping("/listarticles")
    public ModelAndView listarticles(){
        List<Articles> listAllArticles = art_service.findAll();

        Map<String,Object> model = Map.of("articles", listAllArticles);

        System.out.println("Listes d'articles: " + listAllArticles);

        return new ModelAndView("/store/article", model);
    }



}