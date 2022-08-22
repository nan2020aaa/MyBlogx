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

	public boolean validateAccount(String username, String password) {
		Account account = repository.findByUsername(username);
		if (account == null) {
			return false;
		} else if (account.getPassword().equals(password)) {
			return true;
		} else {
			return false;
		}
	}

	public boolean createAccount(String username, String password, String repeatPassword) {
		if ((!isExist(username)) && passwordMatch(password, repeatPassword)) {
			repository.save(new Account(username, password));
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

//	public Map<String, String> userIterator() {
//		Map<String, String> resultMap = new HashMap<>();
//		for (Account element : repository.findAll()) {
//			resultMap.put(element.getUsername(), element.getPassword());
//		}
//		return resultMap;
//	}
}
