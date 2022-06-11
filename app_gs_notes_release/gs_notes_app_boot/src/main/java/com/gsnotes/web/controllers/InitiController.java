package com.gsnotes.web.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

//Ce controleur affiche la page index 
@Controller
public class InitiController {
	@RequestMapping("/")
	public String index(Model model) {
		return "index";
	}
}
