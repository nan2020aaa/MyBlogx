package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

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
	
	@PostMapping("/register")
	public ModelAndView register(@RequestParam String username, @RequestParam String password,
			@RequestParam String repeatPassword, @RequestParam String email, String picture, ModelAndView mav) {
		picture = "xxx";
		boolean isSuccessful = accountService.createAccount(username, password, repeatPassword, email, picture);
		if (isSuccessful) {
			mav.setViewName("/login");
		} else {
			if (accountService.isExist(username)) {
				mav.addObject("isExist", true);
			} else if (!(accountService.passwordMatch(password, repeatPassword))) {
				mav.addObject("isMatch", false);
			}
			mav.setViewName("/register");
		}
		return mav;
	}
}
