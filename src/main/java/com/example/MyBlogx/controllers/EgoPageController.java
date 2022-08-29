package com.example.MyBlogx.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;
import com.example.MyBlogx.services.BlogService;

@Controller
public class EgoPageController {
	@Autowired
	BlogRepository repository;

	@Autowired
	BlogService blogService;

	@GetMapping("/egoPage")
	public ModelAndView getEgoPage(@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		repository.findAll().stream().forEach(blog -> {
			if (blog.getWriter().equals(user.getUsername())) {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		mav.addObject("writer", user.getUsername());
		return mav;
	}

	@GetMapping("/egoUpdateBlog")
	public ModelAndView getEgoUpdateBlogPage(@RequestParam String theme, ModelAndView mav) {
		Blog targetBlog = repository.findByTheme(theme);
		mav.addObject("theme", theme);
		if (targetBlog.getSummary() != null) {
			mav.addObject("hasSummary", true);
			mav.addObject("summary", targetBlog.getSummary());
		} else {
			mav.addObject("hasSummary", false);
		}
		mav.addObject("content", targetBlog.getContent());
		return mav;
	}

	@PostMapping("/egoUpdateBlog")
	public ModelAndView egoUpdateBlog(@AuthenticationPrincipal UserDetails user, @RequestParam String theme,
			@RequestParam String summary, @RequestParam String content, ModelAndView mav) {
		Blog targetBlog = repository.findByTheme(theme);
		repository.delete(targetBlog);
		blogService.createNewBlog(theme, summary, content, user.getUsername());
		mav.setViewName("redirect:/egoPage");
		return mav;
	}

	@GetMapping("/egoDeleteConfirm")
	public ModelAndView getEgoDeleteConfirmPage(@RequestParam String theme, ModelAndView mav) {
		mav.addObject("theme", theme);
		Blog targetBlog = repository.findByTheme(theme);
		repository.delete(targetBlog);
		return mav;
	}
	
	@GetMapping("/egoNewBlog")
	public String getEgoNewBlogPage() {
		return "/newBlog.html";
	}

	@PostMapping("/egoNewBlog")
	public ModelAndView egoWriteBlog(@AuthenticationPrincipal UserDetails user, @RequestParam String theme,
			@RequestParam String summary, @RequestParam String content, ModelAndView mav) {
		if (repository.findByTheme(theme) == null) {
			mav.addObject("themeExisted", false);
			blogService.createNewBlog(theme, summary, content, user.getUsername());
		} else {
			mav.addObject("themeExisted", true);
		}
		mav.setViewName("redirect:/egoPage");
		return mav;
	}
}