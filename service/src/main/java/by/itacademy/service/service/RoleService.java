package by.itacademy.service.service;

import by.itacademy.database.entity.Role;
import by.itacademy.database.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class RoleService {

    private final RoleRepository roleRepository;

    public Role getByRole(String roleName) {
        return roleRepository.getByRole(roleName);
    }
}
