package com.example.tp2.users;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/store")
@SessionAttributes("logged_usr_mail")
public class UserController {
	@Autowired
	private UserInterface service;

	
	// @Autowired
	// private HttpSession session;
	
	@PostMapping("/create")
	public RedirectView create(		
	@RequestParam String nom, 
	@RequestParam String prenom,
	@RequestParam String email,
	@RequestParam String motdepasse,
	RedirectAttributes redirectAttributes){
	if(service.existById(email)) {
		redirectAttributes.addFlashAttribute("error message","Ce compte existe deja!");
		System.out.println("----------------------------------------------------------------");
		System.out.println("==========> Email already exist  " + email);
		return new RedirectView("/store/home");
		}
		redirectAttributes.addFlashAttribute("creation_ok","Compte creer avec succes");
		System.out.println("----------------------------------------------------------------");
		System.out.println("==========> new user created  " + email);
		service.create(nom, prenom, email,motdepasse);
		return new RedirectView("/store/home");
	}
	
	
	//logout section
	@PostMapping("/logout")
	
		public RedirectView logout(HttpSession session) {
			session.invalidate();
			return new RedirectView("/store/home"); 
		}
	
	
	@PostMapping("/login")
	public ModelAndView login(
		@RequestParam String email,
		@RequestParam String motdepasse,
		Model model,
		HttpSession session) {
			
		Optional<Users> usr = service.findByEmail(email);

		if(usr.isPresent() && usr.get().getMotdepasse().equals(motdepasse)) {
			
			session.setAttribute("user_email", usr.get().getEmail());
			session.setAttribute("user_prenom", usr.get().getPrenom());

			model.addAttribute("success","Connection reussie");

			//printing user_name
			System.out.println("----------------------------------------------------------------");
			System.out.println("========> email logged in "+usr.get().getEmail());	

			return new ModelAndView("store/connected");
		}
		else {
			model.addAttribute("error","Email ou mot de passe Incorrect");
			return new ModelAndView("/store/home");
		
		}
		
		}
		
	
	@GetMapping("/home")
	public ModelAndView home() {
		return new ModelAndView("/store/home");
	}
	
	
}
