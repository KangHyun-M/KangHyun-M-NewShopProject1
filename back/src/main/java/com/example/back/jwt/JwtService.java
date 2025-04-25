package com.example.back.jwt;

import java.util.Optional;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface JwtService {
	
	String generateAccessToken(String username);
	String generateRefreshToken();
	
	Optional<String> extractAccessToken(HttpServletRequest request);
	Optional<String> extractRefreshToken(HttpServletRequest request);
	Optional<String> extractName(String accessToken);
	Jws<Claims> validateToken(String token) throws Exception;
	
	void setAccessToken(HttpServletResponse response, String accessToken);
	void setRefreshToken(HttpServletResponse response, String refreshToken);
}
