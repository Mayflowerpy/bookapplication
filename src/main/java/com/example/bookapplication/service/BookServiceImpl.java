package com.example.bookapplication.service;

import com.example.bookapplication.dto.BookDTO;
import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.mapper.BookMapper;
import com.example.bookapplication.repository.AuthorRepository;
import com.example.bookapplication.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookServiceImpl(BookMapper bookMapper, BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookMapper = bookMapper;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public void saveBook(BookDTO bookDTO) {
        bookRepository.save(bookMapper.toEntity(bookDTO));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getBooksByAuthorName(String authorName) {
        return bookMapper.toDTOList(bookRepository.findBooksByAuthorName(authorName));
    }

    @Override
    @Transactional(readOnly = true)
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO)
                .collect(Collectors.toList());
    }
}
