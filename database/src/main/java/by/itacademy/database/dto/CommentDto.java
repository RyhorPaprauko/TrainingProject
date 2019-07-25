package by.itacademy.database.dto;

import lombok.Data;

@Data
public class CommentDto {

    private Long id;
    private String text;
    private String authorLogin;
    private Long bookId;
}
