package com.books.bookLibrary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.books.bookLibrary.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}