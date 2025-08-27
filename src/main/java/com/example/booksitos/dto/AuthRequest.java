package com.example.booksitos.dto;

import jakarta.validation.constraints.NotNull;

public class AuthRequest {
    @NotNull(message = "no olvides agregar tu email !!!")
    private String email;
    @NotNull(message = "no olvides agregar tu contraseña !!!")
    private String password;

    public AuthRequest() {}

    public AuthRequest(String email, String password) {
        this.email = email;
        this.password = password;}

    public String getEmail() {
        return email;}

    public void setEmail(String email) {
        this.email = email;}

    public String getPassword() {
        return password;}

    public void setPassword(String password) {
        this.password = password;}}