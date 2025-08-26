package com.example.booksitos.repository;

import com.example.booksitos.domain.Book;
import com.example.booksitos.domain.Fav;
import com.example.booksitos.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FavRepository extends JpaRepository<Fav, Long> {
    List<Fav> findByUser(User user);
    boolean existsByUserAndBook(User user, Book book);}