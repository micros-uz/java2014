package uz.micros.jstore.service.blog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.blog.Blog;
import uz.micros.jstore.entity.blog.Comment;
import uz.micros.jstore.entity.blog.Post;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private BlogService blogSvc;

    public Post get(int id) {

        Blog blog = blogSvc.getBlog();

        for(Post post : blog.getPosts()){
            if (post.getId() == id){
                Comment comment = new Comment();
                comment.setDate(new Date());
                comment.setAuthor("Davron");
                comment.setText("Urtoqlar! Let's work!");

                List<Comment> list = new ArrayList<Comment>();
                list.add(comment);
                list.add(comment);
                list.add(comment);
                list.add(comment);

                post.setComments(list);

                return post;
            }
        }

        return null;
    }
}
