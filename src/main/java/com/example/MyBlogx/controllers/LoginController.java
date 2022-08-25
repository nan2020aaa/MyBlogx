package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.models.Account;
import com.example.MyBlogx.services.AccountService;

@Controller
public class LoginController {
	@Autowired
	AccountService accountService;

	@GetMapping("/")
	public String index() {
		return "redirect:/menu";
	}
//
//	@GetMapping("/login")
//	public String getLoginPage() {
//		return "login.html";
//	}

//	@PostMapping("/login")
//	public ModelAndView login(@RequestParam String username, @RequestParam String password, ModelAndView mav) {
////		mav.addObject("isInitial", false);
//		if (accountService.validateAccount(username, password)) {
//			mav.setViewName("menu.html");
//		} else {
//			mav.setViewName("login.html");
//		}
//		return mav;
//	}
}
