package com.example.MyBlogx.controllers;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;
import com.example.MyBlogx.services.AccountService;
import com.example.MyBlogx.services.BlogService;

@Controller
public class SeeBlogController {
	@Autowired
	BlogService blogService = new BlogService();

	@Autowired
	AccountService accountService = new AccountService();

	@Autowired
	BlogRepository repository;

//	public ModelAndView countBlogRecords(@RequestParam String theme, @RequestParam String summary, @RequestParam String content, @RequestParam String writer, @RequestParam LocalDateTime ldt, @RequestParam long likeVol, @RequestParam long commentVol, @RequestParam long rtVol, ModelAndView mav) {
//		mav.addObject("counter", blogService.countBlog());
//		return mav;
//	}

	@GetMapping("/publicList")
	public String getPublicListPage() {
		return "publicList.html";
	}

	@GetMapping("/privateList")
	public String getPrivateListPage() {
		return "privateList.html";
	}

	@GetMapping("/publicBlog")
	public ModelAndView seePublicBlog(ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		for (Blog blog : repository.findAll()) {
			if(blog.getWriter()=="Yanagi") {
				blogList.add(blog);
			}
		}
		for (int i = 0; i < blogList.size(); i++) {
			mav.addObject(("theme-" + (i + 1)), blogList.get(i).getTheme());
			if (blogList.get(i).getSummary() != null) {
				mav.addObject("summary-" + (i + 1), blogList.get(i).getSummary());
			}
			mav.addObject("content-" + (i + 1), blogList.get(i).getContent());
			mav.addObject("writer-" + (i + 1), "Yanagi");
			mav.addObject("like-vol-" + (i + 1), blogList.get(i).getLikeVol());
			mav.addObject("comment-vol-" + (i + 1), blogList.get(i).getCommentVol());
			mav.addObject("rt-vol-" + (i + 1), blogList.get(i).getRtVol());
			mav.addObject("last-update-date-" + (i + 1), blogList.get(i).getDate());
		}
		return mav;
	}
	
	@GetMapping("/privateBlog")
	public ModelAndView seePrivateBlog(ModelAndView mav) {
		List<Blog> blogList = new ArrayList<>();
		for (Blog blog : repository.findAll()) {
				blogList.add(blog);
		}
		for (int i = 0; i < blogList.size(); i++) {
			mav.addObject(("theme-" + (i + 1)), blogList.get(i).getTheme());
			if (blogList.get(i).getSummary() != null) {
				mav.addObject("summary-" + (i + 1), blogList.get(i).getSummary());
			}
			mav.addObject("content-" + (i + 1), blogList.get(i).getContent());
			mav.addObject("writer-" + (i + 1), blogList.get(i).getWriter());
			mav.addObject("like-vol-" + (i + 1), blogList.get(i).getLikeVol());
			mav.addObject("comment-vol-" + (i + 1), blogList.get(i).getCommentVol());
			mav.addObject("rt-vol-" + (i + 1), blogList.get(i).getRtVol());
			mav.addObject("last-update-date-" + (i + 1), blogList.get(i).getDate());
		}
		return mav;
	}

//	@PostMapping("/list")
//	public ModelAndView seeBlog(@RequestParam String theme, @RequestParam String summary, @RequestParam String content, @RequestParam String writer, @RequestParam LocalDateTime ldt, @RequestParam long likeVol, @RequestParam long commentVol, @RequestParam long rtVol, ModelAndView mav) {
//		mav.addObject("theme", theme);
//		if(summary == null) {
//			mav.addObject("existSummary", false);
//		}else {
//			mav.addObject("summary", summary);
//		}
//		mav.addObject("content", content);
//		mav.addObject("writer", writer);
//		mav.addObject("like-vol", likeVol);
//		mav.addObject("comment-vol", commentVol);
//		mav.addObject("rt-vol", rtVol);
//		mav.addObject("last-update-date", ldt);
//		return mav;
//	}
}
