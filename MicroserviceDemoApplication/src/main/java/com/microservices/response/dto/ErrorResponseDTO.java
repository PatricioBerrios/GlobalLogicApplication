package com.microservices.response.dto;

import java.util.List;

public class ErrorResponseDTO {

	private List<ErrorDTO> error;

	public List<ErrorDTO> getError() {
		return error;
	}

	public void setError(List<ErrorDTO> error) {
		this.error = error;
	}

}
