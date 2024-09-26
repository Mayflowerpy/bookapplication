package com.example.bookapplication.controllers;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import com.example.bookapplication.service.BookService;
import com.example.bookapplication.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/getBooksByAuthor")
    public ResponseEntity<List<Book>> getBooksByAuthor(@RequestBody Author author) {
        return new ResponseEntity<>(bookService.getBooksByAuthor(author), HttpStatus.OK);
    }

    @PostMapping("/saveBook")
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
