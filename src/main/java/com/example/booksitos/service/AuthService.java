package com.example.booksitos.service;

import com.example.booksitos.domain.User;
import com.example.booksitos.dto.AuthRequest;
import com.example.booksitos.dto.AuthResponse;
import com.example.booksitos.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(AuthRequest request) {
        User user = new User();
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setEmail(request.getEmail());
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        AuthResponse response = new AuthResponse();
        response.setToken(jwt);
        return response;}

    public AuthResponse login(AuthRequest request) throws IllegalArgumentException {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail());
        var jwt = jwtService.generateToken(user);
        AuthResponse response = new AuthResponse();
        response.setToken(jwt);
        return response;}}