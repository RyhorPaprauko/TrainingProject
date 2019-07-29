package by.itacademy.database.entity;

import by.itacademy.database.BaseTest;
import by.itacademy.database.repository.BookRepository;
import by.itacademy.database.repository.BookingRepository;
import by.itacademy.database.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

public class BookingTest extends BaseTest {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BookRepository bookRepository;

    @Test
    public void checkSaveBooking() {
        Booking booking = Booking.builder()
                .user(userRepository.getOne(1L))
                .books(bookRepository.findAll())
                .completed(false)
                .processed(false)
                .build();

        bookingRepository.save(booking);
        Booking savedBooking = bookingRepository.getOne(booking.getId());
        assertNotNull(savedBooking);
        assertThat(savedBooking, equalTo(booking));
        assertThat(savedBooking.getUser(), equalTo(booking.getUser()));
        assertThat(savedBooking.getBooks(), equalTo(booking.getBooks()));
        assertThat(savedBooking.getCompleted(), equalTo(booking.getCompleted()));
        assertThat(savedBooking.getProcessed(), equalTo(booking.getProcessed()));
    }

    @Test
    public void checkGetBooking() {
        Booking booking = bookingRepository.getOne(1L);

        assertNotNull(booking);
        assertThat(booking.getBooks(), hasSize(2));
        assertThat(booking.isCompleted(), equalTo(false));
        assertThat(booking.isProcessed(), equalTo(false));
        assertThat(booking.getTotalPrice(), equalTo(50.0));
    }
}
