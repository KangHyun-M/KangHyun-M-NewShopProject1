package com.example.back.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.back.constant.Role;
import com.example.back.dto.MemberDTO;
import com.example.back.entity.Member;
import com.example.back.repository.MemberRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
	
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
	
	//이메일 중복확인용
	private boolean isDuplicatedUsername(String username) {
		return memberRepository.existsByUsername(username);
	}
	
	@Transactional
	public Member saveMember(MemberDTO memberDTO) throws Exception {
		if(isDuplicatedUsername(memberDTO.getUsername())) throw new Exception("중복이요");
		Member member = Member.builder()
				.username(memberDTO.getUsername())
				.password(passwordEncoder.encode(memberDTO.getPassword()))
				.role(Role.USER)
				.build();
		return memberRepository.save(member);
	}
}
