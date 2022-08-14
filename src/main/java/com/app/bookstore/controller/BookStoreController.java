package com.app.bookstore.controller;

import com.app.bookstore.model.Book;
import com.app.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "books")
public class BookStoreController implements BookStoreService {

    @Autowired
    private BookStoreService bookStoreService;

    @Override
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        return bookStoreService.createBook(book);
    }

    @Override
    @GetMapping
    public ResponseEntity<?> getAllBooks() {
        return bookStoreService.getAllBooks();
    }

    @Override
    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Integer id) {
        return bookStoreService.getBookById(id);
    }

    @Override
    @PutMapping(path = "/{id}")
    public ResponseEntity<?> updateAllBookDetails(@RequestBody Book book, @PathVariable Integer id) {
        return bookStoreService.updateAllBookDetails(book, id);
    }

    @Override
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteBook(@RequestBody Book book, @PathVariable Integer id) {
        return bookStoreService.deleteBook(book, id);
    }

    @Override
    @PatchMapping(path = "/{id}")
    public ResponseEntity<?> updateBookDetails(@RequestBody Book book, @PathVariable Integer id) {
        return bookStoreService.updateBookDetails(book, id);
    }
}
