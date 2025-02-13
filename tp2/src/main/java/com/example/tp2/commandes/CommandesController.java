package com.example.tp2.commandes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.tp2.users.UserController;

import ch.qos.logback.core.model.Model;


@Controller
@RequestMapping("/commandes")

public class CommandesController {
    @Autowired
    private CommandesInterface com_service;

    @Autowired private UserController usr_cntrl;

    @PostMapping("/newcommande")
    public ModelAndView newcommande( 
        @RequestParam String nom_commande,
        @RequestParam String usr_email_cmd,
        Model model){
        //usr_cntrl.login(usr_email, null, null, null); 

        System.out.println(" ===========> User email in new commande "+ usr_email_cmd);
        com_service.newcommande(nom_commande, usr_email_cmd);

        return new ModelAndView("/store/connected");
       
    }

    @GetMapping
    public ModelAndView list_Commandes() {
       // Iterable<Commandes> commandes = com_service.findAll(); // Fetch commandes
       // model.addAttribute("commandes", commandes); // Send list to Thymeleaf
        var commandes = com_service.findAll();
        var model = Map.of("commandes", commandes);

        return new ModelAndView("/commandes/connected",model); 
    }

}
