package by.itacademy.service.service;

import by.itacademy.database.dto.FilterDto;
import by.itacademy.database.entity.Book;
import by.itacademy.database.repository.BookRepository;
import by.itacademy.service.filter.CatalogExpressionBuilder;
import by.itacademy.service.util.NonNullAndEmptyBeanUtilsBean;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

import static by.itacademy.database.entity.QBook.book;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class BookService {

    private BookRepository bookRepository;

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public Optional<Book> findById(Long id) {
        return bookRepository.findById(id);
    }

    public Iterable<Book> getAll() {
        return bookRepository.findAll();
    }

    public Iterable<Book> getAllFiltered(FilterDto filter) {
        BooleanExpression expression = getFilterExpression(filter);

        return bookRepository.findAll(expression,
                PageRequest.of(filter.getPage(), filter.getLimit()));
    }

    public void delete(Long id) {
        bookRepository.deleteById(id);
    }

    public Book updateBook(Book updatedBook)  {
        BeanUtilsBean copier = new NonNullAndEmptyBeanUtilsBean();

        Book existedBook = bookRepository.getOne(updatedBook.getId());
        try {
            copier.copyProperties(existedBook, updatedBook);
            return bookRepository.save(existedBook);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return bookRepository.save(existedBook);
    }

    private BooleanExpression getFilterExpression(FilterDto filter) {
        CatalogExpressionBuilder exp = new CatalogExpressionBuilder();
        exp.add(filter.getName(), book.name::containsIgnoreCase);
        exp.add(filter.getGenre(), book.genre::eq);
        exp.add(filter.getPrice(), book.price::lt);

        return exp.getExpression();
    }
}
