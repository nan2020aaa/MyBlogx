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

	@GetMapping("/")
	public String index() {
		return "redirect:/menu";
	}

	@GetMapping("/login")
	public String getLoginPage() {
		return "login.html";
	}
}
