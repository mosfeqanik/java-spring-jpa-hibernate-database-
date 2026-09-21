package com.bookresponseentity.bookresponseentity.controller;

import com.bookresponseentity.bookresponseentity.model.Book;
import com.bookresponseentity.bookresponseentity.service.BookService;
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
        Book savedBook = service.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    // READ ALL
    @GetMapping("/books")
    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(service.getAllBooks());
    }

    // READ BY ID
    @GetMapping("/book/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable int id) {

        Book book = service.getBookById(id);

        if (book == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(book);
    }
}