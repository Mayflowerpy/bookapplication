package com.example.bookapplication.service;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public void saveBook(Book book) {
        bookRepository.saveAndFlush(book);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getBooksByAuthor(Author author) {
        return bookRepository.getBooksByAuthor(author);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
}
