package com.example.bookapplication.mapper;

import com.example.bookapplication.dto.AuthorDTO;
import com.example.bookapplication.dto.BookDTO;
import com.example.bookapplication.entity.Author;
import com.example.bookapplication.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
@Component
public interface AuthorMapper {

    AuthorMapper MAPPER = Mappers.getMapper(AuthorMapper.class);
    @Mapping(target = "bookList", source ="bookList", qualifiedByName = "mapBooksToBookDTOs")
    AuthorDTO toDTO(Author author);

    @Mapping(target = "bookList", source = "bookList", qualifiedByName = "mapBookDTOsToBooks")
    Author toEntity(AuthorDTO authorDTO);

    List<AuthorDTO> toDTOList(List<Author> authors);

    @Named("mapBooksToBookDTOs")
    default Set<BookDTO> mapBooksToBookDTOs(Set<Book> books) {
        return books.stream()
                .map(BookMapper.MAPPER::toDTO)
                .collect(Collectors.toSet());
    }

    @Named("mapBookDTOsToBooks")
    default Set<Book> mapBookDTOsToBooks(Set<BookDTO> bookDTOs) {
        return bookDTOs.stream()
                .map(BookMapper.MAPPER::toEntity)
                .collect(Collectors.toSet());
    }
}
