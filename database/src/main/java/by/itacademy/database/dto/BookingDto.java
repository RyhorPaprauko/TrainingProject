package by.itacademy.database.dto;

import by.itacademy.database.entity.Book;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BookingDto {

    private Long id;
    private String username;
    private List<Book> books = new ArrayList<>();
    private Double totalPrice;
}
