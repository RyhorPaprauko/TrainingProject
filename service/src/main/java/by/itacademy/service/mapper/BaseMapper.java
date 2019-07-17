package by.itacademy.service.mapper;

import by.itacademy.database.dto.MarkerDto;
import by.itacademy.database.entity.BaseEntity;

public interface BaseMapper<T extends BaseEntity, E extends MarkerDto> {

    E toDTO(T t);

    T fromDTO(E e);
}
