package com.app.bookstore.service;

import com.app.bookstore.model.Book;
import org.springframework.http.ResponseEntity;

public interface BookStoreService {
    ResponseEntity<?> createBook(Book book);

    ResponseEntity<?> getAllBooks();

    ResponseEntity<?> getBookById(Integer id);

    ResponseEntity<?> updateAllBookDetails(Book book, Integer id);

    ResponseEntity<?> deleteBook(Book book, Integer id);

    ResponseEntity<?> updateBookDetails(Book book, Integer id);
}
