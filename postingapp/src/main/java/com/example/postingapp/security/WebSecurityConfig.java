package com.example.postingapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class WebSecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		
		//アクセス設定
		http.authorizeHttpRequests(
				//全ユーザーにアクセス許可
				(requests) -> requests
				.requestMatchers("/css/**").permitAll()
				.anyRequest().authenticated()//上記以外のURLはログインが必要
				)
		//ログイン設定
		.formLogin((form) -> form
				.loginPage("/login")
				.loginProcessingUrl("/login")
				.defaultSuccessUrl("/?loggedIn")
				.failureUrl("/login?error")
				.permitAll()
				)
		//ログアウト設定
		.logout((logout) -> logout
				.logoutSuccessUrl("/login")
				.permitAll()
				);
		
		return http.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}

