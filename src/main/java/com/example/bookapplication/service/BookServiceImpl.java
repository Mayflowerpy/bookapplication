package com.example.bookapplication.service;

import com.example.bookapplication.entity.Book;
import com.example.bookapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findBooksByAuthorName(authorName);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
