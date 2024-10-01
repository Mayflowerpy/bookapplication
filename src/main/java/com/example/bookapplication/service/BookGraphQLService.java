package com.example.bookapplication.service;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.repository.BookRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BookGraphQLService {

    private final BookRepository bookRepository;

    @Autowired
    public BookGraphQLService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    public List<Book> getBooksByAuthorName(String authorName) {
        return bookRepository.findBooksByAuthorName(authorName);
    }

    public Book saveBook(String title, List<Author> authorList) {
        Book book = new Book();
        book.setTitle(title);
        book.setAuthorList(authorList);
        return bookRepository.save(book);
    }
}
