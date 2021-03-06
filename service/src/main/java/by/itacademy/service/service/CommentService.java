package by.itacademy.service.service;

import by.itacademy.database.dto.CommentDto;
import by.itacademy.database.entity.Comment;
import by.itacademy.database.repository.CommentRepository;
import by.itacademy.service.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentMapper mapper;

    public List<CommentDto> getAllBookComments(Long id) {
        return commentRepository.findAllByBookId(id).stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    public Comment saveComment(CommentDto commentDto) {
        return commentRepository.save(
                mapper.toEntity(commentDto));
    }

    public void deleteComment(Long id) {
        commentRepository.deleteById(id);
    }
}
