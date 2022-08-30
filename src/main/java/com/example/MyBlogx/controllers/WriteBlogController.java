package com.example.MyBlogx.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.services.BlogService;

@Controller
public class WriteBlogController {
	@Autowired
	BlogService blogService = new BlogService();

	@GetMapping("/newBlog")
	public String getNewBlogPage() {
		return "/newBlog.html";
	}

	@PostMapping("/newBlog")
	public ModelAndView writeBlog(@AuthenticationPrincipal UserDetails user, @RequestParam String theme,
			@RequestParam String summary, @RequestParam String content, ModelAndView mav) {
		blogService.createNewBlog(theme, summary, content, user.getUsername());
		mav.setViewName("/menu.html");
		return mav;
	}
}