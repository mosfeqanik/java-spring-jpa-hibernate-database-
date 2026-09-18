package com.books.bookLibrary.controller;

import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.books.bookLibrary.model.Book;
import com.books.bookLibrary.repository.BookRepository;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;

    // Constructor Injection
    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Save Book
    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    // Get All Books
    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}