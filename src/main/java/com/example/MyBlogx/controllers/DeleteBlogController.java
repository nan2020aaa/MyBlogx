package com.example.MyBlogx.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.services.BlogService;

@Controller
public class DeleteBlogController {
	@Autowired
	BlogService blogService;

	@GetMapping("/deleteList")
	public ModelAndView getDeleteListPage(@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		blogService.getAllBlog().stream().forEach(blog -> {
			if (blog.getWriter().equals(user.getUsername())) {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/deleteBlog")
	public ModelAndView getDeleteBlogPage(@RequestParam long id, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogById(id);
		mav.addObject("theme", targetBlog.getTheme());
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
		mav.addObject("id", id);
		return mav;
	}

	@GetMapping("/deleteConfirm")
	public ModelAndView getDeleteConfirmPage(@RequestParam long id, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogById(id);
		mav.addObject("theme", targetBlog.getTheme());
		blogService.deleteBlog(targetBlog);
		return mav;
	}
}
