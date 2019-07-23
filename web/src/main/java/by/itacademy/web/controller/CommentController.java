package by.itacademy.web.controller;

import by.itacademy.database.dto.CommentDto;
import by.itacademy.service.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static by.itacademy.web.path.UrlPath.COMMENT;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(COMMENT)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private final CommentService commentService;

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<CommentDto> getAllBookComments(@RequestBody Long bookId) {
        return commentService.getAllBookComments(bookId);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public void deleteComment(@RequestBody Long commentId) {
        System.out.println();
        commentService.deleteComment(commentId);
    }
}
