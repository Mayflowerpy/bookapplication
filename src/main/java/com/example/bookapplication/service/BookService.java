package com.example.bookapplication.service;

import com.example.bookapplication.dto.BookDTO;

import java.util.List;

public interface BookService {
    void saveBook(BookDTO book);
    List<BookDTO> getBooksByAuthorName(String authorName);
    List<BookDTO> getAllBooks();
}
