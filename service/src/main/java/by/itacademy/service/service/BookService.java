package by.itacademy.service.service;

import by.itacademy.database.dto.BookDto;
import by.itacademy.database.dto.CatalogDto;
import by.itacademy.database.dto.CatalogFilterDto;
import by.itacademy.database.entity.Book;
import by.itacademy.database.repository.BookRepository;
import by.itacademy.service.filter.CatalogExpressionBuilder;
import by.itacademy.service.mapper.BookMapper;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static by.itacademy.database.entity.QBook.book;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper mapper;

    public Book saveBook(BookDto bookDto) {
        return bookRepository.save(
                mapper.toEntity(bookDto));
    }

    public Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Book.class.getName()));
    }

    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public CatalogDto getFilteredCatalog(CatalogFilterDto filter) {
        BooleanExpression expression = getFilterExpression(filter);
        Pageable pageable = PageRequest.of(filter.getPage(), filter.getLimit());

        return CatalogDto.builder()
                .books(bookRepository.findAll(expression, pageable).getContent())
                .totalCount(bookRepository.count(expression))
                .build();
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(BookDto bookDto) {
        Book existedBook = bookRepository.getOne(bookDto.getId());
        System.out.println();
        return bookRepository.save(
                mapper.updateEntity(bookDto, existedBook));
    }

    private BooleanExpression getFilterExpression(CatalogFilterDto filter) {
        CatalogExpressionBuilder exp = new CatalogExpressionBuilder();
        exp.add(filter.getName(), book.name::containsIgnoreCase);
        exp.add(filter.getGenre(), book.genre::eq);
        exp.add(filter.getPrice(), book.price::lt);

        return exp.getExpression();
    }
}
