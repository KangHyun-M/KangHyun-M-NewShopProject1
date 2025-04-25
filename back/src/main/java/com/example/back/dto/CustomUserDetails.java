package com.example.back.dto;


import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.back.entity.Member;

import lombok.Builder;

@Builder
public class CustomUserDetails implements UserDetails {
	
	private final Member member;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities(){
		//추후 추가
		return List.of();
	}
	
	@Override
	public String getPassword() {
		return this.member.getPassword();
	}
	
	@Override
	public String getUsername() {
		return this.member.getUsername();
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return UserDetails.super.isAccountNonExpired();
	}
	
	@Override
	public boolean isAccountNonLocked() {
		return UserDetails.super.isAccountNonLocked();
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		return UserDetails.super.isCredentialsNonExpired();
	}
	
	@Override
	public boolean isEnabled() {
		return UserDetails.super.isEnabled();
	}
}
