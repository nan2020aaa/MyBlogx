package com.example.MyBlogx.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.MyBlogx.models.Blog;
import com.example.MyBlogx.services.BlogService;

@SpringBootTest
@AutoConfigureMockMvc
public class UpdateBlogControllerTest {
	static LocalDateTime now = LocalDateTime.now();
	@MockBean
	private BlogService blogService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void prepareData() {
		when(blogService.getBlogById(any())).thenReturn(null);
	}

	@Test
	public void testGetUpdateListPage_GetUserSBlog_SucceedGet() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		Blog blog2 = new Blog("x",null,"x","Alice",1l,1l,1l,now);
		Blog blog3 = new Blog("x","x","x","Bob",1l,1l,1l,now);
		List<Blog> blogList1 = List.of(blog1);
		List<Blog> blogList2 = List.of(blog2);
		List<Blog> blogList3 = List.of(blog3);
		
		when(blogService.getAllBlog()).thenReturn(blogList1);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/updateList").with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/updateList"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetUpdateListPage_GetOtherBlog_FailGet() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		Blog blog2 = new Blog("x",null,"x","Alice",1l,1l,1l,now);
		Blog blog3 = new Blog("x","x","x","Bob",1l,1l,1l,now);
		List<Blog> blogList1 = List.of(blog1);
		List<Blog> blogList2 = List.of(blog2);
		List<Blog> blogList3 = List.of(blog3);
		
		when(blogService.getAllBlog()).thenReturn(blogList3);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/updateList").with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/updateList"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetUpdateBlogPage_SummaryExisted_Succeed() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		Blog blog2 = new Blog("x",null,"x","Alice",1l,1l,1l,now);
		Blog blog3 = new Blog("x","x","x","Bob",1l,1l,1l,now);
		List<Blog> blogList1 = List.of(blog1);
		List<Blog> blogList2 = List.of(blog2);
		List<Blog> blogList3 = List.of(blog3);
		
		when(blogService.getBlogById(1l)).thenReturn(blog1);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/updateBlog").param("id", "1").with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/updateBlog"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetUpdateBlogPage_SummaryNotExisted_Succeed() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		Blog blog2 = new Blog("x",null,"x","Alice",1l,1l,1l,now);
		Blog blog3 = new Blog("x","x","x","Bob",1l,1l,1l,now);
		List<Blog> blogList1 = List.of(blog1);
		List<Blog> blogList2 = List.of(blog2);
		List<Blog> blogList3 = List.of(blog3);
		
		when(blogService.getBlogById(1l)).thenReturn(blog2);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/updateBlog").param("id", "1").with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/updateBlog"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testUpdateBlog_Succeed() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		
		when(blogService.getBlogById(1l)).thenReturn(blog1);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.post("/updateBlog").with(csrf())
				.with(user(alice)).param("id", "1").param("theme", "x")
				.param("summary", "x").param("content", "x");

		mockMvc.perform(request).andExpect(view().name("/menu")).andExpect(model().attributeDoesNotExist("error"));
	}
}
