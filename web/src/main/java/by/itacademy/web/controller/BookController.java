package by.itacademy.web.controller;

import by.itacademy.database.dto.BookDto;
import by.itacademy.database.entity.Book;
import by.itacademy.database.entity.enam.Genre;
import by.itacademy.service.service.AuthorService;
import by.itacademy.service.service.BookService;
import by.itacademy.service.service.RoleService;
import by.itacademy.web.util.ImageLoader;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

import static by.itacademy.web.util.UrlPath.ADMIN;
import static by.itacademy.web.util.UrlPath.BOOK;

@Controller
@RequestMapping(BOOK)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private final BookService bookService;
    private final RoleService roleService;
    private final AuthorService authorService;

    @GetMapping("/{id}")
    public String getPage(Model model,
                          @PathVariable(value = "id") Long id,
                          @AuthenticationPrincipal UserDetails currentUser) {
        if (Objects.nonNull(currentUser)) {
            model.addAttribute("username", currentUser.getUsername());
            model.addAttribute("isAdmin", currentUser.getAuthorities()
                    .contains(roleService.getByRole("ADMIN")));
        }
        model.addAttribute("book", bookService.findById(id));

        return "book";
    }

    @GetMapping(ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAddBookPage(Model model) {
        model.addAttribute("genres", Genre.values());

        return "new-book";
    }

    @PostMapping(ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(@RequestParam MultipartFile file, BookDto bookDto) {
        bookDto.setImage(ImageLoader.load(file));
        Book book = bookService.saveBook(bookDto);
        return "redirect:/book/admin/" + book.getId();
    }

    @GetMapping(ADMIN + "/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getEditBookPage(Model model, @PathVariable(value = "id") Long id) {
        model.addAttribute("genres", Genre.values());
        model.addAttribute("book", bookService.findById(id));

        return "edit-book";
    }

    @PostMapping(ADMIN + "/update")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editBook(@RequestParam MultipartFile file, BookDto bookDto) {
        if (!file.isEmpty()) {
            bookDto.setImage(ImageLoader.load(file));
        }

        return "redirect:/book/" +
                bookService.updateBook(bookDto).getId();
    }
}
