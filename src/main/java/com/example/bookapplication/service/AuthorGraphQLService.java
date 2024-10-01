package com.example.bookapplication.service;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.repository.AuthorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AuthorGraphQLService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorGraphQLService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }

    public Author getAuthorByName(String name) {
        return authorRepository.findByName(name).orElse(null);
    }

    public Author saveAuthor(String name, List<Book> bookList) {
        Author author = new Author();
        author.setName(name);
        author.setBookList(bookList);
        return authorRepository.save(author);
    }
}
