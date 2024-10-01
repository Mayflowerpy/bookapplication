package com.example.bookapplication.controllers;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.service.AuthorGraphQLService;
import com.example.bookapplication.service.BookGraphQLService;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GraphQLResolver implements GraphQLQueryResolver, GraphQLMutationResolver {

    private final AuthorGraphQLService authorService;
    private final BookGraphQLService bookService;

    @Autowired
    public GraphQLResolver(AuthorGraphQLService authorService, BookGraphQLService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    public List<Book> books() {
        return bookService.getAllBooks();
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

    public Book saveBook(String title, List<Author> authorList) {
        return bookService.saveBook(title, authorList);
    }

    public Author saveAuthor(String name, List<Book> bookList) {
        return authorService.saveAuthor(name, bookList);
    }
}