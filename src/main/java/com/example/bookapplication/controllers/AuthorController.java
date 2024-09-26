package com.example.bookapplication.controllers;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.service.AuthorService;
import com.example.bookapplication.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/author")
public class AuthorController {
    final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/saveAuthor")
    public ResponseEntity<String> saveAuthor(@RequestBody Author author,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(ResponseUtils.getErrorsFromBindingResult(bindingResult),
                    HttpStatus.BAD_REQUEST);
        }
        authorService.saveAuthor(author);
        return ResponseEntity.ok("Author has been added");
    }

    @GetMapping("/getAuthor")
    public ResponseEntity<Author> getAuthor(@RequestParam String name) {
        return new ResponseEntity<>(authorService.getAuthorByName(name), HttpStatus.OK);
    }
}
