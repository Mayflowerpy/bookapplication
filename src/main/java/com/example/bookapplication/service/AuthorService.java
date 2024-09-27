package com.example.bookapplication.service;

import com.example.bookapplication.dto.AuthorDTO;

public interface AuthorService {
    void saveAuthor(AuthorDTO author);
    AuthorDTO getAuthorByName(String name);
}
