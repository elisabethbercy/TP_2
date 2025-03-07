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

import com.example.tp2.articles.Articles;
import com.example.tp2.articles.ArticlesServices;
import com.example.tp2.users.UserService;
import com.example.tp2.users.Users;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/commandes")

public class CommandesController {
    @Autowired
    private CommandesInterface comService;

    @Autowired
    private UserService usrService;

    @Autowired
    private CommandesRepository com_repo;

    @Autowired
    private ArticlesServices artService;

    @PostMapping("/newcommande")
    public RedirectView newcommande( 
        @RequestParam String nomCommande,
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

        Users user = usrService.findByEmail(user_email).orElse(null); // if error here

        
        comService.newCommande(nomCommande, user);

        System.out.println(" ===========> User email saved in new commande from Controller newcommande "+ user_email);
        return  new RedirectView("/commandes/commandes");
      
    }

    @GetMapping("/test")
    public ModelAndView test(
        HttpSession session
    ){
        var listAllCommandes = comService.findAll();
        var model = Map.of("commandes", listAllCommandes);

        System.out.println("Listes de Commandes: " + listAllCommandes);

        return new ModelAndView("/store/test", model);
    }

    // !!! to do : list display before newcommande added

    @GetMapping("/commandes")
    public ModelAndView commandes(HttpSession session){

        ModelAndView modelAndView = new ModelAndView("/store/connected");
        System.out.println("===========================> inside Connected ");
        String user_email = (String) session.getAttribute("user_email");

        if(user_email != null){
            Optional<Users> usersOptional = usrService.findByEmail(user_email);
            System.out.println("===========================> email not null  /commandes ");

            if(usersOptional.isPresent()){

                Users users = usersOptional.get();
                List<Commandes> listAllCommandes = comService.getCommandesByUsers(users);
                session.setAttribute("nomCommande", listAllCommandes.get(0).getNomCommande());

                if (!listAllCommandes.isEmpty()) {
                    // Only set the attribute if the list is not empty
                    session.setAttribute("nomCommande", listAllCommandes.get(0).getNomCommande());
                    modelAndView.addObject("commandes", listAllCommandes);
                    System.out.println("Listes de Commandes: " + listAllCommandes);
                } else {
                    // Handle the case where no commandes are found
                    modelAndView.addObject("message", "No commandes found for the user.");
                }
            }
        }
        return modelAndView;
       // model.addAttribute("error","Email or password incorrect");
		//return new ModelAndView("/store/home");
    }
    

    @GetMapping("/articles")
    public ModelAndView article(@RequestParam Long id,
    HttpSession session,
    RedirectAttributes redirectAttributes) {
        Optional<Commandes> commandeOptional = com_repo.findById(id);

        String user_email = (String) session.getAttribute("user_email");

        if(user_email == null)
        {
            
            redirectAttributes.addFlashAttribute("error", "Session Invalide. Veuillez vous reconnecter.");
            return new ModelAndView("/store/home");
        }

        if (commandeOptional.isPresent()) {
            Commandes commande = commandeOptional.get();

            ModelAndView modelAndView = new ModelAndView("/store/article");
            modelAndView.addObject("idCommande", id);
            modelAndView.addObject("commandes", commande);

            session.setAttribute("idCommande", id );
            return modelAndView;
        } else {
            return new ModelAndView("redirect:/store/connected");
        }
    }

    @GetMapping("/printCommande")
    public ModelAndView printCommande(@RequestParam Long id, 
        HttpSession session,
        RedirectAttributes redirectAttributes) {
        Long idCommande = (Long) session.getAttribute("idCommande");

        String user_email = (String) session.getAttribute("user_email");

        if(user_email == null)
        {
            
            redirectAttributes.addFlashAttribute("error", "Session Invalide. Veuillez vous reconnecter.");
            return new ModelAndView("/store/home");
        }

        if (idCommande == null) {
            redirectAttributes.addFlashAttribute("errorCommandeNull","No item yet to display!!!");
            return new ModelAndView("redirect:/commandes/commandes");
        }

        Optional<Commandes> commandes = comService.findById(idCommande);
        if (commandes.isEmpty()) {
            return new ModelAndView("redirect:/commandes/commandes");
        }

    
        List<Articles> listArtByCom = artService.getArticlesByCommandes(commandes.get());
        Map<String, Object> model = Map.of("articles", listArtByCom);
        return new ModelAndView("/store/printCommande", model);
    }

    

}
