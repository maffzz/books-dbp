package com.example.booksitos.controller;

import com.example.booksitos.domain.Book;
import com.example.booksitos.domain.Fav;
import com.example.booksitos.domain.User;
import com.example.booksitos.exception.NotFound;
import com.example.booksitos.repository.BookRepository;
import com.example.booksitos.repository.FavRepository;
import com.example.booksitos.service.BookService;
import com.example.booksitos.service.FavService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/favs")
@RequiredArgsConstructor
public class FavController {
    private final FavService favService;
    private final BookService bookService;
    private final BookRepository bookRepository;
    private final FavRepository favRepository;

    @PostMapping("/{bookId}")
    public ResponseEntity<Fav> markAsFav(@PathVariable Long bookId,
                                         @RequestBody Map<String, String> body,
                                         @AuthenticationPrincipal User user) {
        String review = body.get("review");
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new NotFound("libro no encontrado !!!"));
        Fav fav = favService.markAsFav(user, book, review);
        return ResponseEntity.ok(fav);}

    @GetMapping
    public ResponseEntity<List<Fav>> getMyFavs(@AuthenticationPrincipal User user) {
        List<Fav> favs = favService.getMyFavs(user);
        return ResponseEntity.ok(favs);}

    @PutMapping("/{favId}")
    public ResponseEntity<Fav> updateReview(
            @PathVariable Long favId,
            @RequestBody String newReview,
            @AuthenticationPrincipal User user) {
        Fav fav = favRepository.findById(favId)
                .orElseThrow(() -> new NotFound("fav no encontrado !!!"));
        Fav updated = favService.updateFavReview(fav, newReview);
        return ResponseEntity.ok(updated);}}