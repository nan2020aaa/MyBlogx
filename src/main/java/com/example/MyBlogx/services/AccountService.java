package com.example.MyBlogx.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.example.MyBlogx.MyBlogxWebSecurityConfig;
import com.example.MyBlogx.models.Account;
import com.example.MyBlogx.repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository repository;

	// 普通のログインする方法。
	public boolean validateAccount(String username, String password) {
		Account account = repository.findByUsername(username);
		if (!hasExisted(username)) {
			return false;
		} else if (account.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public List<Account> getAllAccount() {
		return repository.findAll();
	}

	// loginに使う。
	// emailでログインする方法、後で開発。
//	public boolean validateAccount(String usernameOrEmail, String password) {
//		Account account1 = repository.findByUsername(usernameOrEmail);
//		Account account2 = repository.findByEmail(usernameOrEmail);
//		if (account1 == null && account2 == null) {
//			return false;
//		} else if (account1.getPassword().equals(password) || account2.getPassword().equals(password)) {
//			return true;
//		} else {
//			return false;
//		}
//	}

//	public boolean isLoginByUsername(String username, String password) {
//		Account account = repository.findByUsername(username);
//		if (account.getPassword().equals(password)) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	public String getUsernameByEmail(String usernameOrEmail) {
//		Account account = repository.findByEmail(usernameOrEmail);
//		return account.getUsername();
//	}

	// registerに使う。
	public boolean createAccount(String username, String password, String repeatPassword, String email,
			String picture) {
		if ((!hasExisted(username)) && passwordMatch(password, repeatPassword)) {
			repository.save(new Account(username, password, email, picture));
			UserDetails user = User.withDefaultPasswordEncoder().username(username).password(password).roles("USER")
					.build();
			MyBlogxWebSecurityConfig.manager.createUser(user);
			return true;
		} else {
			return false;
		}
	}

	public boolean passwordMatch(String password, String repeatPassword) {
		if (password.equals(repeatPassword)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean hasExisted(String username) {
		if (repository.findByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}
}
