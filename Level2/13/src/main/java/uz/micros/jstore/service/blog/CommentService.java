package uz.micros.jstore.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.blog.Comment;
import uz.micros.jstore.repository.CommentRepository;

import java.util.Date;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment) {

        comment.setDate(new Date());
        comment.setAuthor("Anonym");

        commentRepository.saveAndFlush(comment);
    }
}
