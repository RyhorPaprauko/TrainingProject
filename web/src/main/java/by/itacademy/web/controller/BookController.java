package by.itacademy.web.controller;

import by.itacademy.database.dto.FilterDto;
import by.itacademy.database.entity.Book;
import by.itacademy.service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.itacademy.web.path.UrlPath.BOOK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(BOOK)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private BookService bookService;

    @GetMapping(produces = APPLICATION_JSON_VALUE)
    public Iterable<Book> getAllBooks() {
        return bookService.getAll();
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_VALUE)
    public Iterable<Book> getAllFilteredBooks(FilterDto filter) {
        return bookService.getAllFiltered(filter);
    }

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_VALUE)
    public Book saveNewBook(Book book) {
        return bookService.saveBook(book);
    }

    @GetMapping(value = "/{id}", produces = APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable(value = "id") Long id) {
        return bookService.findById(id).orElse(null);
    }

    @PutMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_VALUE)
    public Book upgradeBook(@PathVariable(value = "id") Long id, Book book) {
        return bookService.saveBook(book);
    }

    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void deleteBook(@PathVariable(value = "id") Long id) {
        bookService.delete(id);
    }
}
