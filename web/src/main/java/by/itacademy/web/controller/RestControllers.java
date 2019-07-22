package by.itacademy.web.controller;

import by.itacademy.database.dto.CatalogDto;
import by.itacademy.database.dto.CatalogFilterDto;
import by.itacademy.database.dto.CommentDto;
import by.itacademy.database.entity.Comment;
import by.itacademy.service.service.BookService;
import by.itacademy.service.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static by.itacademy.web.path.UrlPath.COMMENT;
import static by.itacademy.web.path.UrlPath.REST_BOOK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestControllers {

    private final BookService bookService;
    private final CommentService commentService;

    @RequestMapping(REST_BOOK)
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public CatalogDto getAllFilteredBooks(@RequestBody CatalogFilterDto filter) {
        return bookService.getFilteredCatalog(filter);
    }

    @RequestMapping(COMMENT)
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public List<CommentDto> getAllBookComments(@RequestBody Long bookId) {
        return commentService.getAllBookComments(bookId);
    }
}
