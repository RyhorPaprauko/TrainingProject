package by.itacademy.web.controller;

import by.itacademy.database.entity.Comment;
import by.itacademy.service.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.itacademy.web.path.UrlPath.COMMENTS;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(COMMENTS)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentController {

    private CommentService commentService;

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public Comment saveComment(@RequestBody Comment comment) {
        return commentService.saveComment(comment);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN') or #comment.user.id == authentication.principal.id")
    public void deleteComment(@RequestBody Comment comment) {
        commentService.deleteComment(comment);
    }
}
