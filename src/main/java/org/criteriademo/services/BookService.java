package org.criteriademo.services;

import org.criteriademo.entities.Book;
import org.criteriademo.repositories.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> searchBooks(Map<String, Object> filters) {
        return bookRepository.findBooksByCriteria(filters);
    }
}
