package com.example.bookapplication.service;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void saveAuthor(Author author) {
        authorRepository.saveAndFlush(author);
    }

    @Override
    @Transactional(readOnly = true)
    public Author getAuthorByName(String name) {
        return authorRepository.getAuthorByName(name);
    }
}
