package by.itacademy.web.controller;

import by.itacademy.service.service.BookService;
import by.itacademy.service.service.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;

import static by.itacademy.web.path.UrlPath.BOOK;

@Controller
@RequestMapping(BOOK)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {

    private final BookService bookService;
    private final RoleService roleService;

    @GetMapping("/{id}")
    public String getPage(Model model,
                          @PathVariable(value = "id") Long id,
                          @AuthenticationPrincipal UserDetails currentUser) {
        if (Objects.nonNull(currentUser)) {
            model.addAttribute("username", currentUser.getUsername());
            model.addAttribute("isAdmin", currentUser.getAuthorities()
                    .contains(roleService.getByRole("ADMIN")));
        }
        model.addAttribute("book", bookService.findById(id).orElseThrow(RuntimeException::new));

        return "book";
    }
}
