package org.criteriademo.repositories;

import org.criteriademo.entities.Book;

import java.util.List;
import java.util.Map;

public interface BookRepository {
    List<Book> findBooksByCriteria(Map<String, Object> filters);
}
