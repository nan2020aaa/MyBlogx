package com.example.MyBlogx.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
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
public class EgoPageControllerTest {
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
	public void testGetEgoPage_GetUserSBlog_SucceedGet() throws Exception {
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
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/egoPage")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/egoPage"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetEgoPage_GetOtherBlog_FailGet() throws Exception {
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
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/egoPage")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/egoPage"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetLinkedEgoPage_GetUserSBlog_SucceedGet() throws Exception {
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
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/linkedEgoPage")
				.with(csrf())
				.with(user(alice))
				.param("writer", "Alice");

		mockMvc.perform(request).andExpect(view().name("/linkedEgoPage"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetLinkedEgoPage_GetOtherBlog_FailGet() throws Exception {
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
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/linkedEgoPage")
				.with(csrf())
				.with(user(alice))
				.param("writer", "Alice");

		mockMvc.perform(request).andExpect(view().name("/linkedEgoPage"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetEgoUpdateBlogPage_SummaryExisted_Succeed() throws Exception {
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
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/egoUpdateBlog")
				.with(csrf())
				.with(user(alice))
				.param("id", "1");

		mockMvc.perform(request).andExpect(view().name("/egoUpdateBlog"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetEgoUpdateBlogPage_SummaryNotExisted_Succeed() throws Exception {
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
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/egoUpdateBlog")
				.with(csrf())
				.with(user(alice))
				.param("id", "1");

		mockMvc.perform(request).andExpect(view().name("/egoUpdateBlog"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testEgoUpdateBlog_Succeed() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		
		when(blogService.getBlogById(1l)).thenReturn(blog1);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/egoUpdateBlog")
				.with(csrf())
				.with(user(alice))
				.param("id", "1")
				.param("theme", "x")
				.param("summary", "x")
				.param("content", "x");

		mockMvc.perform(request).andExpect(view().name("redirect:/egoPage"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetEgoDeleteConfirmPage_Succeed() throws Exception {
		Blog blog1 = new Blog("x","x","x","Alice",1l,1l,1l,now);
		
		when(blogService.getBlogById(1l)).thenReturn(blog1);
		
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/egoDeleteConfirm")
				.with(csrf())
				.with(user(alice))
				.param("id", "1");

		mockMvc.perform(request).andExpect(view().name("/egoDeleteConfirm"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testGetEgoNewBlogPage_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.get("/egoNewBlog")
				.with(csrf())
				.with(user(alice));

		mockMvc.perform(request).andExpect(view().name("/egoNewBlog.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
	
	@Test
	public void testEgoNewBlog_Succeed() throws Exception {
		UserDetails alice = User.withDefaultPasswordEncoder()
				.username("Alice")
				.password("xxx")
				.roles("USER")
				.build();
		
		RequestBuilder request = MockMvcRequestBuilders
				.post("/egoNewBlog")
				.with(csrf())
				.with(user(alice))
				.param("theme", "x")
				.param("summary", "x")
				.param("content", "x");

		mockMvc.perform(request).andExpect(view().name("redirect:/egoPage"))
				.andExpect(model().attributeDoesNotExist("error"));
	}
}
