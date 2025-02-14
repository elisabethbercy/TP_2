package com.example.tp2.commandes;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.tp2.users.UserService;
import com.example.tp2.users.Users;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/commandes")

public class CommandesController {
    @Autowired
    private CommandesInterface com_service;

    @Autowired
    private UserService usr_Service;

    @PostMapping("/newcommande")
    public ModelAndView newcommande( 
        @RequestParam String nom_commande,
        HttpSession session,
        RedirectAttributes redirectAttributes,
        Model model
        ){
       
        //Users user_email = (Users) session.getAttribute("user_email");
        String user_email = (String) session.getAttribute("user_email");

        if (user_email == null) {
            // Redirect to home if no user 
            redirectAttributes.addFlashAttribute("error", "Session Invalide. Veuillez vous reconnecter.");
            return new ModelAndView("redirect:/store/home");
        }

        Users user = usr_Service.findByEmail(user_email).orElse(null); // error here
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "User not found.");
            return new ModelAndView("redirect:/store/home");
        }

        // Users users = user.get();
        List<Commandes> commandes = com_service.findByUsrEmail(user_email);
        // commandes.setNom_commande(nom_commande);
        // commandes.setUsers(users);

        session.setAttribute("commandeid",commandes.get().getId());

        com_service.newcommande(nom_commande, user_email);

        System.out.println(" ===========> User email saved in new commande "+ user_email);

        return new ModelAndView("store/connected");
       
    }

    @GetMapping("/connected")
    public ModelAndView connected(HttpSession session) {
        //Users user = (Users) session.getAttribute("user_data");
        String userEmail = (String) session.getAttribute("user_email");
        
        if (userEmail == null) {
            return new ModelAndView("redirect:/store/home");
        }

        List<Commandes> commandes = com_service.findByUsrEmail(userEmail);


        // //printing commandes in console
        commandes.forEach(c -> System.out.println("Commande: " + c.getNom_commande()));
        
        
        var model = Map.of("commandes", commandes, "userEmail", userEmail);

        return new ModelAndView("store/connected", model);  
    }

    @GetMapping("/store/article")
    public ModelAndView viewArticle(@RequestParam Long id,
        HttpSession session) {
            session.getAttribute("commandeid", commandeid);
        Optional<Commandes> commandeOpt = com_service.findById(id);

        if (commandeOpt.isEmpty()) {
            return new ModelAndView("redirect:/store/home");
        }

        Commandes commande = commandeOpt.get();
        return new ModelAndView("store/article", Map.of("commande", commande));
    }



    

}
