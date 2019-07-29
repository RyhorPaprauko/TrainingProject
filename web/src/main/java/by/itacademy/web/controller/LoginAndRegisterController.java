package by.itacademy.web.controller;


import by.itacademy.database.dto.RegistrationDto;
import by.itacademy.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

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
    public String getRegistrationPage(RegistrationDto registrationDto) {
        return "registration";
    }

    @PostMapping(REGISTRATION)
    public String registration(@ModelAttribute @Valid RegistrationDto userDto, BindingResult bindingResult) {
        System.out.println();

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        return userService.saveUser(userDto)
                .map(it -> "redirect:/login?success=true")
                .orElse("redirect:/registration");
    }

    @GetMapping(REGISTRATION + ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAdminRegistrationPage(RegistrationDto registrationDto) {
        return "admin-registration";
    }

    @PostMapping(REGISTRATION + ADMIN)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminRegistration(@ModelAttribute @Valid RegistrationDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration/admin";
        }

        return userService.saveAdmin(userDto)
                .map(it -> "redirect:/login?success=true")
                .orElse("redirect:/registration/admin");
    }
}

