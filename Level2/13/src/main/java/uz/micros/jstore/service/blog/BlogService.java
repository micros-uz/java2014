package uz.micros.jstore.service.blog;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import uz.micros.jstore.entity.blog.Blog;
import uz.micros.jstore.entity.blog.Post;
import uz.micros.jstore.util.DbManager;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BlogService {

    public Blog getBlog(){
        Blog blog = new Blog();
        blog.setTitle("jStore Corporate Blog!!!");

        List<Post> posts = DbManager.runQuery("select * from posts");

        blog.setPosts(posts);

        return blog;
    }
}
