package com.bookresponseentity.bookresponseentity.repository;
import com.bookresponseentity.bookresponseentity.model.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository {

    List<Book> books = new ArrayList<>();

    public Book save(Book book) {
        books.add(book);
        return book;
    }

    public List<Book> findAll() {
        return books;
    }

    public Book findById(int id) {
        return books.stream()
                .filter(b -> b.getId() == id)
                .findFirst()
                .orElse(null);
    }
}