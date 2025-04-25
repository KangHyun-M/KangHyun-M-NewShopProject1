package com.example.back.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.back.dto.MemberDTO;
import com.example.back.service.MemberService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
	
	private final MemberService memberService;
	
	@PostMapping("/signup")
	public void signUp(@RequestBody MemberDTO memberDTO) throws Exception{
		memberService.saveMember(memberDTO);
	}
	
	@GetMapping("/hello")
    public String test() {
        return "Hello, world!";
    }
}
