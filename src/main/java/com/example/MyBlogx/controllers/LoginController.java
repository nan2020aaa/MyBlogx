package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.services.AccountService;

@Controller
public class LoginController {
	@Autowired
	AccountService accountService;

	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}

	@GetMapping("/")
	public String index() {
		return "redirect:/menu";
	}
	
	@GetMapping("/blog")
	public String getBlogPage() {
		return "blog.html";
	}

	@PostMapping("/login")
	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
		System.out.println(username + "+" + password);
		mav.addObject("isInitial", false);
		if (accountService.validateAccount(username, password)) {
			mav.addObject("name", username);
			mav.setViewName("menu.html");
		} else {
			mav.setViewName("login.html");
		}
		return mav;
	}

}
