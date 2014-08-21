package uz.micros.jstore.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import uz.micros.jstore.entity.blog.Post;
import uz.micros.jstore.repository.PostRepository;

import java.util.Date;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post get(int id) {
        Post post = postRepository.findOne(id);
        return post;
    }
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Post save(Post post) {
        if (post.getDate() == null)
            post.setDate(new Date());
        if (post.getAuthor() == null)
            post.setAuthor("Anonymous");

        return postRepository.saveAndFlush(post);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(int id) {
        postRepository.delete(id);
    }

}
