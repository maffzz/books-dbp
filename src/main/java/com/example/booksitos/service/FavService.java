package com.example.booksitos.service;

import com.example.booksitos.domain.Book;
import com.example.booksitos.domain.Fav;
import com.example.booksitos.domain.User;
import com.example.booksitos.exception.BadRequest;
import com.example.booksitos.exception.Conflict;
import com.example.booksitos.repository.FavRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FavService {
    private final FavRepository favRepository;

    public Fav markAsFav(User user, Book book, String review) {
        if (favRepository.existsByUserAndBook(user, book)) {
            throw new Conflict("este libro ya está marcado como fav !!!");}
        Fav fav = new Fav();
        fav.setUser(user);
        fav.setBook(book);
        fav.setReview(review);
        return favRepository.save(fav);}

    public Fav updateFavReview(Fav fav, String review) {
        if (review == null) {
            throw new BadRequest("el review no puede ser nulo !!!");}
        fav.setReview(review);
        return favRepository.save(fav);}

    public List<Fav> getMyFavs(User user) {
        return favRepository.findByUser(user);}}