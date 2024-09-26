package com.example.bookapplication.service;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.repository.AuthorRepository;
import com.example.bookapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Transactional
    public void saveAuthor(Author author) {
        List<Book> books = author.getBookList().stream()
                .map(book -> bookRepository.findByTitle(book.getTitle()).orElseGet(() -> bookRepository.save(book)))
                .collect(Collectors.toList());
        author.setBookList(books);
        authorRepository.saveAndFlush(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }
}
