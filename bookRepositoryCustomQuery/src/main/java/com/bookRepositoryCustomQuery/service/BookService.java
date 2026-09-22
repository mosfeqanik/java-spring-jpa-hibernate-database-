package com.bookRepositoryCustomQuery.service;

import com.bookRepositoryCustomQuery.model.Book;
import com.bookRepositoryCustomQuery.repository.BookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    public Book addBook(Book book) {
        return repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public List<Book> getByTitle(String title) {
        return repository.findByTitle(title);
    }

    public List<Book> getByAuthor(String author) {
        return repository.findByAuthor(author);
    }
}