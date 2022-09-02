package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
			System.out.println("x "+"username "+username+" password "+password+" repeatPassword "+repeatPassword);
			mav.setViewName("/login");
		} else {
			if (!(accountService.passwordMatch(password, repeatPassword))) {
				System.out.println("y "+"username "+username+" password "+password+" repeatPassword "+repeatPassword);
				mav.addObject("isMatch", false);
			} else if (accountService.hasExisted(username)) {
				System.out.println("z "+"username "+username+" password "+password+" repeatPassword "+repeatPassword);
				mav.addObject("hasExisted", true);
			}
			mav.setViewName("/register");
		}
		return mav;
	}
}
