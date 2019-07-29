package by.itacademy.service.service;

import by.itacademy.database.dto.BookingDto;
import by.itacademy.database.dto.BuyDto;
import by.itacademy.database.entity.Booking;
import by.itacademy.database.repository.BookingRepository;
import by.itacademy.service.mapper.BookingMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingService {

    private final BookingRepository bookingRepository;
    private final BookingMapper mapper;
    private final UserService userService;
    private final BookService bookService;

    public Set<BookingDto> getAllCompletedBookings() {
        return bookingRepository.findByCompletedTrue().stream()
                .map(mapper::toDto)
                .collect(Collectors.toSet());
    }

    public BookingDto getUserBookingDto(String login) {
        return mapper.toDto(
                getUserBooking(login));
    }

    public void addBookToUserBooking(BuyDto buyDto) {
        Booking booking = getUserBooking(buyDto.getUsername());

        booking.getBooks().add(
                bookService.findById(
                        buyDto.getBookId()));

        bookingRepository.save(booking);
    }

    public void removeBookFromUserBooking(BuyDto buyDto) {
        Booking booking = getUserBooking(buyDto.getUsername());

        booking.getBooks().remove(
                bookService.findById(
                        buyDto.getBookId()));

        bookingRepository.save(booking);
    }

    public void completeUserBooking(String login) {
        Booking booking = getUserBooking(login);
        booking.setCompleted(true);

        bookingRepository.save(booking);
    }

    private Booking getUserBooking(String login) {
        return bookingRepository.findByUserLoginAndCompletedFalse(login)
                .orElse(Booking.builder()
                        .user(userService.findByLogin(login))
                        .completed(false)
                        .processed(false)
                        .build());
    }
}
