package com.example.MyBlogx.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
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
public class RegisterControllerTest {
	@MockBean
	private AccountService accountService;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void prepareData() {
		final String password = any();
		final String correctRepeatPassword = password;
		String wrongRepeatPassword = any();
		while (true) {
			if (password.equals(wrongRepeatPassword)) {
				wrongRepeatPassword = any();
			} else {
				break;
			}
		}
		when(accountService.createAccount(any(), any(), password, correctRepeatPassword, any())).thenReturn(true);
		when(accountService.createAccount(eq("Alice"), any(), any(), any(), any())).thenReturn(false);
		when(accountService.createAccount(any(), any(), password, wrongRepeatPassword, any())).thenReturn(false);
		when(accountService.validateAccount(any(), any())).thenReturn(false);
		when(accountService.validateAccount("Alice", "xxx")).thenReturn(true);
	}

	@Test
	public void testGetRegisterPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/register");

		mockMvc.perform(request).andExpect(view().name("register.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testGetTermsAndPoliciesPage_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.get("/termsAndPolicies");

		mockMvc.perform(request).andExpect(view().name("termsAndPolicies.html"))
				.andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testRegister_NewUsernameAndPasswordMatch_Succeed() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/register").param("username", "Bob")
				.param("password", "Bob54321").param("repeatPassword", "Bob54321");

		mockMvc.perform(request).andExpect(view().name("login.html")).andExpect(model().attributeDoesNotExist("error"));
	}

	@Test
	public void testLogin_PasswordNotMatch_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/register").param("username", "Alice")
				.param("password", "ABC12345").param("repeatPassword", "xxx");

		mockMvc.perform(request).andExpect(view().name("register.html")).andExpect(model().attribute("error", true));
	}

	@Test
	public void testLogin_PasswordMatchButUsernameExisted_Fail() throws Exception {
		RequestBuilder request = MockMvcRequestBuilders.post("/register").param("username", "Alice")
				.param("password", "xxx").param("repeatPassword", "xxx");

		mockMvc.perform(request).andExpect(view().name("register.html")).andExpect(model().attribute("error", true));
	}
}
