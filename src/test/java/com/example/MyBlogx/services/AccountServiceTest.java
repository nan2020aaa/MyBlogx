package com.example.MyBlogx.services;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.MyBlogx.models.Account;
import com.example.MyBlogx.repositories.AccountRepository;

@SpringBootTest
public class AccountServiceTest {
	private static AccountService demo;

	@MockBean
	private AccountRepository accountRepository;

	@Autowired
	private AccountService accountService;

	@BeforeEach
	public void prepareData() {
		demo = new AccountService();
		Account alice = new Account("Alice", "xxx", "x", "x");
		List<Account> allUsersList = List.of(alice);

		when(accountRepository.save(any())).thenReturn(null);
		when(accountRepository.findByUsername(any())).thenReturn(null);
		when(accountRepository.findByUsername("Alice")).thenReturn(alice);
		when(accountRepository.findAll()).thenReturn(allUsersList);
	}

	@Test
	public void testPasswordMatch_Match_True() {
		assertTrue(demo.passwordMatch("xxx", "xxx"));
	}

	@Test
	public void testPasswordMatch_NotMatch_False() {
		assertFalse(demo.passwordMatch("xxx", "xx"));
	}

	@Test
	public void testHasExisted_Existed_True() {
		assertTrue(accountService.hasExisted("Alice"));
	}

	@Test
	public void testHasExisted_NotExisted_False() {
		assertFalse(accountService.hasExisted("Bob"));
	}

	@Test
	public void testValidateAccount_CorrectInfo_ReturnTrue() {
		assertTrue(accountService.validateAccount("Alice", "xxx"));
	}

	@Test
	public void testValidateAccount_WrongUsername_ReturnFalse() {
		assertFalse(accountService.validateAccount("Bob", "Bob54321"));
	}

	@Test
	public void testValidateAccount_CorrectUsernameWrongPassword_ReturnFalse() {
		assertFalse(accountService.validateAccount("Alice", "BBC12321"));
	}

	@Test
	public void testCreateAccount_UsernameNotExistedAndPasswordMatch_ReturnTrue() {
		assertTrue(accountService.createAccount("Bob", "Bob54321", "Bob54321", "xxx", "xxx"));
	}

	@Test
	public void testCreateAccount_UsernameExistedAndPasswordMatch_ReturnFalse() {
		assertFalse(accountService.createAccount("Alice", "Bob54321", "Bob54321", "xxx", "xxx"));
	}

	@Test
	public void testCreateAccount_UsernameNotExistedAndPasswordNotMatch_ReturnFalse() {
		assertFalse(accountService.createAccount("Bob", "Bob54321", "xxx", "xxx", "xxx"));
	}

}
