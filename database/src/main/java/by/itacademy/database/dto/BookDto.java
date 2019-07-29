package by.itacademy.database.dto;

import by.itacademy.database.entity.enam.Genre;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
public class BookDto {

    private Long id;
    private String name;
    private String about;
    private String image;
    private Genre genre;
    private Integer price;
    private Set<Long> authorId = new HashSet<>();
}
