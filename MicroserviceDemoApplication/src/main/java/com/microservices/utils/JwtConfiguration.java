package com.microservices.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfiguration {

	@Value("${jwt.secret-key}")
	private String keyValue;

	public String getKeyValue() {
		return keyValue;
	}

}
