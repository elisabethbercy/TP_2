package com.example.tp2.users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/store")

public class UserController {
	@Autowired
	private UserInterface service;
	
	@PostMapping("/create")
	public RedirectView create(		
	@RequestParam String nom, 
	@RequestParam String prenom,
	@RequestParam String email,
	@RequestParam String motdepasse,
	RedirectAttributes redirectAttributes){
	if(service.existById(email)) {
		redirectAttributes.addFlashAttribute("error message","Ce compte existe deja!");
		}
		redirectAttributes.addFlashAttribute("creation_ok","Compte creer avec succes");
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
			
		var usr = service.findById(email);
		
		if(usr.isPresent() && usr.get().getMotdepasse().equals(motdepasse)) {
			//session.setAttribute(email, session);
			session.setAttribute(email,usr.get().getEmail());
			model.addAttribute("success","Connection reussie");

			//rgetting user_name
			System.out.println(usr.get().getPrenom());	
			var usr_connected = usr.get().getPrenom();

			// getting user email for commmande
			System.out.println(usr.get().getPrenom());	
			var usr_email_cmd = usr.get().getEmail();

			// sending usr_name & usr_email to view
			model.addAttribute("connected_usr", usr_connected);
			model.addAttribute("usr_email_cmd", usr_email_cmd);

			return new ModelAndView("/store/connected");
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
