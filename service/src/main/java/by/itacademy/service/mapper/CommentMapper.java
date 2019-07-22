package by.itacademy.service.mapper;

import by.itacademy.database.dto.CommentDto;
import by.itacademy.database.entity.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface CommentMapper {

    @Mapping(target = "authorLogin", source = "comment.user.login")
    CommentDto toDto(Comment comment);
}
