package com.microservices.request.dto;

import java.util.List;

public class UserRequestDTO {

	private String name;
	private String email;
	private String password;
	private String token;
	private List<PhoneUserDTO> phones;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

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

}
