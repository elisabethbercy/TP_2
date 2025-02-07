package com.example.tp2.commandes;

import java.util.Map;

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
	private UserItf service;
	
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
		service.create(nom, prenom, email,motdepasse);
		return new RedirectView("/store/home");
	}
	
	
	//login section
	/*	@PostMapping("/login")
		
		public void connect(String email, String motdepasse) {
			var user= repo.findById(email);
			if(user.isPresent()) {
				Users usr = user.get();
				
				if(usr.getMotdepasse() == motdepasse) {
					return;
				}
			}
			else {
				//
			}
		}
		*/
		
		@PostMapping("/login")
		public ModelAndView login(
			@RequestParam String email,
			@RequestParam String motdepasse,
			Model model,
			HttpSession session) {
				
			var usr = service.findById(email);
			
			if(usr.isPresent() && usr.get().getMotdepasse().equals(motdepasse)) {
				session.setAttribute(email,session);
				model.addAttribute("success","Connection reussie");
					
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
