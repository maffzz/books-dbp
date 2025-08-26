package com.example.booksitos.domain;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Fav {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String review;

    @ManyToOne
    private User user;

    @ManyToOne
    private Book book;}