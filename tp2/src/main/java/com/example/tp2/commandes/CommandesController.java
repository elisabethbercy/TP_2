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
import org.springframework.web.servlet.view.RedirectView;

import com.example.tp2.users.UserService;
import com.example.tp2.users.Users;

import org.springframework.ui.Model;
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
        return  new RedirectView("/commandes/commandes");
      
    }

    @GetMapping("/test")
    public ModelAndView test(){
        var listAllCommandes = com_service.findAll();
        var model = Map.of("commandes", listAllCommandes);

        System.out.println("Listes de Commandes: " + listAllCommandes);

        return new ModelAndView("/store/test", model);
    }

    @GetMapping("/commandes")
    public ModelAndView commandes(HttpSession session){

        ModelAndView modelAndView = new ModelAndView("/store/connected");
        System.out.println("===========================> inside Connected ");
        String user_email = (String) session.getAttribute("user_email");

        if(user_email != null){
            Optional<Users> usersOptional = usr_Service.findByEmail(user_email);
            System.out.println("===========================> email null  /commandes ");

            if(usersOptional.isPresent()){

                Users users = usersOptional.get();
                List<Commandes> listAllCommandes = com_service.getCommandesByUsers(users);

                modelAndView.addObject("commandes", listAllCommandes);
                System.out.println("Listes de Commandes: " + listAllCommandes);
            }
        }
        return modelAndView;
    }
    
    @GetMapping("/article")
    public ModelAndView article(@RequestParam Long id) {
    Optional<Commandes> commandeOptional = com_repo.findById(id);
    if (commandeOptional.isPresent()) {
        Commandes commande = commandeOptional.get();
        ModelAndView modelAndView = new ModelAndView("/store/article");
        modelAndView.addObject("commandes", commande);
        return modelAndView;
    } else {
        return new ModelAndView("redirect:/store/connected");
    }
    }

    

}
