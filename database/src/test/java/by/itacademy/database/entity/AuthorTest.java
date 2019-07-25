package by.itacademy.database.entity;

import by.itacademy.database.BaseTest;
import by.itacademy.database.repository.AuthorRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class AuthorTest extends BaseTest {

    @Autowired
    private AuthorRepository authorRepository;

    @Test
    public void checkSaveAuthor() {
        Author author = Author.builder()
                .name("Федор")
                .surname("Достоевский")
                .bio("1830-1900")
                .build();

        authorRepository.save(author);
        assertNotNull(authorRepository.getOne(author.getId()));
    }

    @Test
    public void checkGetAuthor() {
        Author author = authorRepository.getOne(1L);
        assertNotNull(author);
        assertThat(author.getName(), equalTo("Alexander"));
        assertThat(author.getBio(), equalTo("Molodec"));
        assertThat(author.getSurname(), equalTo("Pushkin"));
    }
}