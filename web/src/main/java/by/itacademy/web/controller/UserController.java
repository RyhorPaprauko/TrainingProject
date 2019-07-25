package by.itacademy.web.controller;

import by.itacademy.database.entity.User;
import by.itacademy.service.service.UserService;
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

import static by.itacademy.web.util.UrlPath.ADMIN;
import static by.itacademy.web.util.UrlPath.USER;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(USER)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private UserService userService;

    @GetMapping(produces = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }


    @PostMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("#id == authentication.principal.id")
    public User updateUser(@PathVariable(value = "id") Long id, @RequestBody User user) {
        return userService.updateUser(user);
    }

    @PutMapping(value = ADMIN, consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public User saveNewAdmin(@RequestBody User user) {
        return userService.saveAdmin(user).orElse(null);
    }


    @DeleteMapping(value = "/{id}", consumes = APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasAuthority('ADMIN')")
    public void deleteUser(@PathVariable(value = "id") Long id) {
        userService.deleteUser(id);
    }
}
