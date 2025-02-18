package com.example.tp2.commandes;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

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

    @Autowired
    private CommandesRepository com_repo;

    @PostMapping("/newcommande")
    public RedirectView newcommande( 
        @RequestParam String nom_commande,
        HttpSession session,
        RedirectAttributes redirectAttributes
        ){
        String user_email = (String) session.getAttribute("user_email");
        System.out.println(user_email+"  check if user_email is in session");

        if (user_email == null) {
            // Redirect to home if no user 
            redirectAttributes.addFlashAttribute("error", "Session Invalide. Veuillez vous reconnecter.");
            return new RedirectView("redirect:/store/home");
        }

        Users user = usr_Service.findByEmail(user_email).orElse(null); // error here
        com_service.newcommande(nom_commande, user);

        System.out.println(" ===========> User email saved in new commande from Controller newcommande "+ user_email);
       
        return new RedirectView("/store/connected");
      
    }

    @GetMapping("/connected")
    public ModelAndView connected(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("/store/home");
        if(session.getAttribute("user_email") !=null){
            List<Commandes> listCommandes =  com_repo.getAllCommandes(session);
            modelAndView.addObject("commandes", listCommandes);

        }
        return modelAndView;


    }


           // String emailString = (String) session.getAttribute("user_email");

        // if (emailString == null) {
        //     // Redirect to home if no user 
        //     redirectAttributes.addFlashAttribute("error", "Liste non Valide ");
        //     return new ModelAndView("redirect:/store/home");
        // }

        // Optional<Users> usersOptional = usr_Service.findByEmail(emailString);

        // if (usersOptional.isEmpty()) {
        //     return new ModelAndView("redirect:/store/home");
        // }

        // Users users = usersOptional.get();
        
        // System.out.println("User details: " + users.getNom() + " " + users.getPrenom());
        
        // List<Commandes> listCommandes = com_service.getCommandesByUsers(users);



        // ModelAndView modelAndViewList = new ModelAndView("store/connected");
        // modelAndViewList.addObject("commandes", listCommandes);

        // System.out.println("Listes de Commandes: " + listCommandes); 

        // return modelAndViewList;
    


    

}
