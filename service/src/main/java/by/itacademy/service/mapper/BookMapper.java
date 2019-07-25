package by.itacademy.service.mapper;

import by.itacademy.database.dto.BookDto;
import by.itacademy.database.entity.Book;
import by.itacademy.service.service.AuthorService;
import by.itacademy.service.service.BookService;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;

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
                bookDto.getAuthorId().stream()
                        .map(id -> authorService.getById(id))
                        .collect(Collectors.toSet())
        );
        return book;
    }
}
