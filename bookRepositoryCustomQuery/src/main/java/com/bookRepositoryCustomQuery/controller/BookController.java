package com.bookRepositoryCustomQuery.controller;

import com.bookRepositoryCustomQuery.model.Book;
import com.bookRepositoryCustomQuery.service.BookService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService service;

    // CREATE
    @PostMapping("/book")
    public ResponseEntity<Book> addBook(@RequestBody Book book) {
        Book saved = service.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    // READ ALL
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    // FIND BY TITLE
    @GetMapping("/books/title/{title}")
    public ResponseEntity<List<Book>> getByTitle(@PathVariable String title) {
        return ResponseEntity.ok(service.getByTitle(title));
    }

    // FIND BY AUTHOR
    @GetMapping("/books/author/{author}")
    public ResponseEntity<List<Book>> getByAuthor(@PathVariable String author) {
        return ResponseEntity.ok(service.getByAuthor(author));
    }
}