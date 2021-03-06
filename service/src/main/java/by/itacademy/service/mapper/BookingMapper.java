package by.itacademy.service.mapper;

import by.itacademy.database.dto.BookingDto;
import by.itacademy.database.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BookingMapper {

    @Mapping(target = "totalPrice", expression = "java(booking.getTotalPrice())")
    @Mapping(target = "username", source = "booking.user.login")
    BookingDto toDto(Booking booking);
}
