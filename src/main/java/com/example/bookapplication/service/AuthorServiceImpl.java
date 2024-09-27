package com.example.bookapplication.service;

import com.example.bookapplication.dto.AuthorDTO;
import com.example.bookapplication.mapper.AuthorMapper;
import com.example.bookapplication.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(AuthorMapper authorMapper, AuthorRepository authorRepository) {
        this.authorMapper = authorMapper;
        this.authorRepository = authorRepository;
    }

    @Transactional
    public void saveAuthor(AuthorDTO author) {
        authorRepository.saveAndFlush(authorMapper.toEntity(author));
    }

    @Override
    @Transactional(readOnly = true)
    public AuthorDTO getAuthorByName(String name) {
        return authorMapper.toDTO(authorRepository.findByName(name).orElse(null));
    }
}
