//package com.example.MyBlogx.controllers;
//
//import java.time.LocalDateTime;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import com.example.MyBlogx.services.AccountService;
//import com.example.MyBlogx.services.BlogService;
//
//@Controller
//public class WriteBlogController {
//	@Autowired
//	BlogService blogService = new BlogService();
//	
//	@Autowired
//	AccountService accountService = new AccountService();
//	
//	@PostMapping("/login")
//	public ModelAndView writeBlog(@RequestParam String theme, @RequestParam String summary, @RequestParam String content, ModelAndView mav) {
//		mav.addObject("theme", theme);
//		if(summary == null) {
//			mav.addObject("existSummary", false);
//		}else {
//			mav.addObject("summary", summary);
//		}
//		mav.addObject("content", content);
//		mav.addObject("writer", accountService.hasLoged());
////		mav.addObject("like-vol", likeVol);
////		mav.addObject("comment-vol", commentVol);
////		mav.addObject("rt-vol", rtVol);
////		mav.addObject("last-update-date", ldt);
//		blogService.createNewBlog(theme, summary, content);
//		return mav;
//	}
//}
