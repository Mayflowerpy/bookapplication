package com.example.bookapplication.repository;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByAuthor(Author author);
}
