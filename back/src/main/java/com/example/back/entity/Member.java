package com.example.back.entity;

import com.example.back.constant.Role;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	//이메일이 아이디
	@Column(unique = true, name = "email")
	private String username;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "email_code")
	private String emailAuthCode;
	
	@Column
	@Enumerated(EnumType.STRING)
	private Role role;
}
