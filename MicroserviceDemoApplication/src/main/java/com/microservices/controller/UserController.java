package com.microservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microservices.request.dto.UserRequestDTO;
import com.microservices.service.UserService;
import com.microservices.utils.CustomException;
import com.microservices.utils.ErrorGenerator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@RestController
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/sign-up")
	public ResponseEntity<?> signUp(@RequestBody UserRequestDTO userRequest) {	
		
		try {
            return ResponseEntity.ok(userService.createUser(userRequest));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(ErrorGenerator.generateError(e.getCode(), e.getMessage()));
        }
				
    }
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestHeader("Authorization") String authorizationHeader) {
	    
		try {
            return ResponseEntity.ok(userService.loginUser(authorizationHeader));
        } catch (CustomException e) {
            return ResponseEntity.badRequest().body(ErrorGenerator.generateError(e.getCode(), e.getMessage()));
        }
		
	}

}
