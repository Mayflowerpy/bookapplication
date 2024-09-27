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
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    @Mapping(target = "authorList", source = "authorList", qualifiedByName = "mapAuthorsToAuthorDTOs")
    BookDTO toDTO(Book book);

    @Mapping(target = "authorList", source = "authorList", qualifiedByName = "mapAuthorDTOsToAuthors")
    Book toEntity(BookDTO bookDTO);

    List<BookDTO> toDTOList(List<Book> books);

    @Named("mapAuthorsToAuthorDTOs")
    default Set<AuthorDTO> mapAuthorsToAuthorDTOs(Set<Author> authors) {
        return authors.stream()
                .map(AuthorMapper.MAPPER::toDTO)
                .collect(Collectors.toSet());
    }

    @Named("mapAuthorDTOsToAuthors")
    default Set<Author> mapAuthorDTOsToAuthors(Set<AuthorDTO> authorDTOs) {
        return authorDTOs.stream()
                .map(AuthorMapper.MAPPER::toEntity)
                .collect(Collectors.toSet());
    }

}
