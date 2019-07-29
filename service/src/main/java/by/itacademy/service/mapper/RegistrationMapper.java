package by.itacademy.service.mapper;

import by.itacademy.database.dto.RegistrationDto;
import by.itacademy.database.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface RegistrationMapper {

    @Mapping(target = "contacts.tel", source = "dto.tel")
    @Mapping(target = "contacts.mail", source = "dto.mail")
    User toUser(RegistrationDto dto);
}
