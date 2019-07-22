package by.itacademy.web.controller;


import by.itacademy.database.dto.UserDto;
import by.itacademy.service.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static by.itacademy.web.path.UrlPath.LOGIN;
import static by.itacademy.web.path.UrlPath.REGISTRATION;

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
        model.addAttribute("user", new UserDto());
        return "registration";
    }

    @PostMapping(REGISTRATION)
    public String registration(UserDto userDto) {
        return userService.saveUser(userDto)
                .map(user -> "redirect:/login?success=true")
                .orElse("redirect:/registration");
    }
}

