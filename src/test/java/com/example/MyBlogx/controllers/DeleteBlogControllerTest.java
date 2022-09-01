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
public class DeleteBlogControllerTest {
	static LocalDateTime now = LocalDateTime.now();
	@MockBean
	private BlogService blogService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void prepareData() {
		Blog blog = new Blog("x","x","x","x",1l,1l,1l,now);
		List<Blog> blogList = List.of(blog);
		
		when(blogService.getBlogById(any())).thenReturn(null);
		when(blogService.getBlogById(1l)).thenReturn(blog);
		when(blogService.getAllBlog()).thenReturn(blogList);
	}

	@Test
	public void testGetDeleteListPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/deleteList")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/deleteList"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetDeleteBlogPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/deleteBlog")
				.with(csrf())
				.with(user(alice))
				.param("id", "1");

		mockMvc.perform(request).andExpect(view().name("/deleteBlog"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetDeleteConfirmPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/deleteConfirm")
				.with(csrf())
				.with(user(alice))
				.param("id", "1");

		mockMvc.perform(request).andExpect(view().name("/deleteConfirm"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
}
