package by.itacademy.service.service;

import by.itacademy.database.dto.RegistrationDto;
import by.itacademy.database.entity.User;
import by.itacademy.database.repository.UserRepository;
import by.itacademy.service.mapper.RegistrationMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;
    private final RegistrationMapper mapper;


    public Optional<User> saveUser(RegistrationDto userDto) {
        User user = mapper.toUser(userDto);
        System.out.println();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.getByRole("USER"));

        return Optional.of(userRepository.save(user));
    }

    public Optional<User> saveAdmin(RegistrationDto userDto) {
        User user = mapper.toUser(userDto);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.getRoles().add(roleService.getByRole("ADMIN"));

        return Optional.of(userRepository.save(user));
    }

    public User findByLogin(String login) {
        return userRepository.findByLogin(login)
                .orElseThrow(() -> new UsernameNotFoundException(login));
    }
}
