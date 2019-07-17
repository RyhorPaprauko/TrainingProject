package by.itacademy.service.service;

import by.itacademy.database.entity.Comment;
import by.itacademy.database.repository.CommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CommentService {

    private CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public void deleteComment(Comment comment) {
        commentRepository.delete(comment);
    }
}
