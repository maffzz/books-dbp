package com.example.booksitos.controller;

import com.example.booksitos.domain.Book;
import com.example.booksitos.domain.User;
import com.example.booksitos.dto.BookDTO;
import com.example.booksitos.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<BookDTO> addBook(@Valid @RequestBody BookDTO dto, @Valid @AuthenticationPrincipal User user) {
        BookDTO saved = bookService.addBook(dto, user);
        return ResponseEntity.ok(saved);}

    @DeleteMapping
    public void deleteBook(@Valid @RequestBody Long bookId) {
        bookService.deleteBook(bookId);}

    @GetMapping("/my")
    public ResponseEntity<List<BookDTO>> getMyBooks(@Valid @AuthenticationPrincipal User user) {
        return ResponseEntity.ok(bookService.getMyBooks(user));}

    @GetMapping("/{author}")
    public ResponseEntity<List<Book>> getBooksByAuthor(@Valid @PathVariable String author) {
        return ResponseEntity.ok(bookService.getBooksByAuthor(author));}

    @GetMapping("/{title}")
    public ResponseEntity<List<Book>> getBooksByTitle(@Valid @PathVariable String title) {
        return ResponseEntity.ok(bookService.getBooksByTitle(title));}}