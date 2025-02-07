package com.example.tp2.commandes;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
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
	@RequestParam String motdepasse){
	service.create(nom, prenom, email,motdepasse);
		
		return new RedirectView("/store/home");
	}
	
	//login section
		@PostMapping("/login")
		public RedirectView login(
		@RequestParam String email,
		@RequestParam String motdepasse
		) {
			service.login(email, motdepasse);
				
			return new RedirectView("/store/home");
		}
		
	
	
	@GetMapping("/init")
	public RedirectView init() {
		service.init();
		return new RedirectView ("/store/home");
		
	}
	
	@GetMapping("/home")
	public ModelAndView home() {
		//var list=service.findAll();
		//var model = Map.of("users", list);
		return new ModelAndView("/store/home");
	}
	
	
}
