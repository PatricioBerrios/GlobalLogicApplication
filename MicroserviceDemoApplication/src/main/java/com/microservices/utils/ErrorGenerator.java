package com.microservices.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import com.microservices.response.dto.ErrorDTO;
import com.microservices.response.dto.ErrorResponseDTO;

public class ErrorGenerator {

	public static ErrorResponseDTO generateError(int code, String description) {

		List<ErrorDTO> errorList = new ArrayList<>();
		errorList.add(new ErrorDTO(generateTimeStamp(), code, description));
		ErrorResponseDTO errorResponse = new ErrorResponseDTO();
		errorResponse.setError(errorList);
		return errorResponse;
	}

	private static String generateTimeStamp() {

		return LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME);
	}

}
