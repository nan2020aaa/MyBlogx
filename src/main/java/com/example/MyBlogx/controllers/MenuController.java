package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.MyBlogx.services.AccountService;

@Controller
public class MenuController {
	@Autowired
	AccountService accountService;

	@GetMapping("/menu")
	public String getMenuPage() {
		return "menu.html";
	}
}
