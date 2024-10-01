package com.example.bookapplication.controllers;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.service.AuthorGraphQLService;
import com.example.bookapplication.service.BookGraphQLService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
public class GraphQLResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final AuthorGraphQLService authorService;
    private final BookGraphQLService bookService;

    @Autowired
    public GraphQLResolver(AuthorGraphQLService authorService, BookGraphQLService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    // Query resolvers
    public List<Author> authors() {
        return authorService.getAllAuthors();
    }

    public List<Book> books() {
        return bookService.getAllBooks();
    }

    public Author author(Long id) {
        return authorService.getAuthorById(id);
    }

    public Book book(Long id) {
        return bookService.getBookById(id);
    }

    public List<Book> getBooksByAuthor(String authorName) {
        return bookService.getBooksByAuthorName(authorName);
    }

    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    public Author getAuthor(String name) {
        return authorService.getAuthorByName(name);
    }

    // Mutation resolvers
    public Book saveBook(String title, List<Author> authorInputs) {
        List<Author> authors = authorInputs.stream()
                .map(authorInput -> {
                    if (authorInput.getId() != null) {
                        return authorService.getAuthorById(authorInput.getId());
                    } else {
                        return authorService.saveAuthor(authorInput.getName(), new ArrayList<>());
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return bookService.saveBook(title, authors);
    }

    public Author saveAuthor(String name, List<Book> bookInputs) {
        List<Book> books = bookInputs.stream()
                .map(bookInput -> {
                    if (bookInput.getId() != null) {
                        return bookService.getBookById(bookInput.getId());
                    } else {
                        return bookService.saveBook(bookInput.getTitle(), new ArrayList<>());
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
        return authorService.saveAuthor(name, books);
    }
}
