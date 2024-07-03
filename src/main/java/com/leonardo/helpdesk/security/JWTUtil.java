package com.leonardo.helpdesk.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {
	
	@Value("${Jwt.expiration}")
	private Long expiration;
	
	@Value("${jwt.secret}")
	private String secret;

	public String generatedToken(String Email) {
		return Jwts.builder()
				.setSubject(Email)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
				.compact();
	}
}
