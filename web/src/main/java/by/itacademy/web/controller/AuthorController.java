package by.itacademy.web.controller;

import by.itacademy.database.entity.Author;
import by.itacademy.service.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static by.itacademy.web.util.UrlPath.AUTHOR;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(AUTHOR)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorController {

    private final AuthorService authorService;

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public List<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public void addAuthor(@RequestBody Author author) {
        authorService.saveAuthor(author);
    }

    @DeleteMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public void deleteAuthor(@RequestBody Long authorId) {
        authorService.deleteAuthor(authorId);
    }
}
