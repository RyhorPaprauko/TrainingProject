package by.itacademy.web.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import static by.itacademy.web.util.UrlPath.ADMIN;
import static by.itacademy.web.util.UrlPath.ORDER;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class OrderPageController {

    @GetMapping(ORDER)
    @PreAuthorize("isAuthenticated()")
    public String getPage(Model model, @AuthenticationPrincipal UserDetails currentUser) {
        model.addAttribute("username", currentUser.getUsername());
        return "order";
    }

    @GetMapping(ORDER + ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdminPage() {
        return "processed-order";
    }
}
