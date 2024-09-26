package com.example.bookapplication.repository;

import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b JOIN b.authorList a WHERE a = :author")
    List<Book> getBooksByAuthor(@Param("author") Author author);
}
