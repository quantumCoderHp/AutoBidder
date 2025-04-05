package com.autobidder.auth.service;

import com.autobidder.auth.dto.AuthResponse;
import com.autobidder.auth.dto.LoginRequest;
import com.autobidder.auth.dto.RegisterRequest;

public interface AuthService {

	AuthResponse register(RegisterRequest request);

	AuthResponse login(LoginRequest request);

}
