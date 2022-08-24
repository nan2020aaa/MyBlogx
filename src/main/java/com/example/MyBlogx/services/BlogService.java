package com.example.MyBlogx.services;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;

@Service
public class BlogService {
	@Autowired
	BlogRepository repository;
	
	@Autowired
	AccountService accountService = new AccountService();
	
	public void createNewBlog(String theme, String summary, String content) {
		repository.save(new Blog(theme, summary, content, accountService.hasLoged(), 0, 0, 0, LocalDateTime.now()));
	}
}
