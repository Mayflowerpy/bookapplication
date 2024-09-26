package com.example.bookapplication.service;


import com.example.bookapplication.entity.Author;

public interface AuthorService {
    void saveAuthor(Author author);

    Author getAuthorByName(String name);
}
