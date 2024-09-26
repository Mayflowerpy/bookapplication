package com.example.bookapplication.service;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;

import java.util.List;

public interface BookService {
    void saveBook(Book book);

    List<Book> getBooksByAuthor(Author author);

    List<Book> getAllBooks();
}
