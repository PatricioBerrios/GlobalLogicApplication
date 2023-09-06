package com.microservices.test;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.microservices.MicroServiceApplication;
import com.microservices.request.dto.UserRequestDTO;
import com.microservices.response.dto.UserResponseDTO;
import com.microservices.service.UserService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes = {MicroServiceApplication.class})
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	/*Successful creation of an user testing*/
	
	@Test
	public void testCreateUserSuccesfully() {
	    
		UserRequestDTO request = new UserRequestDTO();
		request.setEmail("test@prueba.com");
		request.setPassword("a2asfGfdfdf4");
		
		UserResponseDTO user = userService.createUser(request);
		assertNotNull(user);
		assertNotNull(user.getToken());
		assertNotNull(user.getId());
	}
	
	/*Unsuccessful sign-up user due an invalid email*/

	@Test
	public void testInvalidEmailCreateUser() {
		
	    UserRequestDTO request = new UserRequestDTO();
	    request.setEmail("test@prom"); // Invalid mail
	    request.setPassword("a2asfGfdfdf4"); // Valid password

	    try {
	        userService.createUser(request);
	        fail();
	    } catch (Exception e) {
	        assertEquals("Tu contraseña o correo electrónico no cumplen el estándar.", e.getMessage()); 
	    }
	}
	
	/*Unsuccessful sign-up user due an invalid password*/
	
	@Test
	public void testInvalidPasswordCreateUser() {
		
	    UserRequestDTO request = new UserRequestDTO();
	    request.setEmail("test@prueba.com"); // Valid email
	    request.setPassword("fsdfs334s..."); // Invalid password

	    try {
	        userService.createUser(request);
	        fail();
	    } catch (Exception e) {
	        assertEquals("Tu contraseña o correo electrónico no cumplen el estándar.", e.getMessage()); 
	    }
	}
		
}
