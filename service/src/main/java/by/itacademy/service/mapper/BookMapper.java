package by.itacademy.service.mapper;

import by.itacademy.database.dto.BookDto;
import by.itacademy.database.entity.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper extends BaseMapper<Book, BookDto> {
}
