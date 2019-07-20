package by.itacademy.database.repository;

import by.itacademy.database.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.awt.print.Pageable;
import java.util.List;
import java.util.function.Predicate;

public interface BookRepository extends JpaRepository<Book, Long>, QuerydslPredicateExecutor<Book> {
}
