package by.itacademy.web.controller;


import by.itacademy.database.entity.User;
import by.itacademy.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static by.itacademy.web.util.UrlPath.ADMIN;
import static by.itacademy.web.util.UrlPath.LOGIN;
import static by.itacademy.web.util.UrlPath.REGISTRATION;

@Controller
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class LoginAndRegisterController {

    private UserService userService;

    @GetMapping(LOGIN)
    public String getLoginPage() {
        return "login";
    }

    @GetMapping(REGISTRATION)
    public String getRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping(REGISTRATION)
    public String registration(User user) {
        return userService.saveUser(user)
                .map(it -> "redirect:/login?success=true")
                .orElse("redirect:/registration");
    }

    @GetMapping(REGISTRATION + ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdminRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "admin-registration";
    }

    @PostMapping(REGISTRATION + ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminRegistration(User user) {
        return userService.saveAdmin(user)
                .map(it -> "redirect:/login?success=true")
                .orElse("redirect:/registration/admin");
    }
}

