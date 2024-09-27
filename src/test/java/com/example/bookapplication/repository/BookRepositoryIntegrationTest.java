package com.example.bookapplication.repository;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Testcontainers
public class BookRepositoryIntegrationTest {

    @Container
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:15-alpine");

    @DynamicPropertySource
    static void configureProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        authorRepository.deleteAll();

        Author author = new Author();
        author.setName("Author");
        authorRepository.saveAndFlush(author);

        Book book = new Book();
        Author author2 = new Author();
        author2.setName("Author2");
        book.setTitle("Book");
        book.getAuthorList().add(author2);
        bookRepository.saveAndFlush(book);
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = bookRepository.findAll();
        assertThat(books).isNotEmpty();
    }

    @Test
    void testFindByTitle() {
        Optional<Book> book = bookRepository.findByTitle("Book");
        assertThat(book.isPresent());
    }

    @Test
    void testFindBooksByAuthor() {
        List<Book> books = bookRepository.findBooksByAuthorName("Author2");
        assertThat(books).isNotEmpty();
        assertThat(books.get(0).getTitle()).isEqualTo("Book");
    }
}