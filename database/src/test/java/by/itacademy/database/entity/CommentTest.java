package by.itacademy.database.entity;

import by.itacademy.database.BaseTest;
import by.itacademy.database.repository.BookRepository;
import by.itacademy.database.repository.CommentRepository;
import by.itacademy.database.repository.UserRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertNotNull;

public class CommentTest extends BaseTest {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void checkSaveComment() {
        Comment comment = Comment.builder()
                .user(userRepository.getOne(1L))
                .book(bookRepository.getOne(1L))
                .text("Otlichnaya kniga")
                .build();

        commentRepository.save(comment);
        assertNotNull(commentRepository.getOne(comment.getId()));
    }

    @Test
    public void checkGetComment() {
        Comment comment = commentRepository.getOne(1L);

        assertNotNull(comment);
        assertThat(comment.getUser().getLogin(), equalTo("admin"));
        assertThat(comment.getBook().getName(), equalTo("Anchar"));
        assertThat(comment.getText(), equalTo("best book i ever read"));
    }
}