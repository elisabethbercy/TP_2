package com.example.tp2.commandes;

import java.util.List;

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

    @PostMapping("/new_commande")
    public ModelAndView new_commande( 
        @RequestParam String nom_commande,
        @RequestParam String usr_email,
        Model model){
        usr_cntrl.login(usr_email, null, null, null); 

        com_service.new_commande(nom_commande, usr_email);

        return new ModelAndView("/store/connected");
       
    }

    @GetMapping
    public String listCommandes(Model model) {
        List<Commandes> commandes = com_service.getAllCommandes(); // Fetch commandes
        model.addAttribute("commandes", commandes); // Send list to Thymeleaf
        return "commandes"; // Show commandes.html
    }

}
