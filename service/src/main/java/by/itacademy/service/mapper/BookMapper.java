package by.itacademy.service.mapper;

import by.itacademy.database.dto.BookDto;
import by.itacademy.database.entity.Author;
import by.itacademy.database.entity.Book;
import by.itacademy.service.service.AuthorService;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper
public abstract class BookMapper {

    private AuthorService authorService;

    @Autowired
    public final void setAuthorService(AuthorService authorService) {
        this.authorService = authorService;
    }

    public Book toEntity(BookDto bookDto) {
        if (bookDto == null) {
            return null;
        }
        Book book = new Book();

        book.setName(bookDto.getName());
        book.setGenre(bookDto.getGenre());
        book.setAbout(bookDto.getAbout());
        book.setImage(bookDto.getImage());
        book.setPrice(bookDto.getPrice());
        book.setAuthors(
                getAuthorCollection(bookDto)
        );
        return book;
    }

    public Book updateEntity(BookDto bookDto, @MappingTarget Book book) {
        if (bookDto == null) {
            return book;
        }
        if (!bookDto.getName().isEmpty()) {
            book.setName(bookDto.getName());
        }
        if (!bookDto.getAbout().isEmpty()) {
            book.setAbout(bookDto.getAbout());
        }
        if (!bookDto.getImage().isEmpty()) {
            book.setImage(bookDto.getImage());
        }
        if (bookDto.getGenre() != null) {
            book.setGenre(bookDto.getGenre());
        }
        if (bookDto.getPrice() != null) {
            book.setPrice(bookDto.getPrice());
        }
        if (bookDto.getAuthorId().size() > 0) {
            book.setAuthors(
                    getAuthorCollection(bookDto)
            );
        }

        return book;
    }

    private Set<Author> getAuthorCollection(BookDto bookDto) {
        return bookDto.getAuthorId().stream()
                .map(id -> authorService.getById(id))
                .collect(Collectors.toSet());
    }
}
