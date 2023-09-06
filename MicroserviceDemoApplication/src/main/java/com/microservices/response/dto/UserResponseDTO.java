package com.microservices.response.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.microservices.request.dto.PhoneUserDTO;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponseDTO {
	
	private UUID id;
	private String name;
	private String email;
	private String password;
	private LocalDateTime created;
	private LocalDateTime lastLogin;
	private String token;
	private Boolean isActive;
	private List<PhoneUserDTO> phones;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<PhoneUserDTO> getPhones() {
		return phones;
	}

	public void setPhones(List<PhoneUserDTO> phones) {
		this.phones = phones;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public LocalDateTime getCreated() {
		return created;
	}

	public void setCreated(LocalDateTime created) {
		this.created = created;
	}

	public LocalDateTime getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(LocalDateTime lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean isActive() {
		return isActive;
	}

	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

}
