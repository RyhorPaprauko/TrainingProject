package by.itacademy.service.service;

import by.itacademy.database.entity.Author;
import by.itacademy.database.repository.AuthorRepository;
import by.itacademy.service.util.NonNullAndEmptyBeanUtilsBean;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthorService {

    private AuthorRepository authorRepository;

    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    public Iterable<Author> getAll() {
        return authorRepository.findAll();
    }

    public void delete(Long id) {
        authorRepository.deleteById(id);
    }

    public Author updateAuthor(Author updatedAuthor) {
        BeanUtilsBean copier = new NonNullAndEmptyBeanUtilsBean();

        Author existedAuthor = authorRepository.getOne(updatedAuthor.getId());
        try {
            copier.copyProperties(existedAuthor, updatedAuthor);
            return authorRepository.save(existedAuthor);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return authorRepository.save(existedAuthor);
    }

}
