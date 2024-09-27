package com.example.bookapplication.controllers;

import com.example.bookapplication.dto.AuthorDTO;
import com.example.bookapplication.service.AuthorService;
import com.example.bookapplication.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/author")
@Tag(name = "Author Controller", description = "REST APIs для сущности Автор")
public class AuthorController {
    final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/saveAuthor")
    @Operation(summary = "saveAuthor", description = "Сохраняет автора")
    public ResponseEntity<String> saveAuthor(@RequestBody AuthorDTO author,
                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new ResponseEntity<>(ResponseUtils.getErrorsFromBindingResult(bindingResult),
                    HttpStatus.BAD_REQUEST);
        }
        authorService.saveAuthor(author);
        return ResponseEntity.ok("Author has been added");
    }

    @GetMapping("/getAuthor")
    @Operation(summary = "getAuthor", description = "Возвращает автора по имени")
    public ResponseEntity<AuthorDTO> getAuthor(@RequestParam String name) {
        AuthorDTO author = authorService.getAuthorByName(name);
        if (author == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
}
