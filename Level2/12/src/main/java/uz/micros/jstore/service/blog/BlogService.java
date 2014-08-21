package uz.micros.jstore.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.blog.Blog;
import uz.micros.jstore.entity.blog.Post;
import uz.micros.jstore.repository.PostRepository;

import java.util.List;

@Service
public class BlogService {

    @Autowired
    private PostRepository rpstr;

    public Blog getBlog(){
        Blog blog = new Blog();
        blog.setTitle("jStore Corporate Blog!!!");

        List<Post> posts = rpstr.findAll();

        blog.setPosts(posts);

        return blog;
    }
}
