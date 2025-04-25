package com.example.back.service;

import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.example.back.entity.Member;
import com.example.back.repository.MemberRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private final MemberRepository memberRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		Supplier<UsernameNotFoundException> s = () -> new UsernameNotFoundException("username not found");
		Member member = memberRepository.findByUsername(username).orElseThrow(s);
		return User.builder()
					.username(member.getUsername())
					.password(member.getPassword())
					.roles(member.getRole().name())
					.build();
	}
}
