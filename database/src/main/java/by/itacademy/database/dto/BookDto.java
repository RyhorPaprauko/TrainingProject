package by.itacademy.database.dto;

import by.itacademy.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BookDto implements MarkerDto {

    private Long id;
    private String name;
    private String about;
    private String image;
    private Genre genre;
    private Integer price;
}
