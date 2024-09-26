package com.example.bookapplication.service;

import com.example.bookapplication.entity.Book;

import java.util.List;

public interface BookService {
    void saveBook(Book book);
    List<Book> getBooksByAuthorName(String authorName);
    List<Book> getAllBooks();
}
