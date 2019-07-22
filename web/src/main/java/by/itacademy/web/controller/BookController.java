package by.itacademy.web.controller;

import by.itacademy.service.service.BookService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import static by.itacademy.web.path.UrlPath.BOOK;

@Controller
@RequestMapping(BOOK)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private final BookService bookService;

    @GetMapping("/{id}")
    public String getPage(Model model,
                          @PathVariable(value = "id") Long id) {
        return bookService.findById(id)
                .map(model::addAttribute)
                .map(it -> "book")
                .orElse("redirect:/");
    }
}
