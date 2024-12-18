package com.example.postingapp.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.postingapp.entity.Role;
import com.example.postingapp.entity.User;
import com.example.postingapp.form.SignupForm;
import com.example.postingapp.repository.RoleRepository;
import com.example.postingapp.repository.UserRepository;



@Service
public class UserService {
	private final UserRepository userRepository;
	private final RoleRepository roleRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository,RoleRepository roleRepository,PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	@Transactional
	public User createUser(SignupForm signupForm) {
		User user = new User();
		Role role = roleRepository.findByName("ROLE_GENERAL");
		
		user.setName(signupForm.getName());
		user.setEmail(signupForm.getEmail());
		user.setPassword(passwordEncoder.encode(signupForm.getPassword()));
		user.setRole(role);
		user.setEnabled(false);
		
		return userRepository.save(user);
	}
	
	//メールアドレスが登録済みかチェック
	public boolean isEmailRegistered(String email) {
		User user = userRepository.findByEmail(email);
		//登録済みはtrue
		return user != null;
	}
	
	//パスワードと確認用パスワードの一致チェック
	public boolean isSamePassword(String password,String passwordConfirmation) {
		//一致すればtrue
		return password.equals(passwordConfirmation);
	}
	
	//ユーザーを有効にする
	@Transactional
	public void enabledUser(User user) {
		user.setEnabled(true);
		userRepository.save(user);
	}

}