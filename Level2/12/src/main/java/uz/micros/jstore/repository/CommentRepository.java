package uz.micros.jstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.blog.Comment;

@Service
public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
