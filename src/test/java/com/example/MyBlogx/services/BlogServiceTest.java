package com.example.MyBlogx.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.repositories.BlogRepository;

@SpringBootTest
public class BlogServiceTest {
	static LocalDateTime now = LocalDateTime.now();
	@MockBean
	private BlogRepository blogRepository;

	@Autowired
	private BlogService blogService;

	@BeforeEach
	public void prepareData() {
		Blog blog = new Blog("x", "x", "x", "x", 1l, 1l, 1l, now);
		List<Blog> allBlogList = List.of(blog);

		when(blogRepository.save(any())).thenReturn(null);
		when(blogRepository.findById(any())).thenReturn(null);
		//TODO
		when(blogRepository.findById(1l)).thenReturn(blog);
		when(blogRepository.findAll()).thenReturn(allBlogList);
	}

	@Test
	public void testGetBlogById_Successful() {
		assertEquals(blogService.getBlogById(1l), new Blog("x", "x", "x", "x", 1l, 1l, 1l, now));
	}

	@Test
	public void testGetAllBlog_Successful() {
		assertEquals(blogService.getAllBlog(), List.of(new Blog("x", "x", "x", "x", 1l, 1l, 1l, now)));
	}

	@Test
	public void testCreateNewBlog_Successful() {
		blogService.createNewBlog("x", "x", "x", "x");
	}

	@Test
	public void testDeleteBlog_Successful() {
		blogService.deleteBlog(new Blog("x", "x", "x", "x", 1l, 1l, 1l, now));
	}
}
