package com.example.MyBlogx.controllers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.MyBlogx.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class LoginControllerTest {
	@MockBean
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void prepareData() {
	}

	@Test
	public void testIndex_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/");

		mockMvc.perform(request).andExpect(redirectedUrl("http://localhost8081/menu"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetLoginPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/login");

		mockMvc.perform(request).andExpect(view().name("login.html")).andExpect(model().attributeDoesNotExist("error"));
	}
}
