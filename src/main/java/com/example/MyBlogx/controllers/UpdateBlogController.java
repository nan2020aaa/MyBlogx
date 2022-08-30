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
import com.example.MyBlogx.services.BlogService;

@Controller
public class UpdateBlogController {
	@Autowired
	BlogService blogService = new BlogService();

	@GetMapping("/updateList")
	public ModelAndView getUpdateListPage(@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		blogService.getAllBlog().stream().forEach(blog -> {
			if (blog.getWriter().equals(user.getUsername())) {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/updateBlog")
	public ModelAndView getUpdateBlogPage(@RequestParam String theme, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogByTheme(theme);
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

	@PostMapping("/updateBlog")
	public ModelAndView updateBlog(@AuthenticationPrincipal UserDetails user, @RequestParam String theme,
			@RequestParam String summary, @RequestParam String content, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogByTheme(theme);
		blogService.deleteBlog(targetBlog);
		blogService.createNewBlog(theme, summary, content, user.getUsername());
		mav.setViewName("/menu");
		return mav;
	}
}
