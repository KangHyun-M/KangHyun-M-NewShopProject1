package com.example.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.back.handler.LoginSuccessHandler;
import com.example.back.jwt.JsonUsernamePasswordAuthenticationFilter;
import com.example.back.jwt.JwtService;
import com.example.back.repository.MemberRepository;
import com.example.back.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
		
	
	private MemberRepository memberRepository;
	
	private JwtService jwtService;
	
	@Bean  
	public SecurityFilterChain httpFilterChain(HttpSecurity http) throws Exception {  
	    http  
	            .httpBasic(AbstractHttpConfigurer::disable)  
	            .cors(cors ->  
	              cors.configurationSource(corsConfigurationSource()))  
	            .csrf(AbstractHttpConfigurer::disable)  
	            .formLogin(AbstractHttpConfigurer::disable)  
	            .sessionManagement(sessionManagement -> sessionManagement  
	                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  
	            .authorizeHttpRequests((authorizeRequests) -> authorizeRequests  
	                    .requestMatchers("/login","/").permitAll()  
	                    .requestMatchers("/api/signup","/api/hello").permitAll()  
	                    .anyRequest().authenticated()  
	            );  
	  
	    http.addFilterAfter(jsonUsernamePasswordAuthenticationFilter(), LogoutFilter.class);  
	    return http.build();  
	}
	
	@Bean  
	public CorsConfigurationSource corsConfigurationSource(){  
	    CorsConfiguration corsConfig = new CorsConfiguration();  
	  
	    corsConfig.addAllowedOrigin("http://localhost:3000");  
	    corsConfig.setAllowCredentials(true);  
	    corsConfig.addAllowedHeader("*");  
	    corsConfig.addAllowedMethod("*");  
	    corsConfig.addExposedHeader("Authorization");  
	    corsConfig.addExposedHeader("Set-Cookie");  
	    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  
	    source.registerCorsConfiguration("/**", corsConfig);  
	    return source;  
	}
	
	@Bean  
	public PasswordEncoder passwordEncoder() {  
	    DelegatingPasswordEncoder delegatingPasswordEncoder = (DelegatingPasswordEncoder) PasswordEncoderFactories.createDelegatingPasswordEncoder();  
	    delegatingPasswordEncoder.setDefaultPasswordEncoderForMatches(new BCryptPasswordEncoder());  
	    return delegatingPasswordEncoder;  
	}  
	  
	@Bean  
	public UserDetailsService userDetailsService() {  
	    return new CustomUserDetailsService(memberRepository);
	}  
	  
	@Bean  
	public AuthenticationManager authenticationManager() throws Exception {  
	    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();  
	    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());  
	    daoAuthenticationProvider.setUserDetailsService(userDetailsService());  
	    return new ProviderManager(daoAuthenticationProvider);  
	}  
	  
	@Bean  
	public AuthenticationSuccessHandler loginSuccessHandler(){  
	    return new LoginSuccessHandler(jwtService);  
	}

	public JsonUsernamePasswordAuthenticationFilter jsonUsernamePasswordAuthenticationFilter() throws Exception {  
	    JsonUsernamePasswordAuthenticationFilter filter = new JsonUsernamePasswordAuthenticationFilter();  
	  
	    filter.setAuthenticationManager(authenticationManager());  
	    filter.setAuthenticationSuccessHandler(loginSuccessHandler());  
	  
	    return filter;  
	}
}
