package by.itacademy.service.mapper;

import by.itacademy.database.dto.CommentDto;
import by.itacademy.database.entity.Comment;
import by.itacademy.service.service.BookService;
import by.itacademy.service.service.UserService;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper
public abstract class CommentMapper {

    private UserService userService;
    private BookService bookService;

    @Autowired
    public final void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public final void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @Mapping(target = "authorLogin", source = "comment.user.login")
    @Mapping(target = "bookId", source = "comment.book.id")
    public abstract CommentDto toDto(Comment comment);

    public Comment toEntity(CommentDto commentDto) {
        if (commentDto == null) {
            return null;
        }
        Comment comment = new Comment();
        comment.setText(commentDto.getText());
        comment.setUser(
                userService.findByLogin(commentDto.getAuthorLogin()));
        comment.setBook(
                bookService.findById(commentDto.getBookId()));
        return comment;
    }
}
