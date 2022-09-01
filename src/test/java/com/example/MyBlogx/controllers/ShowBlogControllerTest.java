package com.example.MyBlogx.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
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
public class ShowBlogControllerTest {
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
	public void testGetPublicListPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/publicList")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/publicList.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetPrivateListPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/privateList")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/privateList.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetPublicBlogPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/publicBlog")
				.param("id", "1l")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/publicBlog.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetPrivateBlogPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders.get("/privateBlog")
				.param("id", "1l")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request)
				.andExpect(view().name("/privateBlog.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
}
