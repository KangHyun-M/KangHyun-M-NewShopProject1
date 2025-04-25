package com.example.back.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.example.back.jwt.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private final JwtService jwtService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		String username = authentication.getName();
		String accessToken = jwtService.generateAccessToken(username);
		String refreshToken = jwtService.generateRefreshToken();
		
		response.setContentType("/application/json");
		response.setCharacterEncoding("utf-8");
		
		jwtService.setAccessToken(response, accessToken);
		jwtService.setRefreshToken(response, refreshToken);
	}
}
