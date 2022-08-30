package com.example.MyBlogx.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;

@Service
public class BlogService {
	@Autowired
	BlogRepository repository;

	public void createNewBlog(String theme, String summary, String content, String writer) {
		repository.save(new Blog(theme, summary, content, writer, 0, 0, 0, LocalDateTime.now().withNano(0)));
	}

	public List<Blog> getAllBlog() {
		return repository.findAll();
	}

//	public Blog getBlogByTheme(String theme) {
//		return repository.findByTheme(theme);
//	}

	public Blog getBlogById(long id) {
		return repository.findById(id);
	}

	public void deleteBlog(Blog blog) {
		repository.delete(blog);
	}

//	public long counter() {
//		long counter = 1;
//		return counter++;
//	}
}