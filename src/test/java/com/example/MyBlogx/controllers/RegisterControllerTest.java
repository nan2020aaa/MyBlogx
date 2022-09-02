package com.example.MyBlogx.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

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

import com.example.MyBlogx.services.AccountService;

@SpringBootTest
@AutoConfigureMockMvc
public class RegisterControllerTest {
	@MockBean
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void prepareData() {
		when(accountService.hasExisted(any())).thenReturn(false);
		when(accountService.hasExisted(eq("Alice"))).thenReturn(true);
		when(accountService.passwordMatch(any(),any())).thenReturn(false);
		when(accountService.passwordMatch(eq("xxx"),eq("xxx"))).thenReturn(true);
		when(accountService.createAccount(any(), eq("xxx"), eq("xxx"), any(), eq("xxx"))).thenReturn(true);
		when(accountService.createAccount(any(), any(), any(), any(), eq("xxx"))).thenReturn(false);
		when(accountService.createAccount(eq("Alice"), eq("xxx"), eq("xxx"), any(), eq("xxx"))).thenReturn(false);
	}

	@Test
	public void testGetRegisterPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/register");

		mockMvc.perform(request).andExpect(view().name("register.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetTermsAndPoliciesPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.get("/termsAndPolicies");

		mockMvc.perform(request).andExpect(view().name("termsAndPolicies.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testRegister_NewUsernameAndPasswordMatch_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/register")
				.with(csrf())
				.param("username", "Bob")
				.param("password", "xxx")
				.param("repeatPassword", "xxx")
				.param("email", "x")
				.param("picture", "xxx");

		mockMvc.perform(request).andExpect(view().name("/login")).andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testRegister_PasswordNotMatch_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/register")
				.with(csrf())
				.param("username", "Alice")
				.param("password", "ABC12345")
				.param("repeatPassword", "xxx")
				.param("email", "x")
				.param("picture", "xxx");

		mockMvc.perform(request).andExpect(view().name("/register")).andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testRegister_PasswordMatchButUsernameExisted_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders
				.post("/register")
				.with(csrf())
				.param("username", "Alice")
				.param("password", "xxx")
				.param("repeatPassword", "xxx")
				.param("email", "x")
				.param("picture", "xxx");

		mockMvc.perform(request).andExpect(view().name("/register")).andExpect(model().attributeDoesNotExist("error"));
	}
}
