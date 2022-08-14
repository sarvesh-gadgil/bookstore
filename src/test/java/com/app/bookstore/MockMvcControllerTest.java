package com.app.bookstore;

import com.app.bookstore.controller.BookStoreController;
import com.app.bookstore.model.Book;
import com.app.bookstore.service.impl.BookStoreServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookStoreController.class)
@Import(BookStoreController.class)
public class MockMvcControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookStoreServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void bookServiceShouldCreateBook() throws Exception {

        Book book = new Book();
        when(service.createBook(book))
                .thenReturn(null);
        this.mockMvc.perform(post("/books")
                        .content(objectMapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void bookServiceShouldGetAllBooks() throws Exception {
        when(service.getAllBooks())
                .thenReturn(null);
        this.mockMvc.perform(get("/books")).andExpect(status().isOk());
    }

    @Test
    public void bookServiceShouldGetBookById() throws Exception {
        when(service.getBookById(1))
                .thenReturn(null);
        this.mockMvc.perform(get("/books/1")).andExpect(status().isOk());
    }

    @Test
    public void bookServiceShouldUpdateAllBookDetails() throws Exception {
        Book book = new Book();
        when(service.updateAllBookDetails(book, 1))
                .thenReturn(null);
        this.mockMvc.perform(put("/books/1")
                        .content(objectMapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void bookServiceShouldDeleteBook() throws Exception {
        Book book = new Book();
        when(service.deleteBook(book, 1))
                .thenReturn(null);
        this.mockMvc.perform(delete("/books/1")
                        .content(objectMapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void bookServiceShouldUpdateBookDetails() throws Exception {
        Book book = new Book();
        when(service.updateBookDetails(book, 1))
                .thenReturn(null);
        this.mockMvc.perform(patch("/books/1")
                        .content(objectMapper.writeValueAsString(book))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
