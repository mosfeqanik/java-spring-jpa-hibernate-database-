package com.bookRepositoryCustomQuery.repository;

import com.bookRepositoryCustomQuery.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BookRepository {

    // Initialize storage
    private final List<Book> books = new ArrayList<>();

    // Save a book
    public Book save(Book book) {
        books.add(book);
        return book;
    }

    // Return all books
    public List<Book> findAll() {
        return books;
    }

    // Find books by title (case-insensitive)
    public List<Book> findByTitle(String title) {
        return books.stream()
                .filter(book -> book.getTitle()
                        .equalsIgnoreCase(title))
                .collect(Collectors.toList());
    }

    // Find books by author (case-insensitive)
    public List<Book> findByAuthor(String author) {
        return books.stream()
                .filter(book -> book.getAuthor()
                        .equalsIgnoreCase(author))
                .collect(Collectors.toList());
    }
}