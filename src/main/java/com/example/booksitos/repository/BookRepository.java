package com.example.booksitos.repository;

import com.example.booksitos.domain.Book;
import com.example.booksitos.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByUser(User user);
    List<Book> findByAuthor(String author);
    List<Book> findByTitle(String title);}