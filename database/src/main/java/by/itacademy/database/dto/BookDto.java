package by.itacademy.database.dto;

import by.itacademy.database.entity.Author;
import by.itacademy.database.entity.enam.Genre;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
