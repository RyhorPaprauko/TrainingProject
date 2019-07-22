package by.itacademy.database.repository;

import by.itacademy.database.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBook_Id(Long id);
}
