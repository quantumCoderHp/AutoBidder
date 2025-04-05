package com.autobidder.auth.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.autobidder.auth.dto.AuthResponse;
import com.autobidder.auth.dto.LoginRequest;
import com.autobidder.auth.dto.RegisterRequest;
import com.autobidder.auth.entity.User;
import com.autobidder.auth.repository.UserRepository;
import com.autobidder.auth.service.AuthService;

public class AuthServiceImpl implements AuthService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public AuthResponse register(RegisterRequest request) {

		if (userRepository.findByEmail(request.getEmail()).isPresent()) {
			throw new RuntimeException("Email already registered");
		}

		User user = User.builder().email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
				.role(request.getRole()).isActive(true).build();

		userRepository.save(user);

		return new AuthResponse("registered_successfully_token_placeholder");
	}

	@Override
	public AuthResponse login(LoginRequest request) {

		User user = userRepository.findByEmail(request.getEmail())
				.orElseThrow(() -> new RuntimeException("User not found"));

		if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid password");
		}

		return new AuthResponse("logged_in_token_placeholder");
	}

}
