package org.criteriademo.controllers;

import org.criteriademo.entities.Book;
import org.criteriademo.services.BookService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String publisherName,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate publishDateAfter
    ) {
        Map<String, Object> filters = new HashMap<>();
        if (title != null) filters.put("title", title);
        if (genre != null) filters.put("genre", genre);
        if (authorName != null) filters.put("authorName", authorName);
        if (publisherName != null) filters.put("publisherName", publisherName);
        if (publishDateAfter != null) filters.put("publishDateAfter", publishDateAfter);

        return ResponseEntity.ok(bookService.searchBooks(filters));
    }
}
