package by.itacademy.database.entity;

import by.itacademy.database.BaseTest;
import by.itacademy.database.repository.BookRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static by.itacademy.database.entity.enam.Genre.CLASSIC;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class BookTest extends BaseTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void checkSaveBook() {
        Book book = Book.builder()
                .name("Demons")
                .about("XIX century")
                .price(30)
                .genre(CLASSIC)
                .image("images/demons.jpg")
                .build();

        bookRepository.save(book);
        assertNotNull(bookRepository.getOne(book.getId()));
    }

    @Test
    public void checkGetBook() {
        Book book = bookRepository.getOne(1L);
        assertNotNull(book);
        assertThat(book.getName(), equalTo("Anchar"));
        assertThat(book.getAuthors(), hasSize(1));
        assertThat(book.getAuthors().iterator().next().getSurname(), equalTo("Pushkin"));
    }
}
