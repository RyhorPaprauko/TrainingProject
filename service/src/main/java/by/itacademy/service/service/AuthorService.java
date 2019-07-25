package by.itacademy.service.service;

import by.itacademy.database.entity.Author;
import by.itacademy.database.repository.AuthorRepository;
import by.itacademy.service.util.NonNullAndEmptyBeanUtilsBean;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Author getById(Long id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> new ObjectNotFoundException(id, Author.class.getName()));
    }

    public List<Author> getAll() {
        return authorRepository.findAll();
    }
}
