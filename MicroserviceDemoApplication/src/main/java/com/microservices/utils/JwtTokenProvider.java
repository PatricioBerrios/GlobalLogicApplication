package com.microservices.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JwtTokenProvider {

	private final JwtConfiguration jwtConfig;

	@Autowired
	public JwtTokenProvider(JwtConfiguration jwtConfig) {
		this.jwtConfig = jwtConfig;
	}

	public String generateToken(String email, String password) {

		return Jwts.builder().claim("email", email).claim("password", password).setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 5 * 60 * 1000)) // 5 minutes
				.signWith(Keys.hmacShaKeyFor(jwtConfig.getKeyValue().getBytes()), SignatureAlgorithm.HS256).compact();
	}

	public Claims decodeToken(String token) {
		return Jwts.parserBuilder().setSigningKey(Keys.hmacShaKeyFor(jwtConfig.getKeyValue().getBytes())).build()
				.parseClaimsJws(token).getBody();
	}

}