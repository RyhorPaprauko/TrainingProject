package by.itacademy.web.controller;

import by.itacademy.database.dto.BookingDto;
import by.itacademy.database.dto.BuyDto;
import by.itacademy.service.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static by.itacademy.web.util.UrlPath.BOOKING;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8_VALUE;

@RestController
@RequestMapping(BOOKING)
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookingController {

    private final BookingService bookingService;

    @PutMapping(value = "/add", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void addBookToBooking(@RequestBody BuyDto buyDto) {
        bookingService.addBookToUserBooking(buyDto);
    }

    @DeleteMapping(value = "/remove", consumes = APPLICATION_JSON_UTF8_VALUE)
    public void removeBookFromBooking(@RequestBody BuyDto buyDto) {
        bookingService.removeBookFromUserBooking(buyDto);
    }

    @PostMapping(consumes = APPLICATION_JSON_UTF8_VALUE, produces = APPLICATION_JSON_UTF8_VALUE)
    public BookingDto getUserBooking(@RequestBody String login) {
        return bookingService.getUserBookingDto(login);
    }

    @PutMapping(consumes = APPLICATION_JSON_UTF8_VALUE)
    public void completeUserBooking(@RequestBody String login) {
        bookingService.completeUserBooking(login);
    }
}
