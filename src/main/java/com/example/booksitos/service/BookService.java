package com.example.booksitos.service;

import com.example.booksitos.domain.Book;
import com.example.booksitos.domain.User;
import com.example.booksitos.dto.BookDTO;
import com.example.booksitos.exception.BadRequest;
import com.example.booksitos.exception.NotFound;
import com.example.booksitos.mapper.BookMapper;
import com.example.booksitos.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository bookRepository;

    public BookDTO addBook(BookDTO dto, User user) {
        Book newBook = new Book();
        newBook.setAuthor(dto.getAuthor());
        newBook.setTitle(dto.getTitle());
        newBook.setUser(user);
        bookRepository.save(newBook);
        BookDTO response = new BookDTO();
        response.setAuthor(dto.getAuthor());
        response.setTitle(dto.getTitle());
        return response;}

    public void deleteBook(Long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new NotFound("el libro no existe !!!");}
        if (bookId == null) {
            throw new BadRequest("el id del libro no puede ser nulo !!!");}
        bookRepository.deleteById(bookId);}

    public List<BookDTO> getMyBooks(User user) {
        List<Book> books = bookRepository.findByUser(user);
        if (books.isEmpty()) {
            throw new NotFound("no tienes libros aún !!!");}
        return books.stream()
                .map(BookMapper::toDTO)
                .toList();}

    public List<Book> getBooksByAuthor(String author) {
        List<Book> books = bookRepository.
                findByAuthor(author);
        if (books.isEmpty()) {
            throw new NotFound("no hay libros con el autor " + author);}
        return books;}

    public List<Book> getBooksByTitle(String title) {
        List<Book> books = bookRepository.findByTitle(title);
        if (books == null) {
            throw new NotFound("no hay libros con el nombre " + title);}
        return books;}}