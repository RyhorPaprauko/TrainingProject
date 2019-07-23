package by.itacademy.database.entity;

import by.itacademy.database.BaseTest;
import by.itacademy.database.repository.RoleRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class RoleTest extends BaseTest {

    @Autowired
    private RoleRepository roleRepository;

    @Test
    public void checkSaveRole() {
        Role role = Role.builder()
                .role("ADMIN")
                .build();

        roleRepository.save(role);
        assertNotNull(roleRepository.getOne(role.getId()));
    }

    @Test
    public void checkGetRole() {
        Role role = roleRepository.getOne(1L);

        assertNotNull(role);
        assertThat(role.getRole(), equalTo("ADMIN"));
    }
}
