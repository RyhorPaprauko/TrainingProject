package by.itacademy.database.repository;

import by.itacademy.database.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Optional<Booking> findByUserLoginAndCompletedFalse(String username);
}
