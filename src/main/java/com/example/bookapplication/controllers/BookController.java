package com.example.bookapplication.controllers;

import com.example.bookapplication.entity.Book;
import com.example.bookapplication.service.BookService;
import com.example.bookapplication.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
@Tag(name = "Book Controller", description = "REST APIs для сущности Книга")
public class BookController {
    final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    @Operation(summary = "getAllBooks", description = "Возвращает все книги")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getBooksByAuthor")
    @Operation(summary = "getBooksByAuthor", description = "Возвращает список книг по автору")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestParam String name) {
        List<Book> books = bookService.getBooksByAuthorName(name);
        if (books.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @PostMapping(value = "/saveBook", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "saveBook", description = "Сохраняет книгу")
    public ResponseEntity<String> saveBook(@RequestBody Book book,
                                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(ResponseUtils.getErrorsFromBindingResult(bindingResult),
                    HttpStatus.BAD_REQUEST);
        }
        bookService.saveBook(book);
        return ResponseEntity.ok("Book has been added");
    }
}
