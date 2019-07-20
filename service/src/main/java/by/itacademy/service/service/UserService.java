package by.itacademy.service.service;

import by.itacademy.database.entity.User;
import by.itacademy.database.repository.RoleRepository;
import by.itacademy.database.repository.UserRepository;
import by.itacademy.service.util.NonNullAndEmptyBeanUtilsBean;
import lombok.AllArgsConstructor;
import org.apache.commons.beanutils.BeanUtilsBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;


    public Optional<User> saveUser(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.getByRole("USER"));

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> saveAdmin(User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleRepository.getByRole("ADMIN"));

        return Optional.of(userRepository.save(user));
    }

    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User updateUser(User updatedUser) {
        BeanUtilsBean copier = new NonNullAndEmptyBeanUtilsBean();

        User existedUser = userRepository.getOne(updatedUser.getId());

        try {
            copier.copyProperties(existedUser, updatedUser);
            return userRepository.save(existedUser);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return existedUser;
    }
}