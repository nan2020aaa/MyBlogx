package com.example.MyBlogx.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import com.example.MyBlogx.services.BlogService;

@SpringBootTest
@AutoConfigureMockMvc
public class WriteBlogControllerTest {
	@MockBean
	private BlogService blogService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void testGetNewBlogPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/newBlog")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request)
		.andExpect(view().name("/newBlog.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testWriteBlog_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/newBlog")
				.with(csrf())
				.with(user(alice))
				.param("theme", "x")
				.param("summary", "x")
				.param("content", "x");

		mockMvc.perform(request).andExpect(view().name("/menu.html")).andExpect(model().attributeDoesNotExist("error"));
	}
}
