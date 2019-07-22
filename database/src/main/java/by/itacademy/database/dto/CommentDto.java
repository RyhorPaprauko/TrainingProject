package by.itacademy.database.dto;

import lombok.Data;

@Data
public class CommentDto implements MarkerDto {

    private Long id;
    private String text;
    private String authorLogin;
}
