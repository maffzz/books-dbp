package com.example.booksitos.mapper;

import com.example.booksitos.domain.Book;
import com.example.booksitos.dto.BookDTO;

public class BookMapper {
    public static BookDTO toDTO(Book book) {
        BookDTO dto = new BookDTO();
        dto.setAuthor(book.getAuthor());
        dto.setTitle(book.getTitle());
        return dto;}}