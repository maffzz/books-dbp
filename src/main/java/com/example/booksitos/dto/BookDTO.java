package com.example.booksitos.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookDTO {
    @NotNull(message = "no olvides agregar el titulo del libro !!!")
    private String title;
    @NotNull(message = "no olvides agregar el nombre del autor !!!")
    private String author;}