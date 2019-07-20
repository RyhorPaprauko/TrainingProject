package by.itacademy.database.dto;

import by.itacademy.database.entity.Book;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CatalogDto {

    private List<Book> books;
    private Long totalCount;
}
