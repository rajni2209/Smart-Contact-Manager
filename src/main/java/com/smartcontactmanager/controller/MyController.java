package com.smartcontactmanager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontactmanager.Entity.Users;
import com.smartcontactmanager.repository.UserRepository;

import jakarta.validation.Valid;

@Controller
public class MyController {

    private BCryptPasswordEncoder bCryptPasswordEncoder;
    
    MyController(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	
	
	@Autowired
	private UserRepository userRepository;

	
	@GetMapping("/")
	public String test(Model model) {
		model.addAttribute("title", "home - smart contact manager");
		return "home";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		model.addAttribute("title", "about - smart contact manager");
		return "about";
	}
	
	
	@GetMapping("/login")
	public String signin(@RequestParam(value = "error", required = false) String error, Model model) {
		model.addAttribute("title", "signin - smart contact manager");
		if (error != null) {
	        model.addAttribute("errorMsg", "Invalid username or password.");
	    }
		return "login";
	}
	
	
	@GetMapping("/signup")
	public String sign(Model model) {
		model.addAttribute("title", "signup - smart contact manager");
		model.addAttribute("users", new Users());
		return "signup";
	}
	
	@PostMapping("/signup")
	public String succes(@Valid @ModelAttribute("users") Users users , BindingResult result ,  Model model) {
		
		users.setEnable(true);
		users.setRole("USER");
		users.setImageUrl("rajni.jpg");
		
		if (userRepository.existsByEmail(users.getEmail())) {
			result.rejectValue("email", null, "Email already exists");
		}
		
		
		if(result.hasErrors()) {
			return "signup";
		}
		
		users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
		
		Users saveUser = userRepository.save(users);
		System.out.println(users);
		
		
		return "redirect:/login";
	}
	
	
	
	
	
}
