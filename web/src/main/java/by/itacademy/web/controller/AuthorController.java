package by.itacademy.web.controller;

import by.itacademy.database.entity.Author;
import by.itacademy.service.service.AuthorService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.itacademy.web.path.UrlPath.AUTHOR;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(AUTHOR)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorController {

    private AuthorService authorService;

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    public Iterable<Author> getAllAuthors() {
        return authorService.getAll();
    }

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Author saveNewAuthor(@RequestBody Author author) {
        return authorService.saveAuthor(author);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_UTF8_VALUE)
    public Author getAuthorById(@PathVariable(value = "id") Long id) {
        return authorService.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Author updateAuthor(@PathVariable(value = "id") Long id, @RequestBody Author author) {
        return authorService.updateAuthor(author);
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteAuthor(@PathVariable(value = "id") Long id) {
        authorService.delete(id);
    }
}