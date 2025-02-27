
package com.example.tp2.articles;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ArrayList;
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
        System.out.println(user_email+"  check if user_email is in session  new article");

        String nomCommande = (String) session.getAttribute("nomCommande");
        System.out.println(nomCommande+"  check if nomCommande is in session new article");
        
        

        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println(idCommande+" <<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        if (user_email == null) {
            // Redirect to home if no user 
            return new RedirectView("redirect:/store/home");
        }

        if(nomCommande == null){
            // Redirect to home if no user
            return new RedirectView("redirect:/commandes/commandes");
        }

        Commandes commandes = com_service.findById(idCommande).orElseThrow(()-> new NoSuchElementException());
        session.setAttribute("idCommande", commandes.getId());

        art_service.newArticle(nomArticle, qteArticle, prixArticle, commandes);

        System.out.println(" ===========> User email saved in new article from Controller newarticle "+ user_email);

        System.out.println(" ===========> Commande ID saved in new article from Controller newarticle "+ commandes.getId());

       // return new RedirectView("/articles/listarticles?id="+ commandes.getId());
        return new RedirectView("/articles/article_old?idCommande="+ commandes.getId());
    }


    // @GetMapping("/article")
    // public ModelAndView article(@RequestParam Long idCommande) {
    //     System.out.println("check ======> idCommande: " + idCommande);
    //     ModelAndView articleView = new ModelAndView("/store/article");

    //     Optional<Commandes> commandesOptional = com_service.findById(idCommande);
    //     if (commandesOptional.isEmpty()) {
    //         System.out.println("⚠️ Commande not found for id: " + idCommande);
    //         return new ModelAndView("error"); // Redirect or return an error page
    //     }

    //     Commandes commandes = commandesOptional.get();
    //     if (commandes.getArticles() == null) {
    //         commandes.setArticles(new ArrayList<>()); // Prevent Thymeleaf errors
    //     }

    //     System.out.println("✅ Found commande: " + commandes); // Debugging
    //     articleView.addObject("commande", commandes);
    //     return articleView;
    // }

    


    @GetMapping("/article_old")
    public ModelAndView article(
        HttpSession session,
        @RequestParam("idCommande") Long id
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");

        if(idCommande == null){
            return new ModelAndView("redirect:/commandes/commandes");
        }

        Optional<Commandes> commandes = com_service.findById(idCommande);

        if(commandes.isEmpty()){
            return new ModelAndView("redirect:/commandes/commandes");
        }


        List<Articles> listArtByCom = art_service.getArticlesByCommandes(commandes.get());
        Map<String,Object> model = Map.of("articles", listArtByCom);
        // session.setAttribute("articles", );

        return new ModelAndView("/store/article",model);
    }

    @GetMapping("/listarticlesbycommande")
    public ModelAndView listarticlesbycommande(
        @RequestParam Long id,
        HttpSession session
    ){
        Long idCommande = (Long) session.getAttribute("idCommande");
        System.out.println("check ======> idCommande: In article " + idCommande);


        Optional<Commandes> commandes = com_service.findById(idCommande);

        List<Articles> listArtByCom = art_service.getArticlesByCommandes(commandes.get());
        Map<String,Object> model = Map.of("articles", listArtByCom);

        return new ModelAndView("/store/article",model);
    }



    @GetMapping("/listarticles")
    public ModelAndView listarticles(){
        List<Articles> listAllArticles = art_service.findAll();

        Map<String,Object> model = Map.of("articles", listAllArticles);

        System.out.println("Listes d'articles: " + listAllArticles);

        return new ModelAndView("/store/article", model);
    }



}