package org.criteriademo.repositories.impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.criteriademo.entities.Author;
import org.criteriademo.entities.Book;
import org.criteriademo.entities.Publisher;
import org.criteriademo.repositories.BookRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Book> findBooksByCriteria(Map<String, Object> filters) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> query = cb.createQuery(Book.class);
        Root<Book> book = query.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (filters.containsKey("title")) {
            predicates.add(cb.like(book.get("title"), "%" + filters.get("title") + "%"));
        }

        if (filters.containsKey("genre")) {
            predicates.add(cb.equal(book.get("genre"), filters.get("genre")));
        }

        if (filters.containsKey("publishDateAfter")) {
            predicates.add(cb.greaterThanOrEqualTo(
                    book.get("publishDate"),
                    (LocalDate) filters.get("publishDateAfter")
            ));
        }

        if (filters.containsKey("authorName")) {
            Join<Book, Author> authorJoin = book.join("author");
            predicates.add(cb.like(authorJoin.get("name"), "%" + filters.get("authorName") + "%"));
        }

        if (filters.containsKey("publisherName")) {
            Join<Book, Publisher> publisherJoin = book.join("publisher");
            predicates.add(cb.equal(publisherJoin.get("name"), filters.get("publisherName")));
        }

        query.select(book).where(cb.and(predicates.toArray(new Predicate[0])));

        return entityManager.createQuery(query).getResultList();
    }
}
