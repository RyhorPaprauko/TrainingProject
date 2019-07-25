package by.itacademy.web.controller;

import by.itacademy.database.entity.Book;
import by.itacademy.service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping("/api/book")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookRestController {

    private BookService bookService;

    @PostMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public Book updateBook(@PathVariable(value = "id") Long id, @RequestBody Book book) {
        return bookService.updateBook(book);
    }

    @GetMapping(value = "/{id}")
    public Book getBook(@PathVariable(value = "id") Long id) {
        return bookService.findById(id);
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.delete(id);
    }
}
