package com.app.bookstore.service.impl;

import com.app.bookstore.model.Book;
import com.app.bookstore.repository.BookStoreRepository;
import com.app.bookstore.service.BookStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookStoreServiceImpl implements BookStoreService {

    @Autowired
    private BookStoreRepository bookStoreRepository;

    @Override
    public ResponseEntity<?> createBook(Book book) {
        try {
            bookStoreRepository.save(book);
            return new ResponseEntity<>("Successfully created a book", HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed due to bad input", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getAllBooks() {
        List<Book> books = bookStoreRepository.findAll();
        if (books != null && !books.isEmpty()) {
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed due to bad input", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> getBookById(Integer id) {
        Optional<Book> book = getBook(id);
        if (book.isPresent()) {
            List<Book> books = new ArrayList<>();
            books.add(book.get());
            return new ResponseEntity<>(books, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("No books found with the given id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateAllBookDetails(Book book, Integer id) {
        try {
            Optional<Book> oldBook = getBook(id);
            if (oldBook.isPresent()) {
                Book oldBookObj = oldBook.get();
                oldBookObj.setAuthor(book.getAuthor());
                oldBookObj.setCategory(book.getCategory());
                oldBookObj.setDescription(book.getDescription());
                oldBookObj.setIsbn(book.getIsbn());
                oldBookObj.setName(book.getName());
                oldBookObj.setClassification(book.getClassification());
                oldBookObj.setPrice(book.getPrice());
                bookStoreRepository.save(oldBookObj);
                return new ResponseEntity<>("Updated Successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Unable to find book with the given id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed due to bad input", HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public ResponseEntity<?> deleteBook(Book book, Integer id) {
        Optional<Book> oldBook = getBook(id);
        if (oldBook.isPresent()) {
            bookStoreRepository.delete(oldBook.get());
            return new ResponseEntity<>("Delete the book successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Unable to find book with the given id", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<?> updateBookDetails(Book book, Integer id) {
        try {
            Optional<Book> oldBook = getBook(id);
            if (oldBook.isPresent()) {
                Book oldBookObj = oldBook.get();
                boolean doUpdate = false;
                if (StringUtils.hasLength(book.getAuthor())) {
                    oldBookObj.setAuthor(book.getAuthor());
                    doUpdate = true;
                }

                if (StringUtils.hasLength(book.getCategory())) {
                    oldBookObj.setCategory(book.getCategory());
                    doUpdate = true;
                }

                if (StringUtils.hasLength(book.getDescription())) {
                    oldBookObj.setDescription(book.getDescription());
                    doUpdate = true;
                }

                if (StringUtils.hasLength(book.getIsbn())) {
                    oldBookObj.setIsbn(book.getIsbn());
                    doUpdate = true;
                }

                if (StringUtils.hasLength(book.getName())) {
                    oldBookObj.setName(book.getName());
                    doUpdate = true;
                }

                if (StringUtils.hasLength(book.getClassification().name())) {
                    oldBookObj.setClassification(book.getClassification());
                    doUpdate = true;
                }

                if (book.getPrice() != null) {
                    oldBookObj.setPrice(book.getPrice());
                    doUpdate = true;
                }

                if (doUpdate) {
                    bookStoreRepository.save(oldBookObj);
                }
                return new ResponseEntity<>("Updated Successfully", HttpStatus.NO_CONTENT);
            } else {
                return new ResponseEntity<>("Unable to find book with the given id", HttpStatus.NOT_FOUND);
            }
        } catch (Exception ex) {
            return new ResponseEntity<>("Failed due to bad input", HttpStatus.BAD_REQUEST);
        }
    }

    private Optional<Book> getBook(Integer id) {
        return bookStoreRepository.findById(id);
    }
}
