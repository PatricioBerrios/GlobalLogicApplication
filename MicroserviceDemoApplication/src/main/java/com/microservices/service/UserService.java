package com.microservices.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.microservices.entity.PhoneEntity;
import com.microservices.entity.UserEntity;
import com.microservices.repository.PhoneRepository;
import com.microservices.repository.UserRepository;
import com.microservices.request.dto.PhoneUserDTO;
import com.microservices.request.dto.UserRequestDTO;
import com.microservices.response.dto.UserResponseDTO;
import com.microservices.utils.CustomException;
import com.microservices.utils.JwtTokenProvider;
import com.microservices.utils.UserCreationUtils;

import io.jsonwebtoken.Claims;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PhoneRepository phoneRepository;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public UserResponseDTO createUser(UserRequestDTO userRequest) {

		if (!UserCreationUtils.validateMail(userRequest.getEmail())
				|| !UserCreationUtils.validatePassword(userRequest.getPassword())) {
			throw new CustomException(400, "Tu contraseña o correo electrónico no cumplen el estándar.");
		}

		if (userRepository.existsByEmail(userRequest.getEmail())) {
			throw new CustomException(409, "El correo electrónico ya está registrado.");
		}

		UserEntity userEntity = userRepository.save(getUserEntity(userRequest));

		// Save phone numbers
		Optional.ofNullable(userRequest.getPhones()).filter(phones -> !phones.isEmpty())
				.ifPresent(phones -> saveUserPhoneNumbers(phones, userEntity.getId()));

		// Convert to requested json response format
		UserResponseDTO response = new UserResponseDTO();
		
		if(userEntity != null) {
			response.setToken(jwtTokenProvider.generateToken(userEntity.getEmail(), userEntity.getPassword()));
			response.setActive(userEntity.isActive());
			response.setCreated(userEntity.getCreated());
			response.setId(userEntity.getId());
			response.setLastLogin(userEntity.getLastLogin());
		}
		
		return response;
	}

	public UserResponseDTO loginUser(String authorization) {

		Claims claims;
		try {
			claims = jwtTokenProvider.decodeToken(authorization);
		} catch (Exception e) {
			throw new CustomException(401, "Token se encuentra expirado.");
		}

		UserEntity user = userRepository.findByEmailAndPassword(claims.get("email", String.class),
				claims.get("password", String.class));

		// Get phones by user id and convert to requested json format
		List<PhoneUserDTO> phonesResponse = convertToPhoneDTO(phoneRepository.findByUserId(user.getId().toString()));

		// Convert to requested json format
		UserResponseDTO response = new UserResponseDTO();
		response.setName(user.getName());
		response.setEmail(user.getEmail());
		response.setPassword(user.getPassword());
		response.setActive(user.isActive());
		response.setCreated(user.getCreated());
		response.setId(user.getId());
		response.setLastLogin(user.getLastLogin());
		response.setToken(jwtTokenProvider.generateToken(user.getEmail(), user.getPassword()));
		response.setPhones(phonesResponse);
		return response;

	}

	private void saveUserPhoneNumbers(List<PhoneUserDTO> phones, UUID id) {

		List<PhoneEntity> phonesEntity = convertToPhoneEntity(phones, id);
		phoneRepository.saveAll(phonesEntity);

	}

	private List<PhoneEntity> convertToPhoneEntity(List<PhoneUserDTO> phones, UUID userId) {

		return phones.stream().map(phoneRequest -> {
			PhoneEntity phoneEntity = new PhoneEntity();
			phoneEntity.setNumber(phoneRequest.getNumber());
			phoneEntity.setCitycode(phoneRequest.getCitycode());
			phoneEntity.setContrycode(phoneRequest.getContrycode());
			phoneEntity.setUserId(userId.toString());
			return phoneEntity;
		}).collect(Collectors.toList());
	}

	public UserEntity getUserEntity(UserRequestDTO userRequest) {

		UserEntity user = new UserEntity();
		user.setName(userRequest.getName());
		user.setEmail(userRequest.getEmail());
		user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
		user.setCreated(LocalDateTime.now());
		user.setLastLogin(null);
		user.setActive(true);
		return user;
	}

	public List<PhoneUserDTO> convertToPhoneDTO(List<PhoneEntity> phones) {

		return phones.stream().map(phoneEntity -> {
			PhoneUserDTO phone = new PhoneUserDTO();
			phone.setNumber(phoneEntity.getNumber());
			phone.setCitycode(phoneEntity.getCitycode());
			phone.setContrycode(phoneEntity.getContrycode());
			return phone;
		}).collect(Collectors.toList());

	}

}
