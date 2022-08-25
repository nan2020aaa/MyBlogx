package com.example.MyBlogx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.servlet.ModelAndView;

import com.example.MyBlogx.repositories.AccountRepository;
import com.example.MyBlogx.services.AccountService;

@Configuration
@EnableWebSecurity
public class MyBlogxWebSecurityConfig extends WebSecurityConfigurerAdapter {
//	@Autowired
//	AccountRepository repository;
//
//	@Override
//	@Bean
//	public UserDetailsService userDetailsService() {
//		List<UserDetails> resultList = new ArrayList();
//		repository.findAll().stream().forEach(account -> {
//			UserDetails user = User.withDefaultPasswordEncoder().username(account.getUsername())
//					.password(account.getPassword()).roles("USER").build();
//			resultList.add(user);
//		});
//
//		return new InMemoryUserDetailsManager(resultList);
//	}
	
	@Autowired
	AccountRepository repository;

	@Override
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> resultList = new ArrayList();
		repository.findAll().stream().forEach(
				account->{
					UserDetails user = User.withDefaultPasswordEncoder().username(account.getUsername())
							.password(account.getPassword()).roles("USER").build();
					resultList.add(user);
				}
				);

		return new InMemoryUserDetailsManager(resultList);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/login*", "/termsAndPolicies", "/publicList", "/publicBlog", "/register", "/css/**", "/js/**", "/jpeg/**").permitAll().anyRequest().authenticated()//
				.and().formLogin().loginPage("/login")//
				.and().logout().permitAll();//
		
	}
}
