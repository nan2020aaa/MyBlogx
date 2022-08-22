package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.MyBlogx.services.AccountService;

@Controller
public class RegisterController {
	@Autowired
	AccountService accountService;

	@GetMapping("/register")
	public String getRegisterPage() {
		return "register.html";
	}
	
	@GetMapping("/termsAndPolicies")
	public String getTermsAndPoliciesPage() {
		return "termsAndPolicies.html";
	}
}
