package by.itacademy.database.entity;

import by.itacademy.database.BaseTest;
import by.itacademy.database.repository.RoleRepository;
import by.itacademy.database.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class UserTest extends BaseTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void checkSaveUser() {
        User user = User.builder()
                .login("login")
                .password("pass")
                .fullName("Ryhor")
                .contacts(Contacts.of("ryhor@mail.ru", "+375(25)906-47-10"))
                .build();

        userRepository.save(user);
        assertNotNull(userRepository.getOne(user.getId()));
    }

    @Test
    public void checkGetUser() {
        User user = userRepository.getOne(1L);
        assertNotNull(user);
        assertThat(user.getLogin(), equalTo("admin"));
        assertThat(user.getRoles().iterator().next().getRole(), equalTo("ADMIN"));
    }
}