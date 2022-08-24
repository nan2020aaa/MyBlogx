package com.example.MyBlogx.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.MyBlogx.models.Account;
import com.example.MyBlogx.repositories.AccountRepository;

@Service
public class AccountService {
	@Autowired
	AccountRepository repository;
	//普通のログインする方法。
	public boolean validateAccount(String usernameOrEmail, String password) {
		Account account1 = repository.findByUsername(usernameOrEmail);
		if (account1 == null) {
			return false;
		} else if (account1.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}
	
	//loginに使う。
	//emailでログインする方法、後で開発。
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
	
	public boolean isLoginByUsername(String usernameOrEmail, String password) {
		Account account1 = repository.findByUsername(usernameOrEmail);
		if (account1.getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}
	
	public String getUsernameByEmail(String usernameOrEmail) {
		Account account1 = repository.findByEmail(usernameOrEmail);
		return account1.getUsername();
	}

	//registerに使う。
	public boolean createAccount(String username, String password, String repeatPassword, String email, String picture) {
		if ((!isExist(username)) /*&& passwordMatch(password, repeatPassword)*/) {
			repository.save(new Account(username, password, email, picture));
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

	public boolean isExist(String username) {
		if (repository.findByUsername(username) != null) {
			return true;
		} else {
			return false;
		}
	}
	
	//ログイン状態で、ログインのユーザーネームを戻す、さもないと、ナルを戻す。
	public String hasLoged() {
		if (Account.hasLoged) {
			return Account.hasLogedUser;
		}else {
			return null;
		}
	}
	
	public Map<String, String> userIterator() {
		Map<String, String> resultMap = new HashMap<>();
		for (Account element : repository.findAll()) {
			resultMap.put(element.getUsername(), element.getPassword());
		}
		return resultMap;
	}
}
