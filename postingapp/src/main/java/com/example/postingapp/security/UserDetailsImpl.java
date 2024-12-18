package com.example.postingapp.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.postingapp.entity.User;

public class UserDetailsImpl implements UserDetails{
	private final User user;
	private final Collection<GrantedAuthority> authorities;
	
	public UserDetailsImpl(User user,Collection<GrantedAuthority> authorities) {
		this.user = user;
		this.authorities = authorities;
	}
	
	//Userオブジェクトを返す
	public User getUser() {
		return user;
	}
	
	//ロールのコレクションを返す
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	//ハッシュ化済みのパスワードを返す
	@Override
	public String getPassword() {
		return user.getPassword();
		
	}
	
	//ログイン時に使用するユーザー名（メールアドレスを返す）
	@Override
	public String getUsername() {
		return user.getEmail();
	}
	
	//アカウントが期限切れでなければtrue
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	
	//ユーザーがロックされていなければtrue
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	
	@Override
	//ユーザーのパスワードが期限切れでなければtrue
	public boolean isCredentialsNonExpired() {
		return true;
	}
	
	@Override
	//ユーザーが有効であればtrue
	public boolean isEnabled() {
		return user.getEnabled();
	}

}
