package com.example.MyBlogx.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;
import com.example.MyBlogx.services.AccountService;
import com.example.MyBlogx.services.BlogService;

@Controller
public class SeeBlogController {

	@Autowired
	BlogRepository repository;

	@GetMapping("/publicList")
	public ModelAndView getPublicListPage(ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		repository.findAll().stream().forEach(blog -> {
			if (blog.getWriter() == "Yanagi") {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/privateList")
	public ModelAndView getPrivateListPage(ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		repository.findAll().stream().forEach(blog -> {
			blogList.add(blog);
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/publicBlog")
	public ModelAndView getPublicBlogPage(@RequestParam String theme, ModelAndView mav) {
		Blog targetBlog = repository.findByTheme(theme);
		mav.addObject("theme", theme);
		if (targetBlog.getSummary() != null) {
			mav.addObject("hasSummary", true);
			mav.addObject("summary", targetBlog.getSummary());
		} else {
			mav.addObject("hasSummary", false);
		}
		mav.addObject("writer", targetBlog.getWriter());
		mav.addObject("content", targetBlog.getContent());
		mav.addObject("date", targetBlog.getDate());
		mav.addObject("likeVol", targetBlog.getLikeVol());
		mav.addObject("commentVol", targetBlog.getCommentVol());
		mav.addObject("rtVol", targetBlog.getRtVol());
		return mav;
	}

	@GetMapping("/privateBlog")
	public ModelAndView getPrivateBlogPage(@RequestParam String theme, ModelAndView mav) {
		Blog targetBlog = repository.findByTheme(theme);
		mav.addObject("theme", theme);
		if (targetBlog.getSummary() != null) {
			mav.addObject("hasSummary", true);
			mav.addObject("summary", targetBlog.getSummary());
		} else {
			mav.addObject("hasSummary", false);
		}
		mav.addObject("writer", targetBlog.getWriter());
		mav.addObject("content", targetBlog.getContent());
		mav.addObject("date", targetBlog.getDate());
		mav.addObject("likeVol", targetBlog.getLikeVol());
		mav.addObject("commentVol", targetBlog.getCommentVol());
		mav.addObject("rtVol", targetBlog.getRtVol());
		return mav;
	}
}
