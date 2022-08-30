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
public class EgoPageController {
	@Autowired
	BlogService blogService;

	@GetMapping("/egoPage")
	public ModelAndView getEgoPage(@AuthenticationPrincipal UserDetails user, ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		blogService.getAllBlog().stream().forEach(blog -> {
			if (blog.getWriter().equals(user.getUsername())) {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/linkedEgoPage")
	public ModelAndView getLinkedEgoPage(@RequestParam String writer, ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		blogService.getAllBlog().stream().forEach(blog -> {
			if (blog.getWriter().equals(writer)) {
				blogList.add(blog);
			}
		});
		mav.addObject("blogList", blogList);
		return mav;
	}

	@GetMapping("/egoUpdateBlog")
	public ModelAndView getEgoUpdateBlogPage(@RequestParam long id, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogById(id);
		mav.addObject("theme", targetBlog.getTheme());
		if (targetBlog.getSummary() != null) {
			mav.addObject("hasSummary", true);
			mav.addObject("summary", targetBlog.getSummary());
		} else {
			mav.addObject("hasSummary", false);
		}
		mav.addObject("content", targetBlog.getContent());
		mav.addObject("id", id);
		return mav;
	}

	@PostMapping("/egoUpdateBlog")
	public ModelAndView egoUpdateBlog(@AuthenticationPrincipal UserDetails user, @RequestParam long id,
			@RequestParam String theme, @RequestParam String summary, @RequestParam String content, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogById(id);
		blogService.deleteBlog(targetBlog);
		blogService.createNewBlog(theme, summary, content, user.getUsername());
		mav.setViewName("redirect:/egoPage");
		return mav;
	}

	@GetMapping("/egoDeleteConfirm")
	public ModelAndView getEgoDeleteConfirmPage(@RequestParam long id, ModelAndView mav) {
		Blog targetBlog = blogService.getBlogById(id);
		mav.addObject("theme", targetBlog.getTheme());
		blogService.deleteBlog(targetBlog);
		return mav;
	}

	@GetMapping("/egoNewBlog")
	public String getEgoNewBlogPage() {
		return "/egoNewBlog.html";
	}

	@PostMapping("/egoNewBlog")
	public ModelAndView egoWriteBlog(@AuthenticationPrincipal UserDetails user, @RequestParam String theme,
			@RequestParam String summary, @RequestParam String content, ModelAndView mav) {
		blogService.createNewBlog(theme, summary, content, user.getUsername());
		mav.setViewName("redirect:/egoPage");
		return mav;
	}
}