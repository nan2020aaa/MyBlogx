package com.example.MyBlogx.controllers;
//package com.example.MyBlogx.controllers;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.MyBlogx.models.Account;
//import com.example.MyBlogx.services.AccountService;
//
//@Controller
//public class LoginXController {
//	@Autowired
//	AccountService accountService;
//
//	@GetMapping("/login")
//	public String getLoginPage() {
//		return "login.html";
//	}
//	
//	@GetMapping("/")
//	public String index() {
//		return "redirect:/login";
//	}
//	
//	@GetMapping("/menuBlog")
//	public String getMenuPage() {
//		return "menu.html";
//	}
//	
//	@GetMapping("/blog")
//	public String getBlogPage() {
//		return "blog.html";
//	}
//	
//	@PostMapping("/login")
//	public ModelAndView login(@RequestParam String usernameOrEmail, @RequestParam String password, ModelAndView mav) {
//		System.out.println(usernameOrEmail + " password: " + password);
//		mav.addObject("isInitial", false);
//		if (accountService.validateAccount(usernameOrEmail, password)) {
//			Account.hasLoged = true;
//			if (accountService.isLoginByUsername(usernameOrEmail, password)) {
//				Account.hasLogedUser=usernameOrEmail;
//			}else {
//				Account.hasLogedUser=accountService.getUsernameByEmail(usernameOrEmail);
//			}
//			mav.addObject("username", Account.hasLogedUser);
//			mav.setViewName("menu.html");
//		} else {
//			mav.setViewName("blog.html");
//		}
//		return mav;
//	}
//
//}