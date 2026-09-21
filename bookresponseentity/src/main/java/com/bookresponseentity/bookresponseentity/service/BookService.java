package com.bookresponseentity.bookresponseentity.service;

import com.bookresponseentity.bookresponseentity.model.Book;
import com.bookresponseentity.bookresponseentity.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bookresponseentity.bookresponseentity.model.Book;
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

    public Book getBookById(int id) {
        return repository.findById(id);
    }
}