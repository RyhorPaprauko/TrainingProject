package by.itacademy.web.controller;

import by.itacademy.database.dto.CatalogDto;
import by.itacademy.database.dto.CatalogFilterDto;
import by.itacademy.service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.itacademy.web.util.UrlPath.REST_BOOK;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RestControllers {

    private final BookService bookService;

    @RequestMapping(REST_BOOK)
    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public CatalogDto getAllFilteredBooks(@RequestBody CatalogFilterDto filter) {
        return bookService.getFilteredCatalog(filter);
    }
}
