package com.example.bookapplication.repository;

import com.example.bookapplication.entity.Book;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    //    @EntityGraph(attributePaths = "authorList")
    @Query("SELECT b FROM Book b JOIN b.authorList a WHERE a.name = :name")
    List<Book> findBooksByAuthorName(@Param("name") String name);
    Optional<Book> findByTitle(String title);
}
