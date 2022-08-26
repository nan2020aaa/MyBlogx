package com.example.MyBlogx.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;

@Controller
public class DeleteBlogController {
	@Autowired
	BlogRepository repository;

	@GetMapping("/deleteList")
	public ModelAndView getDeleteListPage(@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		repository.findAll().stream().forEach(blog -> {
			if (blog.getWriter() == user.getUsername()) {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/deleteBlog")
	public String getDeleteBlogPage() {
		return "deleteBlog.html";
	}
}
